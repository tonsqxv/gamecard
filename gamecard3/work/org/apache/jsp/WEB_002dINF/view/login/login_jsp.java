package org.apache.jsp.WEB_002dINF.view.login;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

String path = request.getContextPath();    
//获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:    
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 

      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n");
      out.write("<title>研发管理平台 </title>\n");
      out.write("<link href=\"");
      out.print(basePath );
      out.write("/assets/css/login/login.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("<script type=\"text/JavaScript\">\n");
      out.write("<!--\n");
      out.write("function MM_swapImgRestore() { //v3.0\n");
      out.write("  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;\n");
      out.write("}\n");
      out.write("\n");
      out.write("function MM_preloadImages() { //v3.0\n");
      out.write("  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();\n");
      out.write("    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)\n");
      out.write("    if (a[i].indexOf(\"#\")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}\n");
      out.write("}\n");
      out.write("\n");
      out.write("function MM_findObj(n, d) { //v4.01\n");
      out.write("  var p,i,x;  if(!d) d=document; if((p=n.indexOf(\"?\"))>0&&parent.frames.length) {\n");
      out.write("    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}\n");
      out.write("  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];\n");
      out.write("  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);\n");
      out.write("  if(!x && d.getElementById) x=d.getElementById(n); return x;\n");
      out.write("}\n");
      out.write("\n");
      out.write("function MM_swapImage() { //v3.0\n");
      out.write("  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)\n");
      out.write("   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}\n");
      out.write("}\n");
      out.write("\n");
      out.write("function initLogin() {\n");
      out.write("    if (window.self != window.top) { //frame window\n");
      out.write("    \twindow.top.location = window.self.location;\n");
      out.write("    \treturn;\n");
      out.write("    }\n");
      out.write("    if (window.opener && ! window.opener.closed) { //pop window\n");
      out.write("    \twindow.opener.location.reload();\n");
      out.write("    \twindow.close();\n");
      out.write("    \treturn;\n");
      out.write("    }\n");
      out.write("if ('' != '') {\n");
      out.write("\t\tif(''=='1'){\n");
      out.write("\t\t\talert(\"登录失败:帐号不存在\");\n");
      out.write("\t\t}else if(''=='2'){\n");
      out.write("\t\t\talert(\"登录失败:帐号已被禁用\");\n");
      out.write("\t\t}else if(''=='3'){\n");
      out.write("\t\t\talert(\"登录失败:用户名不合法\");\n");
      out.write("\t\t}else if(''=='4'){\n");
      out.write("\t\t\talert(\"登录失败:密码不合法\");\n");
      out.write("\t\t}else if(''=='5'){\n");
      out.write("\t\t\talert(\"登录失败:密码错误\");\n");
      out.write("\t\t}else if(''=='6'){\n");
      out.write("\t\t\talert(\"登录失败:系统错误\");\n");
      out.write("\t\t}\n");
      out.write("\t\telse if(''=='7'){\n");
      out.write("\t\t\talert(\"大陆环境断开且台湾本地库密码错误，若近期修改过密码，请尝试用旧密码重新登录！\");\n");
      out.write("\t\t}else if(''=='8'){\n");
      out.write("\t\t\talert(\"域用户验证异常\");\n");
      out.write("\t\t}else if(''=='9'){\n");
      out.write("\t\t\talert(\"您输入的验证码不正确或验证码已经过期!\");\n");
      out.write("\t\t}\n");
      out.write("\t}\n");
      out.write("\tdocument.getElementById('username').focus();\n");
      out.write("}\n");
      out.write("\n");
      out.write("function login() {\n");
      out.write("\t/*var u = document.getElementById('username').value;\n");
      out.write("\tvar p = document.getElementById('password').value;\n");
      out.write("\tif (u == null || u == '') {\n");
      out.write("\t\talert('用户名不能为空!');\n");
      out.write("\t\treturn;\n");
      out.write("\t}\n");
      out.write("\tif (u.length < 3) {\n");
      out.write("\t\talert('用户名不能小于3个字符!');\n");
      out.write("\t\treturn;\n");
      out.write("\t}\n");
      out.write("\tif(p == null || p == ''){\n");
      out.write("        alert( '密码' + '不能为空');\n");
      out.write("        return false;\n");
      out.write("    }\n");
      out.write("    var verifyCode = document.getElementById('verifyCode').value;\n");
      out.write("    if(verifyCode == null || verifyCode == \"\" ||verifyCode.length < 4 )\n");
      out.write("\t{\n");
      out.write("\t\talert(\"请输入4位验证码!\");\n");
      out.write("\t\treturn false;\n");
      out.write("\t}*/\n");
      out.write("    \n");
      out.write("\tdocument.getElementById('loginForm').submit();\n");
      out.write("}\n");
      out.write("// 检验密码，包括长度为八位，必需为数据与字母混合等\n");
      out.write("function checkPassword(password, fieldname) {\n");
      out.write("    if(password == null || password == ''){\n");
      out.write("        alert( fieldname + '不能为空');\n");
      out.write("        return false;\n");
      out.write("    }\n");
      out.write("    if(password.length < 8){\n");
      out.write("        alert( fieldname + '长度必须大于八位');\n");
      out.write("        return false;\n");
      out.write("    }\n");
      out.write("    var pwdPtn = /^[\\x21-\\x7e]*$/;\n");
      out.write("\tif(!pwdPtn.test(password)){\n");
      out.write("\t    alert( fieldname + '必须由数字和英文字母组成');\n");
      out.write("\t    return false;\n");
      out.write("\t}\n");
      out.write("    //判断是否数字和英文组成\n");
      out.write("    var numFlag = false;\n");
      out.write("    var charFlag = false;\n");
      out.write("    for(var i =0; i < password.length; i++){\n");
      out.write("        if(password.charAt(i) >= '0' && password.charAt(i) <= '9'){\n");
      out.write("            numFlag = true;\n");
      out.write("        }\n");
      out.write("        if( ( password.charAt(i) >= 'A' && password.charAt(i) <= 'Z' ) || ( password.charAt(i) >= 'a' && password.charAt(i) <= 'z' )){\n");
      out.write("            charFlag = true;\n");
      out.write("        }\n");
      out.write("    }\n");
      out.write("    if(!charFlag || !numFlag ) {\n");
      out.write("        alert(fieldname + '必须由数字和英文字母组成');\n");
      out.write("\t\treturn false;\n");
      out.write("    }\n");
      out.write("    return true;\n");
      out.write("}\n");
      out.write("\n");
      out.write("//-->\n");
      out.write("\n");
      out.write("function changeImg(action) {\n");
      out.write("\t\tvar imgSrc = document.getElementById(\"imgObj\");\n");
      out.write("\t\tvar timestamp = (new Date()).valueOf(); \n");
      out.write("\t\tvar src = action+\"?timestamp=\"+timestamp; \n");
      out.write("\t\timgSrc.src= src;  \n");
      out.write("\t}  \n");
      out.write("\n");
      out.write("</script>\n");
      out.write("\n");
      out.write("</head>\n");
      out.write("<body onload=\"initLogin();\" class=\"bg_g2\">\n");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\n");
      out.write("<form id=\"loginForm\" name=\"loginForm\" action=\"login\" method=\"post\">\n");
      out.write("  <div>\n");
      out.write("     <ul class=\"l_ti lh61\">\n");
      out.write("       <li class=\"wid1002 mpc\"><img src=\"");
      out.print(basePath );
      out.write("/assets/images/login/l_logo.gif\" /></li>\n");
      out.write("     </ul>\n");
      out.write("    <ul class=\"l_n lh90\">\n");
      out.write("       <li class=\"wid1002 mpc\"><img src=\"");
      out.print(basePath );
      out.write("/assets/images/login/l_p_name.jpg\"/></li>\n");
      out.write("    </ul>\n");
      out.write("     <ul class=\"bg_b lh229\">\n");
      out.write("       <li class=\"mpc l_mi\">&nbsp;</li> \n");
      out.write("    </ul>\n");
      out.write("     <ul class=\"bg_g\">\n");
      out.write("       <li class=\"wid1002 mpc\">\n");
      out.write("         <div class=\"p_l48\">\n");
      out.write("         <dl class=\"p_t20 gray1 fl\">\n");
      out.write("              <b class=\"f14 lh46\">用户名：</b>\n");
      out.write("              <dt>\n");
      out.write("           <dd><img src=\"");
      out.print(basePath );
      out.write("/assets/images/login/l_btn_l_i.jpg\" class=\"fl\"/><div class=\"fl logn_inbg1 lh37 wid238\"><input id=\"username\" name=\"userName\" type=\"text\"  class=\"inp1 lh37 logn_in1\"/></div></dd>\n");
      out.write("              </dt>\n");
      out.write("         </dl>\n");
      out.write("          <dl class=\"p_t20 gray1 fl ma_l84\">\n");
      out.write("              <b class=\"f14 lh46 ma_r140\">密&nbsp;&nbsp;码：</b>\n");
      out.write("            <dt>      \n");
      out.write("                <img src=\"");
      out.print(basePath );
      out.write("/assets/images/login/l_btn_l_i.jpg\" class=\"fl\"/>\n");
      out.write("                <div class=\"fl logn_inbg1 lh37 wid238\"><input id=\"password\" name=\"password\" type=\"password\"  class=\"inp1 lh37 logn_in1\"/></div>\n");
      out.write("            </dt>\n");
      out.write("          </dl>\n");
      out.write("          \n");
      out.write("          <dl class=\"p_t20 gray1 fl ma_l84\">\n");
      out.write("              <b class=\"f14 lh46\">验证码：</b>\n");
      out.write("              <dt>\n");
      out.write("                <div><img src=\"");
      out.print(basePath );
      out.write("/assets/images/login/l_btn_l_i.jpg\" class=\"fl\"/>\n");
      out.write("                <ul class=\"fl logn_inbg1 lh37 wid106\">\n");
      out.write("                <input name=\"verifyCode\" id=\"verifyCode\" type=\"text\"  class=\"inp1 lh37 logn_iny\" onkeypress=\"if(event.keyCode == 13) login();\"/>\n");
      out.write("                </ul>\n");
      out.write("                <img id=\"imgObj\" class=\"fl ma_l14 ma_t8\" src=\"");
      out.print(basePath);
      out.write("/checkImgCode/createImgCode\">&nbsp;&nbsp;<a href=\"javascript:changeImg('");
      out.print(basePath);
      out.write("/checkImgCode/createImgCode')\" class=\"red_t lh37\">换一张</a>\n");
      out.write("                </div>\n");
      out.write("              </dt>\n");
      out.write("          </dl>\n");
      out.write("           \n");
      out.write("          <dl class=\"cl p_t20\"></dl>\n");
      out.write("          </div>\n");
      out.write("         <dl class=\"tc p_b36\"><a href=\"#\" onclick=\"login()\"><img src=\"");
      out.print(basePath );
      out.write("/assets/images/login/l_btn.jpg\" border=\"0\"/></a></dl></li> \n");
      out.write("     </ul>\n");
      out.write("     <ul>\n");
      out.write("       <li class=\"bg_g1 lh21\">&nbsp;</li>\n");
      out.write("        <li class=\"bg_g2\">\n");
      out.write("        <div class=\"wid1002 mpc tc p_t8\">\n");
      out.write("          <br />\n");
      out.write("          枪杆子 Copyright ©2013 sf-express.com Inc. All rights reserved.\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        </li>\n");
      out.write("     </ul>\n");
      out.write("</div>\n");
      out.write("</form>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fif_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent(null);
    // /WEB-INF/view/login/login.jsp(146,0) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty error }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("\t<script type=\"text/JavaScript\">\n");
        out.write("\talert('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${error }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("');\n");
        out.write("\t</script>\n");
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }
}
