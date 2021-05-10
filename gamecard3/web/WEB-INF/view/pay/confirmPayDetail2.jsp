
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
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Home Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="<%=basePath %>/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <!-- 自适应 -->
    <link href="<%=basePath %>/assets/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
    
    <script src="<%=basePath %>/assets/js/common/jquery-1.8.2.min.js"></script>
    <script src="<%=basePath %>/assets/bootstrap/js/bootstrap.min.js"></script>

     <style type="text/css">      
      body {
        padding-top: 60px;/*body距离顶部底部距离*/
        padding-bottom: 40px;
      }      
    </style>
 </head>
 <body>

    <div class="container-fluid">
      <div class="row-fluid">

    		<div class="offset2 span8">

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
                <td><%= details.getPayerInfo().getAddress().getStreet1() %></td>
              </tr>
              <tr>
                <td>
                  Street 2:
                </td>
                <td><%= details.getPayerInfo().getAddress().getStreet2() %></td>
              </tr>
			   <tr>
                <td>
                  City:
                </td>
                <td><%= details.getPayerInfo().getAddress().getCityName() %></td>
              </tr>
			   <tr>
                <td>
                  State:
                </td>
                <td><%= details.getPayerInfo().getAddress().getStateOrProvince() %></td>
              </tr> 
			  <tr>
                <td>
                  ZIP Code:
                </td>
                <td><%= details.getPayerInfo().getAddress().getPostalCode() %></td>
              </tr> 
			  <tr>
                <td>
                  Country:
                </td>
                <td><%= details.getPayerInfo().getAddress().getCountryName() %></td>
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