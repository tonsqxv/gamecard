package com.macower.businessdata.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.macower.businessdata.entity.Member;
import com.macower.businessdata.entity.Order;

public class MailTpl {

	/**
	 * 修改密码邮件模板
	 * 
	 * @param old
	 * @return
	 */
	public String getChangePasswordTpl(Member old) {
		StringBuffer msg = new StringBuffer();
		msg.append("<html>Your new customer account details:");
		msg.append("<br/><br/><br/>");
		msg.append("your UserID:")
				.append("<a href=\"http://www.n3ds-card.com/member/login\">")
				.append(old.getEmail()).append("</a>");
		msg.append("<br/>");
		msg.append("your New Password:").append(old.getPassword());
		msg.append("<br/>");
		msg.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		msg.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		msg.append("<a href=\"http://www.n3ds-card.com/member/login\">");
		msg.append("login in now").append("</a>");
		msg.append("</html>");
		return msg.toString();
	}

	/**
	 * 用户注册邮件模板
	 * 
	 * @param obj
	 * @return
	 */
	public String getMemberRegisterTpl(Member obj) {
		StringBuffer msg = new StringBuffer();
		msg.append("<html>");
		msg.append("<div style=\"padding:0px 20px 20px 20px\">");
		msg.append("<h2 style=\"font-size:22px; height:30px; color:#cc6600;border-bottom:dashed 1px gray\">");
		msg.append("Thanks for Registering at n3ds-card.com</h2>");
		String firstName = (obj.getFirstName()==null)?"":" "+obj.getFirstName() ;
		String lastName = (obj.getLastName()==null)?"":obj.getLastName() ;
		msg.append("<p>Hi " +lastName+ firstName + ",</p>");
		msg.append("<p>Thank you for creating your account at n3ds-card.com. Your account details are as follows:</p>");
		msg.append("<p>");
		msg.append("<strong>Email Address:</strong> " + obj.getEmail()
				+ "<br/>");
		msg.append("<strong>Password:</strong> " + obj.getPassword());
		msg.append("</p>");
		msg.append("<p>To sign in to your account, please visit <a href='http://www.n3ds-card.com/member/login'>http://www.n3ds-card.com/member/login</a>");
		msg.append("or <a href='http://www.n3ds-card.com/member/login'>click here</a>.</p>");
		msg.append("<p>If you have any questions regarding your account, click 'Reply' in your email client and we'll be only too happy to help.</p>");
		msg.append("<p style=\"margin-top: 12px\">");
		msg.append("<strong>n3ds-card.com</strong><br/>");
		msg.append("<a href=\"http://www.n3ds-card.com\">http://n3ds-card.com</a></p>");
		msg.append("</div>");
		msg.append("</html>");
		return msg.toString();
	}

	/**
	 * 找回密码邮件模板
	 * 
	 * @param m
	 * @return
	 */
	public String getFindPasswordTpl(Member m) {
		StringBuffer msg = new StringBuffer();
		msg.append("<html>Your new customer account details:");
		msg.append("<br/><br/><br/>");
		msg.append("your UserID:")
				.append("<a href=\"http://www.n3ds-card.com/member/login\">")
				.append(m.getEmail()).append("</a>");
		msg.append("<br/>");
		msg.append("your Password:").append(m.getPassword());
		msg.append("<br/>");
		msg.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		msg.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		msg.append("<a href=\"http://www.n3ds-card.com/member/login\">");
		msg.append("login in now").append("</a>");
		msg.append("</html>");
		return msg.toString();
	}

	/**
	 * 发货邮件通知模板
	 * 
	 * @param m
	 * @return
	 */
	public String getDispatchMailTpl(Order order) {
		StringBuffer msg = new StringBuffer();
		msg.append("<html>");
		msg.append(formateDate2(new Date())+",N3DS-card &lt;servicen3dscard@gmail.com&gt; wrote:") ;
		msg.append("<div style='PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-BOTTOM: 20px; PADDING-TOP: 0px'>");
		msg.append("<h2 style='FONT-SIZE: 22px; MIN-HEIGHT: 30px; COLOR: #cc6600; BORDER-BOTTOM: gray 1px dashed'>");
		msg.append("Order Status Changed</h2>");
		String firstName = (order.getFirstName()==null)?"":" "+order.getFirstName() ;
		String lastName = (order.getLastName()==null)?"":order.getLastName() ;
		msg.append("<p>Hi " +lastName + firstName + ",</p>");
		msg.append("<p>An order you recently placed on our website has had its status changed.</p>");
		msg.append("<p>The status of order <strong>" + order.getOrderNo()
				+ "</strong> is now <strong>Shipped</strong></p>");
		msg.append("<h3 style='FONT-SIZE: 13px; COLOR: #cc6600'>Order Details</h3>");
		msg.append("<table width='100%''>");
		msg.append("<tbody>");
		msg.append("<tr>");
		msg.append("<td style='FONT-WEIGHT: bold; FONT-SIZE: 13px; FONT-FAMILY: Arial' valign='top' width='25%'>Order Total:</td>");
		msg.append("<td style='FONT-SIZE: 13px; FONT-FAMILY: Arial'>"
				+ order.getDiscountTotalPrice() + " USD</td>");
		msg.append("</tr>");
		msg.append("<tr>");
		msg.append("<td style='FONT-WEIGHT: bold; FONT-SIZE: 13px; FONT-FAMILY: Arial' valign='top' width='25%'>Date Placed:</td>");
		msg.append("<td style='FONT-SIZE: 13px; FONT-FAMILY: Arial'>"+formateDate(order.getDispatchTm())+"</td>");
		msg.append("</tr>");
		msg.append("</tbody></table>") ;
		msg.append("<h3 style='FONT-SIZE: 13px; COLOR: #cc6600'>Address</h3>");
		String street2 = order.getStreet2() ;
		if(street2 == null){
			street2 = "" ;
		}else{
			street2 = street2.trim()+" " ;
		}
		msg.append(lastName + firstName+" "+order.getStreet1()+" "+street2+order.getCity()+", "+order.getState()+" "+order.getZipCode()+" "+order.getCountryName());
		
		msg.append("<h3 style='FONT-SIZE: 13px; COLOR: #cc6600'>Shipment Tracking Numbers / Links</h3>");
		if (order.getShippingOptionAmount() == 0) {
			msg.append("No tracking numbers are assigned to your order yet.");
		} else {
			msg.append(" Your tracking number is :" + order.getShippingNo());
		}

		msg.append("<p><strong>Where to Track</strong> ? Refer to our <a href='http://www.n3ds-card.com/help/shippingAndReturn' target='_blank'>Shipping &amp; Returns</a> page.</p>");
		msg.append("<p></p>");
		msg.append("<p>-------------------------------------------------------------<br/>");
		msg.append("<span style='BACKGROUND-COLOR: #ffcc99'>Order Status Notes</span>:");
		msg.append("<br/>\"<strong>Paied</strong>\" - Payment confirmed, orders are switched over to shipping Department and will be shipped in 12 hours.");
		msg.append("<br/>\"<strong>Shipped</strong>\" - Orders have left our shipping Department and are picked up by Postal Man, online track available 3 days later for Hongkong Register Airmail.");
		msg.append("<br/>\"<strong>Cancelled</strong>\" - Payment does not go through, or cancel as required. </p>");
		msg.append("<p><a href='http://www.n3ds-card.com'>www.n3ds-card.com</a></p>");
		msg.append("<p>&nbsp;</p></div>");
		msg.append("</html>");
		return msg.toString();
	}
	
	
	

	/**
	 * 付款邮件通知模板
	 * 
	 * @param m getPaiedMailTpl
	 * @return
	 */
	public String getPaiedMailTpl(Order order) {
		StringBuffer msg = new StringBuffer();
		msg.append("<html>");
		msg.append(formateDate2(new Date())+",N3DS-card &lt;servicen3dscard@gmail.com&gt; wrote:") ;
		msg.append("<div style='PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-BOTTOM: 20px; PADDING-TOP: 0px'>");
		msg.append("<h2 style='FONT-SIZE: 22px; MIN-HEIGHT: 30px; COLOR: #cc6600; BORDER-BOTTOM: gray 1px dashed'>");
		msg.append("Order Status Changed</h2>");
		String firstName = (order.getFirstName()==null)?"":" "+order.getFirstName() ;
		String lastName = (order.getLastName()==null)?"":order.getLastName() ;
		msg.append("<p>Hi " +lastName + firstName + ",</p>");
		msg.append("<p>An order you recently placed on our website has had its status changed.</p>");
		msg.append("<p>The status of order <strong>" + order.getOrderNo()
				+ "</strong> is now <strong>Paied</strong></p>");
		msg.append("<h3 style='FONT-SIZE: 13px; COLOR: #cc6600'>Order Details</h3>");
		msg.append("<table width='100%''>");
		msg.append("<tbody>");
		msg.append("<tr>");
		msg.append("<td style='FONT-WEIGHT: bold; FONT-SIZE: 13px; FONT-FAMILY: Arial' valign='top' width='25%'>Order Total:</td>");
		msg.append("<td style='FONT-SIZE: 13px; FONT-FAMILY: Arial'>"
				+ order.getDiscountTotalPrice() + " USD</td>");
		msg.append("</tr>");
		msg.append("<tr>");
		msg.append("<td style='FONT-WEIGHT: bold; FONT-SIZE: 13px; FONT-FAMILY: Arial' valign='top' width='25%'>Date Placed:</td>");
		msg.append("<td style='FONT-SIZE: 13px; FONT-FAMILY: Arial'>"+formateDate(order.getPayTm())+"</td>");
		msg.append("</tr>");
		msg.append("</tbody></table>") ;
		msg.append("<h3 style='FONT-SIZE: 13px; COLOR: #cc6600'>Address</h3>");
		String street2 = order.getStreet2() ;
		if(street2 == null){
			street2 = "" ;
		}else{
			street2 = street2.trim()+" " ;
		}
		msg.append(lastName + firstName+" "+order.getStreet1()+" "+street2+order.getCity()+", "+order.getState()+" "+order.getZipCode()+" "+order.getCountryName());
		
		msg.append("<h3 style='FONT-SIZE: 13px; COLOR: #cc6600'>comment</h3>");
		
		msg.append("<p>your order will be shipped out within 1 workday.Please check your address,If you have any questions, please <a href='http://www.n3ds-card.com/help/contact'>contact us</a> or email to <b>servicen3dscard@gmail.com</b></p>");
		msg.append("<p>-------------------------------------------------------------<br/>");
		msg.append("<span style='BACKGROUND-COLOR: #ffcc99'>Order Status Notes</span>:");
		
		msg.append("<br/>\"<strong>Paied</strong>\" - Payment confirmed, orders are switched over to shipping Department and will be shipped in 12 hours.");
		msg.append("<br/>\"<strong>Shipped</strong>\" - Orders have left our shipping Department and are picked up by Postal Man, online track available 3 days later for Hongkong Register Airmail.");
		msg.append("<br/>\"<strong>Cancelled</strong>\" - Payment does not go through, or cancel as required. </p>");
		msg.append("<p><a href='http://www.n3ds-card.com'>www.n3ds-card.com</a></p>");
		msg.append("<p>&nbsp;</p></div>");
		msg.append("</html>");
		return msg.toString();
	}


	private String formateDate(Date date) {

		String tt = new SimpleDateFormat("MMM yyyy ", Locale.ENGLISH)
				.format(date);
		String d = new SimpleDateFormat("d", Locale.ENGLISH).format(date);
		return d + "th " + tt;
	}
	
	private String formateDate2(Date date) {

		return  new SimpleDateFormat("'On' EEE,MMM dd,yyyy, 'at' h:mm a",Locale.ENGLISH).format(new Date());
		
	}
	
	


}
