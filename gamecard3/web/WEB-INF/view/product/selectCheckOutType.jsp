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
    		<div class="col-lg-3">
   					<%@include file="left.jsp" %>
   			</div><!--/span2-->
    		<div class="col-lg-9">
    					<ul class="breadcrumb">
						      <li><a href="<%=basePath %>/index">Home</a> <span class="divider">&gt;&gt; My Shopping cart</span></li>
						</ul>
				        <h4><font color="orange"> Already have an account?</font></h4>
				        <hr> 
			           <form id="memberLoginForm" class="form-horizontal" action="<%=basePath %>/member/memberLogin" method="post">
			           			
			           			<div class="row form-group">
					              <span class="control-label col-lg-3"></span>
					              <div class="col-lg-4">
					                <div><font color="red"><span id="error">${error}</span></font></div>
					              </div>
					            </div>
					            <div class="row form-group">
					              <span class="control-label col-lg-3">Email:</span>
					              <div class="col-lg-4">
					                <input class="form-control input-sm" type="text" id="email" name="email" placeholder="Email">
					              </div>
					            </div>
					            <div class="row form-group">
					              <span class="control-label col-lg-3">Password:</span>
					              <div class="col-lg-4">
					                <input class="form-control input-sm" type="password" id="password" name="password" placeholder="Password">
					              </div>
					            </div>
					            <div class="row form-group">
					              <div class="col-lg-4 col-lg-offset-3">
					                <button type="button" class="btn btn-warning "	onclick="submitLogin()">Sign in</button>
					              </div>
					            </div>
					            <div class="row form-group">
					              <div class="col-lg-4 col-lg-offset-3">
					                 <p><a href="<%=basePath%>/member/findPassword">Forgot your password?</a></p>
					              </div>
					            </div>
			          </form>
			          
			          <hr> 
			          <div class="row">
			          <h4><font color="orange"> Not a existing customer?</font></h4>
			          </div>
			          
			          <hr> 
			          <div class="row">
					            <div class="col-lg-6">
						              <p>Register with us for a faster checkout,to track </p>
						              <p>the status of your order and more.</p>
						              <a href="<%=basePath%>/member/register"><button type="submit" class="btn btn-warning">Join Now</button></a>
					            </div>
					            <div class="col-lg-6">
						              <p>You can also checkout as a guest:</p>
						              <p>&nbsp;</p>
						              <a href="<%=basePath%>/pay/prePayForm"><button type="submit" class="btn btn-warning">Check out as a guest </button></a>
					            </div>
			          </div>
			          <hr>    		  		 
    		  
		          
    		</div><!--/span8-->

      </div><!-- row-fluid -->

    </div><!-- container-fluid -->
  
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
 
 function submitLogin(){
	 //提交时再次验证用户名，密码是否正确
	 var email = trim($("#email").val());	  
	 var password = trim($("#password").val());	
	 if(!verfiyPassword()){
		 return ;
	 } 
	 
	 $.ajax({
			  type:"post",
			  cache:false,    
			  dataType : "json",
			  url:"<%=basePath%>/member/checkEmailAndPassword",  
			  data: {"email":email,"password":password},
			  success:function(data){
				  if(data.success == "no"){	    		
		    		   $("#error").html("<i class='icon-remove'></i>email or password error");	    		    
		    		   return false;
		    	   }else{
		    		   $("#error").html("");	
		    		   $("#memberLoginForm").submit();
		    		   return true; 
		    	   }
			  }
		  });
	}
 </script>  	
  </body>
</html>