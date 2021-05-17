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
   					<%@include file="left.jsp" %>
   			</div><!--/span2-->
    		<div class="span8">
		    	  <ul class="breadcrumb">
		    		  <li><a href="<%=basePath %>/index">Home</a> <span class="divider">&gt;&gt; about us</span></li>
		    	  </ul>
				 
		          <h4><font color="orange">About us</font></h4>
				  <h5>Welcome to our store</h5>
				  <p>
					<a href="http://www.n3ds-card.com"><u>www.n3ds-card.com</u></a>, established in 2005, is a professional supplier of Nintendo Flash Cards, we are located in Hua’qiang North, Shenzhen, China, where is considered to be the biggest electronics Market of the world. We only supply genuine Nintendo flash card, including 
					<c:forEach items="${categories}" var="t">
						<u><a onmouseover="color:red" onmouseout=""  href="<%=basePath4left %>/product/${t.id}/listProductByCategory"><i>${t.name }</i></a></u>,
					</c:forEach>
					Kingston and Sandisk  Memory Card and so on.
				  </p>
				  <p>
				  	It is due to the best service that our Team and Products are recognized by our customers from all over the world, we have and still will spare no efforts to supply the best service for more and more customers. In order to give you a good shopping experience, we have designed the very simple website. We hope you can easily buy the cards you need. We are very confident of our cards. If you have any problems and questions when shopping, please don’t hesitate to let us know. We will give you a quickly and satisfied answer as soon as possible. 
				  </p>

		          <h5>Promise</h5>
		          <p>Take it easy to register here, we take serious of the information of our customers, only the specified staffs are allowed to get access to.</p>
				  <p>We only sell Genuine R4i / R4 Cards and other Flash Cards, best in quality and service.</p>
				  <p>All Flash Cards are free shipping to worldwide, what’s more, we will supply a tracking number for your package, and of course, it is free, Nevertheless, the price is competitive.</p>
				  <p>All Products we sell are 100% tested and Firmware updated before shipping, shipped out within 24 hours (except the holidays).</p>
				  <p>If the package not received over 35 days after payment, please contact us, you can get the full refund in 2 workdays or re-shipping the order within 24 hours.</p>
		          
		          <h5>Payment</h5>
		          <p>We can accept Paypal and any other paying methods. the tracking number will upload to Paypal, please to check it for your order.</p>
				 
				  <h5>Have a good shopping</h5>
		          <p>Enjoying your shopping here and have a good day！</p>
		          <p>Kind regards</p>
		          <p>Jack Williams, the N3DS-Card CEO</p>
			     
    		</div><!--/span8-->
    		

      </div><!-- row-fluid -->

    </div><!-- container-fluid -->
 
  </body>
</html>