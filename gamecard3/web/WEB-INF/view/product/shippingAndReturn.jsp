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
    
    <div class="container">
      <div class="row">
    		<div class="col-lg-2 col-lg-offset-1" >
   					<%@include file="left.jsp" %>
   			</div><!--/span2-->
    		<div class="col-lg-8">
    			  <ul class="breadcrumb">
		    		  <li><a href="<%=basePath %>/index">Home</a> <span class="divider">&gt;&gt; Shipping&amp;Returns</span></li>
		    	  </ul>
		          <h3><font color="orange">Shipping Methods</font></h3>
		          <hr>
				  <h4><font color="orange">Shipping</font></h4>
				  <p><b>ShippingFree Shipping on All Orders</b> to Worldwide. (All orders are free shipping)</p>
				  <p>All orders will be <b>shipped out within 1 workday.</b></p>
				  
				  <hr>
				  <h4><font color="orange">Shipping Methods</font></h4>
				  <p>We currently offer 3 shipping methods for you to choose. However, some of the shipping methods may not be available in your country. The shipping rates may also vary depending on your country of residence. Please read below for more information:
				  </p>
				  <p>&diams;&nbsp;&nbsp;&nbsp;Order <b>under USD 39.99</b> will be shipped free of charge by <b>HongKong Air Mail</b> (No Tracking#).</p>
				  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Weight is less than 2KG.</p>
				  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;You may receive your order within <b>7 to 35 days</b>.</p>
				  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;You can pay extra USD 2.00 to upgrade to <b>Hong Kong Registered Air Mail</b>.</p>
				  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;You can pay extra USD 20.00 or 25.00 to upgrade to <b>DHL / UPS / EMS</b>.</p>
				  
				  <p>&diams;&nbsp;&nbsp;&nbsp;Order between <b>USD 39.99-99.99</b> will be shipped free of charge by <b>Hong Kong Registered Air Mail</b> (Tracking No. Available).</p>
				  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Weight is less than 2KG.</p>
				  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;You may receive your order within <b>7 to 35 days</b>.</p>
				  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;You can pay extra USD 18.00 or 23.00 to upgrade to <b>DHL / UPS / EMS</b>.</p>
				  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tracking Website: <a href="#" onclick="javascript:window.open('http://app3.hongkongpost.com/CGI/mt/enquiry.jsp');">http://app3.hongkongpost.com/CGI/mt/enquiry.jsp</a></p>
				  
				  <p>&diams;&nbsp;&nbsp;&nbsp;Order <b>up USD 99.99</b> will be shipped free of charge by <b>DHL / UPS / EMS / Aramex Express</b> </p>
				  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(Tracking No. and detailed tracking information Available). </p>
				  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Note：<font color="red">DSTWO is not included</font> because of its high cost and little profit.</p>
				  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hope you can understand us.</p>
				  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;DHL and UPS Express are the fastest methods.</p>
				  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;You may receive your order within <b>3 to 7 workdays</b>.</p>
				  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;If your geographical is considered "Remote Area" by UPS or DHL, we will automatically revert to </p>
				  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;EMS, which could take slightly longer time, usually 5 to 7 workdays. </p>
				  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>DHL</b>: <a href="#" onclick="javascript:window.open('http://www.dhl.com/en.html');">http://www.dhl.com/en.html</a></p>
				  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>UPS</b>: <a href="#" onclick="javascript:window.open('http://www.ups.com/');">http://www.ups.com/</a></p>
				  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Aramex</b>: <a href="#" onclick="javascript:window.open('http://www.aramex.com/');">http://www.aramex.com/</a></p>
				  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Fedex</b>: <a href="#" onclick="javascript:window.open('http://www.fedex.com/');">http://www.fedex.com/</a></p>
				  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>EMS</b>: <a href="#" onclick="javascript:window.open('http://www.ems.com.cn/english.html');">http://www.ems.com.cn/english.html</a>  (some place might take 10 days)</p>
				  
				  <hr>
				  <h4><font color="orange">Track Package Online</font></h4>
				  <p><strong>1.&nbsp; When online tracking information is available ?</strong></p>
				  <ul>
						<li>Hongkong Registered Airmail - 3 days after being shipped;</li>
						<li>DHL Express / UPS / Fedex - 12 ~ 24 hours after being shipped;</li>
						<li>EMS Express - 1 ~ 2 days after being shipped.</li>
				  </ul>
				  <p><strong>2. Where to get my tracking number ?</strong></p>
				  <ul>
						<li>At <a href="http://www.n3ds-card.com/member/toMyAccount">your account</a> at N3DS-CARD;</li>
						<li>From your order status email sent automatically by our system to your registered email address;</li>
						<li><a href="http://www.n3ds-card.com/help/contact">Contact us</a> if you order it as a guest.</li>
				  </ul>
				  <p><strong>3. Where to track my package ?</strong></p>
				  <ul>
						<li>Hongkong Registered Airmail -&nbsp; <a href="http://app3.hongkongpost.com/CGI/mt/enquiry.jsp" target="_blank">http://app3.hongkongpost.com/CGI/mt/enquiry.jsp</a></li>
				  </ul>
				  <img alt="" src="<%=basePath%>/assets/icos/product/shippingSearch.jpg">
				  <p></p>
				  <ul>
						<li>DHL Express -&nbsp; <a href="http://www.dhl.com/en/express/tracking/shippers_reference.html" target="_blank">http://www.dhl.com/en/express/tracking/shippers_reference.html</a></li>
				  </ul>
				  <img alt="" src="<%=basePath%>/assets/icos/product/DHL-shipping.jpg">
				  <p></p>
				  <ul>
						<li>EMS Express -&nbsp; <a href="http://www.ems.com.cn/english-main.jsp" target="_blank">http://www.ems.com.cn/english-main.jsp</a></li>
				  </ul>
				  <ul>
						<li>UPS Express -&nbsp; <a href="http://www.ups.com/" target="_blank">http://www.ups.com/</a></li>
				  </ul>
				  <ul>
						<li>Fedex Express -&nbsp; <a href="http://www.fedex.com/" target="_blank">http://www.fedex.com/</a></li>
				  </ul>
					
				  <hr>
				  <h4><font color="orange">Delivery time Note</font></h4>
				  <p>&Delta;&nbsp;&nbsp;&nbsp;<b>Hongkong Surface / Registered Airmail</b></p>
				  <p>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;&nbsp;United States, Canada: 8-20 business days after shipment</p>
				  <p>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;&nbsp;Australia, New Zealand, Singapore: 5-12 business days </p>
				  <p>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;&nbsp;United Kingdom, France, Spain, Germany, Netherlands, Japan, Belgium, Denmark, Finland, Ireland, </p>
				  <p>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;&nbsp;Norway, Portugal, Sweden, Switzerland: 8-20 workdays</p>
				  <p>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;&nbsp;Italy, Brazil: 10-25 workdays.</p>
				  <p>&nbsp;&nbsp;&nbsp;&nbsp;&bull;&nbsp;&nbsp;Other countries: 8-30 business days.</p>
				  
				  <p>&Delta;&nbsp;&nbsp;&nbsp;<b>DHL / UPS / Fedex Express</b> - 3 ~ 5 days for most countries</p>
				  <p>&Delta;&nbsp;&nbsp;&nbsp;<b>EMS Express </b>- 7 ~ 10 days for most countries</p>
				  
				  <hr>
				  <h3><font color="orange">Warranty &amp; Returns Policy</font></h3>
				  <hr>
				  <h4><font color="orange">Warranty</font></h4>
				  <ul>
				  		<li>
							<span style="font-style: italic; color: #ff0000;">6 Months Quality Warranty</span>
							-- If any quality problem with products within 6 months quality warranty, can get them replaced for the same product with the same version for free. Customer is responsible for shipping-back fee. (
							<span style="font-weight: bold; color: #ff0000;">Note</span>
							: (1) Flashcart version update is
							<span style="font-weight: bold;">not</span>
							in 6 months quality warranty. For example, you bought and ran a R4i-Gold V1.4 well and now R4i-gold.com changed its version into R4i-gold V1.4.1, former R4i-Gold V1.4 does not support DSi FW 1.4.1, n3dscart.com will not have R4i-Gold v1.4 replaced with R4i-gold V1.4.1 for you; (2) If the flashcart has been out of production, the flashcart will automatically be out of 6 Months Quality Warranty and it is qualified with one Month Quality Warranty )
					</li>
				  </ul>
				  
				  <hr>
				  <h4><font color="orange">Returns Policy</font></h4>
				<ul>
				 	<li>Please make sure that you understand our return policy and ensure that all criteria are met before physically sending any items back to n3ds-card.com. </li>
				</ul>
				<ul>
					<li>
					Please note that we cannot process any products that are returned without our prior knowledge. If you wish to return any or all parts of your order, you can
					<a href="http://www.n3ds-card.com/member/login">login</a>
					your account, submit a request and obtain a "RMA Requests" Form that must be included with your package. Or
					<a href="http://www.n3ds-card.com/help/contact">contact us</a>
					directly.
					</li>
				</ul>
				<ul>
					<li>
					<span style="font-style: italic;">Quality Issues</span>
					-- If a product has quality problems within 7 days time which starts from the day of customers receiving it, n3ds-card.com will be responsible for exchanging your item or returning for a full refund. After confirming that the product has not been damaged on purpose, we will send a full refund or exchange the product for free.
					</li>
				</ul>
				<ul>
					<li>
					<span style="font-style: italic;">Non Quality Issues</span>
					-- If customers wish to return an item for non quality reasons within 7 days after receiving the product, we will return 85% the full price (15% will be withheld to cover the shipping fee).
					</li>
				</ul>
				<ul>
					<li>Returned products should be in original condition with original packaging. If an accessory or the original item was lost during the process of returning, we will deduct the price of the lost item from the refund.</li>
				</ul>
				 
			 	<hr>
			  	<h3><font color="orange">Return Guide</font></h3>
			  	<hr>
			  	<p>If you need to return your order for getting replacement / refund , we hope you see instructions below for how to return a package.</p> 
				<hr>
				<h4><font color="orange">Instructions</font></h4>
				<p>
					<span style="color: #99cc00;">
						<span style="font-size: large;">
							<strong>1 </strong>
						</span>
					</span>
					If you need to return your order for any reason, please
					<a href="http://www.n3ds-card.com/help/contact">contact us</a>
					and	tell us why. We will do our very best to resolve any problem caused by us or our shipper.
				</p>
				<p>
				<span style="font-size: large;">
					<span style="color: #99cc00;">
					<strong>2 </strong>
					</span>
				</span>
				Please make sure the item was originally purchased from n3dscart.com. Please ensure the item wasn’t suffered damage by you.
				</p>
				<p>
					<strong>
						<span style="font-size: large;">
						<span style="color: #99cc00;">3 </span>
						</span>
					</strong>
					In order to save the shipping fee and avoid customers clearance , we suggest you input the 3ds/r4/r4i cards in a simple envelope.
					<strong>  Ship out via Surface AirMail or Registered AirMail</strong>
					.
				</p>
				<p>
					<strong>a)</strong>
					If conditions permit, please print a partial eamils record and highlight the order ID . Or prepare for a piece of paper and write down your Order ID. With this order ID, we can handle your case quicker.
				</p>
				<p>
					<strong>b）</strong>
					Except 3DS Card / R4 ds card /r4i dsi cards, do not insert any packaging, accessories or anything else if it is for replacement. Please also attch the cards to the paper.
					<strong>If it is for full refund, please return the whole pacakges</strong>
					.
				</p>
				  
    		</div><!--/span8-->

      </div><!-- row-fluid -->

    </div><!-- container-fluid -->
 
  </body>
</html>