package org.apache.jsp.WEB_002dINF.view.common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class decorator_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/WEB-INF/view/common/taglibs.jsp");
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
      out.write('\n');

	//获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:    
	String basePathforHead = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath(); 

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("<title>n3ds-card<sitemesh:write property='title'/></title>\n");
      out.write("\t<!-- n3ds-card  mobledeal -->\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("    <meta name=\"google-translate-customization\" content=\"1e73dcaa81aa4ac7-61a6e2a043015e7a-g53185def3341f4dc-11\"></meta>\n");
      out.write("   \n");
      out.write("    <!-- 自定义css -->\n");
      out.write("    <link href=\"");
      out.print(basePathforHead );
      out.write("/assets/css/product/common.css\" rel=\"stylesheet\">\n");
      out.write("     <!-- Bootstrap -->\n");
      out.write("    <link href=\"");
      out.print(basePathforHead );
      out.write("/assets/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" media=\"screen\">\n");
      out.write("    <link href=\"");
      out.print(basePathforHead );
      out.write("/assets/bootstrap/css/bootstrap-theme.min.css\" rel=\"stylesheet\">\n");
      out.write(" \t\n");
      out.write(" \t<script src=\"");
      out.print(basePathforHead );
      out.write("/assets/js/common/jquery-1.8.2.min.js\"></script>\n");
      out.write("    <script src=\"");
      out.print(basePathforHead );
      out.write("/assets/bootstrap/js/bootstrap.min.js\"></script>\n");
      out.write("    \n");
      out.write("    \n");
      out.write("\t<script type=\"text/javascript\" src=\"//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit\"></script>\n");
      out.write("    <script type=\"text/javascript\" language=\"javascript\">\n");
      out.write("    function googleTranslateElementInit() {\n");
      out.write("    \t  new google.translate.TranslateElement({pageLanguage: 'en', layout: google.translate.TranslateElement.InlineLayout.SIMPLE, autoDisplay: false, multilanguagePage: true}, 'google_translate_element');\n");
      out.write("    \t}\n");
      out.write("    \n");
      out.write("\tfunction addToCart(productId,amount,unitPrice,color,size){\n");
      out.write("   \t\t$.ajax({\n");
      out.write("   \t\t\t\ttype:\"post\",\n");
      out.write("   \t\t\t\tdataType : \"json\",  \n");
      out.write("\t\t        url:\"");
      out.print(basePathforHead );
      out.write("/shopItem/add\",\n");
      out.write("\t\t        data: {\"productId\":productId,\"amount\":amount,\"unitPrice\":unitPrice,\"color\":color,\"size\":size},\n");
      out.write("\t\t        success:function(data){\n");
      out.write("\t\t    \t   if(data.success == 'ok'){\n");
      out.write("\t\t    \t\t //加入成功后跳转到我的购物车\n");
      out.write("\t\t    \t\t  window.location.href=\"");
      out.print(basePathforHead );
      out.write("/product/toMyCart\" ;\n");
      out.write("\t\t    \t   }\n");
      out.write("\t\t       }  \t\t    \t   \n");
      out.write("\t\t});    \t\t\n");
      out.write("   \t}\n");
      out.write("\t</script>  \n");
      out.write("     <style type=\"text/css\">      \n");
      out.write("      body {\n");
      out.write("        //padding-top: 60px;/*body距离顶部底部距离*/\n");
      out.write("        padding-bottom: 40px;\n");
      out.write("      }      \n");
      out.write("    </style>\n");
      out.write("    <sitemesh:write property='head'/>\n");
      out.write("  </head>\n");
      out.write("  <body>\n");
      out.write("  \n");
      out.write("\t<!-- Head Body -->\n");
      out.write("\t<nav class=\"navbar navbar-default navbar-inverse\" role=\"navigation\">\n");
      out.write("\t <!-- Brand and toggle get grouped for better mobile display -->\n");
      out.write("\t  <div class=\"navbar-header\">\n");
      out.write("\t    <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-ex1-collapse\">\n");
      out.write("\t      <span class=\"sr-only\">Toggle navigation1</span>\n");
      out.write("\t      <span class=\"icon-bar\"></span>\n");
      out.write("\t      <span class=\"icon-bar\"></span>\n");
      out.write("\t      <span class=\"icon-bar\"></span>\n");
      out.write("\t    </button>\n");
      out.write("\t    <a class=\"navbar-brand\" href=\"");
      out.print(basePathforHead );
      out.write("/index\">N3DS-CARD</a>\n");
      out.write("\t  </div>\n");
      out.write("\t  <div class=\"collapse navbar-collapse navbar-ex1-collapse\">\n");
      out.write("\t\t    <ul class=\"nav navbar-nav\">\n");
      out.write("\t\t\t\t  <li><img src=\"");
      out.print(basePathforHead );
      out.write("/assets/icos/product/logo.png\"></li>\n");
      out.write("\t\t\t\t  <li class=\"active\"><a href=\"");
      out.print(basePathforHead );
      out.write("/index\">Home</a></li>\n");
      out.write("\t\t\t\t  <li>\n");
      out.write("\t\t\t\t      <a href=\"");
      out.print(basePathforHead);
      out.write("/member/toMyAccount\">\n");
      out.write("\t\t\t\t          ");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t\t\t      </a>\n");
      out.write("\t\t\t\t  </li>\n");
      out.write("\t\t    </ul>\n");
      out.write("\t\t    <p class=\"navbar-text pull-right\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\n");
      out.write("\t\t               \n");
      out.write("\t\t               \n");
      out.write("\t        <p class=\"navbar-text pull-right\">\n");
      out.write("\t\t\t\t\t");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f1.setParent(null);
      // /WEB-INF/view/common/decorator.jsp(86,5) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty member}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
      int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
      if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("\t\t\t\t\t\t<a href=\"");
          out.print(basePathforHead );
          out.write("/member/login\" class=\"navbar-link\">Sign in</a>&nbsp;&nbsp;\n");
          out.write("\t\t\t\t\t\t<a href=\"");
          out.print(basePathforHead );
          out.write("/member/register\" class=\"navbar-link\">Register</a>&nbsp;&nbsp;\n");
          out.write("\t\t\t\t\t");
          int evalDoAfterBody = _jspx_th_c_005fif_005f1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
      out.write("\n");
      out.write("\t\t\t\t\t<a href=\"");
      out.print(basePathforHead );
      out.write("/order/trackShipment\" class=\"navbar-link\">Track shipment</a>&nbsp;&nbsp;\n");
      out.write("\t\t\t\t\t<a href=\"");
      out.print(basePathforHead );
      out.write("/product/toMyCart\" class=\"navbar-link\">MyCart</a>&nbsp;&nbsp;\n");
      out.write("\t           \t\t<a href=\"");
      out.print(basePathforHead );
      out.write("/order/toMyOrder\" class=\"navbar-link\">MyOrders</a>&nbsp;&nbsp;\n");
      out.write("\t           \t\t<a href=\"");
      out.print(basePathforHead );
      out.write("/member/toMyAccount\" class=\"navbar-link\">MyAccount</a>&nbsp;&nbsp;\n");
      out.write("\t            \t");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f2 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_005fif_005f2.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f2.setParent(null);
      // /WEB-INF/view/common/decorator.jsp(94,14) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty member}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
      int _jspx_eval_c_005fif_005f2 = _jspx_th_c_005fif_005f2.doStartTag();
      if (_jspx_eval_c_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("\t            \t\t<a href=\"");
          out.print(basePathforHead );
          out.write("/member/logout\" class=\"navbar-link\">Sign out</a>&nbsp;&nbsp;\n");
          out.write("\t            \t");
          int evalDoAfterBody = _jspx_th_c_005fif_005f2.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f2);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f2);
      out.write("\n");
      out.write("\t        </p>\n");
      out.write("\t        <p class=\"navbar-text pull-right\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\n");
      out.write("\t        <div class=\"pull-right\" style=\"padding-top:8px;\" id=\"google_translate_element\" ></div>\n");
      out.write("\t    \n");
      out.write("\t  </div><!-- /.navbar-collapse -->\n");
      out.write("\t</nav>\n");
      out.write("    \n");
      out.write("    <!-- Main Body -->\n");
      out.write("    <div>\n");
      out.write("      <sitemesh:write property='body'/>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("\t<!-- Foot Body -->\n");
      out.write("    <div class=\"row navbar-static-bottom\">\n");
      out.write("    \t<hr/>\n");
      out.write("    \t<div class=\"row\" align=\"center\">\n");
      out.write("    \t<img alt=\"\" src=\"");
      out.print(basePathforHead );
      out.write("/assets/icos/product/bottomlogo1.jpg\">\n");
      out.write("    \t</div>\n");
      out.write("\t    <div class=\"row\">\n");
      out.write("\t      <p class=\"text-center\">All prices are in USD. &copy; Copyright 2013 n3ds-card.com. Sitemap | Powered by n3ds-card.com      \n");
      out.write("\t      <script src=\"http://s22.cnzz.com/stat.php?id=5536743&web_id=5536743&show=pic\" language=\"JavaScript\"></script></p>\n");
      out.write("\t      <!--  \n");
      out.write("\t\t  <script type='text/javascript' src='http://www3.365webcall.com/IMMe1.aspx?settings=mw7NNb7NNN670N7z3ANmmmbPz3ANmwIIwz3AN6mmP0&LL=1'></script>\n");
      out.write("\t\t  -->\n");
      out.write("\t\t  </div>\n");
      out.write("\t</div>\n");
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
    // /WEB-INF/view/common/decorator.jsp(78,14) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty member}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("welcome！");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${member.lastName }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("&nbsp;");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${member.firstName }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
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
