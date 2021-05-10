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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Home Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="<%=basePath %>/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <!-- 自适应 -->
    <link href="<%=basePath %>/assets/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
    
    <script src="<%=basePath %>/assets/js/common/jquery-1.8.2.min.js"></script>
    <script src="<%=basePath %>/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=basePath %>/assets/js/common/util.js"></script>

     <style type="text/css">      
      body {
        padding-top: 60px;/*body距离顶部底部距离*/
        padding-bottom: 40px;
      }      
    </style>

  </head>
  <body>
  <%@include file="../product/head.jsp" %>

    <div class="container-fluid">
      <div class="row-fluid">
    		<div class="span2 offset1" >
   					<%@include file="../product/left.jsp" %>
   			</div><!--/span2-->
    		<div class="span8">
				<p><a href="<%=basePath %>/index">Home</a>  &gt;&gt; Your Shopping cart</p>
	            <hr> 
	            <h4>Shopping cart Products</h4>
	            <hr>  
	            <table class="table table-bordered ">
		            <colgroup>
		              <col class="span6">
		              <col class="span2">
		              <col class="span2">
		              <col class="span4">
		            </colgroup>
		            <thead>
		              <tr>
		                <th>Product</th>
		                <th>Quantity</th>
		                <th>Price</th>
		                <th>Total</th>
		              </tr>
		            </thead>
		            <tbody>
		            <c:forEach items="${orderDetailList }" var="orderDetail">
		              <tr>
		                <td>
		                  ${orderDetail.productName }
		                </td>
		                <td>${orderDetail.amount }</td>
		                <td><font color="red">$${orderDetail.unitPrice }</font></td>
		                <td><font color="red">$${orderDetail.unitPrice*orderDetail.amount }</font></td>
		              </tr>
		              </c:forEach>
		              <tr>
		              <td colspan="4"><div align="right"><span class="text-right">Total：<font color="red">$${order.orderTotalPrice }</font></span></div></td>
		              </tr>
		            </tbody>
		          </table>
         	   <hr/>
         	   
         	   <form id="payForm" class="form-horizontal" action="<%=basePath %>/pay/payAccountLogin" method="post">
         	   <input type="hidden" name="currencyCodeType" value="USD"/>
         	   <input type="hidden" name="paymentType"  value="Sale">
         	   <h4>1.Choose a shipping method</h4>
         	   <table class="table table-bordered table-condensed">
		            <tbody>
		            		<c:forEach items="${shippingOptionList}" var="shippingOption">
			            		<tr>	 
		            				  <td>
		            				  <label class="radio">
		            				  &nbsp;<input type="radio" name="shippingOptionId" value="${shippingOption.id }" <c:if test="${order.shippingOptionId == shippingOption.id}">checked='checked'</c:if>  />
		            				  ${shippingOption.shippingOptionName }&nbsp;&nbsp;(<font color="red">$${shippingOption.shippingOptionAmount })</font>
		            				  </label>
		            				  </td>
		            			</tr>
		            		</c:forEach>
		            </tbody>
	           </table>
         	   <hr/>
         	   <h4>2.Shopping Address</h4>
         	   <hr> 
	         	<div>
			         <label class="radio inline">
						  <input type="radio" name="optionsRadios" value="option1" checked>
						  <strong>Use New Address</strong>
					 </label>
					 <label class="radio inline">
						  <input type="radio" name="optionsRadios" value="option1" >
						  <strong>Use Paypal Address</strong>
					 </label>
	           </div>
	           <br/>
	           <div> <!-- 地址信息用div包装 -->
	          		<div class="control-group">
			              <label class="control-label" for="email">Buyer's Email(Required):</label>
			              <div class="controls">
			                <input type="text" id="email" name="email" maxlength="60" value="${order.email }"><font color="red">&nbsp;*</font>
			              </div>
		            </div>
		             <div class="control-group">
			              <label class="control-label" for="firstName">First name:</label>
			              <div class="controls">
			                <input type="text" id="firstName" name="firstName" maxlength="30" value="${order.firstName }"><font color="red">&nbsp;*</font>
			              </div>
		            </div>
		            <div class="control-group">
			              <label class="control-label" for="lastName">Last name:</label>
			              <div class="controls">
			                <input type="text" id="lastName" name="lastName" maxlength="30" value="${order.lastName }"><font color="red">&nbsp;*</font>
			              </div>
		            </div>
		            <div class="control-group">
			              <label class="control-label" for="phoneNum">Phone Number:</label>
			              <div class="controls">
			                <input type="text" id="phoneNum" name="phoneNum" maxlength="30" value="${order.phoneNum }"><font color="red">&nbsp;*</font>
			              </div>
		            </div>
		            <div class="control-group">
			              <label class="control-label" for="street1">Street1:</label>
			              <div class="controls">
			                <input type="text" id="street1" name="street1" maxlength="150" value="${order.street1 }"><font color="red">&nbsp;*</font>
			              </div>
		            </div>
		             <div class="control-group">
			              <label class="control-label" for="street2">Street2:</label>
			              <div class="controls">
			                <input type="text" id="street2" name="street2" maxlength="150" value="${order.street2 }">
			              </div>
		            </div>
		            <div class="control-group">
			              <label class="control-label" for="city">Suburb/City:</label>
			              <div class="controls">
			                <input type="text" id="city" name="city" maxlength="30" value="${order.city }"><font color="red">&nbsp;*</font>
			              </div>
		            </div>
		            <div class="control-group">
			              <label class="control-label" for="state">State/Province:</label>
			              <div class="controls">
			                <input type="text" id="state" name="state" maxlength="30" value="${order.state }"><font color="red">&nbsp;*</font>
			              </div>
		            </div>
		            <div class="control-group">
			              <label class="control-label" for="country">Country:</label>
			              <div class="controls">
			              	<select name="country" id="country" style="width:8;">
			              		<option value="">Please select...</option>
			              		<c:forEach items="${countryList}" var="country">
			              			<option value="${country.code }"  <c:if test="${country.code == order.country}">selected</c:if>>${country.country }</option>
			              		</c:forEach>
			              		
			              		
			              	</select>
			                <font color="red">&nbsp;*</font>
			              </div>
		            </div>
		            <div class="control-group">
			              <label class="control-label" for="zipCode">Zip/Postcode:</label>
			              <div class="controls">
			                <input type="text" id="zipCode" name="zipCode" maxlength="30" value="${order.zipCode }"><font color="red">&nbsp;*</font>
			              </div>
		            </div>
              </div>
         	  <hr>    		  		 
         	  <h4>3.Add other special requests or your courier contact information here(1000 characters)</h4>
         	  <table class="table table-bordered table-condensed">
		            <tbody>
	            			<tr>
	            				<td><textarea class="span12" rows="8" name="memberMessage"  placeholder="input your infomation">${order.memberMessage }</textarea></td>
	            			</tr>
	            			
		            </tbody>
	           </table>
	           </form> 
         	  
	          <div class="control-group" align="center">
		          <img id="submitButtonId" src="<%=basePath%>/assets/icos/product/bntGoToPay.jpg" onclick="javascript:submitPay();">
		      </div>
    	</div><!--/span8-->

      </div><!-- row-fluid -->

    </div><!-- container-fluid -->
    	
    	
   <script type="text/javascript">  
   function varifyShippingOptionId(){
	   var chkObjs = document.getElementsByName("shippingOptionId");
       for(var i=0;i<chkObjs.length;i++){
           if(chkObjs[i].checked){
				return true ;
           }
       }
    alert("Please choose a shipping mehtod") ;
	   return false ;
   }

 function submitPay(){
	 if(!varifyShippingOptionId()){
		return false ; 
	 }
/*	var email = trim($("#email").val()) ;
	if(email == ""){
		alert("Please input your email");
		return false ;
	}
	var firstName = trim($("#firstName").val()) ;
	if(firstName == ""){
		alert("Please input your first name");
		return false ;
	}
	var lastName = trim($("#lastName").val()) ;
	if(lastName == ""){
		alert("Please input your last name");
		return false ;
	}
	var phoneNum = trim($("#phoneNum").val()) ;
	if(phoneNum == ""){
		alert("Please input your phone Number");
		return false ;
	}
	var street1 = trim($("#street1").val()) ;
	if(street1 == ""){
		alert("Please input your street1");
		return false ;
	}
	var city = trim($("#city").val()) ;
	if(city == ""){
		alert("Please input your city");
		return false ;
	}
	var state = trim($("#state").val()) ;
	if(state == ""){
		alert("Please input your state");
		return false ;
	}
	var country = trim($("#country").val()) ;
	if(country == ""){
		alert("Please input your country");
		return false ;
	}
	var zipCode = trim($("#zipCode").val()) ;
	if(zipCode == ""){
		alert("Please input your zipCode");
		return false ;
	}
	
*/
	$("#submitButtonId").attr("disabled","disabled") ;
	$("#payForm").submit();
	
	}
 </script>
  </body>
</html>