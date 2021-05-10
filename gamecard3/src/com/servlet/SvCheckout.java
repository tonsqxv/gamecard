package com.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;

import com.paypal.sdk.profiles.APIProfile;
import com.paypal.sdk.profiles.ProfileFactory;
import com.paypal.sdk.services.CallerServices;
import com.paypal.sdk.util.Util;
import com.paypal.soap.api.AckCodeType;
import com.paypal.soap.api.AddressType;
import com.paypal.soap.api.BasicAmountType;
import com.paypal.soap.api.CountryCodeType;
import com.paypal.soap.api.CurrencyCodeType;
import com.paypal.soap.api.PaymentActionCodeType;
import com.paypal.soap.api.PaymentDetailsItemType;
import com.paypal.soap.api.PaymentDetailsType;
import com.paypal.soap.api.SetExpressCheckoutRequestDetailsType;
import com.paypal.soap.api.SetExpressCheckoutRequestType;
import com.paypal.soap.api.SetExpressCheckoutResponseType;
import com.paypal.soap.api.ShippingOptionType;

public class SvCheckout extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SvCheckout() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		 HttpSession session =request.getSession();
		try {
			CallerServices caller = (CallerServices) session.getValue("caller");
			SetExpressCheckoutRequestType pprequest = new SetExpressCheckoutRequestType();
			SetExpressCheckoutRequestDetailsType details = new SetExpressCheckoutRequestDetailsType();

			StringBuffer url = new StringBuffer();
			url.append("http://");
			url.append(request.getServerName());
			url.append(":");
			url.append(request.getServerPort());
			url.append(request.getContextPath());

			String returnURL = url.toString()
					+ "/GetExpressCheckoutDetails.jsp";
			String cancelURL = url.toString() + "/Calls.html";

			details.setReturnURL(returnURL + "?paymentAmount="
					+ request.getParameter("paymentAmount")
					+ "&currencyCodeType="
					+ request.getParameter("currencyCodeType"));
			details.setCancelURL(cancelURL);
			details.setBuyerEmail(request.getParameter("buyersemail"));	

			details.setPaymentAction(PaymentActionCodeType.fromString(request
					.getParameter("paymentType")));
			session.setAttribute("paymentType", request
					.getParameter("paymentType"));
			
			details.setCallbackURL("https://www.ppcallback.com/callback.pl");
			details.setCallbackTimeout("5");

			// populate line item details
			String[] amountItems = request.getParameterValues("L_AMT");
			String[] qtyItems = request.getParameterValues("L_QTY");
			String[] names = request.getParameterValues("L_NAME");	
			float ft=0.0f;
			PaymentDetailsItemType[] lineItems = new PaymentDetailsItemType[names.length];
			for (int i = 0; i < names.length; i++) {
				
				//if(names[i]!= null && !names[i].equalsIgnoreCase("")){
				//	String AMT=(amountItems[i].trim()=="")? "0" : amountItems[i];
				//	PaymentDetailsItemType item = new PaymentDetailsItemType();
				//	BasicAmountType amt = new BasicAmountType();
				//	amt.setCurrencyID(CurrencyCodeType.fromString(request
				//					.getParameter("currencyCodeType")));
				//	amt.set_value(amountItems[i]);
				//	String QTY=(qtyItems[i].trim()=="")? "0" : qtyItems[i];
				//	item.setQuantity(new BigInteger(QTY));
				//	item.setName(names[i]);
				//	item.setNumber(Integer.toString(i));
				//	item.setAmount(amt);
				//	ft += Float.valueOf(QTY).floatValue()*Float.valueOf(AMT).floatValue();
				//	lineItems[i] = item;
				// }
				
				PaymentDetailsItemType item = new PaymentDetailsItemType();
				BasicAmountType amt = new BasicAmountType();
				amt.setCurrencyID(CurrencyCodeType.fromString(request
						.getParameter("currencyCodeType")));
				amt.set_value(amountItems[i]);
				item.setQuantity(new BigInteger(qtyItems[i]));
				item.setName(names[i]);
				item.setNumber(Integer.toString(i));
				item.setAmount(amt);
				ft += Float.valueOf(qtyItems[i].trim()).floatValue()*Float.valueOf(amountItems[i].trim()).floatValue();
				lineItems[i] = item;
			}

			BasicAmountType shippingTotal = new BasicAmountType();
			shippingTotal.set_value("3.00");
			shippingTotal.setCurrencyID(CurrencyCodeType.fromString(request
					.getParameter("currencyCodeType")));
			BasicAmountType itemsTotal = new BasicAmountType();
			itemsTotal.set_value(Float.toString(ft));
			itemsTotal.setCurrencyID(CurrencyCodeType.fromString(request
					.getParameter("currencyCodeType")));
			
			PaymentDetailsType[] payDetails = new PaymentDetailsType[1];
			PaymentDetailsType paydtl=new PaymentDetailsType();
			paydtl.setPaymentDetailsItem(lineItems);
			paydtl.setShippingTotal(shippingTotal);
			paydtl.setItemTotal(itemsTotal);
			payDetails[0]=paydtl;
			details.setPaymentDetails(payDetails);
			
			//populate shipping address details
			details.setAddressOverride("1");
			String[] shippingMethods = { "Ground", "UPS Air" };
			ShippingOptionType[] shippings = new ShippingOptionType[2];
			for (int i = 0; i < 2; i++) {
				ShippingOptionType ship = new ShippingOptionType();
				ship.setShippingOptionName(shippingMethods[i]);
				BasicAmountType amt = new BasicAmountType();
				amt.setCurrencyID(CurrencyCodeType.fromString(request
						.getParameter("currencyCodeType")));
				if (i == 0) {
					ship.setShippingOptionIsDefault("true");
					amt.set_value("3.00");
				} else {
					ship.setShippingOptionIsDefault("false");
					amt.set_value("17.00");
				}
				ship.setShippingOptionAmount(amt);
				shippings[i] = ship;
			}
			details.setFlatRateShippingOptions(shippings);


			//populate shipping address (in a real application buyer shipping address is chosen or collected on the merchant site)	
			AddressType address = new AddressType();
			address.setName(request.getParameter("NAME"));
			address.setStreet1(request.getParameter("SHIPTOSTREET"));
			address.setCityName(request.getParameter("SHIPTOCITY"));
			address.setStateOrProvince(request.getParameter("SHIPTOSTATE"));
			address.setCountryName(request.getParameter("SHIPTOCOUNTRYNAME"));
			address.setCountry(CountryCodeType.US);
			address.setPostalCode(request.getParameter("SHIPTOZIP"));	
			details.setAddress(address);
			
			//populate order total
			BasicAmountType orderTotal = new BasicAmountType();
			//orderTotal.set_value(request.getParameter("paymentAmount"));
			orderTotal.setCurrencyID(CurrencyCodeType.fromString(request
					.getParameter("currencyCodeType")));
			float amt = Util.round(ft + 3.00f,2);
			orderTotal.set_value(Float.toString(amt));
			details.setOrderTotal(orderTotal);
			float maxamt = Util.round(amt+35.00f,2);
			BasicAmountType maxAmount = new BasicAmountType();
			maxAmount.set_value(Float.toString(maxamt));
			maxAmount.setCurrencyID(CurrencyCodeType.fromString(request
					.getParameter("currencyCodeType")));
			details.setMaxAmount(maxAmount);
			
				
			pprequest.setSetExpressCheckoutRequestDetails(details);
			String testEnv = (String) session.getAttribute("environment");
			
				SetExpressCheckoutResponseType ppresponse = (SetExpressCheckoutResponseType) caller
						.call("SetExpressCheckout", pprequest);
				if (!ppresponse.getAck().equals(AckCodeType.Success)
						&& !ppresponse.getAck().equals(
								AckCodeType.SuccessWithWarning)) {
					session.setAttribute("response", ppresponse);
					response.sendRedirect("APIError.jsp");
					return;
				} else {
					String redirectURL = "https://www."
							+ testEnv
							+ ".paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="
							+ ppresponse.getToken();
					response.sendRedirect(redirectURL);
				}
			} catch (Exception e) {
			//	out.println(e);
				session.setAttribute("exception", e);
				response.sendRedirect("Error.jsp");
				return;
			}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
