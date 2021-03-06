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
  <%@include file="../product/head.jsp" %>

    <div class="container">
      <div class="row">
    		<div class="col-lg-3">
   					<%@include file="../product/left.jsp" %>
   			</div><!--/span2-->
    		<div class="col-lg-9">
	    		 <ul class="breadcrumb">
			          <li><a href="<%=basePath %>/index">Home</a> <span class="divider">&gt;&gt;My Shopping cart</span></li>
			     </ul>
	             <h4><font color="orange">Shopping cart Products</font></h4>
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
		                <th>Item Price</th>
		                <th>Quantity</th>
		                <th>Item Total</th>
		              </tr>
		            </thead>
		            <tbody>
		            <c:forEach items="${orderDetailList }" var="orderDetail">
		              <tr>
			                <td>
			                  ${orderDetail.productName }
			                </td>
			                <td><font color="red">$${orderDetail.basePrice }</font></td>
			                <td>${orderDetail.amount }</td>
			                <td><font color="red">$${orderDetail.itemTotal }</font></td>
		              </tr>
		              </c:forEach>
		              <c:if test="${order.orderTotalPrice > order.discountTotalPrice}">
			              <tr>
			              		<td colspan="4"><div align="right"><span class="text-right">original price：<font color="red">$${order.orderTotalPrice }</font></span></div></td>
			              </tr>
			              
			              <tr>
			              		<td colspan="4"><div align="right"><span class="text-right">discount：<font color="red">-$${order.discountPrice }</font></span></div></td>
			              </tr>
		              </c:if>
		              <tr>
		              		<td colspan="4"><div align="right"><span class="text-right">Sub-Total：<font color="red">$${order.discountTotalPrice }</font></span></div></td>
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
		            				  <div class="radio">
		            				  <label class="radio">
		            				  &nbsp;<input type="radio" name="shippingOptionId" value="${shippingOption.id }" <c:if test="${order.shippingOptionId == shippingOption.id}">checked='checked'</c:if>  />
		            				  ${shippingOption.shippingOptionName }&nbsp;&nbsp;(<font color="red">$${shippingOption.shippingOptionAmount })</font>
		            				  </label>
		            				  </div>
		            				  </td>
		            			</tr>
		            		</c:forEach>
		            </tbody>
	           </table>
         	   <hr/>
         	   <h4>2.Shopping Contact Method</h4>
         	   <hr> 
	         	
	           <div> <!-- 地址信息用div包装 -->
	          		<div class="row form-group">
			              <span class="control-label col-lg-4">Buyer's Email(Required)<font color="red">&nbsp;*</font>:</span>
			              <div class="col-lg-4">
			                <input class="form-control input-sm" type="text" id="email" name="email" maxlength="60" value="${order.email }">
			              </div>
		            </div>
		            <div class="row form-group">
			              <span class="control-label col-lg-4">First name<font color="red">&nbsp;*</font>:</span>
			              <div class="col-lg-4">
			                <input class="form-control input-sm" type="text" id="firstName" name="firstName" maxlength="30" value="${order.firstName }">
			              </div>
		            </div>
		            <div class="row form-group">
			              <span class="control-label col-lg-4">Last name<font color="red">&nbsp;*</font>:</span>
			              <div class="col-lg-4">
			                <input class="form-control input-sm" type="text" id="lastName" name="lastName" maxlength="30" value="${order.lastName }">
			              </div>
		            </div>
		            <div class="row form-group">
			              <span class="control-label col-lg-4">Phone Number<font color="red">&nbsp;*</font>:</span>
			              <div class="col-lg-4">
			                <input class="form-control input-sm" type="text" id="phoneNum" name="phoneNum" maxlength="30" value="${order.phoneNum }">
			              </div>
		            </div>
		            <div class="row form-group">
			              <span class="control-label col-lg-4">Street1<font color="red">&nbsp;*</font>:</span>
			              <div class="col-lg-4">
			                <input class="form-control input-sm" type="text" id="street1" name="street1" maxlength="150" value="${order.street1 }">
			              </div>
		            </div>
		             <div class="row form-group">
			              <span class="control-label col-lg-4">Street2:</span>
			              <div class="col-lg-4">
			                <input class="form-control input-sm" type="text" id="street2" name="street2" maxlength="150" value="${order.street2 }">
			              </div>
		            </div>
		            <div class="row form-group">
			              <span class="control-label col-lg-4">Suburb/City<font color="red">&nbsp;*</font>:</span>
			              <div class="col-lg-4">
			                <input class="form-control input-sm" type="text" id="city" name="city" maxlength="30" value="${order.city }">
			              </div>
		            </div>
		            <div class="row form-group">
			              <span class="control-label col-lg-4">State/Province<font color="red">&nbsp;*</font>:</span>
			              <div class="col-lg-4">
			                <input class="form-control input-sm" type="text" id="state" name="state" maxlength="30" value="${order.state }">
			              </div>
		            </div>
		            <div class="row form-group">
			              <span class="control-label col-lg-4">Country<font color="red">&nbsp;*</font>:</span>
			              <div class="col-lg-4">
			              	<select class="form-control" name="country" id="country" style="width:8;">
			              		<option value="">Please select...</option>
			              		<c:forEach items="${countryList}" var="country">
			              			<option value="${country.code }"  <c:if test="${country.code == order.country}">selected</c:if>>${country.country }</option>
			              		</c:forEach>
			              	</select>
			              </div>
		            </div>
		            <div class="row form-group">
			              <span class="control-label col-lg-4">Zip/Postcode:</span>
			              <div class="col-lg-4">
			                <input class="form-control input-sm" type="text" id="zipCode" name="zipCode" maxlength="30" value="${order.zipCode }"><font color="red">&nbsp;*</font>
			              </div>
		            </div>
		        
              </div>
         	  <hr>    		  		 
         	  <h4>3.Add other special requests or your courier contact information here(1000 characters)</h4>
         	  <table class="table table-bordered table-condensed">
		            <tbody>
	            			<tr>
	            				<td><textarea class="form-control" rows="8" name="memberMessage"  placeholder="input your infomation">${order.memberMessage }</textarea></td>
	            			</tr>
	            			
		            </tbody>
	           </table>
	           </form> 
         	  
	          <div class="control-group" align="center">
		          <img id="submitButtonId" src="<%=basePath%>/assets/icos/product/btnPayPal.jpg" onclick="javascript:submitPay();">
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
	 var email = trim($("#email").val()) ;
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
	$("#submitButtonId").attr("disabled","disabled") ;
	$("#payForm").submit();
	
	}
 </script>
  </body>
</html>