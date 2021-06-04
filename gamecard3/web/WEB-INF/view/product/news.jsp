<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@include file="../common/taglibs.jsp" %>
<%
	String path = request.getContextPath();    
	//获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:    
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path; 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <script src="<%=basePath %>/assets/js/common/util.js"></script>
    
	 <link href="<%=basePath %>/assets/css/page/page.css" rel="stylesheet">
	 <link href="<%=basePath %>/assets/css/page/image1.gif">
	 <link href="<%=basePath %>/assets/css/page/image2.gif">
	 <link href="<%=basePath %>/assets/css/page/meneame.jpg">
	 
  </head>
  <body>
  
    <div class="container">
      <div class="row">
     		<div class="col-lg-2 col-lg-offset-1">
   					<%@include file="left.jsp" %>
   			</div><!--/span2-->
       		<div class="col-lg-8">
		    	  <ul class="breadcrumb">
		    		  <li><a href="<%=basePath %>/index">Home</a> <span class="divider">&gt;&gt; news</span></li>
		    	  </ul>
				  <table class="table table-condensed table-hover">
				  	<tbody>
				  		<c:forEach items="${page.data }" var="news" >
				  		<tr>
				  			<td width="90%" style="border: 0"><a onclick="javascript:window.open('<%=basePath %>/news/${news.id }/newsDetail');" ><span style="color: #000000">${news.title }</span></a></td>
				  			<td width="10%" style="border: 0"><fmt:formatDate value="${news.createTm}" pattern="yyyy-MM-dd"/></td>
				  		</tr>
				  		</c:forEach>
				  	</tbody>
				  </table>
			     
			       
		<!-- 分页工具条 begin -->
        <div class="span12" >
   			<!-- 总页数大于1时才显示分页工具 -->
	       <c:if test="${page.totalPage >1}">
	       <div class="yahoo2">
	       		<c:choose>
	       			<c:when test="${page.totalPage <=10}">
	       				<c:forEach var="i" begin="1" end="${page.totalPage}">
		       				<c:choose>
		       					<c:when test="${i == (page.currentPage+1) }">
		       						<span class="current">${i}</span>
		       					</c:when>
		       					<c:otherwise>
		       						<a href="<%=basePath%>/news/?pageIndex=${i}">${i}</a>
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
	       								<a href="<%=basePath%>/news/?pageIndex=${page.currentPage}">&lt;Prev</a>
	       							</c:otherwise>
	       						</c:choose>
		       					<span class="disabled">&lt;Prev</span>
		       					<c:forEach var="i" begin="1" end="5">
		       						<c:choose>
		       							<c:when test="${(page.currentPage+1)==i }">
		       								<span class="current">${i}</span>
		       							</c:when>
		       							<c:otherwise>
		       								<a href="<%=basePath%>/news/?pageIndex=${i}">${i }</a>
		       							</c:otherwise>
		       						</c:choose>
		       					</c:forEach>
								<a href="<%=basePath%>/news/?pageIndex=6">6</a>
								<a href="<%=basePath%>/news/?pageIndex=7">7</a>...
								<a href="<%=basePath%>/news/?pageIndex=${page.totalPage-1}">${page.totalPage-1}</a>
								<a href="<%=basePath%>/news/?pageIndex=${page.totalPage}">${page.totalPage}</a>
								<a href="<%=basePath%>/news/?pageIndex=${(page.currentPage+1)+1}">Next&gt;</a>
	       					</c:when>
	       					
	       					<c:when test="${(page.currentPage+1)>(page.totalPage-5)}">
	       						<a href="<%=basePath%>/news/?pageIndex=${page.currentPage}">&lt;Prev</a>
	       						<a href="<%=basePath%>/news/?pageIndex=1">1</a>
	       						<a href="<%=basePath%>/news/?pageIndex=2">2</a>
	       						...
	       						<c:forEach var="i" begin="${page.totalPage-6}" end="${page.totalPage}">
		       						<c:choose>
			       							<c:when test="${(page.currentPage+1)==i }">
			       								<span class="current">${i}</span>
			       							</c:when>
			       							<c:otherwise>
			       								<a href="<%=basePath%>/news/?pageIndex=${i}">${i }</a>
			       							</c:otherwise>
		       						</c:choose>
	       						</c:forEach>
	       						<c:choose>
	       							<c:when test="${(page.currentPage+1)==page.totalPage }">
	       								<span class="disabled"> Next&gt;</span>
	       							</c:when>
	       							<c:otherwise>
	       								<a href="<%=basePath%>/news/?pageIndex=${(page.currentPage+1)+1}">Next&gt; </a>
	       							</c:otherwise>
	       						</c:choose>
	       					</c:when>
	       					<c:otherwise>
	       						<c:if test="${(page.currentPage+1)>5}">
	       							<c:if test="${(page.currentPage+1)<=(page.totalPage-5)}">
			       						<a href="<%=basePath%>/news/?pageIndex=${page.currentPage}">&lt;Prev</a>
			       						<a href="<%=basePath%>/news/?pageIndex=${(page.currentPage+1)-4}">${(page.currentPage+1)-4}</a>
			       						<a href="<%=basePath%>/news/?pageIndex=${(page.currentPage+1)-3}">${(page.currentPage+1)-3}</a>
			       						<a href="<%=basePath%>/news/?pageIndex=${(page.currentPage+1)-2}">${(page.currentPage+1)-2}</a>
			       						<a href="<%=basePath%>/news/?pageIndex=${(page.currentPage+1)-1}">${(page.currentPage+1)-1}</a>
			       						<span class="current">${(page.currentPage+1)}</span>
			       						<a href="<%=basePath%>/news/?pageIndex=${(page.currentPage+1)+1}">${(page.currentPage+1)+1}</a>
			       						<a href="<%=basePath%>/news/?pageIndex=${(page.currentPage+1)+2}">${(page.currentPage+1)+2}</a>
			       						...
			       						<a href="<%=basePath%>/news/?pageIndex=${page.totalPage-1}">${page.totalPage-1}</a>
										<a href="<%=basePath%>/news/?pageIndex=${page.totalPage}">${page.totalPage}</a>
			       						<a href="<%=basePath%>/news/?pageIndex=${(page.currentPage+1)+1}">Next&gt;</a>
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
   		
    		</div><!--/span8-->
    		

      </div><!-- row-fluid -->

    </div><!-- container-fluid -->
 
  </body>
</html>