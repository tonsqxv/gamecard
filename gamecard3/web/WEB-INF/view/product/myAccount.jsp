<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@include file="../common/taglibs.jsp" %>
<%
	String path = request.getContextPath();    
	//获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:    
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path; 
%>
<!DOCTYPE html>
<html lang="en">
 <head>
	
 </head>
 <body>
  <%@include file="head.jsp" %>

    <div class="container">
      <div class="row">

    	<div class="col-lg-3" >  		
    		  <%@include file="myAccount4Left.jsp" %>      
         </div><!--/span2-->

    	<div class="col-lg-9">
		  <ul class="breadcrumb">
		      <li><a href="<%=basePath %>/index">Home</a> <span class="divider">&gt;&gt; My Orders</span></li>
		  </ul>
          <h4><font color="orange">My Account</font></h4>
    		  	
          <table class="table table-bordered table-striped">
            <colgroup>
              <col class="span1">
              <col class="span7">
            </colgroup>
            <thead>
              <tr>
                <th>Item</th>
                <th>Description</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>
                  Email Address
                </td>
                <td>${member.email }</td>
              </tr>
              <tr>
                <td>
                  First Name
                </td>
                <td>${member.firstName }</td>
              </tr>
              <tr>
                <td>
                  First Name
                </td>
                <td>${member.lastName }</td>
              </tr>
              <tr>
                <td>
                  Password
                </td>
                <td>******</td>
              </tr>
              <tr>
                <td>
                  Tel No.
                </td>
                <td>${member.phoneNumber }</td>
              </tr>
            </tbody>
          </table>

          <hr>

    		</div><!--/span8-->

      </div><!-- row-fluid -->


    </div><!-- container-fluid -->
  
  </body>
</html>