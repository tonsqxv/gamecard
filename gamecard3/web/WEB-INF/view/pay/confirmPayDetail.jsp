
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.paypal.sdk.services.CallerServices" %>
<%@ page import="com.paypal.soap.api.*" %>
<%@ page import="com.macower.businessdata.entity.Order" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();    
	//获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:    
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path; 
	GetExpressCheckoutDetailsResponseDetailsType details = (GetExpressCheckoutDetailsResponseDetailsType)request.getAttribute("details") ;
	Order order = (Order)request.getAttribute("order") ;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
	
 </head>
 <body>

    <div class="container">
      <div class="row">
    	<div class="col-lg-8 col-lg-offset-2">
          <table class="table table-bordered table-striped">
             <thead>
              <tr>
                <th>Address</th>
                <th>Description</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>
                  Street 1:
                </td>
                <td><%= order.getStreet1() %></td>
              </tr>
              <tr>
                <td>
                  Street 2:
                </td>
                <td><%= order.getStreet2() %></td>
              </tr>
			   <tr>
                <td>
                  City:
                </td>
                <td><%= order.getCity() %></td>
              </tr>
			   <tr>
                <td>
                  State:
                </td>
                <td><%= order.getState() %></td>
              </tr> 
			  <tr>
                <td>
                  ZIP Code:
                </td>
                <td><%= order.getZipCode() %></td>
              </tr> 
			  <tr>
                <td>
                  Country:
                </td>
                <td><%= order.getCountryName() %></td>
              </tr>
			  <tr>
                <td>
                  ShippingCalculationMode:
                </td>
                <td><%= details.getUserSelectedOptions().getShippingCalculationMode() %></td>
              </tr>
			   <tr>
                <td>
                  ShippingOptionAmount:
                </td>
                <td><%= details.getUserSelectedOptions().getShippingOptionAmount() %></td>
              </tr> 
			  <tr>
                <td>
                 ShippingOptionName:
                </td>
                <td><%= details.getUserSelectedOptions().getShippingOptionName() %></td>
              </tr>
			   <tr>
                <td>
                 <strong>Order Total:</strong>
                </td>
                <td><font color="red"><%= request.getParameter("currencyCodeType")%>&nbsp;<%= (details.getPaymentDetails())[0].getOrderTotal() %></font></td>
              </tr>
            </tbody>
          </table>
           <div class="control-group" align="center">
	           <form id="confirmFrom" method=POST action="<%=basePath %>/pay/confirmPayment">
					<input type="hidden" name="token" value="<%= details.getToken() %>" />
					<input type="hidden" name="PayerID" value="<%= details.getPayerInfo().getPayerID()%>" />
					<input type="hidden" name="currencyCodeType" value="<%= request.getParameter("currencyCodeType")%>" />
					<input type="hidden" name="paymentAmount" value="<%= (details.getPaymentDetails())[0].getOrderTotal() %>" />
					<img alt="" id="submitButtonId" src="<%=basePath%>/assets/icos/product/btnXpressCheckout.gif" onclick="javascript:submitPay();">
				</form>
		              	
		   </div>


          <hr>
    		</div><!--/span8-->

      </div><!-- row-fluid -->


    </div><!-- container-fluid -->
   <script type="text/javascript">  
   function submitPay(){
	   $("#submitButtonId").attr("disabled","disabled") ;
		$("#confirmFrom").submit();
   }
   </script>
  
  </body>
</html>