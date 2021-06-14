<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@include file="../common/taglibs.jsp" %>
<%
	String path = request.getContextPath();    
	//获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:    
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path; 
%>
<!DOCTYPE html>
<html lang="en">
 <head>
 
 <link href="<%=basePath %>/assets/css/page/page.css" rel="stylesheet">
 <link href="<%=basePath %>/assets/css/page/image1.gif">
 <link href="<%=basePath %>/assets/css/page/image2.gif">
 <link href="<%=basePath %>/assets/css/page/meneame.jpg">
 </head>
 <body>
  <%@include file="head.jsp" %>

    <div class="container">
      <div class="row">

   		<div class="col-lg-3">
   					<%@include file="left.jsp" %>
   		</div><!--/span2-->
   		
    	<div class="col-lg-9">
          <h4><font color="orange">${category.name}</font></h4>
          <hr>
          <!-- 产品展示 -->
          <c:set var="index" value="1"></c:set>
          <c:forEach items="${page.data }" var="card">
          
	          	 <c:if test="${index%4==1 }">
		    	   <div class="row">
		    	 </c:if>
		    	  
	             <div class="col-lg-3">
		              <a href="<%=basePath %>/product/${card.id}/productDetail" class="thumbnail">
		                <img src="<%=basePath %>/assets/images/product/${card.mainImgPath}"/>
		              </a>
		              <p class="text-center common">
		              <a href="<%=basePath %>/product/${card.id}/productDetail">${card.productName}</a>
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
		              		<p class="text-left span6">&nbsp;&nbsp;<img src="<%=basePath %>/assets/images/product/common/IcoRating${card.star }.gif"/></p> 
		                    <p class="text-right span6">
							<a href="javascript:addToCart(${card.id},1,${card.actualSellPrice });"><img src="<%=basePath %>/assets/icos/product/addToCart.jpg"/></a>
		                   </p>
		              </div>
	             </div>
	            <c:choose>
		          		<c:when test="${index == page.pageSize}">
		          			</div><!--/row-fluid-->
		           			 <hr>
		          		</c:when>
		          		<c:when test="${index == fn:length(page.data)}">
		          			</div><!--/row-fluid-->
		           			 <hr>
		          		</c:when>
		          		<c:otherwise>
		          			<c:if test="${index%4==0 }">
					    	   </div><!--/row-fluid-->
					    	    <hr>
					    	 </c:if>
		          		</c:otherwise>
	          	 </c:choose> 
	          <c:set var="index" value="${index+1 }"></c:set>
          </c:forEach>
          
          <!-- 分页工具条 begin -->
          <div class="row" >
   			<!-- 总页数大于1时才显示分页工具 -->
	       <c:if test="${page.totalPage >1}">
	       <div class="badoo">
	       		<c:choose>
	       			<c:when test="${page.totalPage <=10}">
	       				<c:forEach var="i" begin="1" end="${page.totalPage}">
		       				<c:choose>
		       					<c:when test="${i == (page.currentPage+1) }">
		       						<span class="current">${i}</span>
		       					</c:when>
		       					<c:otherwise>
		       						<a href="<%=basePath %>/product/${categoryId}/listProductByCategory?pageIndex=${i}">${i}</a>
		       					</c:otherwise>
		       				</c:choose>
	       				</c:forEach>
	       			</c:when>
	       			<c:when test="${page.totalPage >10}">
	       				<c:choose>
	       					<c:when test="${(page.currentPage+1)<=5 }">
	       						<c:choose>
	       							<c:when test="${(page.currentPage+1)==1}">
	       								<span class="disabled">&lt;Prev</span>
	       							</c:when>
	       							<c:otherwise>
	       								<a href="<%=basePath %>/product/${categoryId}/listProductByCategory?pageIndex=${page.currentPage}">&lt;Prev</a>
	       							</c:otherwise>
	       						</c:choose>
		       					<span class="disabled">&lt;Prev</span>
		       					<c:forEach var="i" begin="1" end="5">
		       						<c:choose>
		       							<c:when test="${(page.currentPage+1)==i }">
		       								<span class="current">${i}</span>
		       							</c:when>
		       							<c:otherwise>
		       								<a href="<%=basePath %>/product/${categoryId}/listProductByCategory?pageIndex=${i}">${i }</a>
		       							</c:otherwise>
		       						</c:choose>
		       					</c:forEach>
								<a href="<%=basePath %>/product/${categoryId}/listProductByCategory?pageIndex=6">6</a>
								<a href="<%=basePath %>/product/${categoryId}/listProductByCategory?pageIndex=7">7</a>...
								<a href="<%=basePath %>/product/${categoryId}/listProductByCategory?pageIndex=${page.totalPage-1}">${page.totalPage-1}</a>
								<a href="<%=basePath %>/product/${categoryId}/listProductByCategory?pageIndex=${page.totalPage}">${page.totalPage}</a>
								<a href="<%=basePath %>/product/${categoryId}/listProductByCategory?pageIndex=${(page.currentPage+1)+1}">Next&gt;</a>
	       					</c:when>
	       					
	       					<c:when test="${(page.currentPage+1)>(page.totalPage-5)}">
	       						<a href="<%=basePath %>/product/${categoryId}/listProductByCategory?pageIndex=${page.currentPage}">&lt;Prev</a>
	       						<a href="<%=basePath %>/product/${categoryId}/listProductByCategory?pageIndex=1">1</a>
	       						<a href="<%=basePath %>/product/${categoryId}/listProductByCategory?pageIndex=2">2</a>
	       						...
	       						<c:forEach var="i" begin="${page.totalPage-6}" end="${page.totalPage}">
		       						<c:choose>
			       							<c:when test="${(page.currentPage+1)==i }">
			       								<span class="current">${i}</span>
			       							</c:when>
			       							<c:otherwise>
			       								<a href="<%=basePath %>/product/${categoryId}/listProductByCategory?pageIndex=${i}">${i }</a>
			       							</c:otherwise>
		       						</c:choose>
	       						</c:forEach>
	       						<c:choose>
	       							<c:when test="${(page.currentPage+1)==page.totalPage }">
	       								<span class="disabled"> Next&gt;</span>
	       							</c:when>
	       							<c:otherwise>
	       								<a href="<%=basePath %>/product/${categoryId}/listProductByCategory?pageIndex=${(page.currentPage+1)+1}">Next&gt; </a>
	       							</c:otherwise>
	       						</c:choose>
	       					</c:when>
	       					<c:otherwise>
	       						<c:if test="${(page.currentPage+1)>5}">
	       							<c:if test="${(page.currentPage+1)<=(page.totalPage-5)}">
			       						<a href="<%=basePath %>/product/${categoryId}/listProductByCategory?pageIndex=${page.currentPage}">&lt;Prev</a>
			       						<a href="<%=basePath %>/product/${categoryId}/listProductByCategory?pageIndex=${(page.currentPage+1)-4}">${(page.currentPage+1)-4}</a>
			       						<a href="<%=basePath %>/product/${categoryId}/listProductByCategory?pageIndex=${(page.currentPage+1)-3}">${(page.currentPage+1)-3}</a>
			       						<a href="<%=basePath %>/product/${categoryId}/listProductByCategory?pageIndex=${(page.currentPage+1)-2}">${(page.currentPage+1)-2}</a>
			       						<a href="<%=basePath %>/product/${categoryId}/listProductByCategory?pageIndex=${(page.currentPage+1)-1}">${(page.currentPage+1)-1}</a>
			       						<span class="current">${(page.currentPage+1)}</span>
			       						<a href="<%=basePath %>/product/${categoryId}/listProductByCategory?pageIndex=${(page.currentPage+1)+1}">${(page.currentPage+1)+1}</a>
			       						<a href="<%=basePath %>/product/${categoryId}/listProductByCategory?pageIndex=${(page.currentPage+1)+2}">${(page.currentPage+1)+2}</a>
			       						...
			       						<a href="<%=basePath %>/product/${categoryId}/listProductByCategory?pageIndex=${page.totalPage-1}">${page.totalPage-1}</a>
										<a href="<%=basePath %>/product/${categoryId}/listProductByCategory?pageIndex=${page.totalPage}">${page.totalPage}</a>
			       						<a href="<%=basePath %>/product/${categoryId}/listProductByCategory?pageIndex=${(page.currentPage+1)+1}">Next&gt;</a>
		       						</c:if>
	       						</c:if>
	       					</c:otherwise>
	       				</c:choose>
	       			</c:when>
	       		</c:choose>
	       </div>
	       </c:if>		
   		</div><!--/span12-->
   		<!-- 分页工具条 end -->
   		
       </div><!--span8-->
       
	</div > <!-- row-fluid -->
   </div><!-- container-fluid -->
    
    
  </body>
</html>