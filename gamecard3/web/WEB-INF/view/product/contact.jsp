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
  </head>
  <body>
  <%@include file="head.jsp" %>
   <c:if test="${!empty msg }">
    	<script type="text/javascript"> 
    	alert('${msg }');
    	</script>
    </c:if>
    
    <div class="container-fluid">
      <div class="row-fluid">
    		<div class="span2 offset1" >
   					<%@include file="left.jsp" %>
   			</div><!--/span2-->
    		<div class="span8">
    			  <ul class="breadcrumb">
		    		  <li><a href="<%=basePath %>/index">Home</a> <span class="divider">&gt;&gt; contact us</span></li>
		    	  </ul>
		          <h4><font color="orange">Contact us</font></h4>
		          <hr>  
		          <p>You have an apple and I have an apple, when exchange, each got an apple; You have an idea and I have an idea, exchanged, each got two ideas.</p>
		          <p align="right"> ------------Bernard Shaw</p>
		          <p>We think highly of exchanging, if you have some questions and good ideas, welcome to contact us, as a rewards, we will send you a mysterious presents together with the package. </p>
		          <p align="left">Email:<strong>servicen3dscard@gmail.com</strong></p>
		          
			      <hr>
			      <h4><font color="orange">Ship To:</font></h4>
          		  <hr> 
          		  
          		  <form id="messageForm" class="form-horizontal" action="<%=basePath %>/contact/message" method="post">
			            <div class="control-group">
			              <label class="control-label" for="fullName">Full Name:</label>
			              <div class="controls">
			                <input type="text" id="fullName" name="fullName"><font color="red">&nbsp;*</font>
			              </div>
			            </div>
			            <div class="control-group">
			              <label class="control-label" for="email">E-mail:</label>
			              <div class="controls">
			                <input type="text" id="email" name="email"><font color="red">&nbsp;*</font>
			              </div>
			            </div>
			            <div class="control-group">
			              <label class="control-label" for="company">Company name:</label>
			              <div class="controls">
			                <input type="text" id="company" name="company">
			              </div>
			            </div>
			            <div class="control-group">
			              <label class="control-label" for="phoneNumber">Phone Number:</label>
			              <div class="controls">
			                <input type="text" id="phoneNumber" name="phoneNumber">
			              </div>
			            </div>
			            <div class="control-group">
			              <label class="control-label" for="message">Message:</label>
			              <div class="controls">
			                <textarea class="span8" rows="8" name="message" placeholder="input your infomation"></textarea>
			              </div>
			            </div>
			            <div class="control-group">
			              <label class="control-label" for="checkCode">Identified Code:</label>
			              <div class="controls">
			                <input name="verifyCode" id="verifyCode" type="text"  class="inp1 lh37 logn_iny" onkeypress="if(event.keyCode == 13) submitMessage();"/>
                			<img id="imgObj" class="fl ma_l14 ma_t8" src="<%=basePath%>/checkImgCode/createImgCode">&nbsp;&nbsp;<a href="javascript:changeImg('<%=basePath%>/checkImgCode/createImgCode')" class="red_t lh37">Refresh</a>
                
			              </div>
			            </div>
			            <div class="control-group" align="center">
			              <img alt="" src="<%=basePath%>/assets/icos/product/btnSubmit.jpg" onclick="submitMessage()">
			            </div>
          		</form>   
    		</div><!--/span8-->

      </div><!-- row-fluid -->

    </div><!-- container-fluid -->
  <script type="text/javascript">  
	//更换验证码
  function changeImg(action) {
  		var imgSrc = document.getElementById("imgObj");
  		var timestamp = (new Date()).valueOf(); 
  		var src = action+"?timestamp="+timestamp; 
  		imgSrc.src= src;  
  	} 
  
  	function submitMessage(){
  		var fullName = trim($("#fullName").val()) ;
  		if(fullName == ""){
  			alert("Please input your fullname");
  			return false ;
  		}
  		var email = trim($("#email").val()) ;
  		if(email == ""){
  			alert("Please input your email");
  			return false ;
  		}
  		
  		$("#messageForm").submit();
  	}
  </script>
  </body>
</html>