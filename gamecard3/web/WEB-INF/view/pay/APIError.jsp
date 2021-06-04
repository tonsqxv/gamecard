
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.paypal.soap.api.*" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();    
	//获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:    
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path; 
	AbstractResponseType ppresponse = (AbstractResponseType) session.getAttribute("response");
	session.removeAttribute("response") ;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>n3ds-card</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="<%=basePath %>/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <!-- 自适应 -->
    <link href="<%=basePath %>/assets/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
    
    <script src="<%=basePath %>/assets/js/common/jquery-1.8.2.min.js"></script>
    <script src="<%=basePath %>/assets/bootstrap/js/bootstrap.min.js"></script>

     <style type="text/css">      
      body {
        padding-top: 60px;/*body距离顶部底部距离*/
        padding-bottom: 40px;
      }      
    </style>
 </head>
 <body>

    <div class="container">
      <div class="row">
    	<div class="col-lg-8 col-lg-offset-2">
          <table class="table table-bordered table-striped">
              <thead>
              <tr>
                <th>error</th>
                <th>Description</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>
                  Ack:
                </td>
                <td><font color="red"><%= ppresponse.getAck() %></font></td>
              </tr>
              <tr>
                <td>
                  Correlation ID:
                </td>
                <td><%= ppresponse.getCorrelationID() %></td>
              </tr>
              <tr>
                <td>
                  Version:
                </td>
                <td><%= ppresponse.getVersion() %></td>
              </tr>
              <%
					for (int i = 0; i < ppresponse.getErrors().length; i++) {
				%>
			   <tr>
                <td>
                 Error Number:
                </td>
                <td><%= ppresponse.getErrors(i).getErrorCode() %></td>
              </tr>
			   <tr>
                <td>
                  Short Message:
                </td>
                <td><%= ppresponse.getErrors(i).getShortMessage() %></td>
              </tr> 
			 
			   <tr>
                <td>
                 Long Message:
                </td>
                <td><font color="red"><%= ppresponse.getErrors(i).getLongMessage() %></font></td>
              </tr>
              <%
					}
				%>
            </tbody>
          </table>
          
    		</div><!--/span8-->

      </div><!-- row-fluid -->


    </div><!-- container-fluid -->
  
  </body>
</html>