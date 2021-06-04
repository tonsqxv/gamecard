 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@include file="../common/taglibs.jsp" %>

<%
	//获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:    
	String basePath4MyAccountleft = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();   
	
%>  
		<div class="list-group">
				  <a class="list-group-item" href="<%=basePath4MyAccountleft%>/member/toMyAccount">MyAccount</a>
				  <a class="list-group-item" href="<%=basePath4MyAccountleft%>/order/toMyOrder">MyOrders</a>
				  <c:if test="${!empty member }">
						<a class="list-group-item" href="<%=basePath4MyAccountleft%>/member/changePassword">Change Password</a>
				  </c:if>
		</div>	
		          
		          