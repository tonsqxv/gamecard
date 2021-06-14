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
  </head>
  <body>
  <%@include file="head.jsp" %>

    <div class="container">
      <div class="row">
    		<div class="col-lg-3" >
   					<%@include file="left.jsp" %>
   			</div><!--/span2-->
    		<div class="col-lg-9">
    		    <ul class="breadcrumb">
			         <li><a href="<%=basePath %>/index">Home</a> <span class="divider">&gt;&gt; Forgot your password?</span></li>
			    </ul>
	            <h4><font color="orange">Forgot your password?</font></h4>
	          	<hr>
    		  	<div>
					  <font color="red"><span id="email-help">${error}</span></font>
					  <form id="findMyPasswordForm" class="form-search" action="<%=basePath %>/member/findMyPassword" method="post">
					  	  <div class="row">
					  	       <span class="col-lg-2">Email Address:</span>
					  	       <div class="col-lg-4">
					  	         <input class="form-control input-sm" type="text" id="email" name="email" class="input-large">
					  	       </div>
					  	       <div class="col-lg-3">
					  	         <img alt="" src="<%=basePath%>/assets/icos/product/btnSubmit.jpg" onclick="javascript:submitEmail();">
					  	       </div>
					  	  </div>
					  </form>
    		  	</div>
    		</div><!--/span8-->

      </div><!-- row-fluid -->

    </div><!-- container-fluid -->
 
 <script type="text/javascript">   
 
 function submitEmail(){
	 var email = trim($("#email").val());	         
	    if(email == ""){
		   	 $("#email-help").html("<i class='icon-remove'></i>email can not be null");
		   	return false;
	    }
	    $("#findMyPasswordForm").submit();
	 	return  true;
 }
 
 </script>   	
  </body>
</html>