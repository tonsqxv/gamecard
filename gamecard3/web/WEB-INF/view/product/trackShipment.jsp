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
    		<div class="col-lg-3">
   					<%@include file="left.jsp" %>
   			</div><!--/span2-->
    		<div class="col-lg-9">
    			  <ul class="breadcrumb">
		    		  <li><a href="<%=basePath %>/index">Home</a> <span class="divider">&gt;&gt; Track Shipment</span></li>
		    	  </ul>
		          <h4><font color="orange">Track Shipment</font></h4>
		          <hr>
				  <p>
				  If you have already registered in our website, please sign in to your account → <a href="<%=basePath %>/order/toMyOrder"><font color="red">"MyOrders"</font></a> to get your tracking number.
				  </p>
				  <br>
				  <p>If you did not register in our website, please directly enter your order number to get your tracking number. </p>
				  <br>
				  <span id="search-help"></span>
				  <form class="form-search">
				       <div class="row form-group">
					          <span class="control-label col-lg-3"><b>Order Number:</b></span>
				              <div class="col-lg-3">
				              	<input class="form-control input-sm" type="text" id="orderNo">
				              </div>
				              <div class="col-lg-3">
				              	<img alt="" src="<%=basePath%>/assets/icos/product/btnSubmit.jpg" onclick="javascript:searchTackShipment();">
				              </div>
				       </div>
				  </form>
			     
    		</div><!--/span8-->
    		

      </div><!-- row-fluid -->

    </div><!-- container-fluid -->
 
 <script type="text/javascript">   
 	function searchTackShipment(){
 		var orderNo = trim($("#orderNo").val());
 		if(orderNo == ""){
 			$("#search-help").html("<i class='icon-remove'></i><font color='red'>Please input your Order number</font>");
 			return ;
 		}
 		$.ajax({
 			  type:"post",
 			  cache:false,    
 			  dataType : "json",
 			  url:"findShippingNoByOrderNo",
 			  data: {"orderNo":orderNo},
 			  success:function(data){
 				  if(data.success == "no"){	    		
 		    		   $("#search-help").html("<i class='icon-remove'></i><font color='red'>Please fill in the correct order number.</font>");	    		    
 		    	   }else if(data.success == "empty"){	  
 		    		  $("#search-help").html("<i class='icon-remove'></i><font color='red'>The tracking number has not yet been assigned.</font>");	    		    
 		    	   }else{	    		  	 
 		    		  $("#search-help").html("");
 		    		  window.open('http://app3.hongkongpost.hk/CGI/mt/mt2result.jsp?method=post&submit=Enter&tracknbr='+data.success);
 		    	   }
 			  }
 		  });
 	}
 </script>
  </body>
</html>