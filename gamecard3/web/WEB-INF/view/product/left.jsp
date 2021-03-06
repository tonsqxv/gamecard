 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@include file="../common/taglibs.jsp" %>

<%
	//获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:    
	String basePath4left = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();   
	
%>  

				<!-- 分类树 -->
				<c:if test="${!empty categoryTree }">
				<div class="dropdown clearfix">
		              <ul class="dropdown-menu nav nav-list btn-block"  style="display: block; position: static; margin-bottom: 5px; *width: 180px;">
		                <!-- 类别标题 -->
		                <h4 align="center">CATEGORIES</h4>
		                <!-- 分割线 -->
		                <li class="divider"></li>
		                <!-- 遍历类别 -->
		     			<c:forEach items="${categoryTree}" var="t">
		     			<c:choose>
								<c:when test="${empty t.children}">
									<li class="dropdown-submenu"><a tabindex="-1" href="<%=basePath4left %>/product/${t.id}/listProductByCategory">${t.name }</a></li>
								</c:when>
								<c:otherwise>
									<li class="dropdown-submenu">
									<a>${t.name }</a>
									<ul class="dropdown-menu">
									<c:forEach items="${t.children}" var="c">
										<li><a tabindex="-1" href="<%=basePath4left %>/product/${c.id}/listProductByCategory">${c.name}</a></li>
									</c:forEach>
									</ul>
									</li>
								</c:otherwise>
						   </c:choose>
		                </c:forEach>
		                <li class="divider"></li>
		              </ul>
		          </div>
		          </c:if>
		          
		          <!-- 热门产品 -->
		          <c:if test="${!empty hotProducts }">
		          <div class="panel panel-default">
					  <div class="panel-heading">
					    <h3 class="panel-title">Hot Products</h3>
					  </div>
					  <div class="panel-body">
						  	<c:set var="hotProducts_index" value="1"></c:set>
							<c:forEach items="${hotProducts }" var="card">
							<c:if test="${hotProducts_index != (hotSize+1) }">
							    <div class="row">
							    	  <div class="col-lg-7">
									    	<a href="<%=basePath4left %>/product/${card.id}/productDetail" class="thumbnail">
						                  		<img src="<%=basePath4left %>/assets/images/product/${card.mainImgPath }"/>
						                   </a>
							    	  </div>
							    	  <div class="col-lg-5" align="left">
							    	  		<p><strong ><font color="red">$${card.actualSellPrice }</font></strong></p>
				                			<p>Sold:${card.sales+card.baseSales}</p>
							    	  </div>
								</div>
						        <div class="row">
						              <p align="center" class="common"><a href="<%=basePath4left %>/product/${card.id}/productDetail" title="${card.productName }">${card.shortName }</a></p>
						        </div>
								<br>
						   <c:set var="hotProducts_index" value="${hotProducts_index+1 }"></c:set>
				       	   </c:if>    
						   </c:forEach>
						   <div class="row">
			              		<p class="text-right"><a href="<%=basePath4left %>/product/viewMoreProduct?type=1">View More &gt;&gt;</a></p>
			               </div>
					  </div>
				</div>
				</c:if>

 				<!-- 新产品 -->
		          <c:if test="${!empty newProducts }">
		          <div class="panel panel-default">
					  <div class="panel-heading">
					    <h3 class="panel-title">New Products</h3>
					  </div>
					  <div class="panel-body">
						  	<c:set var="newProducts_index" value="1"></c:set>
							<c:forEach items="${newProducts }" var="card">
							<c:if test="${newProducts_index != (newSize+1) }">
							    <div class="row">
							    	  <div class="col-lg-7">
									    	<a href="<%=basePath4left %>/product/${card.id}/productDetail" class="thumbnail">
						                  		<img src="<%=basePath4left %>/assets/images/product/${card.mainImgPath }"/>
						                   </a>
							    	  </div>
							    	  <div class="col-lg-5" align="left">
							    	  		<p><strong ><font color="red">$${card.actualSellPrice }</font></strong></p>
				                			<p>Sold:${card.sales+card.baseSales}</p>
							    	  </div>
								</div>
						        <div class="row">
						              <p align="center" class="common"><a href="<%=basePath4left %>/product/${card.id}/productDetail" title="${card.productName }">${card.shortName }</a></p>
						        </div>
								<br>
						   <c:set var="newProducts_index" value="${newProducts_index+1 }"></c:set>
				       	   </c:if>    
						   </c:forEach>
						   <div class="row">
			              		<p class="text-right"><a href="<%=basePath4left %>/product/viewMoreProduct?type=2">View More &gt;&gt;</a></p>
			               </div>
					  </div>
				</div>
				</c:if>

					<!-- 特价商品 -->
		          <c:if test="${!empty discountProducts }">
		          <div class="panel panel-default">
					  <div class="panel-heading">
					    <h3 class="panel-title">Discount Products</h3>
					  </div>
					  <div class="panel-body">
						  	<c:set var="discountProducts_index" value="1"></c:set>
							<c:forEach items="${discountProducts }" var="card">
							<c:if test="${discountProducts_index != (discountSize+1) }">
							    <div class="row">
							    	  <div class="col-lg-7">
									    	<a href="<%=basePath4left %>/product/${card.id}/productDetail" class="thumbnail">
						                  		<img src="<%=basePath4left %>/assets/images/product/${card.mainImgPath }"/>
						                   </a>
							    	  </div>
							    	  <div class="col-lg-5" align="left">
							    	  		<p><strong ><font color="red">$${card.actualSellPrice }</font></strong></p>
				                			<p>Sold:${card.sales+card.baseSales}</p>
							    	  </div>
								</div>
						        <div class="row">
						              <p align="center" class="common"><a href="<%=basePath4left %>/product/${card.id}/productDetail" title="${card.productName }">${card.shortName }</a></p>
						        </div>
								<br>
						   <c:set var="discountProducts_index" value="${discountProducts_index+1 }"></c:set>
				       	   </c:if>    
						   </c:forEach>
						   <div class="row">
			              		<p class="text-right"><a href="<%=basePath4left %>/product/viewMoreProduct?type=3">View More &gt;&gt;</a></p>
			               </div>
					  </div>
				</div>
				</c:if>
		          
		          
		          