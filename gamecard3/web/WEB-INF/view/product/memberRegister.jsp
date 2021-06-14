<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();    
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path; 

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <script src="<%=basePath %>/assets/js/common/util.js"></script>

  </head>

  <body >
  <div class="container">
  
  	<form id="memberRegisterForm" action="memberRegister" method="post"> 
	<div class="panel panel-warning">
		<div class="panel-heading">
			type your base info
		</div>
		<div class="panel-body">
			 <div class="row form-group">
		              <span class="control-label col-lg-2 col-lg-offset-1">My e-mail<font color="red">&nbsp;*</font>:</span>
		              <div class="col-lg-3">
		                 <input class="form-control input-sm" type="text" maxlength="50" id="email"  name="email">
		              </div>
		              <span id="email-help" class="col-lg-5"></span>
		     </div>
		     <div class="row form-group">
		              <span class="control-label col-lg-2 col-lg-offset-1">Your password<font color="red">&nbsp;*</font>:</span>
		              <div class="col-lg-3">
		                 <input class="form-control input-sm" id="password"  name="password" type="password" maxlength="50">
		              </div>
		              <span id="password-help" class="col-lg-5"></span>
		     </div>
		     <div class="row form-group">
		              <span class="control-label col-lg-2 col-lg-offset-1">Type it again<font color="red">&nbsp;*</font>:</span>
		              <div class="col-lg-3">
		                 <input class="form-control input-sm" id="repassword" type="password" maxlength="50">
		              </div>
		              <span id="repassword-help" class="col-lg-5"></span>
		     </div>
		     <div class="row form-group">
		              <span class="control-label col-lg-2 col-lg-offset-1">First name<font color="red">&nbsp;*</font>:</span>
		              <div class="col-lg-3">
		                 <input class="form-control input-sm" type="text" maxlength="50" id="firstName" name="firstName">
		              </div>
		              <span id="firstName-help" class="col-lg-5"></span>
		     </div>
		     <div class="row form-group">
		              <span class="control-label col-lg-2 col-lg-offset-1">Last name<font color="red">&nbsp;*</font>:</span>
		              <div class="col-lg-3">
		                 <input class="form-control input-sm" type="text" maxlength="50" id="lastName" name="lastName">
		              </div>
		              <span id="lastName-help" class="col-lg-5"></span>
		     </div>
		     <div class="row form-group">
		              <span class="control-label col-lg-2 col-lg-offset-1">Mobile phone (Optional):</span>
		              <div class="col-lg-3">
		                 <input class="form-control input-sm" type="text" maxlength="30" id="phoneNumber" name="phoneNumber">
		              </div>
		              <span id="phoneNumber-help" class="col-lg-5"></span>
		     </div>
		     <div class="row form-group">
		              <div class="col-lg-3 col-lg-offset-3">
		                  <button class="btn btn-lg btn-primary btn-block" type="button" onclick="verifySubmit()">Register</button>
		              </div>
		     </div>
		 </div><!-- end panel-body -->
	</div>
	</form>

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


