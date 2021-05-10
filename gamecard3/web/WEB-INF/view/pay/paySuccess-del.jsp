
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.paypal.sdk.services.CallerServices" %>
<%@ page import="com.paypal.soap.api.*" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();    
	//获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:    
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path; 

	DoExpressCheckoutPaymentResponseDetailsType responseDetails = (DoExpressCheckoutPaymentResponseDetailsType)request.getAttribute("responseDetails") ;

%>

<html>
<head>
<title>PayPal JSP SDK - DoExpressCheckoutPayment API</title>
<link href="<%=basePath %>/assets/css/product/pay/sdk.css" rel="stylesheet" type="text/css"/>
</head> 
<body alink=#0000FF vlink=#0000FF>
<br>
<center>
<font size=2 color=black face=Verdana><b>DoExpressCheckoutPayment</b></font>
<br><br>

<b>Thank you for your payment!</b><br><br>
<table width=300>
	<tr>
		<td align=right>Transaction ID:</td>
		<td align=left><%= (responseDetails.getPaymentInfo())[0].getTransactionID() %></td>
	</tr>
	<tr>
		<td align=right>Amount:</td>
		<td align=left><%= (responseDetails.getPaymentInfo())[0].getGrossAmount().getCurrencyID() + " " + (responseDetails.getPaymentInfo())[0].getGrossAmount().get_value() %></td>
	</tr>
</table>

</center>
<a id="CallsLink" href="<%=basePath%>/index">Home</a>
</body>
</html>
