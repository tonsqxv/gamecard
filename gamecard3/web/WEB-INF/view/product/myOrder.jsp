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
	
 </head>
 <body>
  <%@include file="head.jsp" %>
 		
    <div class="container-fluid">
      <div class="row-fluid">

    		<div class="span2 offset1" >
		    		<div class="well sidebar-nav">
			            <ul class="nav nav-list">
			              <li ><a href="<%=basePath%>/member/toMyAccount">MyAccount</a></li>
			              <li class="active"><a href="<%=basePath%>/order/toMyOrder">MyOrders</a></li>
			              <c:if test="${!empty member }">
			              <li><a href="<%=basePath%>/member/changePassword">Change Password</a></li>
			              </c:if>
			            </ul>
		          </div><!--/.well -->
		              
       		 </div><!--/span2-->

    	<div class="span8">
	      <ul class="breadcrumb">
		      <li><a href="<%=basePath %>/index">Home</a> <span class="divider">&gt;&gt; My Orders</span></li>
		  </ul>
          <h4><font color="orange">My Orders</font></h4>
          <hr> 
          <select>
            <option>Last Month</option>
            <option> 1 Month Ago</option>
          </select>	  		 

          <table class="table table-bordered table-condensed">
            <tbody>
              <tr>
                <td colspan="12">
                    <div class="row-fluid">
	                      <div class="span2">
	                         OrderNo
	                      </div>
	                       <div class="span4">
	                         Items
	                      </div>
	                      <div class="span2">
	                        Price
	                      </div>
	                      <div class="span2">
	                        Status
	                      </div>
	                      <div class="span2">
	                       Operation
	                      </div>
                    </div>  
                </td>
              </tr>
              
             <c:forEach items="${orders }" var="order">
              <tr>
                <td colspan="12">
                    <div class="row-fluid">
	                      <div class="span2">
	                      		<h6>&nbsp;</h6>
	                          <a href="#" onclick="javascript:window.open('<%=basePath%>/orderDetail/${order.id }/orderDetail');">${order.orderNo}</a>
	                      		<p><fmt:formatDate value="${order.createTm}" pattern="MM/dd/yyyy" /></p> 
	                      </div>
	                      <div class="span4 row-fluid">
	                      		<div class="span12">
	                      		<c:forEach items="${order.orderDetails}"  var="orderDetail">
	                      			<div class="span4">
				                          <a href="#" onclick="javascript:window.open('<%=basePath %>/product/${orderDetail.productId}/productDetail');"  class="thumbnail">
				                          <img src="<%=basePath %>/assets/images/product/${orderDetail.mainImgPath}" alt="">
				                          </a>  
		                       		 </div>
	                      		</c:forEach>
	                      		</div>
	                      </div>
	                      <div class="span2">
	                      	<h6>&nbsp;</h6>
	                      	<c:choose>
	                      			<c:when test="${order.orderTotalPrice > order.discountTotalPrice }">
	                      				<del><h6>$${order.orderTotalPrice }</h6></del>
	                      			</c:when>
	                      			<c:otherwise>
	                      				
	                      			</c:otherwise>
	                      	</c:choose>
	                        <strong ><font color="red">$${order.discountTotalPrice }</font></strong>
	                      </div>
	                      <div class="span2">
	                      	<h6>&nbsp;</h6>
	                      	<c:choose>
									<c:when test="${order.orderStatus == 1}">
										Awaiting Payment
										<p><a href="<%=basePath %>/order/${order.id }/cancelOrder">Cancel</a></p>
									</c:when>
									<c:when test="${order.orderStatus == 2}">
										Paied
										<p><fmt:formatDate value="${order.payTm}" pattern="MM/dd/yyyy" /></p> 
									</c:when>
									<c:when test="${order.orderStatus == 3}">
										Shipped
										<p><fmt:formatDate value="${order.dispatchTm}" pattern="MM/dd/yyyy" /></p> 
									</c:when>
									<c:when test="${order.orderStatus == 4}">
										Completed
										<p><fmt:formatDate value="${order.completedTm}" pattern="MM/dd/yyyy" /></p> 
									</c:when>
									<c:when test="${order.orderStatus == 5}">
										Cancelled
										<p><fmt:formatDate value="${order.cancelledTm}" pattern="MM/dd/yyyy" /></p> 
									</c:when>
									<c:when test="${order.orderStatus == 6}">
										Pending audit
										<p><fmt:formatDate value="${order.refundApplyTm}" pattern="MM/dd/yyyy" /></p> 
									</c:when>
									<c:when test="${order.orderStatus == 7}">
										Refunded
										<p><fmt:formatDate value="${order.refundedTm}" pattern="MM/dd/yyyy" /></p> 
									</c:when>
									<c:when test="${order.orderStatus == 8}">
										Declined
										<p><fmt:formatDate value="${order.declinedTm}" pattern="MM/dd/yyyy" /></p> 
									</c:when>
									<c:when test="${order.orderStatus == 9}">
										Refunding
										<p><fmt:formatDate value="${order.declinedTm}" pattern="MM/dd/yyyy" /></p> 
									</c:when>
							</c:choose>
	                      </div>
	                      <div class="span2">
	                      	<h6>&nbsp;</h6>
	                        <c:choose>
										<c:when test="${order.orderStatus==1}">
											<a href="<%=basePath%>/product/${order.id }/checkOut" >
											<button class="btn btn-warning" type="button"><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;checkout&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong></button>
											</a>
										</c:when>
										<c:when test="${order.orderStatus==2}">
											<a href="#refundRequestWindow" onclick="javascript:refundRequest('${order.id}');" data-toggle="modal">
											<button class="btn btn-warning" type="button"><strong>Refund Request</strong></button>
											</a>
										</c:when>
										<c:when test="${order.orderStatus==3}">
											<a onclick="javascript:window.open('http://app3.hongkongpost.hk/CGI/mt/mt2result.jsp?method=post&submit=Enter&tracknbr=${order.shippingNo}')" >
											<button class="btn btn-warning" type="button"><strong>&nbsp;Track Package&nbsp;&nbsp;</strong></button>
											</a>
										</c:when>
										<c:otherwise>
										</c:otherwise>
							</c:choose>
	                      </div>
                    </div>  
                </td>
              </tr>
              </c:forEach>
             
            </tbody>
          </table>
 			<!--申请退款 弹出框 start-->
         
			  <div id="refundRequestWindow" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			      <div class="modal-header" style="background-color: #F2F2F2">
			          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			          <h3 id="myModalLabel" style="font-size:22px; height:30px; color:#cc6600;">Refund Request:</h3>
			      </div>
			      <div class="modal-body">
			      	<div>
			      		<form id="redundApplyForm" action="<%=basePath %>/order/redundApply" method="post">
         				<input type="hidden" id="orderId" name="id">
				            <div class="span2 offset1">
				            	<p class="text-left">Reason:</p>
				            </div>
				            <div class="span8"><p class="text-left">
								<select class="span12" name="refundApplyReason" >
					    			   <option value="">Select Reason</option>
					                   <option value="1">order error</option>
					                   <option value="2">others</option>
				                 	</select>
							 </div>
							 <div class="span10 offset1">
							 		<textarea class="span12" rows="6" name="refundApplyDesc" placeholder="input your infomation please!"></textarea>
							 </div>
						</form>
          			</div>
      			</div>
		      <div class="modal-footer">
		          <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
		          <button class="btn btn-warning" onclick="javascript:submitRefundRequestForm();">Sumbit</button>
		          
		      </div>
  		</div>
		<!-- end 申请退款 弹出框 -->
    		</div><!--/span8-->

      </div><!-- row-fluid -->


    </div><!-- container-fluid -->
  
  <script type="text/javascript">  
  function refundRequest(id){
	  $("#orderId").val(id) ;
  }
  
  function submitRefundRequestForm(){
	  $("#redundApplyForm").submit() ;
  }
   </script>
  </body>
</html>