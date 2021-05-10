<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();    
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path; 

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script src="<%=basePath %>/assets/js/common/util.js"></script>
	<style type="text/css">
		#wrap1{
		background-color: #F5F5F5;
		}
		
		.font-strong{
		color:red;
		font-weight:bold;
		font-family:"宋体";
		}
		 #stepol {list-style: none;}
		.m_step{padding:20px 0px 40px 0px;}
		.m_step li{float:left;}
		.m_step li span{float:left;padding-left:54px;height:39px;background-image:url(<%=basePath %>/assets/icos/member/register/step_bg.png);background-repeat:no-repeat;background-position:0 -120px;color:#999;}
		.m_step li.first span.finished{background-position:0 0;padding-left:43px;}
		.m_step li.first span.finished{background-position:left 0;}
		.m_step li span.finished{background-position:0 -300px;color:#333;}
		.m_step li span.finished strong{background-position:right -60px;}
		.m_step li strong{display:inline-block;*display:inline;*zoom:1;height:39px;padding-right:30px;background-image:url(<%=basePath %>/assets/icos/member/register/step_bg.png);background-repeat:no-repeat;background-position:right -180px;font:bold 13px/38px 'Microsfot Yahei';white-space:nowrap;}
		.m_step li.last strong{background-position:right -240px;}
		.m_step li.last span.finished strong{background-position:right -360px;}
		.m_step li strong em{float:left;font-size:24px;margin:0 12px 0 -20px; font:normal 24px/36px Arial;position:relative;}
		.m_step li .finished strong em{color:#ebb102;}
		
		.border-topdiv{
		    
		    background-color: #efefe;
		    border-bottom: 1px solid #C6DCC6;
		    border-top: 2px solid #68BB68;    
		}
 
</style>
 <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
     <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<%=basePath %>/assets/icos/register/apple-touch-icon-144-precomposed.png">
     <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<%=basePath %>/assets/icos/register/apple-touch-icon-114-precomposed.png">
  	 <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<%=basePath %>/assets/icos/register/apple-touch-icon-72-precomposed.png">
  	 <link rel="apple-touch-icon-precomposed" href="<%=basePath %>/assets/icos/register/apple-touch-icon-57-precomposed.png">
 	 <link rel="shortcut icon" href="<%=basePath %>/assets/icos/register/favicon.png">
  
  </head>

  <body >
  <div class="container ">

 	<div id="main_well" class="span9 offset2  well border-topdiv">
 	<form id="memberRegisterForm" action="memberRegister" method="post"> 
 		<input type="hidden" name="locked" value="0" />
  		<div class="m_step">   
            <ol id="stepol" ><li class="first"><span class="finished"><strong><em>1</em>base info</strong></span></li>
            <li class="last"><span ><strong><em>2</em>finished register</strong></span></li>
			</ol>
 		</div>
		 <div class="row">    
		 		<span id="email-help" class="offset3 span5"></span>
			    <span class="offset1 span2"><label><font color='red'>*</font>My e-mail:</label></span>
			    <span class="span3"><input type="text" class="input-xlarge font-strong " maxlength="50" id="email"  name="email" /></span>
		</div>
		 <div class="row">    
		 		<span id="password-help" class="offset3 span5"></span>
			    <span class="offset1 span2"><label><font color='red'>*</font>Your password:</label></span>
			    <span class="span3"><input id="password"  name="password" type="password" maxlength="50" class="input-xlarge font-strong"/></span>
		</div>  
		 <div class="row">    
		 		<span id="repassword-help"   style="font-color:rgb(51, 165, 200)"  class="offset3 span5 "></span>
			    <span class="offset1 span2"><label><font color='red'>*</font>Type it again:</label></span>
			    <span class="span3"><input id="repassword"    type="password" maxlength="50" class="input-xlarge font-strong" /></span>
		</div> 
		<div class="row">  
				<span id="firstName-help" class="offset3 span5 "></span>	
			    <span class="offset1 span2"><label><font color='red'>*</font>First name:</label></span>
			    <span class="span3"><input type="text" class="input-xlarge font-strong" maxlength="50" id="firstName" name="firstName" /></span>
	    </div>
	    <div class="row">  
	    		<span id="lastName-help" class="offset3 span5 "></span>	 
			    <span class="offset1 span2"><label><font color='red'>*</font>Last name:</label></span>
			    <span class="span3"><input type="text" class="input-xlarge font-strong" maxlength="50" id="lastName" name="lastName"/></span>
	    </div>
	    <div class="row">  
	    		<span id="phoneNumber-help" class="offset3 span5 "></span>	 
			    <span class="offset1 span2"><label><font color='red'></font>Mobile phone (Optional):</label></span>
			    <span class="span3"><input type="text" class="input-xlarge font-strong" maxlength="30" id="phoneNumber" name="phoneNumber"/></span>
	    </div>
		<div class="row"> 
		  <span class="offset2 span1">&nbsp;</span><button type="button" onclick="verifySubmit()" class=" span3 btn btn-large btn-success "><strong>Register</strong></button>
		 </div>
	</form>
 	</div>
</div>
 
  
   <script type="text/javascript">   
   function refreshCode() {
	   $('#checkcodeImg').attr("src","user!checkCode.action?"+Math.random()+new Date());        
   }

   $(document).ready(function(){ 
	   
	  // $("#checkcodeImg").attr("src","user!checkCode.action?"+Math.random()+new Date());
	   
	   $("#email").blur(function(){	verfiyEmail();    });  
	   
	   $("#password").blur(function (){  verfiyPass();  });
	   
	   $("#repassword").blur(function (){  verfiyRePass();    });
	   
	   $("#firstName").blur(function (){  verfiyFirstName();  });
	   
	   $("#lastName").blur(function (){   verfiyLastName();  });
	   
	   });   
   
function verfiyEmail(){
	 
	var email = trim($("#email").val());		         
    if(email == ""){
	   	 $("#email-help").html("<i class='icon-remove'></i><font color='red'>Please input your e-mail</font>");
	   	return false;
    }
    var rule=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	   if (!rule.test(email))  {
	     $("#email-help").html("<i class='icon-remove'></i><font color='red'>email formate error</font>");  		  	        
	        return false;  
	    }       
	   
   	$.ajax({
		  type:"post",
		  cache:false,    
		  dataType : "json",
		  url:"checkEmail",
		  data: {"email":email},
		  success:function(data){
			  if(data.success == "ok"){	    		
	    		   $("#email-help").html("<i class='icon-remove'></i><font color='red'>email has been registered</font>");	    		    
	    		   return false;
	    	   }else{	    		  	    		 
	    		   var html = "<i class='icon-ok icon-green'></i>";
	    		   $("#email-help").html("");
	    		   return true; 
	    	   }
		  }
	  });
}

function verfiyPass(){	
	   var pwd = trim($("#password").val());		  
	   if(pwd=="" ){
		   var html = "<i class='icon-remove'></i><font color='red'>Please input your new password</font>";
		   $("#password-help").html(html);
		   return false;
	   }else{
		   var html = "<i class='icon-ok'></i>";
		   $("#password-help").html("");
		   return true;
	   }
}

function verfiyRePass(){
	   var pwd = trim($("#password").val());
	   var repwd = trim($("#repassword").val());
	   if(repwd== ""){
		   var html = "<i class='icon-remove'></i><font color='red'>Please input your confirm password</font>";
		   $("#repassword-help").html(html);
		   return false;
	   }
	   if(pwd!=repwd){
		   var html = "<i class='icon-remove'></i><font color='red'>Please Check that your passwords match and try again</font>";
		   $("#repassword-help").html(html);
		   return false;
	   }else{
		   var html = "<i class='icon-ok'></i>";
		   $("#repassword-help").html("");
		   return true;
	   }
}
//校验姓
function verfiyFirstName(){	
	 var firstName = trim($("#firstName").val());		  
	   if(firstName=="" ){
		   var html = "<i class='icon-remove'></i><font color='red'>Please input your first name</font>";
		   $("#firstName-help").html(html);
		   return false;
	   }else{
		   var html = "<i class='icon-ok'></i>";
		   $("#firstName-help").html("");
		   return true;
	   }
}

//校验名字
function verfiyLastName(){	
	 var lastName = trim($("#lastName").val());		  
	   if(lastName=="" ){
		   var html = "<i class='icon-remove'></i><font color='red'>Please input your last name</font>";
		   $("#lastName-help").html(html);
		   return false;
	   }else{
		   var html = "<i class='icon-ok'></i>";
		   $("#lastName-help").html("");
		   return true;
	   }
}

//提交表单
function verifySubmit(){
	var email = trim($("#email").val());		         
    if(email == ""){
	   	 $("#email-help").html("<i class='icon-remove'></i><font color='red'>Please input your e-mail</font>");
	   	return false;
    }
	$.ajax({
		  type:"post",
		  cache:false,    
		  dataType : "json",
		  url:"checkEmail",
		  data: {"email":email},
		  success:function(data){
			  if(data.success == "ok"){	    		
	    		   $("#email-help").html("<i class='icon-remove'></i><font color='red'>email has been registered</font>");	    		    
	    		   return false;
	    	   }else{	    		  	    		 
	    		   var html = "<i class='icon-ok icon-green'></i>";
	    		   $("#email-help").html("");
	    		   
	    		   if(verfiyPass()&&verfiyRePass()&&verfiyFirstName()&&verfiyLastName()){ 
	    			 	$("#memberRegisterForm").submit();
	    			 }	
	    		   return true; 
	    	   }
		  }
	  });
	
}
   </script>     
   </body>
   
</html>


