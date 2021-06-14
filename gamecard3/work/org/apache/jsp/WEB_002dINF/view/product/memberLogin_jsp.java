package org.apache.jsp.WEB_002dINF.view.product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class memberLogin_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(2);
    _jspx_dependants.add("/WEB-INF/view/product/../common/taglibs.jsp");
    _jspx_dependants.add("/WEB-INF/view/product/head.jsp");
  }

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

      out.write('\n');
      out.write('\n');
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

	response.setHeader("Pragma","No-cache");
	//response.setHeader("Cache-Control","public");
	//response.setDateHeader("Last-Modified", System.currentTimeMillis());
	//response.setDateHeader("Expires", System.currentTimeMillis() + 1000 * 60 * 5);

      out.write('\n');
      out.write('\n');

String path = request.getContextPath();    
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path; 


      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <script src=\"");
      out.print(basePath );
      out.write("/assets/js/common/util.js\"></script>\n");
      out.write("\n");
      out.write("  </head>\n");
      out.write("  <body>\n");
      out.write("  \n");
      out.write("   ");
      out.write(" \n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

	response.setHeader("Pragma","No-cache");
	//response.setHeader("Cache-Control","public");
	//response.setDateHeader("Last-Modified", System.currentTimeMillis());
	//response.setDateHeader("Expires", System.currentTimeMillis() + 1000 * 60 * 5);

      out.write('\n');
      out.write('\n');
      out.write('\n');

	//获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:    
	String basePath4head = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();   

      out.write("  \n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<nav class=\"navbar navbar-default\" role=\"navigation\">\n");
      out.write("  <!-- Brand and toggle get grouped for better mobile display -->\n");
      out.write("  <div class=\"navbar-header\">\n");
      out.write("    <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-ex1-collapse\">\n");
      out.write("      <span class=\"sr-only\">Toggle navigation</span>\n");
      out.write("      <span class=\"icon-bar\"></span>\n");
      out.write("      <span class=\"icon-bar\"></span>\n");
      out.write("      <span class=\"icon-bar\"></span>\n");
      out.write("    </button>\n");
      out.write("    <a class=\"navbar-brand\" href=\"#\">NAVIGATOR</a>\n");
      out.write("  </div>\n");
      out.write("\n");
      out.write("  <!-- Collect the nav links, forms, and other content for toggling -->\n");
      out.write("  <div class=\"collapse navbar-collapse navbar-ex1-collapse\">\n");
      out.write("    <ul class=\"nav navbar-nav\">\n");
      out.write("     \t<li class=\"active\"><a href=\"");
      out.print(basePath4head );
      out.write("/index\">Home</a></li>\n");
      out.write("\t\t<li class=\"divider-vertical\"></li>  \n");
      out.write("\t\t<li><a href=\"");
      out.print(basePath4head);
      out.write("/help/about\">About Us</a></li>\n");
      out.write("\t\t<li><a href=\"");
      out.print(basePath4head);
      out.write("/help/contact\">Contact Us</a></li>\n");
      out.write("\t\t<li><a href=\"");
      out.print(basePath4head);
      out.write("/help/shippingAndReturn\">Shipping&amp;Returns</a></li>\n");
      out.write("\t    <li class=\"divider-vertical\"></li>  \n");
      out.write("\t\t<li><a href=\"");
      out.print(basePath4head);
      out.write("/help/\">Help&amp;Faqs</a></li>\n");
      out.write("\t\t<li><a href=\"");
      out.print(basePath4head);
      out.write("/news/\">News</a></li>\n");
      out.write("      \n");
      out.write("    </ul>\n");
      out.write("    <form class=\"navbar-form navbar-right\" role=\"search\" action=\"");
      out.print(basePath4head);
      out.write("/product/searchBy\">\n");
      out.write("      <div class=\"form-group\">\n");
      out.write("        <input type=\"text\" name=\"searchByParam_productName\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${searchByParam_productName }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" class=\"form-control\" placeholder=\"Search\">\n");
      out.write("      </div>\n");
      out.write("      <button type=\"submit\" class=\"btn btn-default\">Submit</button>\n");
      out.write("    </form>\n");
      out.write("    \n");
      out.write("    \n");
      out.write("  </div><!-- /.navbar-collapse -->\n");
      out.write("</nav>\n");
      out.write("\n");
      out.write("    ");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\n");
      out.write("    \n");
      out.write("     <div class=\"container\">\n");
      out.write("\n");
      out.write("      <form id=\"loginForm\" class=\"\" action=\"");
      out.print(basePath);
      out.write("/member/memberLogin\" method=\"post\">\n");
      out.write("      <div class=\"row\">\n");
      out.write("\t\t    <div class=\"col-lg-4 col-lg-offset-4\">\n");
      out.write("\t\t        <h2 class=\"form-signin-heading\">Please sign in</h2>\n");
      out.write("\t\t        <div><font color=\"red\"><span id=\"error\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${error}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</span></font></div>\n");
      out.write("\t\t        <input id=\"email\" class=\"form-control\" type=\"text\" name=\"email\" maxlength=\"50\" class=\"input-block-level\" placeholder=\"email...\" autofocus/>\n");
      out.write("\t\t        <br>\n");
      out.write("\t\t        <input id=\"password\" class=\"form-control\" type=\"password\" name=\"password\" maxlength=\"50\" class=\"input-block-level\" placeholder=\"password...\"/>\n");
      out.write("\t\t        <label class=\"checkbox\">\n");
      out.write("\t\t          <input type=\"checkbox\" value=\"remember-me\"> Remember me\n");
      out.write("\t\t        </label>\n");
      out.write("\t\t        <button class=\"btn btn-lg btn-primary btn-block\" type=\"button\" onclick=\"verifySubmit()\">Sign in</button>\n");
      out.write("\t        </div>\n");
      out.write("\t        <div class=\"col-lg-4 col-lg-offset-4\">\n");
      out.write("\t        <a href=\"");
      out.print(basePath);
      out.write("/member/findPassword\" style=\"text-decoration:underline;\">Forgot your password?</a>\n");
      out.write("\t\t    <p align=\"right\">&nbsp;&nbsp;<a href=\"");
      out.print(basePath );
      out.write("/member/register\" style=\"text-decoration:underline;\">Register now</a></p>\n");
      out.write("\t\t\t    \t\n");
      out.write("\t        </div>\n");
      out.write("        </div>\n");
      out.write("      </form>\n");
      out.write("\n");
      out.write("    </div> <!-- /container -->\n");
      out.write("    <br>\n");
      out.write("    <div class=\"row\">    \n");
      out.write("    \t<div class=\"col-lg-12 col-lg-offset-5\">\n");
      out.write("    \t <h4>Sign in help</h4>           \n");
      out.write("           &nbsp;&nbsp;Forgot your password?&nbsp;&nbsp;<a href=\"#\" style=\"text-decoration:underline;\">Get Password Help</a>      \n");
      out.write("    \t</div> \n");
      out.write("    </div>\n");
      out.write("    <br/><br/><br/>\n");
      out.write(" \n");
      out.write(" <script type=\"text/javascript\">   \n");
      out.write(" $(document).ready(function(){ \n");
      out.write("\t \t\n");
      out.write("\t   \n");
      out.write("\t   $(\"#email\").blur(function(){\n");
      out.write("\t\t   verfiyEmail();\n");
      out.write("\t        });  \n");
      out.write("\t   \n");
      out.write("\t   $(\"#password\").blur(function(){\n");
      out.write("\t\t   verfiyPassword();\n");
      out.write("\t        });  \n");
      out.write("\t   \n");
      out.write("\t   \n");
      out.write("\t   });   \n");
      out.write(" \t\n");
      out.write(" function verfiyEmail(){\n");
      out.write("\t var email = trim($(\"#email\").val());\t         \n");
      out.write("\t    if(email == \"\"){\n");
      out.write("\t\t   \t $(\"#error\").html(\"<i class='icon-remove'></i>Please input your e-mail\");\n");
      out.write("\t\t   \treturn false;\n");
      out.write("\t    }\n");
      out.write("\t    \n");
      out.write("    \t$.ajax({\n");
      out.write("\t\t\t  type:\"post\",\n");
      out.write("\t\t\t  cache:false,    \n");
      out.write("\t\t\t  dataType : \"json\",\n");
      out.write("\t\t\t  url:\"");
      out.print(basePath);
      out.write("/member/checkEmail\",\n");
      out.write("\t\t\t  data: {\"email\":email},\n");
      out.write("\t\t\t  success:function(data){\n");
      out.write("\t\t\t\t  if(data.success == \"no\"){\t    \t\t\n");
      out.write("\t\t    \t\t   $(\"#error\").html(\"<i class='icon-remove'></i>email not exsits\");\t    \t\t    \n");
      out.write("\t\t    \t\t   return false;\n");
      out.write("\t\t    \t   }else{\n");
      out.write("\t\t    \t\t   $(\"#error\").html(\"\");\t  \n");
      out.write("\t\t    \t\t   return true; \n");
      out.write("\t\t    \t   }\n");
      out.write("\t\t\t  }\n");
      out.write("\t\t  });\n");
      out.write("\t    \n");
      out.write("\t    \n");
      out.write("\t \treturn  true;\n");
      out.write("\t \n");
      out.write(" }\n");
      out.write(" \n");
      out.write(" function verfiyPassword(){\n");
      out.write("\t var email = trim($(\"#email\").val());\t  \n");
      out.write("\t var password = trim($(\"#password\").val());\t         \n");
      out.write("\t    if(email == \"\"){\n");
      out.write("\t\t   \t $(\"#error\").html(\"<i class='icon-remove'></i>Please input your e-mail\");\n");
      out.write("\t\t   \treturn false;\n");
      out.write("\t    }\n");
      out.write("\t    if(password == \"\"){\n");
      out.write("\t\t   \t $(\"#error\").html(\"<i class='icon-remove'></i>Please input your password\");\n");
      out.write("\t\t   \treturn false;\n");
      out.write("\t    }\n");
      out.write("\t    \n");
      out.write("\t \treturn  true;\n");
      out.write("\t \n");
      out.write(" }\n");
      out.write(" \n");
      out.write(" function verifySubmit(){\n");
      out.write("\t if(!verfiyPassword()){ \n");
      out.write("\t \treturn false ;\n");
      out.write("\t }\t\n");
      out.write("\t var email = trim($(\"#email\").val());\t  \n");
      out.write("\t $.ajax({\n");
      out.write("\t\t  type:\"post\",\n");
      out.write("\t\t  cache:false,    \n");
      out.write("\t\t  dataType : \"json\",\n");
      out.write("\t\t  url:\"");
      out.print(basePath);
      out.write("/member/checkEmail\",\n");
      out.write("\t\t  data: {\"email\":email},\n");
      out.write("\t\t  success:function(data){\n");
      out.write("\t\t\t  if(data.success == \"no\"){\t    \t\t\n");
      out.write("\t    \t\t   $(\"#error\").html(\"<i class='icon-remove'></i>email not exsits\");\t    \t\t    \n");
      out.write("\t    \t\t   return false;\n");
      out.write("\t    \t   }else{\n");
      out.write("\t    \t\t   $(\"#error\").html(\"\");\t\n");
      out.write("\t    \t\t   $(\"#loginForm\").submit();\n");
      out.write("\t    \t\t   return true; \n");
      out.write("\t    \t   }\n");
      out.write("\t\t  }\n");
      out.write("\t  });\n");
      out.write("\t\n");
      out.write("\t}\n");
      out.write(" </script>\n");
      out.write("  </body>\n");
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
    // /WEB-INF/view/product/memberLogin.jsp(18,4) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty msg }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("    \t<script type=\"text/javascript\"> \n");
        out.write("    \talert('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${msg }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("');\n");
        out.write("    \t</script>\n");
        out.write("    ");
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
