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
    <script src="<%=basePath %>/assets/js/common/util.js"></script>
    
	 <link href="<%=basePath %>/assets/css/page/page.css" rel="stylesheet">
	 <link href="<%=basePath %>/assets/css/page/image1.gif">
	 <link href="<%=basePath %>/assets/css/page/image2.gif">
	 <link href="<%=basePath %>/assets/css/page/meneame.jpg">
	 
	 <link href="<%=basePath %>/assets/xheditor-1.1.14/common.css"/>
	 <script type="text/javascript" src="<%=basePath %>/assets/xheditor-1.1.14/xheditor-1.1.14-en.min.js"></script>
 
  </head>
  <body>
  
    <div class="container">
      <div class="row">
     		<div class="col-lg-3">
   					<%@include file="left.jsp" %>
   			</div><!--/span2-->
       		<div class="col-lg-9">
		    	  <ul class="breadcrumb">
		    		  <li><a href="<%=basePath %>/index">Home</a> <span class="divider">&gt;&gt; news</span></li>
		    	  </ul>
				  <div align="center"><h4>${news.title }</h4></div>
				  <br>
				  <div>
				  ${news.context }<br>
				  </div>
				  <br>
				  <div align="right"><fmt:formatDate value="${news.createTm}" pattern="yyyy-MM-dd"/>&nbsp;&nbsp;&nbsp;&nbsp;Comments:${page.totalSize }&nbsp;&nbsp;</div>
				 
				  <hr>
				  
				  <!-- 评论 -->
				  <c:if test="${!empty page.data }">
				  <p><b>Comments</b></p>
				  <c:forEach items="${page.data }" var="newsComments">
						<ul class="breadcrumb">
				    		  <li>${newsComments.lastName }&nbsp;${newsComments.firstName }&nbsp;&nbsp;<fmt:formatDate value="${newsComments.commentTm}" pattern="yyyy-MM-dd hh:mm"/> </li>
				    	</ul>
				 		<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${newsComments.context }</div><br>
				  </c:forEach>
				  
				  <!-- 分页工具条 begin -->
			        <div class="span12">
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
					       						<a href="<%=basePath%>/news/${news.id}/newsDetail?pageIndex=${i}">${i}</a>
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
				       								<a href="<%=basePath%>/news/${news.id}/newsDetail?pageIndex=${page.currentPage}">&lt;Prev</a>
				       							</c:otherwise>
				       						</c:choose>
					       					<span class="disabled">&lt;Prev</span>
					       					<c:forEach var="i" begin="1" end="5">
					       						<c:choose>
					       							<c:when test="${(page.currentPage+1)==i }">
					       								<span class="current">${i}</span>
					       							</c:when>
					       							<c:otherwise>
					       								<a href="<%=basePath%>/news/${news.id}/newsDetail?pageIndex=${i}">${i }</a>
					       							</c:otherwise>
					       						</c:choose>
					       					</c:forEach>
											<a href="<%=basePath%>/news/${news.id}/newsDetail?pageIndex=6">6</a>
											<a href="<%=basePath%>/news/${news.id}/newsDetail?pageIndex=7">7</a>...
											<a href="<%=basePath%>/news/${news.id}/newsDetail?pageIndex=${page.totalPage-1}">${page.totalPage-1}</a>
											<a href="<%=basePath%>/news/${news.id}/newsDetail?pageIndex=${page.totalPage}">${page.totalPage}</a>
											<a href="<%=basePath%>/news/${news.id}/newsDetail?pageIndex=${(page.currentPage+1)+1}">Next&gt;</a>
				       					</c:when>
				       					
				       					<c:when test="${(page.currentPage+1)>(page.totalPage-5)}">
				       						<a href="<%=basePath%>/news/${news.id}/newsDetail?pageIndex=${page.currentPage}">&lt;Prev</a>
				       						<a href="<%=basePath%>/news/${news.id}/newsDetail?pageIndex=1">1</a>
				       						<a href="<%=basePath%>/news/${news.id}/newsDetail?pageIndex=2">2</a>
				       						...
				       						<c:forEach var="i" begin="${page.totalPage-6}" end="${page.totalPage}">
					       						<c:choose>
						       							<c:when test="${(page.currentPage+1)==i }">
						       								<span class="current">${i}</span>
						       							</c:when>
						       							<c:otherwise>
						       								<a href="<%=basePath%>/news/${news.id}/newsDetail?pageIndex=${i}">${i }</a>
						       							</c:otherwise>
					       						</c:choose>
				       						</c:forEach>
				       						<c:choose>
				       							<c:when test="${(page.currentPage+1)==page.totalPage }">
				       								<span class="disabled"> Next&gt;</span>
				       							</c:when>
				       							<c:otherwise>
				       								<a href="<%=basePath%>/news/${news.id}/newsDetail?pageIndex=${(page.currentPage+1)+1}">Next&gt; </a>
				       							</c:otherwise>
				       						</c:choose>
				       					</c:when>
				       					<c:otherwise>
				       						<c:if test="${(page.currentPage+1)>5}">
				       							<c:if test="${(page.currentPage+1)<=(page.totalPage-5)}">
						       						<a href="<%=basePath%>/news/${news.id}/newsDetail?pageIndex=${page.currentPage}">&lt;Prev</a>
						       						<a href="<%=basePath%>/news/${news.id}/newsDetail?pageIndex=${(page.currentPage+1)-4}">${(page.currentPage+1)-4}</a>
						       						<a href="<%=basePath%>/news/${news.id}/newsDetail?pageIndex=${(page.currentPage+1)-3}">${(page.currentPage+1)-3}</a>
						       						<a href="<%=basePath%>/news/${news.id}/newsDetail?pageIndex=${(page.currentPage+1)-2}">${(page.currentPage+1)-2}</a>
						       						<a href="<%=basePath%>/news/${news.id}/newsDetail?pageIndex=${(page.currentPage+1)-1}">${(page.currentPage+1)-1}</a>
						       						<span class="current">${(page.currentPage+1)}</span>
						       						<a href="<%=basePath%>/news/${news.id}/newsDetail?pageIndex=${(page.currentPage+1)+1}">${(page.currentPage+1)+1}</a>
						       						<a href="<%=basePath%>/news/${news.id}/newsDetail?pageIndex=${(page.currentPage+1)+2}">${(page.currentPage+1)+2}</a>
						       						...
						       						<a href="<%=basePath%>/news/${news.id}/newsDetail?pageIndex=${page.totalPage-1}">${page.totalPage-1}</a>
													<a href="<%=basePath%>/news/${news.id}/newsDetail?pageIndex=${page.totalPage}">${page.totalPage}</a>
						       						<a href="<%=basePath%>/news/${news.id}/newsDetail?pageIndex=${(page.currentPage+1)+1}">Next&gt;</a>
					       						</c:if>
				       						</c:if>
				       					</c:otherwise>
				       				</c:choose>
				       			</c:when>
				       		</c:choose>
				       </div>
				       </c:if>	
			   	  	   <hr>
			   		</div><!--/span12-->
			   		<!-- 分页工具条 end -->
				  </c:if>
				  
				  <form id="commentForm" class="form-horizontal" action="<%=basePath %>/news/${news.id }/comment" method="post">
				   <div class="control-group">
			              <label class="control-label" for="message"><b>Comment:</b></label>
			              <div class="controls">
			                <textarea id="comment" name="comment" class="xheditor" rows="12" cols="90" style="width: 90%"></textarea>
			              </div>
			       </div>
			      <div class="control-group" align="center">
			           <img alt="" src="<%=basePath%>/assets/icos/product/btnSubmit.jpg" onclick="submitComment()">
			      </div>
			     </form>
    		</div><!--/span8-->
    		

      </div><!-- row-fluid -->

    </div><!-- container-fluid -->
    
 <script type="text/javascript">  

  	function submitComment(){
  		var comment = trim($("#comment").val()) ;
  		if(comment == ""){
  			alert("Please input your comment context");
  			return false ;
  		}
  		$("#commentForm").submit();
  	}
  </script>
  </body>
</html>