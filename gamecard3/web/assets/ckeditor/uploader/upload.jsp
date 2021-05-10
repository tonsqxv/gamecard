<%@ page session="true"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<TITLE>Update Manager Password</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=utf-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		</head>
<body>
<%
	String callback = request.getParameter("CKEditorFuncNum");//获取回调JS的函数Num
	String realPath = (String)request.getAttribute("realPath");
	out.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("+callback+",'"+realPath+"')</script>");
 %>
</body>
</html>