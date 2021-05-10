<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,com.macower.sample.entity.*"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%
String path = request.getContextPath();    
//获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:    
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path; 

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>样例列表</title>
	
	
	<script src="<%=basePath %>/assets/js/common/jquery-1.8.2.min.js"></script>
	<script type="text/javascript">
  	  $(document).ready(function(){
  		  
  		  $("#ajax").click(function(){
  			 // var username = $("username").attr("value");
  			  
  			  var params = {username:"wzh",password:"123456"} ;
  			  
  			  $.ajax({
  				  url:"<%=request.getContextPath()%>/sample/ajax",
  				  type:"post",
  				  data:params,
  				  success:function(data){
  					// var resp  =  eval(data);
  					  alert(data);
  				  }
  			  });
  			  
  		  });
  		  
  	  });
  		
	</script>
</head>
<body>
	session中的对象：${loginUser.username }
	<br/>
	
	<a href="../add">Add</a>-->--${redirect_attr }
	<br/>
	<a href="<%=request.getContextPath()%>/sample/add">Add2</a>
	<br />
	
	<br/>
	<input id="ajax" type="button" value="ajax" />
	<br/>
	<%
	List<Sample> list = (List<Sample>)request.getAttribute("samples") ;
	for(Sample s : list){
		%>
		id:<%=s.getId() %>-----username:<%=s.getUsername() %>-----password:<%=s.getPassword() %>-----email:<%=s.getEmail() %>
		<a href="<%=basePath %>/sample/<%=s.getId() %>/show">查看</a>
		<br/>
		<%
	} //end for
	%>
</body>
</html>