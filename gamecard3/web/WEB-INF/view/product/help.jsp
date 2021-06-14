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
    		<div class="col-lg-3">
   					<%@include file="left.jsp" %>
   			</div><!--/span2-->
    		<div class="col-lg-9">
    			<ul class="breadcrumb">
		    		  <li><a href="<%=basePath %>/index">Home</a> <span class="divider">&gt;&gt; help</span></li>
		    	</ul>
	          	<h4><font color="orange">Frequently Asked Questions</font></h4>
	          	<hr>
    		  	<div>
    		  		<h5>1、Q; How to fix the R4 DS loading screen?</h5>
      	 				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;A: http://www.youtube.com/watch?v=urWFeHRZby8</p>
      	 				<br/>
      	 			<h5>2、Q: NDS/DSi/3DS console screen said: “The r4 card id fake".</h5>
      	 				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;A: Please have a try according to following two methods:</p>
      	 				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ⅰ、Format the SD card again, then update the stuff into sd card, to see whether it is normal.</p>
      	 				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ⅱ、please click here to have a look:</p>
      	 				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;http://www.youtube.com/watch?v=urWFeHRZby8</p>
      	 				<br/>
      	 			<h5>3、Q: If flash card has been inserted to console, but screen does not automatically jump to the system interface, then what can I do?</h5>
      	 				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; A: Press “A” and press “A” more again.</p>
      	 				<br/>
      	 			<h5>4、 Q: Whether I need to update the DSTWO firmware, if 3DS is the latest version v4.1.0-8?</h5>
      	 				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;A: Yes, you have to update the firmware V1.15. (You can download the firmware v1.15 from our "downloads" page).</p>
      	 				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Before updating the firmware, you need to confirm the kernel EOS V1.11 in your sd card. (You can also download v1.11 from our "downloads" page).</p>
      	 				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Update steps:</p>
      	 				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Step 1: Download the kernel EOS V1.11.</p>
      	 				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Step 2: Download the firmware V1.15.</p>
      	 				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Step 3: Unzip firmware v1.15 and copy the upgrade file such as "dstwoupdate.dat" to Micro SD.</p>
      	 				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Step 4: Find a DS console (NDS\DS\DSLite\DSi\3DS) which can run this EOS system, if you have the latest version console can not run EOS system, sorry, you have to find one.</p>
      	 				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Step 5: Turn on the power, EOS will detect the upgrade file, press A to upgrade.</p>
      	 				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Then DSTWO can update the latest 3DS V4.1.0-8.</p>
      	 				
      	 				<br/>	
    		  	</div>
    		</div><!--/span8-->

      </div><!-- row-fluid -->

    </div><!-- container-fluid -->
    	
  </body>
</html>