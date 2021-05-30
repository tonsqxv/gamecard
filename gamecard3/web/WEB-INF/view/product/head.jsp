 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@include file="../common/taglibs.jsp" %>

<%
	//获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:    
	String basePath4head = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();   
%>  

	<div class="navbar">
		   <div class="navbar-inner">
		        <a class="brand" href="#">NAVIGATOR</a>
		        <ul class="nav nav-tabs">
		          <li class="active"><a href="<%=basePath4head %>/index">Home</a></li>
		          <li class="divider-vertical"></li>  
		           <li><a href="<%=basePath4head%>/help/about">About Us</a></li>
		           <li><a href="<%=basePath4head%>/help/contact">Contact Us</a></li>
		           <li><a href="<%=basePath4head%>/help/shippingAndReturn">Shipping&amp;Returns</a></li>
		           <li class="divider-vertical"></li>  
		           <li><a href="<%=basePath4head%>/help/">Help&amp;Faqs</a></li>
		           <li><a href="<%=basePath4head%>/news/">News</a></li>
		        </ul>
		        <form class="navbar-form pull-right" action="<%=basePath4head%>/product/searchBy">
                   <input type="text" name="searchByParam_productName" class="span3" placeholder="key word" value="${searchByParam_productName }">
                   <button type="submit" class="btn">Search</button>
                 </form>
		   </div>
    </div>