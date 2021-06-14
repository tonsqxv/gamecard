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
		    		<%@include file="myAccount4Left.jsp" %>
       		 </div><!--/span2-->

    	<div class="col-lg-9">

          <p><a href="home.html">Home</a>  &gt;&gt; Order Items</p>
          <hr> 
          <h4>Order Items</h4>
          <hr> 

          <table class="table table-bordered table-condensed">
            <tbody>
              <tr>
                <td colspan="12">
                    <div class="row">
                      <div class="col-lg-2">
                         
                      </div>
                      <div class="col-lg-4">
                        Description
                      </div>
                      <div class="col-lg-2">
                        Quantity
                      </div>
                      <div class="col-lg-2">
                        Item Price
                      </div>
                      <div class="col-lg-2">
                        Item Total
                      </div>
                    </div>  
                </td>
              </tr>
              <c:forEach items="${orderDetailList }" var="orderDetail">
              <tr>
                <td colspan="12">
                    <div class="row">
                      <div class="col-lg-2">
                        <a href="#" onclick="javascript:window.open('<%=basePath %>/product/${orderDetail.productId}/productDetail');" class="thumbnail">
                          <img src="<%=basePath %>/assets/images/product/${orderDetail.mainImgPath}" width="130" height="130"/>
                        </a> 
                      </div>
                      <div class="col-lg-4">
                     	  <h6>&nbsp;</h6>
                      	  <a href="#" onclick="javascript:window.open('<%=basePath %>/product/${orderDetail.productId}/productDetail');">
                          ${orderDetail.productName}
                          </a>
                      </div>
                      <div class="col-lg-2">
                      	  <h6>&nbsp;</h6>
                         ${orderDetail.amount}
                      </div>
                      <div class="col-lg-2">
                      	 <h6>&nbsp;</h6>
                     	 <c:if test="${orderDetail.basePrice > orderDetail.unitPrice }">
	                      		<del><h6>$${orderDetail.basePrice }</h6></del>
	                     </c:if>
                        <strong ><font color="red">$${orderDetail.unitPrice}</font></strong>
                      </div>
                      <div class="col-lg-2">
                      	<h6>&nbsp;</h6>
                        <strong ><font color="red">$<span>${orderDetail.itemTotal}</span></font></strong>
                      </div>
                    </div>  
                </td>
              </tr>
              </c:forEach>
            </tbody>
          </table>

    		</div><!--/span8-->

      </div><!-- row-fluid -->


    </div><!-- container-fluid -->
   <script type="text/javascript"> 
  
  
    </script>
  </body>
</html>