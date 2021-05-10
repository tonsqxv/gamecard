<%@page import="java.io.File"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>图片浏览</title>
<script type="text/javascript">
//这段函数是重点，不然不能和CKEditor互动了
function funCallback(funcNum,fileUrl){
	var parentWindow = ( window.parent == window ) ? window.opener : window.parent;
	parentWindow.CKEDITOR.tools.callFunction(funcNum, fileUrl);
	window.close();
}
</script>
</head>
<body>
<%
	String path = request.getContextPath() + "/";
	String type = "";
	if(request.getParameter("type") != null)//获取文件分类
		type = request.getParameter("type").toLowerCase() + "/";
	String clientPath = "public/" + type;
	File root = new File(request.getSession().getServletContext().getRealPath(clientPath));
	if(!root.exists()){
		root.mkdirs();
	}
	String callback = request.getParameter("CKEditorFuncNum");
	File[] files = root.listFiles();
	if(files.length > 0){
		for(File file:files ) {
			String src = path + clientPath + file.getName();
			if("images/".equalsIgnoreCase(type)){
				out.println("<img width='110px' height='70px' src='" + src + "' title='" + file.getName() +"' alt='" + file.getName() + "' onclick=\"funCallback("+callback+",'"+ src +"')\">");
			}else if("flashs/".equalsIgnoreCase(type)){
				out.println("<button style='width:110px; height:70px;background:#EEFFEE; border:1px; padding:0px; cursor:hand' onclick=\"funCallback("+callback+",'"+ src +"')\">"+				
				"<EMBED width='110px' height='70px' autostart='false' wmode='transparent' src='" + src + "' title='" + file.getName() +"' alt='" + file.getName() + "')\"></button>");
			}
		}
	}else{
		out.println("<h3>未检测到资源。</h3>");
	}
 %>
</body>
</html>