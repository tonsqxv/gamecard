<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page isELIgnored="false" %>
<%@include file="../common/taglibs.jsp" %>
<%
String path = request.getContextPath();    
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

    
     <div class="container">

      <form id="loginForm" class="" action="<%=basePath%>/member/memberLogin" method="post">
      <div class="row">
		    <div class="col-lg-4 col-lg-offset-4">
		        <h2 class="form-signin-heading">Please sign in</h2>
		        <div><font color="red"><span id="error">${error}</span></font></div>
		        <input id="email" class="form-control" type="text" name="email" maxlength="50" class="input-block-level" placeholder="email..." autofocus/>
		        <input id="password" class="form-control" type="password" name="password" maxlength="50" class="input-block-level" placeholder="password..."/>
		        <label class="checkbox">
		          <input type="checkbox" value="remember-me"> Remember me
		        </label>
		        <button class="btn btn-lg btn-primary btn-block" type="button" onclick="verifySubmit()">Sign in</button>
	        </div>
	        <div class="col-lg-4 col-lg-offset-4">
	        <a href="<%=basePath%>/member/findPassword" style="text-decoration:underline;">Forgot your password?</a>
		    <p align="right">&nbsp;&nbsp;<a href="<%=basePath %>/member/register" style="text-decoration:underline;">Register now</a></p>
			    	
	        </div>
        </div>
      </form>

    </div> <!-- /container -->
    <br>
    <div class="row">    
    	<div class="col-lg-12 col-lg-offset-5">
    	 <h4>Sign in help</h4>           
           &nbsp;&nbsp;Forgot your password?&nbsp;&nbsp;<a href="#" style="text-decoration:underline;">Get Password Help</a>      
    	</div> 
    </div>
    <br/><br/><br/>
 
 <script type="text/javascript">   
 $(document).ready(function(){ 
	 	
	   
	   $("#email").blur(function(){
		   verfiyEmail();
	        });  
	   
	   $("#password").blur(function(){
		   verfiyPassword();
	        });  
	   
	   
	   });   
 	
 function verfiyEmail(){
	 var email = trim($("#email").val());	         
	    if(email == ""){
		   	 $("#error").html("<i class='icon-remove'></i>Please input your e-mail");
		   	return false;
	    }
	    
    	$.ajax({
			  type:"post",
			  cache:false,    
			  dataType : "json",
			  url:"<%=basePath%>/member/checkEmail",
			  data: {"email":email},
			  success:function(data){
				  if(data.success == "no"){	    		
		    		   $("#error").html("<i class='icon-remove'></i>email not exsits");	    		    
		    		   return false;
		    	   }else{
		    		   $("#error").html("");	  
		    		   return true; 
		    	   }
			  }
		  });
	    
	    
	 	return  true;
	 
 }
 
 function verfiyPassword(){
	 var email = trim($("#email").val());	  
	 var password = trim($("#password").val());	         
	    if(email == ""){
		   	 $("#error").html("<i class='icon-remove'></i>Please input your e-mail");
		   	return false;
	    }
	    if(password == ""){
		   	 $("#error").html("<i class='icon-remove'></i>Please input your password");
		   	return false;
	    }
	    
	 	return  true;
	 
 }
 
 function verifySubmit(){
	 if(!verfiyPassword()){ 
	 	return false ;
	 }	
	 var email = trim($("#email").val());	  
	 $.ajax({
		  type:"post",
		  cache:false,    
		  dataType : "json",
		  url:"<%=basePath%>/member/checkEmail",
		  data: {"email":email},
		  success:function(data){
			  if(data.success == "no"){	    		
	    		   $("#error").html("<i class='icon-remove'></i>email not exsits");	    		    
	    		   return false;
	    	   }else{
	    		   $("#error").html("");	
	    		   $("#loginForm").submit();
	    		   return true; 
	    	   }
		  }
	  });
	
	}
 </script>
  </body>
</html>