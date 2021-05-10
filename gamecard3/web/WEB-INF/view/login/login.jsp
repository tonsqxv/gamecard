<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String path = request.getContextPath();    
//获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:    
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>研发管理平台 </title>
<link href="<%=basePath %>/assets/css/login/login.css" rel="stylesheet" type="text/css" />
<script type="text/JavaScript">
<!--
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

function initLogin() {
    if (window.self != window.top) { //frame window
    	window.top.location = window.self.location;
    	return;
    }
    if (window.opener && ! window.opener.closed) { //pop window
    	window.opener.location.reload();
    	window.close();
    	return;
    }
if ('' != '') {
		if(''=='1'){
			alert("登录失败:帐号不存在");
		}else if(''=='2'){
			alert("登录失败:帐号已被禁用");
		}else if(''=='3'){
			alert("登录失败:用户名不合法");
		}else if(''=='4'){
			alert("登录失败:密码不合法");
		}else if(''=='5'){
			alert("登录失败:密码错误");
		}else if(''=='6'){
			alert("登录失败:系统错误");
		}
		else if(''=='7'){
			alert("大陆环境断开且台湾本地库密码错误，若近期修改过密码，请尝试用旧密码重新登录！");
		}else if(''=='8'){
			alert("域用户验证异常");
		}else if(''=='9'){
			alert("您输入的验证码不正确或验证码已经过期!");
		}
	}
	document.getElementById('username').focus();
}

function login() {
	/*var u = document.getElementById('username').value;
	var p = document.getElementById('password').value;
	if (u == null || u == '') {
		alert('用户名不能为空!');
		return;
	}
	if (u.length < 3) {
		alert('用户名不能小于3个字符!');
		return;
	}
	if(p == null || p == ''){
        alert( '密码' + '不能为空');
        return false;
    }
    var verifyCode = document.getElementById('verifyCode').value;
    if(verifyCode == null || verifyCode == "" ||verifyCode.length < 4 )
	{
		alert("请输入4位验证码!");
		return false;
	}*/
    
	document.getElementById('loginForm').submit();
}
// 检验密码，包括长度为八位，必需为数据与字母混合等
function checkPassword(password, fieldname) {
    if(password == null || password == ''){
        alert( fieldname + '不能为空');
        return false;
    }
    if(password.length < 8){
        alert( fieldname + '长度必须大于八位');
        return false;
    }
    var pwdPtn = /^[\x21-\x7e]*$/;
	if(!pwdPtn.test(password)){
	    alert( fieldname + '必须由数字和英文字母组成');
	    return false;
	}
    //判断是否数字和英文组成
    var numFlag = false;
    var charFlag = false;
    for(var i =0; i < password.length; i++){
        if(password.charAt(i) >= '0' && password.charAt(i) <= '9'){
            numFlag = true;
        }
        if( ( password.charAt(i) >= 'A' && password.charAt(i) <= 'Z' ) || ( password.charAt(i) >= 'a' && password.charAt(i) <= 'z' )){
            charFlag = true;
        }
    }
    if(!charFlag || !numFlag ) {
        alert(fieldname + '必须由数字和英文字母组成');
		return false;
    }
    return true;
}

//-->

function changeImg(action) {
		var imgSrc = document.getElementById("imgObj");
		var timestamp = (new Date()).valueOf(); 
		var src = action+"?timestamp="+timestamp; 
		imgSrc.src= src;  
	}  

</script>

</head>
<body onload="initLogin();" class="bg_g2">
<c:if test="${!empty error }">
	<script type="text/JavaScript">
	alert('${error }');
	</script>
</c:if>

<form id="loginForm" name="loginForm" action="login" method="post">
  <div>
     <ul class="l_ti lh61">
       <li class="wid1002 mpc"><img src="<%=basePath %>/assets/images/login/l_logo.gif" /></li>
     </ul>
    <ul class="l_n lh90">
       <li class="wid1002 mpc"><img src="<%=basePath %>/assets/images/login/l_p_name.jpg"/></li>
    </ul>
     <ul class="bg_b lh229">
       <li class="mpc l_mi">&nbsp;</li> 
    </ul>
     <ul class="bg_g">
       <li class="wid1002 mpc">
         <div class="p_l48">
         <dl class="p_t20 gray1 fl">
              <b class="f14 lh46">用户名：</b>
              <dt>
           <dd><img src="<%=basePath %>/assets/images/login/l_btn_l_i.jpg" class="fl"/><div class="fl logn_inbg1 lh37 wid238"><input id="username" name="userName" type="text"  class="inp1 lh37 logn_in1"/></div></dd>
              </dt>
         </dl>
          <dl class="p_t20 gray1 fl ma_l84">
              <b class="f14 lh46 ma_r140">密&nbsp;&nbsp;码：</b>
            <dt>      
                <img src="<%=basePath %>/assets/images/login/l_btn_l_i.jpg" class="fl"/>
                <div class="fl logn_inbg1 lh37 wid238"><input id="password" name="password" type="password"  class="inp1 lh37 logn_in1"/></div>
            </dt>
          </dl>
          
          <dl class="p_t20 gray1 fl ma_l84">
              <b class="f14 lh46">验证码：</b>
              <dt>
                <div><img src="<%=basePath %>/assets/images/login/l_btn_l_i.jpg" class="fl"/>
                <ul class="fl logn_inbg1 lh37 wid106">
                <input name="verifyCode" id="verifyCode" type="text"  class="inp1 lh37 logn_iny" onkeypress="if(event.keyCode == 13) login();"/>
                </ul>
                <img id="imgObj" class="fl ma_l14 ma_t8" src="<%=basePath%>/checkImgCode/createImgCode">&nbsp;&nbsp;<a href="javascript:changeImg('<%=basePath%>/checkImgCode/createImgCode')" class="red_t lh37">换一张</a>
                </div>
              </dt>
          </dl>
           
          <dl class="cl p_t20"></dl>
          </div>
         <dl class="tc p_b36"><a href="#" onclick="login()"><img src="<%=basePath %>/assets/images/login/l_btn.jpg" border="0"/></a></dl></li> 
     </ul>
     <ul>
       <li class="bg_g1 lh21">&nbsp;</li>
        <li class="bg_g2">
        <div class="wid1002 mpc tc p_t8">
          <br />
          枪杆子 Copyright ©2013 sf-express.com Inc. All rights reserved.
        </div>
        
        </li>
     </ul>
</div>
</form>
</body>
</html>