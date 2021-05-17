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

     <style type="text/css">
       
        /*form表单样式*/
        .form-signin{
          max-width: 300px;
          padding: 1px 150px 20px;  /*上 左右 下*/
          margin: 0 auto 20px;
          background-color: #F5F5F5;/*背景颜色*/
          border: 1px solid #a5a6a7;
          -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
                border-radius: 5px;
          -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
            -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);

        }
        /*表单输入框样式*/
        .form-signin input[type="text"] ,
        .form-signin input[type="password"]{
          font-size: 25px;
          height: auto;
          margin-bottom: 5px;
          padding: 5px 10px;
        }
      </style>

  </head>
  <body>
  
   <%@include file="head.jsp" %>
    <c:if test="${!empty msg }">
    	<script type="text/javascript"> 
    	alert('${msg }');
    	</script>
    </c:if>

    <div class="container ">
    	<div class="offset2 span8">
		      <form id="loginForm" class="form-signin" action="memberLogin" method="post">
		         <h2>Please sign in</h2>
		          <div><font color="red"><span id="error">${error}</span></font></div>	
		           Email:
		           <input id="email"  type="text" name="email" maxlength="50" class="input-block-level" placeholder="email..."/>
		           Password:
		           <input id="password" type="password" name="password" maxlength="50" class="input-block-level" placeholder="password..."/>
		           <label class="checkbox">
		            <input type="checkbox" value="Remember Me"/>Remember Me     
		           </label>
		           <input type=button class="btn btn-large btn-success"  onclick="verifySubmit()" value="Sign In"/>&nbsp;&nbsp;
		           <a href="<%=basePath%>/member/findPassword" style="text-decoration:underline;">Forgot your password?</a>
		           <p align="right">&nbsp;&nbsp;<a href="<%=basePath %>/member/register" style="text-decoration:underline;">Register now</a></p>
		      </form>
      </div>
    </div>
    

    <div class="span12 offset5">     
         <h4>Sign in help</h4>           
           &nbsp;&nbsp;Forgot your password?&nbsp;&nbsp;<a href="#" style="text-decoration:underline;">Get Password Help</a>           
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
			  url:"checkEmail",
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
		  url:"checkEmail",
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