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
		    		<div class="well sidebar-nav">
			            <ul class="nav nav-list">
			              <li ><a href="<%=basePath%>/member/toMyAccount">MyAccount</a></li>
			              <li ><a href="<%=basePath%>/order/toMyOrder">MyOrders</a></li>
			              <c:if test="${!empty member }">
			              <li><a href="<%=basePath%>/member/changePassword">Change Password</a></li>
			              </c:if>
			            </ul>
		          </div><!--/.well -->
		              
       		 </div><!--/span2-->

    	 <div class="span8">
		  <ul class="breadcrumb">
		      <li><a href="<%=basePath %>/index">Home</a> <span class="divider">&gt;&gt; Change Password</span></li>
		  </ul>
          <h4><font color="orange">Change Password</font></h4>
          <table class="table table-bordered table-condensed">
            <tbody>
              <tr>
                <td >
                      <div class="span12">
                        <form id="changePasswordForm" class="form-horizontal" action="<%=basePath %>/member/changMyPassword" method="post">
                          <div class="control-group">
                            <label class="control-label" for="error"></label>
                            <div class="controls">
                            	<font color="red"><span id="error">${error}</span></font>
                            </div>
                          </div>
                          <div class="control-group">
                            <label class="control-label" for="oldpassword">Password:</label>
                            <div class="controls">
                              <input type="password" id="oldpassword" name="oldpassword" placeholder="password">
                            </div>
                          </div>
                          <div class="control-group">
                            <label class="control-label" for="newpassword">New Password:</label>
                            <div class="controls">
                              <input type="password" id="newpassword" name="newpassword" placeholder="new password">
                            </div>
                          </div>
                          <div class="control-group">
                            <label class="control-label" for="newpassword2">Confirm Password:</label>
                            <div class="controls">
                              <input type="password" id="newpassword2" name="newpassword2" placeholder="Confirm password">
                            </div>
                          </div>
                          <div class="control-group">
                            <div class="controls">                              
                              <img alt="" src="<%=basePath%>/assets/icos/product/btnSubmit.jpg" onclick="javascript:submitForm();">
                            </div>
                          </div>
                        </form>
                      </div>
                     
              </tr>              
             
            </tbody>
          </table>

    		  
    		</div><!--/span8-->

      </div><!-- row-fluid -->


    </div><!-- container-fluid -->
  <script type="text/javascript">   
  
  function submitForm(){
	  var oldpassword = trim($("#oldpassword").val());	         
	    if(oldpassword == ""){
		   	 $("#error").html("<i class='icon-remove'></i>Please input your password");
		   	return false;
	    }
	    var newpassword = trim($("#newpassword").val());	         
	    if(newpassword == ""){
		   	 $("#error").html("<i class='icon-remove'></i>Please input your newpassword");
		   	return false;
	    }
	    var newpassword = trim($("#newpassword").val());	         
	    if(newpassword == ""){
		   	 $("#error").html("<i class='icon-remove'></i>Please input your new password");
		   	return false;
	    }
	    
	    var newpassword2 = trim($("#newpassword2").val());	         
	    if(newpassword2 == ""){
		   	 $("#error").html("<i class='icon-remove'></i>Please input your new password again");
		   	return false;
	    }
	    if(newpassword != newpassword2){
	    	 $("#error").html("<i class='icon-remove'></i>Please Check that your new password match and try again");
			   	return false;
	    }
	  $("#changePasswordForm").submit() ;
  }
  </script>
  </body>
</html>