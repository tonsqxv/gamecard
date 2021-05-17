 
 <%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>   
   
<!doctype html>
<html lang="utf-8">
  <%@include file="/WEB-INF/view/common/head.jsp" %> 
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
  <head>
    <meta charset="utf-8"/>

    <title>Macower</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>    
    <meta name="author" content="macower"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  
 
    <link href="<%=basePath %>assets/css/bootstrap.css" rel="stylesheet"/>
    <link href="<%=basePath %>assets/css/bootstrap-responsive.css" rel="stylesheet"/>
   
   <link href="<%=basePath %>assets/css/docs.css"  rel="stylesheet"/>
   <link href="<%=basePath %>assets/css/grumble.css"  rel="stylesheet"/>
    <link href="<%=basePath %>assets/css/prettify.css"  rel="stylesheet"/>  
<style> 
 
 .headertab a:hover{background:url(http://e.kuaipan.cn/static/style/blue/images/tab_underline.jpg) left bottom repeat-x;}
.headertab a.cur{background:url(http://e.kuaipan.cn/static/style/blue/images/tab_underline.jpg) left bottom repeat-x;}
  .navbar-wrapper {
      position: absolute;
      top: 0;
      left: 0;
      right: 0;
      z-index: 10;
      margin-top: 50px;
       height: 50px;
      margin-bottom: -90px; /* Negative margin to pull up carousel. 90px is roughly margins and height of navbar. */
    }  
    
    b {
    color: #C3522F;
    font-size: 20px;
    font-weight:24px;
    }
    p{
    color: #333333;
    font-size: 14px;
    font-weight:24px;
    }
    
    
    /*************
 * features in the font page
 * ***************/

.features {
 
  margin: 0 auto;
  margin-bottom: 14px;
  border: 1px solid #e7e8e9;
  background-color: #fff;
  border-radius: 1px;
}

.features div span {
  display: block;
  float: left;

}

.features div span a.block {
  position: relative;
  text-decoration: none;
  color: #979798;
  display: block;
  padding: 33px 24px 0 100px;
}

.features ul li a.block > div{
   background:url('https://statics.dnspod.cn/yantai/img/content/features.png'); 
   margin-top: 0;
   margin-left: 0;   
}

 #feature-1-a{
    margin-top: 0;
   margin-left: 0;
   width:70px;
   height:68px;
  background:url('https://statics.dnspod.cn/yantai/img/content/features.png') 0px -12px; 
 }

 #feature-1-b{
 background:url('https://statics.dnspod.cn/yantai/img/content/features.png') 1px -80px; 
 }

 #feature-2-a{
      margin-top: 0;
   margin-left: 0;
   width:70px;
   height:68px;
background:url('https://statics.dnspod.cn/yantai/img/content/features.png') -87px 0px; 
 }

 #feature-2-b{
   background:url('https://statics.dnspod.cn/yantai/img/content/features.png') -86px -68px; 

 }




 #feature-3-a{
    margin-top: 0;
   margin-left: 0;
   width:70px;
   height:68px;
  background:url('https://statics.dnspod.cn/yantai/img/content/features.png') -177px 0px; 
 }

 #feature-3-b{
 background:url('https://statics.dnspod.cn/yantai/img/content/features.png') -176px -68px; 
 }

 #feature-4-a{
      margin-top: 0;
   margin-left: 0;
   width:70px;
   height:68px;
background:url('https://statics.dnspod.cn/yantai/img/content/features.png') 0px -150px; 
 }

 #feature-4-b{
   background:url('https://statics.dnspod.cn/yantai/img/content/features.png') 1px -220px; 
 }




 #feature-5-a{
   margin-top: 0;
   margin-left: 0;
   width:70px;
   height:68px;

   background:url('https://statics.dnspod.cn/yantai/img/content/features.png') -86px -145px; 
 }

 #feature-5-b{
   background:url('https://statics.dnspod.cn/yantai/img/content/features.png') -85px -215px; 
 }


 #feature-6-a{
   margin-top: 0;
   margin-left: 0;
   width:70px;
   height:68px;

   background:url('https://statics.dnspod.cn/yantai/img/content/features.png') -177px -150px; 
 }

 #feature-6-b{
   background:url('https://statics.dnspod.cn/yantai/img/content/features.png') -176px -220px; 
   height:62px;
 }





.features div span div.up {
  position: absolute;
  left: 23px;
  top: 35px;
}

.features div span div.down {
  position: absolute;
  left: 23px;
  top: 35px;
}

.lt-ie7 .features div span div.up{
  left: -70px;
}

.features div span h3 {
  font-size: 16px;
  color: #30363f;
  margin-bottom: 12px;
}
.features div span a.block:hover h3 {
  color: #1a6cc1;
}

.features div span p {
  font-size: 12px;
  line-height: 22px;
}



.enterprise {
 
  margin: 0 auto;
  margin-bottom: 14px;
  border: 1px solid #e7e8e9;
  border-radius: 3px;
  background: #fff url(https://statics.dnspod.cn/yantai/img/index/enterprise.jpg) no-repeat scroll bottom right;
  padding: 28px 0 0 22px;
}

.enterprise a.block {
  position: relative;
  text-decoration: none;
  color: #979798;
  display: block;
}

.enterprise h2 {
  font-size: 20px;
  color: #64686f;
  margin-bottom: 20px;
}
.enterprise p {
  font-size: 12px;
  line-height: 20px;
  width: 500px;
  margin-bottom: 16px;
}

.enterprise a.block span {
  color: #1a6cc1;
}
.enterprise a.block:hover span {
  text-decoration: underline;
}

.enterprise p.contact-header {
  color: #7d7d7d;
}

.enterprise .contact {
  margin-top: 35px;
  margin-bottom: 28px;
  color: #7d7d7d;
  font-size: 18px;
}

.enterprise .contact span {
  margin-left: 5px;
  margin-right: 56px;
}

.enterprise .contact a {
  text-decoration: none;
  color: #979798;
}

.enterprise .contact a:hover {
  color: #1a6cc1;
}



 

footer {
  padding-bottom: 36px;
  color: #888;
  border-top：2px solid #EE7711;
 
  background-color:rgb(47, 47, 47);
}

footer a {
  color: #888;
  text-decoration: none;
}

footer a:hover {
  color: #ee7711;
  text-decoration: none;
}

footer li.weibo a{
  display:inline-block;   
  vertical-align:middle;
}

.support-info li.tqq img,.support-info li.tsina img {
  width:15px;
  height:15px;
  vertical-align:middle;
  margin-bottom:3px;
}

footer li.weibo a img{
   width:16px;
   height:16px;
}

footer nav {
  width: 990px;
  margin: 0 auto;
  margin-bottom: 38px;
  border-top：2px solid #EE7711;  
  background-color:rgb(47, 47, 47);
  border-radius: 3px;
}

footer nav ol.breadcrumbs {
  float: none;
  height: 38px;
  margin: 0;
  list-style: none;
  font-weight: normal;
}
footer nav ol.breadcrumbs li {
  float: left;
  margin: 0;
  padding: 0 0 0 15px;
}

footer nav ol.breadcrumbs li, footer nav ol.breadcrumbs li a {
  height: 38px;
  line-height: 38px;
  font-size: 12px;
  color: #30363f;
  font-weight: normal;
}

footer nav ol.breadcrumbs li a {
  float: left;
  padding: 0 26px 0 0;
  margin-left: 0px;
  background: url(../img/index/breadcrumb_separator.gif) no-repeat 100% 50%;
}

footer nav ol.breadcrumbs li.home {
  padding: 0 0 0 22px;
}

footer nav ol.breadcrumbs li a:hover { color: #1a6cc1; text-decoration: none; }



footer .directorynav {
  clear: both;
  
  font-size: 12px;
  padding-top: 19px;
}
footer .c {
  clear: both;
}
footer .directorynav .column {
  float: left;
  width: 164px;
  padding-left: 22px;
}
footer .directorynav .weibo {
  padding-top: 4px;
}

/*--- Directory Nav ---*/
.directorynav .column { width: 120px; padding: 0 0 6px 18px; }
.directorynav .column.last { float: left !important; padding-right: 0; }
.directorynav h3 { color: #666; margin: 0; font-size: 12px; line-height: 14px; font-weight: bold; padding-bottom: 10px; }
.directorynav a:hover h3 { color: #1A6CC1;}
.directorynav h3.standalone { margin-bottom: 18px; }
.directorynav a { display: block; zoom: 1; line-height: 22px;}
.directorynav h3.standalone a:link,
.directorynav h3.standalone a:visited { color: #444; }
.directorynav ul { margin-bottom: 0; padding-bottom: 9px; }
.directorynav ul li { }

.directorynav #dn-cola { width: 160px; }
.directorynav #dn-colb { width: 114px; }
.directorynav #dn-colc { width: 114px; }
.directorynav #dn-cold { width: 114px; }
.directorynav #dn-cole { width: 120px; }
.directorynav #dn-colf { width: 130px; }

footer .links { 
  margin: 0 auto;
  margin-bottom: 14px;
  font-size: 12px;
}

footer .links h3 {
  display: inline;
}

footer .links a {
  margin-left: 5px;
}
footer .copyright {  
  margin: 0 auto;
  width：10;
  padding-top: 16px;
  margin-bottom: 14px;
  font-size: 12px;
  text-align: center;
  border-top: 1px dotted #cbcccd;
  line-height: 16px;
}





    
 .border-topdiv{
    
    background-color: #efefe;
    border-bottom: 1px solid #C6DCC6;
    border-top: 2px solid #68BB68;
    margin-top: -20;    
}
  .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      } 

.clear{
clear: both;
}
    .carousel {
      margin-bottom: 5px;
    }

    .carousel .container {
      position: relative;
      z-index: 9;
    }

    .carousel-control {
      height: 50px;
      margin-top: 0;
      font-size: 100px;
      text-shadow: 0 1px 1px rgba(0,0,0,.4);
      background-color: transparent;
      border: 0;
      z-index: 10;
    }

    .carousel .item {
      width:100%;
      
      height: 500px;
    }
    .carousel img {
      margin-left: auto;
      margin-right: auto;
      width:970;
      height: 500px;
    }

    .carousel-caption {
      background-color: transparent;
      position: static;
      max-width: 550px;
      padding: 0 20px;
      margin-top: 120px;
    }
    .carousel-caption h1,
    .carousel-caption .lead {
      margin: 0;
      line-height: 1.25;
      color: #fff;
      text-shadow: 0 1px 1px rgba(0,0,0,.4);
    }
    .carousel-caption .btn {
      margin-top: 10px;
    }


 /* RESPONSIVE CSS
    -------------------------------------------------- */

    @media (max-width: 970px) {
    .navbar-wrapper {
      position: absolute;
      top: 0;
      left: 0;
      right: 0;
      z-index: 10;
      margin-top: 50px;       
      margin-bottom: -90px; /* Negative margin to pull up carousel. 90px is roughly margins and height of navbar. */
    }  
      .container.navbar-wrapper {
        margin-bottom: 0;
        width: auto;
      }
      .navbar-inner {
        border-radius: 0;
        margin: -20px 0;
      }

      .carousel .item {
        width: 100%;       
        height: 500px;
      }
      .carousel img {
       margin-left: auto;
       margin-right: auto;
        
        width:970;
        height: 500px;
      }

   
    }


    @media (max-width: 767px) {
	.navbar-wrapper {
      position: absolute;
      top: 0;
      left: 0;
      right: 0;
      z-index: 10;
      margin-top: 50px;       
      margin-bottom: -90px; /* Negative margin to pull up carousel. 90px is roughly margins and height of navbar. */
    }  
      .navbar-inner {
        margin: -20px;
      }

      .carousel {
        margin-left: -20px;
        margin-right: -20px;
      }
      .carousel .container {

      }
      .carousel .item {
        height: 350px;
      }
      .item img {
        margin-left: auto;
        margin-right: auto;
        width: 700px;
        height: 350px;
      }
      .carousel-caption {
        width: 65%;
        padding: 0 70px;
        margin-top: 80px;
      }
      .carousel-caption h1 {
        font-size: 30px;
      }
      .carousel-caption .lead,
      .carousel-caption .btn {
        font-size: 18px;
      }
 
    }
   
 </style>
    <!--[if lt IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<%=basePath %>assets/ico/apple-touch-icon-144-precomposed.png"/>
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<%=basePath %>assets/ico/apple-touch-icon-114-precomposed.png"/>
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<%=basePath %>assets/ico/apple-touch-icon-72-precomposed.png"/>
 	<link rel="apple-touch-icon-precomposed" href="<%=basePath %>assets/ico/apple-touch-icon-57-precomposed.png"/>
	<link rel="shortcut icon" href="<%=basePath %>assets/ico/favicon.png"/>
  </head>
<body>
<div id="loginModel2" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="loginLab" aria-hidden="true">
    <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="loginLab">用户登录</h3>
  </div>
  <div class="modal-body "> 
		    <form class="form-signin" id="form1" >
	        <h2 class="form-signin-heading">登录</h2>
	        <input type="text" class="input-block-level" id="name" name="name" placeholder="请输入登录的邮箱地址"/>
	        <input type="password" class="input-block-level" id="password" name="password" placeholder="请输入登录的密码"/>
	        <label class="checkbox">
	          <span> <input type="checkbox" value="remember-me" />记住我  </span> 
	        </label>	       
	        <button id="login-btn" class="btn btn-large btn-primary" type="button">&nbsp;登&nbsp;&nbsp;录&nbsp; </button>
	        <button id="resgister-btn" class="btn btn-large btn-success" type="button">&nbsp;注&nbsp;&nbsp;册&nbsp; </button>
	        
	       
	      </form>	 
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button> 
  </div> 
  </div>
<%@include file="common/nav-top.jsp" %> 
<%@include file="common/wrapper.jsp" %> 

    
   


  <!-- Carousel
    ================================================== -->
    <div id="myCarousel" class="carousel slide">
      <div class="carousel-inner">
        <div class="item active" style="background: url(assets/images/login/banner_bg01.gif) repeat">
          <img src="assets/images/login/01.jpg" alt="">
          
        </div>
        
        <div class="item "  style="background: url(assets/images/login/banner_bg02.gif) repeat">
          <img src="assets/images/login/02.png" width="970" height="500" alt="">           
          </div>
           <div class="item" style="background: url(assets/images/login/banner_bg03.gif) repeat">
          <img src="assets/images/login/03.jpg" width="970" height="500"  alt="" >
        </div>             
      </div>
      <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
      <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
    </div><!-- /.carousel -->
    <div style="algin:center; width:90%;  margin-left:auto; margin-right:auto;">
    
 <div class="row" >
 <section class="features">
  <div class="row-fliud ">   
  <span class="span4">
      <a href="product/build" class="block">
        <h3>互联网的系统建设</h3>
        <p>我们立足移动互联网和云计算的大时代，为您卓越的解决方案。</p>
        <div class='down' id='feature-1-b'></div>
        <div class='up' id='feature-1-a'></div>
      </a>
   </span>
    <span class="span4">
      <a href="product/technology" class="block">
        <h3>监视浏览器自动调整界面</h3>
        <p>我们拥有自身研发的前端技术能够自动调整和适配浏览器和硬件设备。</p>
        <div class='down' id='feature-3-b'></div>
        <div class='up' id='feature-3-a'></div>
      </a>
    </span>
    <span class="span4">
      <a href="product/design" class="block">
        <h3>完善的开发交付流程</h3>
        <p>我们拥有最权威和最专业的需求分析师，能够与您进行最有效的沟通快速定位您的需求</p>
        <div class='down' id='feature-4-b'></div>
        <div class='up' id='feature-4-a'></div>
      </a>
    </span>
       <span class="span4">
      <a href="product/capability" class="block  ">
        <h3>性能强劲</h3>     
   <p>采用目前主流的J2EE核心技术，通过自身研发的中间件，缓存框架， 以及Hadoop等云端技术切实为您解决系统的性能瓶颈！使您的系统快如闪电.</p>
        <div class='down' id='feature-2-b'></div>
        <div class='up' id='feature-2-a'></div>
      </a>
    </span>
    <span class="span4">
      <a href="product/support" class="block">
        <h3>7x24小时技术支持</h3>
        <p>我们提供 7x24 专家级服务，时刻在线，快速解决问题</p>
        <div class='down' id='feature-5-b'></div>
        <div class='up' id='feature-5-a'></div>
      </a>
    </span>
   
    <span class="span4">
      <a href="product/service" class="block">
        <h3>了解更多</h3>
        <p>系统建设，搜索引擎建设和优化，专业站点建设服务，电子商务支付系统建设，企业网页聊天工具建设、企业一站式平台建设，流程引擎的开发和定制,云端Hadoop存储术方案建设</p>
        <div class='down' id='feature-6-b'></div>
        <div class='up' id='feature-6-a'></div>
      </a>
    </span>
  </div>
</section>
 </div>
 </div>
  <div class="row">
<section id='enterprise' class="enterprise">
  <a  class="block offset1">
    <h2>企业用户</h2>
    <p>我们不仅有面向个人网站的提供服务，还有专门为企业量身打造的付费系统级的建设服务。在这里您得到全面的服务支持。</p>
    <p class="contact-header">你可以 <span>了解更多详情</span>，或立即在线咨询</p>
  </a>
  <div class="contact offset1 ">
    <img src="${basePath}assets/images/phone.gif" width="15" height="15" alt="使用400电话联系 DNSPod">
    <span>(+86)186-0301-5170</span>
       <img src="${basePath}assets/images/qq-na.gif" width="15" height="16" alt="800020304"><span>921747919</span>  </div>
</section> 
</div>
          
<script>
   function userLogin(){
	   $.ajax({type:"post",
		       url:"user/user!showLogin.action",		     
		       success:function(rs){
		    	   alert(rs);
		    	   $("#loginModel").html(rs);
		    	   $("#loginModel").show();		    	   
		       } 		 
		});
   }      
    </script> 
 <script src="http://www.bootcss.com/p/unslider/jquery.event.move.js"></script>
 <script src="http://www.bootcss.com/p/unslider/jquery.event.swipe.js"></script>
 <script src="<%=basePath %>assets/js/projects.js"></script>
 <script src="<%=basePath %>assets/js/holder/holder.js"></script>
<script src="<%=basePath %>assets/js/google-code-prettify/prettify.js"></script>
<script src="<%=basePath %>assets/js/application.js"/></script>
<script src="<%=basePath %>assets/js/bootstrap-transition.js"></script>
<script src="<%=basePath %>assets/js/bootstrap-carousel.js"></script>
   
<script src="<%=basePath %>assets/js/bootstrap-button.js"></script>
 
<script src="<%=basePath %>assets/js/bootstrap-typeahead.js"></script>
    <script>
      !function ($) {
        $(function(){        
          $('#myCarousel').carousel()
        })
      }(window.jQuery)
    </script>
    <script> 
</script>  

 <!-- footer
    ================================================== -->
        
    <footer   >
      <nav>
        <ol class="breadcrumbs">
                      <li class="home">首页</li>
                  </ol>
        <div class="directorynav">
          <div id="dn-cola" class="column first">
            <h3>为什么选择枪杆子<h3>
            <ul>
              <li><a href="product/customers">谁在选择使用 枪子服务</a></li>             
            </ul>
          </div>
          <div id="dn-colb" class="column">
            <h3>产品介绍</h3>
            <ul>
              <li><a href="/product">智能建站</a></li>
              <li><a href="/product">应用系统</a></li>
              <li><a href="/product/dtoken">电子商务支付</a></li>
              <li><a href="/plans/personal">微博平台</a></li>
              <li><a href="/plans/enterprise">企业服务</a></li>
            </ul>
          </div>
          <div id="dn-colc" class="column">
            <a href="product/contactus"><h3>帮助中心</h3></a>
            <ul>
               <li><a href="/Support">在线反馈</a></li>
              <li><a href="#">常见问题</a></li>
              <li><a href="#">文档资料</a></li>              
            </ul>
          </div>
          <div id="dn-cold" class="column">
            <h3>其他</h3>
            <ul>
              <li><a href="#">API</a></li>
              <li><a href="#">客户端</a></li>
              <li><a href="#" target="_blank">手机版</a></li>
              <li><a href="#" target="_blank">开源</a></li>
            </ul>
          </div>
          <div id="dn-cole" class="column">
            <h3>关于</h3>
            <ul>
              <li><a href="/about/Aboutus">关于我们</a></li>
              <li><a href="/about/blog" target="_blank">官方博客</a></li>
              <li><a href="/obs">工作机会</a></li>
              <li><a href="/about/Cooperator">合作伙伴</a></li>
              <li><a href="/about/Link">友情链接</a></li>
            </ul>
          </div>
          <div id="dn-colf" class="column last">
            <h3>联系我们</h3>
            <ul>
              <li>  (+86)186-0301-5170</li>
              <li>  (+86)159-2006-4230</li>
              <li><a href="#">技术支持</a></li>
              <li><a href="#">投诉建议</a></li>
              <li class="weibo">
                 <a href="http://t.qq.com/macower" title="Macower腾讯微博"><img src="${basePath}assets/images/tqq.ico" alt="DMacower腾讯微博"></a>  
                 <a href="http://weibo.com/macower" title="Macower新浪微博"><img src="${basePath}assets/images/tsina.ico" alt="Macower新浪微博"></a> 
                 <a href="https://www.facebook.com/Macower" title="Macower Facebook"><img src="${basePath}assets/images/facebook.ico" alt="Macower Facebook"></a> 
                 <a href="https://twitter.com/Macower" title="Macower twitter"><img src="${basePath}assets/images/twitter.ico" alt="Macower twitter"></a> 
                 <a href="#"><img src="${basePath}assets/images/weixin.png" alt="Macower Weixin"></a> 
              </li>
            </ul>
          </div>
          <div class="c"></div>
        </div>
      </nav>
     
      <section class="copyright">
        <div>&copy; 2013 <a href="http://www.macower.com/" title="最稳定最安全的智能系统网站建设团队">Macower.Inc</a>  All rights reserved.</div>        
       
      </section>
    </footer> 
  </body>   
</html>
