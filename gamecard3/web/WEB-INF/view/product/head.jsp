 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@include file="../common/taglibs.jsp" %>

<%
	//获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:    
	String basePath4head = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();   
%>  



<nav class="navbar navbar-default" role="navigation">
  <!-- Brand and toggle get grouped for better mobile display -->
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="#">NAVIGATOR</a>
  </div>

  <!-- Collect the nav links, forms, and other content for toggling -->
  <div class="collapse navbar-collapse navbar-ex1-collapse">
    <ul class="nav navbar-nav">
     	<li class="active"><a href="<%=basePath4head %>/index">Home</a></li>
		<li class="divider-vertical"></li>  
		<li><a href="<%=basePath4head%>/help/about">About Us</a></li>
		<li><a href="<%=basePath4head%>/help/contact">Contact Us</a></li>
		<li><a href="<%=basePath4head%>/help/shippingAndReturn">Shipping&amp;Returns</a></li>
	    <li class="divider-vertical"></li>  
		<li><a href="<%=basePath4head%>/help/">Help&amp;Faqs</a></li>
		<li><a href="<%=basePath4head%>/news/">News</a></li>
      
    </ul>
    <form class="navbar-form navbar-right" role="search" action="<%=basePath4head%>/product/searchBy">
      <div class="form-group">
        <input type="text" name="searchByParam_productName" value="${searchByParam_productName }" class="form-control" placeholder="Search">
      </div>
      <button type="submit" class="btn btn-default">Submit</button>
    </form>
    
    
  </div><!-- /.navbar-collapse -->
</nav>
