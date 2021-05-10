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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	response.setHeader("Pragma","No-cache");
	//response.setHeader("Cache-Control","public");
	//response.setDateHeader("Last-Modified", System.currentTimeMillis());
	//response.setDateHeader("Expires", System.currentTimeMillis() + 1000 * 60 * 5);

      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");

	//获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:    
	String basePathforHead = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath(); 

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>n3ds-card<sitemesh:write property='title'/></title>\r\n");
      out.write("\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("    \r\n");
      out.write("    \r\n");
      out.write("     <!-- Bootstrap -->\r\n");
      out.write("    <link href=\"");
      out.print(basePathforHead );
      out.write("/assets/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" media=\"screen\">\r\n");
      out.write("    <link href=\"");
      out.print(basePathforHead );
      out.write("/assets/bootstrap/css/bootstrap-responsive.min.css\" rel=\"stylesheet\">\r\n");
      out.write(" \t\r\n");
      out.write(" \t<script src=\"");
      out.print(basePathforHead );
      out.write("/assets/js/common/jquery-1.8.2.min.js\"></script>\r\n");
      out.write("    <script src=\"");
      out.print(basePathforHead );
      out.write("/assets/bootstrap/js/bootstrap.min.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" language=\"javascript\">\r\n");
      out.write("   \r\n");
      out.write("\tfunction addToCart(productId,amount,unitPrice,color,size){\r\n");
      out.write("   \t\t$.ajax({\r\n");
      out.write("   \t\t\t\ttype:\"post\",\r\n");
      out.write("   \t\t\t\tdataType : \"json\",  \r\n");
      out.write("\t\t        url:\"");
      out.print(basePathforHead );
      out.write("/shopItem/add\",\r\n");
      out.write("\t\t        data: {\"productId\":productId,\"amount\":amount,\"unitPrice\":unitPrice,\"color\":color,\"size\":size},\r\n");
      out.write("\t\t        success:function(data){\r\n");
      out.write("\t\t    \t   if(data.success == 'ok'){\r\n");
      out.write("\t\t    \t\t //加入成功后跳转到我的购物车\r\n");
      out.write("\t\t    \t\t  window.location.href=\"");
      out.print(basePathforHead );
      out.write("/product/toMyCart\" ;\r\n");
      out.write("\t\t    \t   }\r\n");
      out.write("\t\t       }  \t\t    \t   \r\n");
      out.write("\t\t});    \t\t\r\n");
      out.write("   \t}\r\n");
      out.write("\t</script>  \r\n");
      out.write("     <style type=\"text/css\">      \r\n");
      out.write("      body {\r\n");
      out.write("        padding-top: 60px;/*body距离顶部底部距离*/\r\n");
      out.write("        padding-bottom: 40px;\r\n");
      out.write("      }      \r\n");
      out.write("    </style>\r\n");
      out.write("    <sitemesh:write property='head'/>\r\n");
      out.write("  </head>\r\n");
      out.write("  <body>\r\n");
      out.write("  \r\n");
      out.write("\r\n");
      out.write("    <!-- Head Body -->\r\n");
      out.write("\t <div class=\"navbar navbar-inverse navbar-fixed-top\">\r\n");
      out.write("      <div class=\"navbar-inner\">\r\n");
      out.write("        <div class=\"container-fluid\">\r\n");
      out.write("          <button type=\"button\" class=\"btn btn-navbar\" data-toggle=\"collapse\" data-target=\".nav-collapse\">\r\n");
      out.write("            <span class=\"icon-bar\"></span>\r\n");
      out.write("            <span class=\"icon-bar\"></span>\r\n");
      out.write("            <span class=\"icon-bar\"></span>\r\n");
      out.write("          </button>\r\n");
      out.write("          <a class=\"brand\" href=\"");
      out.print(basePathforHead );
      out.write("/index\">N3DS-CARD</a>\r\n");
      out.write("          <div class=\"nav-collapse collapse navbar-inverse-collapse\">\r\n");
      out.write("\t            <ul class=\"nav\">\r\n");
      out.write("\t\t            \t<li><img src=\"");
      out.print(basePathforHead );
      out.write("/assets/icos/product/logo.png\"></li>\r\n");
      out.write("\t\t              \t<li class=\"active\"><a href=\"");
      out.print(basePathforHead );
      out.write("/index\">Home</a></li>\r\n");
      out.write("\t\t              \t<li><a href=\"");
      out.print(basePathforHead);
      out.write("/member/toMyAccount\">\r\n");
      out.write("\t\t              \t\t\t");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("</a>\r\n");
      out.write("\t\t\t\t\t\t</li>\r\n");
      out.write("\t            </ul>\r\n");
      out.write("\t            <p class=\"navbar-text pull-right\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\r\n");
      out.write("               <!-- \r\n");
      out.write("                <ul class=\"nav pull-right\">\r\n");
      out.write("                      <li class=\"divider-vertical\"></li>\r\n");
      out.write("                      <li class=\"dropdown\">\r\n");
      out.write("                        <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">MyAccount <b class=\"caret\"></b></a>\r\n");
      out.write("                        <ul class=\"dropdown-menu\">\r\n");
      out.write("                          <li><a href=\"");
      out.print(basePathforHead );
      out.write("/product/toMyCart\" class=\"navbar-link\">MyCart</a></li>\r\n");
      out.write("                          <li><a href=\"");
      out.print(basePathforHead );
      out.write("/order/toMyOrder\" class=\"navbar-link\">MyOrders</a></li>\r\n");
      out.write("                          <li><a href=\"");
      out.print(basePathforHead );
      out.write("/member/toMyAccount\" class=\"navbar-link\">MyAccount</a></li>\r\n");
      out.write("                        </ul>\r\n");
      out.write("                      </li>\r\n");
      out.write("                 </ul>\r\n");
      out.write("                 -->\r\n");
      out.write("                 <p class=\"navbar-text pull-right\">\r\n");
      out.write("\t\t\t\t\t\t");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f1.setParent(null);
      // /WEB-INF/view/common/decorator.jsp(88,6) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty member}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
      int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
      if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t<a href=\"");
          out.print(basePathforHead );
          out.write("/member/login\" class=\"navbar-link\">Sign in</a>&nbsp;&nbsp;\r\n");
          out.write("\t\t\t\t\t\t\t<a href=\"");
          out.print(basePathforHead );
          out.write("/member/register\" class=\"navbar-link\">Register</a>&nbsp;&nbsp;\r\n");
          out.write("\t\t\t\t\t\t");
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
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<a href=\"");
      out.print(basePathforHead );
      out.write("/order/trackShipment\" class=\"navbar-link\">Track shipment</a>&nbsp;&nbsp;\r\n");
      out.write("\t\t\t\t\t\t<a href=\"");
      out.print(basePathforHead );
      out.write("/product/toMyCart\" class=\"navbar-link\">MyCart</a>&nbsp;&nbsp;\r\n");
      out.write("\t               \t\t<a href=\"");
      out.print(basePathforHead );
      out.write("/order/toMyOrder\" class=\"navbar-link\">MyOrders</a>&nbsp;&nbsp;\r\n");
      out.write("\t               \t\t<a href=\"");
      out.print(basePathforHead );
      out.write("/member/toMyAccount\" class=\"navbar-link\">MyAccount</a>&nbsp;&nbsp;\r\n");
      out.write("\t\t               \t<span id=\"cartItemsSun\"></span>\r\n");
      out.write("\t               \t\t\r\n");
      out.write("\t\t               \t");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f2 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_005fif_005f2.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f2.setParent(null);
      // /WEB-INF/view/common/decorator.jsp(98,18) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty member}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
      int _jspx_eval_c_005fif_005f2 = _jspx_th_c_005fif_005f2.doStartTag();
      if (_jspx_eval_c_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t               \t\t<a href=\"");
          out.print(basePathforHead );
          out.write("/member/logout\" class=\"navbar-link\">Sign out</a>&nbsp;&nbsp;\r\n");
          out.write("\t\t               \t");
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
      out.write("\r\n");
      out.write("\t            </p>\r\n");
      out.write("\t           \r\n");
      out.write("               \r\n");
      out.write("          </div><!--/.nav-collapse -->\r\n");
      out.write("        </div>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <!-- Main Body -->\r\n");
      out.write("    <div>\r\n");
      out.write("      <sitemesh:write property='body'/>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("\t<!-- Foot Body -->\r\n");
      out.write("    <div>\r\n");
      out.write("    \t<hr/>\r\n");
      out.write("    \t<div class=\"text-center\" >\r\n");
      out.write("    \t<img alt=\"\" src=\"");
      out.print(basePathforHead );
      out.write("/assets/icos/product/bottomlogo1.jpg\">\r\n");
      out.write("    \t</div>\r\n");
      out.write("\t    <footer>\r\n");
      out.write("\t      <p class=\"text-center\">All prices are in USD. &copy; Copyright 2013 n3ds-card.com. Sitemap | Powered by n3ds-card.com      \r\n");
      out.write("\t      <script src=\"http://s22.cnzz.com/stat.php?id=5536743&web_id=5536743&show=pic\" language=\"JavaScript\"></script></p>\r\n");
      out.write("\t      <!--  \r\n");
      out.write("\t\t  <script type='text/javascript' src='http://www3.365webcall.com/IMMe1.aspx?settings=mw7NNb7NNN670N7z3ANmmmbPz3ANmwIIwz3AN6mmP0&LL=1'></script>\r\n");
      out.write("\t\t  -->\r\n");
      out.write("\t\t  </footer>\r\n");
      out.write("\t</div>\r\n");
      out.write("  </body>\r\n");
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
    // /WEB-INF/view/common/decorator.jsp(68,19) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty member}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\twelcome！");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${member.lastName }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("&nbsp;");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${member.firstName }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t");
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
