 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@include file="../common/taglibs.jsp" %>

<%
	//获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:    
	String basePath4footcustomerlike = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();   
%>  
 <h4>Customers Who Viewed This Product Also Viewed</h4>
          
          <div class="row-fluid">
	           <c:forEach items="${customersLikes }" var="card">
	          			<div class="span3">
				              <a href="<%=basePath4footcustomerlike %>/product/${card.id}/productDetail" class="thumbnail">
				                <img src="<%=basePath4footcustomerlike %>/assets/images/product/${card.mainImgPath}"/>
				              </a>
				              <p class="text-center">
				              <a href="<%=basePath4footcustomerlike %>/product/${card.id}/productDetail">${card.productName}</a>
				              </p>
				              <c:choose>
									<c:when test="${card.preSellPrice > card.actualSellPrice}">
										<font color="red">&nbsp;&nbsp;$${card.actualSellPrice }</font>&nbsp;&nbsp;&nbsp;&nbsp;<del>$${card.preSellPrice }</del>
									</c:when>
									<c:otherwise>
										<font color="red">&nbsp;&nbsp;$${card.actualSellPrice }</font>
									</c:otherwise>
				 		    </c:choose>
				              <div>
				              		<p class="text-left span6">&nbsp;&nbsp;<img src="<%=basePath4footcustomerlike %>/assets/images/product/common/IcoRating5.gif"/></p> 
									<a href="javascript:addToCart(${card.id},1,${card.actualSellPrice });"><img src="<%=basePath4footcustomerlike %>/assets/icos/product/addToCart.jpg"/></a>
				              </div>
	             		</div>
	          	</c:forEach>
	          	
          </div><!--/row-fluid-->