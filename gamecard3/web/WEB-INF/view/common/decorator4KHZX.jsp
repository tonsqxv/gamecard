<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@include file="taglibs.jsp" %>

<%
	//获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:    
	String basePathforHead = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath(); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>n3ds-card<sitemesh:write property='title'/></title>
	<!-- n3ds-card  mobledeal -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="google-translate-customization" content="1e73dcaa81aa4ac7-61a6e2a043015e7a-g53185def3341f4dc-11"></meta>
   
    <!-- 自定义css -->
    <link href="<%=basePathforHead %>/assets/css/product/common.css" rel="stylesheet">
     <!-- Bootstrap -->
    <link href="<%=basePathforHead %>/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="<%=basePathforHead %>/assets/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
 	
 	<script src="<%=basePathforHead %>/assets/js/common/jquery-1.8.2.min.js"></script>
    <script src="<%=basePathforHead %>/assets/bootstrap/js/bootstrap.min.js"></script>
    
    
	<script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
    <script type="text/javascript" language="javascript">
    function googleTranslateElementInit() {
    	  new google.translate.TranslateElement({pageLanguage: 'en', layout: google.translate.TranslateElement.InlineLayout.SIMPLE, autoDisplay: false, multilanguagePage: true}, 'google_translate_element');
    	}
    
	function addToCart(productId,amount,unitPrice,color,size){
   		$.ajax({
   				type:"post",
   				dataType : "json",  
		        url:"<%=basePathforHead %>/shopItem/add",
		        data: {"productId":productId,"amount":amount,"unitPrice":unitPrice,"color":color,"size":size},
		        success:function(data){
		    	   if(data.success == 'ok'){
		    		 //加入成功后跳转到我的购物车
		    		  window.location.href="<%=basePathforHead %>/product/toMyCart" ;
		    	   }
		       }  		    	   
		});    		
   	}
	</script>  
     <style type="text/css">      
      body {
        //padding-top: 60px;/*body距离顶部底部距离*/
        padding-bottom: 40px;
      }      
    </style>
    <sitemesh:write property='head'/>
  </head>
  <body>
  
	<!-- Head Body -->
	<nav class="navbar navbar-default navbar-inverse" role="navigation">
	 <!-- Brand and toggle get grouped for better mobile display -->
	  <div class="navbar-header">
	    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
	      <span class="sr-only">Toggle navigation1</span>
	      <span class="icon-bar"></span>
	      <span class="icon-bar"></span>
	      <span class="icon-bar"></span>
	    </button>
	    <a class="navbar-brand" href="<%=basePathforHead %>/index">N3DS-CARD</a>
	  </div>
	  <div class="collapse navbar-collapse navbar-ex1-collapse">
		    <ul class="nav navbar-nav">
				  <li><img src="<%=basePathforHead %>/assets/icos/product/logo.png"></li>
				  <li class="active"><a href="<%=basePathforHead %>/index">Home</a></li>
				  <li>
				      <a href="<%=basePathforHead%>/member/toMyAccount">
				          <c:if test="${!empty member}">welcome！${member.lastName }&nbsp;${member.firstName }</c:if>
				      </a>
				  </li>
		    </ul>
		    <p class="navbar-text pull-right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
		               
		               
	        <p class="navbar-text pull-right">
					<c:if test="${empty member}">
						<a href="<%=basePathforHead %>/member/login" class="navbar-link">Sign in</a>&nbsp;&nbsp;
						<a href="<%=basePathforHead %>/member/register" class="navbar-link">Register</a>&nbsp;&nbsp;
					</c:if>
					<a href="<%=basePathforHead %>/order/trackShipment" class="navbar-link">Track shipment</a>&nbsp;&nbsp;
					<a href="<%=basePathforHead %>/product/toMyCart" class="navbar-link">MyCart</a>&nbsp;&nbsp;
	           		<a href="<%=basePathforHead %>/order/toMyOrder" class="navbar-link">MyOrders</a>&nbsp;&nbsp;
	           		<a href="<%=basePathforHead %>/member/toMyAccount" class="navbar-link">MyAccount</a>&nbsp;&nbsp;
	            	<c:if test="${!empty member}">
	            		<a href="<%=basePathforHead %>/member/logout" class="navbar-link">Sign out</a>&nbsp;&nbsp;
	            	</c:if>
	        </p>
	        <p class="navbar-text pull-right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
	        <div class="pull-right" style="padding-top:8px;" id="google_translate_element" ></div>
	    
	  </div><!-- /.navbar-collapse -->
	</nav>
    
    <!-- Main Body -->
    <div>
      <sitemesh:write property='body'/>
    </div>

	<!-- Foot Body -->
    <div>
    	<hr/>
    	<div class="text-center" >
    	<img alt="" src="<%=basePathforHead %>/assets/icos/product/bottomlogo1.jpg">
    	</div>
	    <footer>
	      <p class="text-center">All prices are in USD. &copy; Copyright 2013 n3ds-card.com. Sitemap | Powered by n3ds-card.com      
	      <script src="http://s22.cnzz.com/stat.php?id=5536743&web_id=5536743&show=pic" language="JavaScript"></script></p>
	      <!--  
		  <script type='text/javascript' src='http://www3.365webcall.com/IMMe1.aspx?settings=mw7NNb7NNN670N7z3ANmmmbPz3ANmwIIwz3AN6mmP0&LL=1'></script>
		  -->
		  </footer>
	</div>
  </body>
</html>