<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();    
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path; 

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

	<!-- Ext -->
 	<link rel="stylesheet" type="text/css" 	href="<%=basePath %>/assets/ext-3.4.0/resources/css/ext-all.css" />
	<script type="text/javascript" 	src="<%=basePath %>/assets/ext-3.4.0/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="<%=basePath %>/assets/ext-3.4.0/ext-all.js"></script>
	<script type="text/javascript" src="<%=basePath %>/assets/ext-3.4.0/src/locale/ext-lang-zh_CN.js"></script>
	
	<!-- common -->
	<link rel="stylesheet" type="text/css" 	href="<%=basePath %>/assets/css/common/ext-common.css" />
	<script type="text/javascript" src="<%=basePath %>/assets/js/common/ext-common.js"></script>
	<script type="text/javascript" src="<%=basePath %>/assets/js/common/util.js"></script>
	
	<!-- my js file -->
	<script type="text/javascript" src="<%=basePath %>/assets/js/businessdata/member.js"></script>
	
</head>
<body>
</body>
</html>