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
<html>
  <head>
    <script src="<%=basePath %>/assets/js/common/util.js"></script>

  </head>
  <body>
  <%@include file="head.jsp" %>

    <div class="container-fluid">
      <div class="row-fluid">

    	<div class="span2 offset1" >
   					<%@include file="left.jsp" %>
   		</div><!--/span2-->

    	<div class="span8">
		  <ul class="breadcrumb">
		    		  <li><a href="<%=basePath %>/index">Home</a> <span class="divider">&gt;&gt; Your Shopping cart</span></li>
		  </ul>
          <h4><font color="orange">Your Shopping Cart</font></h4>
    	<br>
		<form id="gernarateOrderForm" action="<%=basePath %>/order/gernarateOrder" method="post">
          <table class="table table-bordered table-condensed">
            <tbody>
              <tr>
                <td colspan="10">
                    <div class="row-fluid">
                      <div class="span2">
                         Cart Items
                      </div>
                      <div class="span4">
                        	Product
                      </div>
                      <div class="span2">
                       	 	Item Price
                      </div>
                      <div class="span2">
                        	Quantity
                      </div>
                      <div class="span1">
                   		     Item Total
                      </div>
                       <div class="span1">
                   		     
                      </div>
                    </div>  
                </td>
              </tr>
              <!-- 循环遍历购物车里的商品 -->
              
              <c:forEach items="${shopItemList }" var="shopItem">
               <input type="hidden" name="shopItemId"  value="${shopItem.id}">
             <tr>
                <td colspan="12">
                    <div class="row-fluid">
                      <div class="span2">
                        <a href="#" onclick="javascript:window.open('<%=basePath %>/product/${shopItem.productId}/productDetail');" class="thumbnail">
                          <img src="<%=basePath %>/assets/images/product/${shopItem.cardMainImg}" width="130" height="130"/>
                          <input type="hidden" name="item_productId"  value="${shopItem.productId}">
                        </a>  
                      </div>
                      <div class="span4">
	                      	<h6>&nbsp;</h6>
	                        <a  href="#" onclick="javascript:window.open('<%=basePath %>/product/${shopItem.productId}/productDetail');">
	                         <span id="item_productName_${shopItem.id }">${shopItem.productName }</span>
	                        </a>
	                        <c:choose>
								<c:when test="${!empty shopItem.color and empty shopItem.size}">
									<p><b>color</b>:${shopItem.color}</p>
								</c:when>
								<c:when test="${empty shopItem.color and !empty shopItem.size}">
									<p><b>size</b>:${shopItem.size}</p>
								</c:when>
								<c:when test="${!empty shopItem.color and !empty shopItem.size}">
									<p><b>color</b>:${shopItem.color}&nbsp;<b>size</b>:${shopItem.size}</p>
								</c:when>
								<c:otherwise>
								</c:otherwise>
						   </c:choose>
                         	
                      </div>
                      <div class="span2">
                      	<c:choose>
								<c:when test="${shopItem.preSellPrice > shopItem.unitPrice }">
									<del><h6>${shopItem.preSellPrice }</h6></del>
								</c:when>
								<c:otherwise>
									<h6>&nbsp;</h6>
								</c:otherwise>
						</c:choose>
                        <strong ><font color="red">$<span id="item_unitPrice_${shopItem.id }">${shopItem.unitPrice }</span></font></strong>
                      </div>
                      <div class="span2">
                      	<h6>&nbsp;</h6>
                        <select id="item_amount_${shopItem.id }" name ="item_amount" class="span8" onChange="javascript:amountChange(${shopItem.unitPrice },${shopItem.id });">
                        <c:forEach var ="i" begin="1" end="30">
								<c:choose>
									<c:when test="${shopItem.amount==i}">
										<option value="${i }" selected>${i }</option>
									</c:when>
									<c:otherwise>
										<option value="${i }">${i }</option>
									</c:otherwise>
								</c:choose>
						</c:forEach>
                        </select>
                      </div>
                      <div class="span1">
                      	<h6>&nbsp;</h6>
                        <strong ><font color="red">$<span id="item_unitTotalPrice_${shopItem.id }">${shopItem.itemTotal }</span></font></strong>
                      </div>
                      <div class="span1">
                      	<h6>&nbsp;</h6>
                        <a onclick="javascript:deleteItem(${shopItem.id },${shopItem.unitPrice });">
                        	 <img src="<%=basePath %>/assets/icos/product/deleteShopItem.gif"/>
                        </a>
                      </div>
                    </div>  
                </td>
              </tr>
               </c:forEach>
              <tr>
                <td colspan="12">
                  <p align="right"><strong> Total:&nbsp;&nbsp;  </strong><strong ><font color="red" size="4">$<span id="totalPrice">${totalPrice }</span></font></strong></p>
                </td>
              </tr>
              <tr>
                <td colspan="12">
                  <p align="right"><button onclick="javascript:gernarateOrder();" class="btn  btn-warning" type="button"><strong>PROCEED TO CHECKOUT</strong></button></p>
                </td>
              </tr>
            </tbody>
          </table>
			</form>
          <hr>

         <%@include file="foot4customerlike.jsp" %>

          <hr>
    		  
    		</div><!--/span8-->

      </div><!-- row-fluid -->


    </div><!-- container-fluid -->
    
     <script type="text/javascript">  
    
 	
     //参数说明 单价，id
    function  amountChange(unitPrice,id){
    	
    	 var obj = document.getElementById("item_amount_"+id); //定位id
    	 var index = obj.selectedIndex; // 选中索引
    	 var value = obj.options[index].value; // 选中值
    	 var unitTotalPriceId = "item_unitTotalPrice_"+id ;
    	 
    	 //记录原来的总金额
    	 var oldunitTotalPrice = document.getElementById(unitTotalPriceId).innerHTML ;
    	 
    	 document.getElementById(unitTotalPriceId).innerHTML = dealTotalPrice((value*unitPrice).toFixed(2)) ;
    	 
    	 //每次change计算总金额  计算公式：原总金额 +（现单价总金额-原单价总金额）
    	 var totalPrice = document.getElementById("totalPrice").innerHTML ;
    	 var newUnitTotalPrice = document.getElementById(unitTotalPriceId).innerHTML ;
    	 
    	 var number = add(newUnitTotalPrice,-oldunitTotalPrice) ;
    	var oldTotalPrice =document.getElementById("totalPrice").innerHTML ;
    	document.getElementById("totalPrice").innerHTML = add(oldTotalPrice,number) ;

     }
     
     //生成订单
     function gernarateOrder(){
    	 //判断是否有购物车记录  判断总金额是否为0
    	 var totalPrice =document.getElementById("totalPrice").innerHTML ;
    	 var i = parseFloat(totalPrice) ;
    	 if(parseFloat(totalPrice) <= 0){
    		 return ;
    	 }
    	
    	 //需要传递的参数 ：1：productId  2：amount 3:shopItemId 购物车编号
    	 $("#gernarateOrderForm").submit() ;
     }
     
     function deleteItem(id,unitPrice){
    	 var obj = document.getElementById("item_amount_"+id); //定位id
    	 var index = obj.selectedIndex; // 选中索引
    	 var value = obj.options[index].value; // 选中值
    	 var unitTotalPriceId = "item_unitTotalPrice_"+id ;
    	//记录要删除记录的总金额
    	 var deleteUnitTotalPrice = document.getElementById(unitTotalPriceId).innerHTML ;
    	 var oldTotalPrice =document.getElementById("totalPrice").innerHTML ;
    	 var newTotalPrice = add(oldTotalPrice,-deleteUnitTotalPrice) ;
    	 
    	 $.ajax({
				type:"post",
				dataType : "json",  
		        url:"<%=basePath %>/shopItem/deleteItem",
		        data: {"id":id},
		        success:function(data){
		    	   if(data.success == 'ok'){
		    		  	var tr=obj.parentNode.parentNode.parentNode.parentNode;
		    	    	var tbody=tr.parentNode;
		    	    	tbody.removeChild(tr);
		    		 // var text = ${fn:length(memberNotLoginShopItems)} ;
		    		 // document.getElementById("cartItemsSun").innerHTML = text ;
		    		 
		    		 //删除后更新总金额
	    	    	 document.getElementById("totalPrice").innerHTML = newTotalPrice ;
		    		 
		    	   }
		       }  		    	   
		});    		
    	 
     }
     
		//计算相加   参数是div里的innerHTML
     function add(arg1,arg2){     
    	 var r1,r2,m;    
    	 try{
    		 r1=arg1.toString().split(".")[1].length
    		 }catch(e){
    			 r1=0
    		}    
    	try{
    		r2=arg2.toString().split(".")[1].length
    		}catch(e){
    			r2=0
    		}     
    	m=Math.pow(10,Math.max(r1,r2))     
    	var total =  ((arg1*m+arg2*m)/m).toFixed(2);   
    	//对最后的结果做处理
    	total = dealTotalPrice(total) ;
    	
    	return total ;
    	}
  //对小数做处理 如果大于两位小数 则截取两位小数 
  function dealTotalPrice(total){
	 	 var arr = total.split(".") ;
	  	if(arr.length == 2 && arr[1].length > 2){
	  		total = arr[0]+"."+arr[1].substring(0,2) ;
	  	}
	  	return total ;
  }
		
   //浮点数乘法运算   
   function floatMul(arg1,arg2)   { 
	   var m=0,s1=arg1.toString(),s2=arg2.toString();    
	   try{m+=s1.split(".")[1].length}catch(e){}     
	   try{m+=s2.split(".")[1].length}catch(e){}     
	   return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)      
	   }   
  
  </script>
  </body>
  
 
</html>