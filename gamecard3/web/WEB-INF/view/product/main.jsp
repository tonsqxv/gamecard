<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@include file="../common/taglibs.jsp" %>

<%
	String path = request.getContextPath();    
	//获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:    
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path; 
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html5/loose.dtd">
<html>
 <head>
	
 <link href="<%=basePath %>/assets/css/page/page.css" rel="stylesheet">
 <link href="<%=basePath %>/assets/css/page/image1.gif">
 <link href="<%=basePath %>/assets/css/page/image2.gif">
 <link href="<%=basePath %>/assets/css/page/meneame.jpg">
 </head>
 <body data-spy="scroll" data-target=".bs-docs-sidebar">
  
<%@include file="head.jsp" %>
    
    <div class="container-fluid">
      <div class="row-fluid">

   		<div class="span2 offset1" >
   					<%@include file="left.jsp" %>
   		</div><!--/span2-->
   		
   		
    	<div class="span8">
    		  <c:if test="${!empty dynimicProducts}">
	          <!--轮换照片 begin-->
	          <div id="myCarousel" class="carousel slide">
		            <ol class="carousel-indicators">
		              <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		              <li data-target="#myCarousel" data-slide-to="1"></li>
		              <li data-target="#myCarousel" data-slide-to="2"></li>
		              <li data-target="#myCarousel" data-slide-to="3"></li>
		              <li data-target="#myCarousel" data-slide-to="4"></li>
		            </ol>
		            <div class="carousel-inner">
		            	<c:set var="activeIndex" value="1"></c:set>
			            <c:forEach items="${dynimicProducts }" var="card">
			              <c:if test="${activeIndex==1 }">
			              	<div class="item active">
				                <a href="<%=basePath %>/product/${card.id}/productDetail" class="thumbnail">
				                  <img src="<%=basePath %>/assets/images/product/${card.hotImgPath}" alt="" width="800">
				                </a>
				              </div>
			              </c:if>
			              <c:if test="${activeIndex!=1 }">
			              	<div class="item">
				                <a href="<%=basePath %>/product/${card.id}/productDetail" class="thumbnail">
				                  <img src="<%=basePath %>/assets/images/product/${card.hotImgPath}" alt="" width="800">
				                </a>
				              </div>
			              </c:if>
			              <c:set var="activeIndex" value="${activeIndex+1 }"></c:set>
			            </c:forEach>               
		            </div>
		            <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
		            <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
	          </div><!--轮换照片 end  -->
          <hr>
          </c:if>
          
          <h4>Featured Products</h4>
          <hr>
          <!-- 产品展示 -->
          <c:set var="index" value="1"></c:set>
          <c:forEach items="${page.data }" var="card" varStatus="status">
          	 <c:if test="${index%4==1 }">
	    		<div class="row-fluid">
	    	 </c:if>
             <div class="span3">
	              <a href="<%=basePath %>/product/${card.id}/productDetail" class="thumbnail">
	                <img src="<%=basePath %>/assets/images/product/${card.mainImgPath}"/>
	              </a>
	              <p class="text-center">
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
          <div class="span12" >
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
		       						<a href="<%=basePath%>/product/mainPage?pageIndex=${i}">${i}</a>
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
	       								<a href="<%=basePath%>/product/mainPage?pageIndex=${page.currentPage}">&lt;Prev</a>
	       							</c:otherwise>
	       						</c:choose>
		       					<span class="disabled">&lt;Prev</span>
		       					<c:forEach var="i" begin="1" end="5">
		       						<c:choose>
		       							<c:when test="${(page.currentPage+1)==i }">
		       								<span class="current">${i}</span>
		       							</c:when>
		       							<c:otherwise>
		       								<a href="<%=basePath%>/product/mainPage?pageIndex=${i}">${i }</a>
		       							</c:otherwise>
		       						</c:choose>
		       					</c:forEach>
								<a href="<%=basePath%>/product/mainPage?pageIndex=6">6</a>
								<a href="<%=basePath%>/product/mainPage?pageIndex=7">7</a>...
								<a href="<%=basePath%>/product/mainPage?pageIndex=${page.totalPage-1}">${page.totalPage-1}</a>
								<a href="<%=basePath%>/product/mainPage?pageIndex=${page.totalPage}">${page.totalPage}</a>
								<a href="<%=basePath%>/product/mainPage?pageIndex=${(page.currentPage+1)+1}">Next&gt;</a>
	       					</c:when>
	       					
	       					<c:when test="${(page.currentPage+1)>(page.totalPage-5)}">
	       						<a href="<%=basePath%>/product/mainPage?pageIndex=${page.currentPage}">&lt;Prev</a>
	       						<a href="<%=basePath%>/product/mainPage?pageIndex=1">1</a>
	       						<a href="<%=basePath%>/product/mainPage?pageIndex=2">2</a>
	       						...
	       						<c:forEach var="i" begin="${page.totalPage-6}" end="${page.totalPage}">
		       						<c:choose>
			       							<c:when test="${(page.currentPage+1)==i }">
			       								<span class="current">${i}</span>
			       							</c:when>
			       							<c:otherwise>
			       								<a href="<%=basePath%>/product/mainPage?pageIndex=${i}">${i }</a>
			       							</c:otherwise>
		       						</c:choose>
	       						</c:forEach>
	       						<c:choose>
	       							<c:when test="${(page.currentPage+1)==page.totalPage }">
	       								<span class="disabled"> Next&gt;</span>
	       							</c:when>
	       							<c:otherwise>
	       								<a href="<%=basePath%>/product/mainPage?pageIndex=${(page.currentPage+1)+1}">Next&gt; </a>
	       							</c:otherwise>
	       						</c:choose>
	       					</c:when>
	       					<c:otherwise>
	       						<c:if test="${(page.currentPage+1)>5}">
	       							<c:if test="${(page.currentPage+1)<=(page.totalPage-5)}">
			       						<a href="<%=basePath%>/product/mainPage?pageIndex=${page.currentPage}">&lt;Prev</a>
			       						<a href="<%=basePath%>/product/mainPage?pageIndex=${(page.currentPage+1)-4}">${(page.currentPage+1)-4}</a>
			       						<a href="<%=basePath%>/product/mainPage?pageIndex=${(page.currentPage+1)-3}">${(page.currentPage+1)-3}</a>
			       						<a href="<%=basePath%>/product/mainPage?pageIndex=${(page.currentPage+1)-2}">${(page.currentPage+1)-2}</a>
			       						<a href="<%=basePath%>/product/mainPage?pageIndex=${(page.currentPage+1)-1}">${(page.currentPage+1)-1}</a>
			       						<span class="current">${(page.currentPage+1)}</span>
			       						<a href="<%=basePath%>/product/mainPage?pageIndex=${(page.currentPage+1)+1}">${(page.currentPage+1)+1}</a>
			       						<a href="<%=basePath%>/product/mainPage?pageIndex=${(page.currentPage+1)+2}">${(page.currentPage+1)+2}</a>
			       						...
			       						<a href="<%=basePath%>/product/mainPage?pageIndex=${page.totalPage-1}">${page.totalPage-1}</a>
										<a href="<%=basePath%>/product/mainPage?pageIndex=${page.totalPage}">${page.totalPage}</a>
			       						<a href="<%=basePath%>/product/mainPage?pageIndex=${(page.currentPage+1)+1}">Next&gt;</a>
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
		
	   </div> <!--row-fluid  -->
    </div><!-- container-fluid -->
    
  	
    
  </body>
</html>