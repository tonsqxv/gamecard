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
   
	<!-- Load the Cloud Zoom CSS file -->
	<link href="<%=basePath %>/assets/cloud-zoom-1.0.2/cloud-zoom.css" rel="stylesheet" type="text/css" />
	<!-- Load the Cloud Zoom JavaScript file -->
	<script type="text/JavaScript" src="<%=basePath %>/assets/cloud-zoom-1.0.2/cloud-zoom.1.0.2.min.js"></script>

  </head>
  <body>
  <%@include file="head.jsp" %>

    <div class="container">
      <div class="row">

    		<div class="col-lg-2 col-lg-offset-1">
   					<%@include file="left.jsp" %>
   			</div><!--/col-lg-2-->

    		<div class="col-lg-8">

          <h2>${product.productName }</h2>
          <hr>    		
    		  <div class="row">
              <div class="col-lg-4">
                <div >
						<a href='<%=basePath %>/assets/images/product/${product.mainImgPath }' class = 'cloud-zoom' id='zoom1'
			              rel="adjustX: 10, adjustY:-4">
			            <img src="<%=basePath %>/assets/images/product/${product.mainImgPath }" alt=''/>
			        </a>
                </div>
	                
                <div align="center">  
                  <div class="col-lg-2">
                      <a href='<%=basePath %>/assets/images/product/${product.mainImgPath }' class='cloud-zoom-gallery'
			        	rel="useZoom: 'zoom1', smallImage: '<%=basePath %>/assets/images/product/${product.mainImgPath }' ">
			            <img src="<%=basePath %>/assets/images/product/${product.mainImgPath }"/>
			          </a>  
                  </div>
                 <c:if test="${!empty product.zoomImg1 }">
                 	<div class="col-lg-2">
	                 	<a href='<%=basePath %>/assets/images/product/${product.zoomImg1 }' class='cloud-zoom-gallery'
				        	rel="useZoom: 'zoom1', smallImage: '<%=basePath %>/assets/images/product/${product.zoomImg1 }' ">
				            <img src="<%=basePath %>/assets/images/product/${product.zoomImg1 }"/>
				        </a> 
                    </div> 
                 </c:if>    
                 <c:if test="${!empty product.zoomImg2 }">
                 	<div class="col-lg-2">
	                 	<a href='<%=basePath %>/assets/images/product/${product.zoomImg2 }' class='cloud-zoom-gallery'
				        	rel="useZoom: 'zoom1', smallImage: '<%=basePath %>/assets/images/product/${product.zoomImg2 }' ">
				            <img src="<%=basePath %>/assets/images/product/${product.zoomImg2 }"/>
				        </a>    
                    </div>
                 </c:if> 
                 <c:if test="${!empty product.zoomImg3 }">
                 	<div class="col-lg-2">
	                 	<a href='<%=basePath %>/assets/images/product/${product.zoomImg3 }' class='cloud-zoom-gallery'
				        	rel="useZoom: 'zoom1', smallImage: '<%=basePath %>/assets/images/product/${product.zoomImg3 }' ">
				            <img src="<%=basePath %>/assets/images/product/${product.zoomImg3 }"/>
				        </a>    
                    </div>
                 </c:if> 
                 <c:if test="${!empty product.zoomImg4 }">
                 	<div class="col-lg-2">
	                 	<a href='<%=basePath %>/assets/images/product/${product.zoomImg4 }' class='cloud-zoom-gallery'
				        	rel="useZoom: 'zoom1', smallImage: '<%=basePath %>/assets/images/product/${product.zoomImg4 }' ">
				            <img src="<%=basePath %>/assets/images/product/${product.zoomImg4 }"/>
				        </a>    
                    </div>
                 </c:if> 
                 <c:if test="${!empty product.zoomImg5 }">
                	 <div class="col-lg-2">
	                 	<a href='<%=basePath %>/assets/images/product/${product.zoomImg5 }' class='cloud-zoom-gallery'
				        	rel="useZoom: 'zoom1', smallImage: '<%=basePath %>/assets/images/product/${product.zoomImg5 }' ">
				            <img src="<%=basePath %>/assets/images/product/${product.zoomImg5 }"/>
				        </a>    
                    </div> 
                 </c:if>   
                  <c:if test="${!empty product.zoomImg6 }">
                 	 <div class="col-lg-2">
	                 	<a href='<%=basePath %>/assets/images/product/${product.zoomImg6 }' class='cloud-zoom-gallery'
				        	rel="useZoom: 'zoom1', smallImage: '<%=basePath %>/assets/images/product/${product.zoomImg6 }' ">
				            <img src="<%=basePath %>/assets/images/product/${product.zoomImg6 }"/>
				        </a>     
                     </div>
                 </c:if>   
                  <c:if test="${!empty product.zoomImg7 }">
                  	<div class="col-lg-2">
	                 	<a href='<%=basePath %>/assets/images/product/${product.zoomImg7 }' class='cloud-zoom-gallery'
				        	rel="useZoom: 'zoom1', smallImage: '<%=basePath %>/assets/images/product/${product.zoomImg7 }' ">
				            <img src="<%=basePath %>/assets/images/product/${product.zoomImg7 }"/>
				        </a>      
                    </div>
                 </c:if>   
                  <c:if test="${!empty product.zoomImg8 }">
                 	 <div class="col-lg-2">
	                 	<a href='<%=basePath %>/assets/images/product/${product.zoomImg8 }' class='cloud-zoom-gallery'
				        	rel="useZoom: 'zoom1', smallImage: '<%=basePath %>/assets/images/product/${product.zoomImg8 }' ">
				            <img src="<%=basePath %>/assets/images/product/${product.zoomImg8 }"/>
				        </a>      
                    </div>
                 </c:if>   
                 <c:if test="${!empty product.zoomImg9 }">
                 	<div class="col-lg-2">
	                 	<a href='<%=basePath %>/assets/images/product/${product.zoomImg9 }' class='cloud-zoom-gallery'
				        	rel="useZoom: 'zoom1', smallImage: '<%=basePath %>/assets/images/product/${product.zoomImg9 }' ">
				            <img src="<%=basePath %>/assets/images/product/${product.zoomImg9 }"/>
				        </a>      
                    </div>
                 </c:if>   
                  <c:if test="${!empty product.zoomImg10 }">
                  	<div class="col-lg-2">
	                 	<a href='<%=basePath %>/assets/images/product/${product.zoomImg10 }' class='cloud-zoom-gallery'
				        	rel="useZoom: 'zoom1', smallImage: '<%=basePath %>/assets/images/product/${product.zoomImg10 }' ">
				            <img src="<%=basePath %>/assets/images/product/${product.zoomImg10 }"/>
				        </a>      
                    </div>
                 </c:if>    
                  
                </div>
              </div>
              <div class="col-lg-8">
	                <br/> 
	                 <div>
		                  <div class="col-lg-3"><p class="text-right"><strong>ProductNo:</strong></p></div>
		                  <div class="col-lg-9"><p class="text-left">${product.productNo }</p></div>
	                 </div>
	                 <c:choose>
							<c:when test="${product.actualSellPrice < product.preSellPrice}">
								<div>
					                  <div class="col-lg-3"><p class="text-right"><strong>Price:</strong></p></div>
					                  <div class="col-lg-9"><p class="text-left"><del>$${product.preSellPrice }</del></p></div>
				                </div>
				                <div>
					                  <div class="col-lg-3"><p class="text-right"><strong>New Price:</strong></p></div>
					                  <div class="col-lg-9"><p class="text-left"><font color="red">$<span id="actualSellPrice">${product.actualSellPrice }</span></font></p></div>
				                </div>
							</c:when>
							<c:otherwise>
								<div>
					                  <div class="col-lg-3"><p class="text-right"><strong>Price:</strong></p></div>
					                  <div class="col-lg-9"><p class="text-left"><font color="red">$<span id="actualSellPrice">${product.actualSellPrice }</span></font></p></div>
				                </div>
							</c:otherwise>
					</c:choose>
	                
	                <div>
		                  <div class="col-lg-3"><p class="text-right"><strong>Name:</strong></p></div>
		                  <div class="col-lg-9"><p class="text-left">${product.productName }</p></div>
	                </div>
	                 
	                <c:forEach items="${kvList }" var="r">
		                  <div>
			                  <div class="col-lg-3"><p class="text-right"><strong>${r.key }:</strong></p></div>
			                  <div class="col-lg-9"><p class="text-left">${r.value }</p></div>
		                 </div>
		            </c:forEach>
		            
	                 <c:if test="${!empty  sizeList}">
	                 <div>
		                  <div class="col-lg-3"><p class="text-right"><strong>Size:</strong></p></div>
		                  <div class="col-lg-9"><p class="text-left"> 
		                  		<c:forEach items="${sizeList }" var="r">
		                  			<button class="btn btn-small" type="button" onclick="javascript:selectSize('${r.key}','${r.price}');">${r.key}</button>
		                  		</c:forEach>
		                  </p>
	                  </div>
	                </div>
	                </c:if>
	                
	                <div>
		                  <div class="col-lg-3"><p class="text-right"><strong>Weight:</strong></p></div>
		                  <div class="col-lg-9"><p class="text-left">${product.weight } KGS</p></div>
	                </div>
	                <div>
		                  <div class="col-lg-3"><p class="text-right"><strong>Availability:</strong></p></div>
		                  <div class="col-lg-9"><p class="text-left">${product.available }</p></div>
	                </div>
	                <div>
		                  <div class="col-lg-3"><p class="text-right"><strong>Sold:</strong></p></div>
		                  <div class="col-lg-9"><p class="text-left">${product.sales+product.baseSales }&nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=basePath %>/assets/images/product/common/IcoRating${product.star }.gif"/></p></div>
	                </div>
	                
	                <div>
		                  <div class="col-lg-3"><p class="text-right"><strong>Quantity:</strong></p></div>
		                  <div class="col-lg-9"><p class="text-left">
								<select id="item_amount" class="col-lg-2">
			                        <c:forEach var ="i" begin="1" end="30">
										<option value="${i }">${i }</option>
									</c:forEach>
	                           </select>
	 							&nbsp;&nbsp;
	 							<a href="javascript:addToCart2(${product.id},${product.actualSellPrice});"><img src="<%=basePath %>/assets/icos/product/addToCart.jpg" /></a>
	 					  </div>
	                </div>
               
              </div>            
          </div><!--/row-->  

          <hr>   		 

          <div>
            <h4>Product Description</h4>
            <p>${product.desc} </p>
            <br/>
          </div><!--/Product Description-->

          <hr>

       <%@include file="foot4customerlike.jsp" %>

          <hr>
    		  
    		</div><!--/col-lg-8-->

      </div><!-- row-fluid -->

    </div><!-- container-fluid -->
    
    	<script type="text/javascript" language="javascript">
    var selectedSizeFlg = false ;
    var selectedSize = "" ;
   	function addToCart2(productId,unitPrice){

	   	 var obj = document.getElementById("item_amount"); //定位id
	   	 var index = obj.selectedIndex; // 选中索引
	   	 var amount = obj.options[index].value; // 选中值
	   	 
	   	if(${fn:length(sizeList)>0}){
		   	 if(selectedSizeFlg == false){
		   		 alert("Please select size") ;
		   		 return ;
		   	 }
	   	}
	   	unitPrice = $("#actualSellPrice").html();
	   	addToCart(productId,amount,unitPrice,'',selectedSize) ;
   	}
   	
   	function selectSize(size,price){
   		selectedSizeFlg = true ;
   		selectedSize = size ;
   		$("#actualSellPrice").html(price);
   	}
   	
	</script> 
	
  </body>
</html>

