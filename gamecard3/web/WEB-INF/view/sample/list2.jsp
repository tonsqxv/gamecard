<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>样例列表</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/sample/sample.css">
</head>
<body>
	session中的对象：${loginUser.username }
	<br/>
	
	<a href="add">Add</a>-->--${redirect_attr }
	<br/>
	<a href="<%=request.getContextPath()%>/sample/add">Add2</a>
	<br />
	<c:forEach items="${samples }" var="sample">
		<a href="${sample.value.username }">${sample.value.username }</a>--------
	${sample.value.password }----${sample.value.email }
	<a href="${sample.value.username }/update">更新</a>
		<a href="${sample.value.username }/delete">删除</a>
		<br />
	</c:forEach>
	
</body>
</html>