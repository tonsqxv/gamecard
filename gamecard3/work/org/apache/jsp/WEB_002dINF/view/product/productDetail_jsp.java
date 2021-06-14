package org.apache.jsp.WEB_002dINF.view.product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class productDetail_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(4);
    _jspx_dependants.add("/WEB-INF/view/product/../common/taglibs.jsp");
    _jspx_dependants.add("/WEB-INF/view/product/head.jsp");
    _jspx_dependants.add("/WEB-INF/view/product/left.jsp");
    _jspx_dependants.add("/WEB-INF/view/product/foot4customerlike.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fchoose;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fotherwise;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fend_005fbegin;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fchoose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fotherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fend_005fbegin = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
    _005fjspx_005ftagPool_005fc_005fchoose.release();
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.release();
    _005fjspx_005ftagPool_005fc_005fotherwise.release();
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fend_005fbegin.release();
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
	//获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:    
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path; 

      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html>\n");
      out.write("  <head>\n");
      out.write("   \n");
      out.write("\t<!-- Load the Cloud Zoom CSS file -->\n");
      out.write("\t<link href=\"");
      out.print(basePath );
      out.write("/assets/cloud-zoom-1.0.2/cloud-zoom.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("\t<!-- Load the Cloud Zoom JavaScript file -->\n");
      out.write("\t<script type=\"text/JavaScript\" src=\"");
      out.print(basePath );
      out.write("/assets/cloud-zoom-1.0.2/cloud-zoom.1.0.2.min.js\"></script>\n");
      out.write("\n");
      out.write("  </head>\n");
      out.write("  <body>\n");
      out.write("  ");
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
      out.write("\n");
      out.write("    <div class=\"container\">\n");
      out.write("      <div class=\"row\">\n");
      out.write("\n");
      out.write("    \t  <div class=\"col-lg-3\">\n");
      out.write("   \t\t\t\t\t");
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
	String basePath4left = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();   
	

      out.write("  \n");
      out.write("\n");
      out.write("\t\t\t\t<!-- 分类树 -->\n");
      out.write("\t\t\t\t");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f0.setParent(null);
      // /WEB-INF/view/product/left.jsp(14,4) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty categoryTree }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
      int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
      if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("\t\t\t\t<div class=\"dropdown clearfix\">\n");
          out.write("\t\t              <ul class=\"dropdown-menu nav nav-list btn-block\"  style=\"display: block; position: static; margin-bottom: 5px; *width: 180px;\">\n");
          out.write("\t\t                <!-- 类别标题 -->\n");
          out.write("\t\t                <h4 align=\"center\">CATEGORIES</h4>\n");
          out.write("\t\t                <!-- 分割线 -->\n");
          out.write("\t\t                <li class=\"divider\"></li>\n");
          out.write("\t\t                <!-- 遍历类别 -->\n");
          out.write("\t\t     \t\t\t");
          //  c:forEach
          org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
          _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
          _jspx_th_c_005fforEach_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
          // /WEB-INF/view/product/left.jsp(22,10) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
          _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/view/product/left.jsp(22,10) '${categoryTree}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${categoryTree}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
          // /WEB-INF/view/product/left.jsp(22,10) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_c_005fforEach_005f0.setVar("t");
          int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
          try {
            int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
            if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              do {
                out.write("\n");
                out.write("\t\t     \t\t\t");
                //  c:choose
                org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f0 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
                _jspx_th_c_005fchoose_005f0.setPageContext(_jspx_page_context);
                _jspx_th_c_005fchoose_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
                int _jspx_eval_c_005fchoose_005f0 = _jspx_th_c_005fchoose_005f0.doStartTag();
                if (_jspx_eval_c_005fchoose_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  do {
                    out.write("\n");
                    out.write("\t\t\t\t\t\t\t\t");
                    //  c:when
                    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f0 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
                    _jspx_th_c_005fwhen_005f0.setPageContext(_jspx_page_context);
                    _jspx_th_c_005fwhen_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
                    // /WEB-INF/view/product/left.jsp(24,8) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                    _jspx_th_c_005fwhen_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty t.children}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
                    int _jspx_eval_c_005fwhen_005f0 = _jspx_th_c_005fwhen_005f0.doStartTag();
                    if (_jspx_eval_c_005fwhen_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      do {
                        out.write("\n");
                        out.write("\t\t\t\t\t\t\t\t\t<li class=\"dropdown-submenu\"><a tabindex=\"-1\" href=\"");
                        out.print(basePath4left );
                        out.write("/product/");
                        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${t.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                        out.write("/listProductByCategory\">");
                        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${t.name }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                        out.write("</a></li>\n");
                        out.write("\t\t\t\t\t\t\t\t");
                        int evalDoAfterBody = _jspx_th_c_005fwhen_005f0.doAfterBody();
                        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                          break;
                      } while (true);
                    }
                    if (_jspx_th_c_005fwhen_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f0);
                      return;
                    }
                    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f0);
                    out.write("\n");
                    out.write("\t\t\t\t\t\t\t\t");
                    //  c:otherwise
                    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f0 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
                    _jspx_th_c_005fotherwise_005f0.setPageContext(_jspx_page_context);
                    _jspx_th_c_005fotherwise_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
                    int _jspx_eval_c_005fotherwise_005f0 = _jspx_th_c_005fotherwise_005f0.doStartTag();
                    if (_jspx_eval_c_005fotherwise_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      do {
                        out.write("\n");
                        out.write("\t\t\t\t\t\t\t\t\t<li class=\"dropdown-submenu\">\n");
                        out.write("\t\t\t\t\t\t\t\t\t<a>");
                        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${t.name }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                        out.write("</a>\n");
                        out.write("\t\t\t\t\t\t\t\t\t<ul class=\"dropdown-menu\">\n");
                        out.write("\t\t\t\t\t\t\t\t\t");
                        //  c:forEach
                        org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f1 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
                        _jspx_th_c_005fforEach_005f1.setPageContext(_jspx_page_context);
                        _jspx_th_c_005fforEach_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fotherwise_005f0);
                        // /WEB-INF/view/product/left.jsp(31,9) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
                        _jspx_th_c_005fforEach_005f1.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/view/product/left.jsp(31,9) '${t.children}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${t.children}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
                        // /WEB-INF/view/product/left.jsp(31,9) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                        _jspx_th_c_005fforEach_005f1.setVar("c");
                        int[] _jspx_push_body_count_c_005fforEach_005f1 = new int[] { 0 };
                        try {
                          int _jspx_eval_c_005fforEach_005f1 = _jspx_th_c_005fforEach_005f1.doStartTag();
                          if (_jspx_eval_c_005fforEach_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                            do {
                              out.write("\n");
                              out.write("\t\t\t\t\t\t\t\t\t\t<li><a tabindex=\"-1\" href=\"");
                              out.print(basePath4left );
                              out.write("/product/");
                              out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${c.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                              out.write("/listProductByCategory\">");
                              out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${c.name}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                              out.write("</a></li>\n");
                              out.write("\t\t\t\t\t\t\t\t\t");
                              int evalDoAfterBody = _jspx_th_c_005fforEach_005f1.doAfterBody();
                              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                              break;
                            } while (true);
                          }
                          if (_jspx_th_c_005fforEach_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            return;
                          }
                        } catch (Throwable _jspx_exception) {
                          while (_jspx_push_body_count_c_005fforEach_005f1[0]-- > 0)
                            out = _jspx_page_context.popBody();
                          _jspx_th_c_005fforEach_005f1.doCatch(_jspx_exception);
                        } finally {
                          _jspx_th_c_005fforEach_005f1.doFinally();
                          _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f1);
                        }
                        out.write("\n");
                        out.write("\t\t\t\t\t\t\t\t\t</ul>\n");
                        out.write("\t\t\t\t\t\t\t\t\t</li>\n");
                        out.write("\t\t\t\t\t\t\t\t");
                        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f0.doAfterBody();
                        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                          break;
                      } while (true);
                    }
                    if (_jspx_th_c_005fotherwise_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f0);
                      return;
                    }
                    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f0);
                    out.write("\n");
                    out.write("\t\t\t\t\t\t   ");
                    int evalDoAfterBody = _jspx_th_c_005fchoose_005f0.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                }
                if (_jspx_th_c_005fchoose_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f0);
                  return;
                }
                _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f0);
                out.write("\n");
                out.write("\t\t                ");
                int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
            }
            if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              return;
            }
          } catch (Throwable _jspx_exception) {
            while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
              out = _jspx_page_context.popBody();
            _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
          } finally {
            _jspx_th_c_005fforEach_005f0.doFinally();
            _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
          }
          out.write("\n");
          out.write("\t\t                <li class=\"divider\"></li>\n");
          out.write("\t\t              </ul>\n");
          out.write("\t\t          </div>\n");
          out.write("\t\t          ");
          int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      out.write("\n");
      out.write("\t\t          \n");
      out.write("\t\t          <!-- 热门产品 -->\n");
      out.write("\t\t          ");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f1.setParent(null);
      // /WEB-INF/view/product/left.jsp(45,12) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty hotProducts }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
      int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
      if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("\t\t          <div class=\"panel panel-default\">\n");
          out.write("\t\t\t\t\t  <div class=\"panel-heading\">\n");
          out.write("\t\t\t\t\t    <h3 class=\"panel-title\">Hot Products</h3>\n");
          out.write("\t\t\t\t\t  </div>\n");
          out.write("\t\t\t\t\t  <div class=\"panel-body\">\n");
          out.write("\t\t\t\t\t\t  \t");
          if (_jspx_meth_c_005fset_005f0(_jspx_th_c_005fif_005f1, _jspx_page_context))
            return;
          out.write("\n");
          out.write("\t\t\t\t\t\t\t");
          //  c:forEach
          org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f2 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
          _jspx_th_c_005fforEach_005f2.setPageContext(_jspx_page_context);
          _jspx_th_c_005fforEach_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f1);
          // /WEB-INF/view/product/left.jsp(52,7) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
          _jspx_th_c_005fforEach_005f2.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/view/product/left.jsp(52,7) '${hotProducts }'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${hotProducts }",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
          // /WEB-INF/view/product/left.jsp(52,7) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_c_005fforEach_005f2.setVar("card");
          int[] _jspx_push_body_count_c_005fforEach_005f2 = new int[] { 0 };
          try {
            int _jspx_eval_c_005fforEach_005f2 = _jspx_th_c_005fforEach_005f2.doStartTag();
            if (_jspx_eval_c_005fforEach_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              do {
                out.write("\n");
                out.write("\t\t\t\t\t\t\t");
                //  c:if
                org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f2 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
                _jspx_th_c_005fif_005f2.setPageContext(_jspx_page_context);
                _jspx_th_c_005fif_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f2);
                // /WEB-INF/view/product/left.jsp(53,7) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                _jspx_th_c_005fif_005f2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${hotProducts_index != (hotSize+1) }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
                int _jspx_eval_c_005fif_005f2 = _jspx_th_c_005fif_005f2.doStartTag();
                if (_jspx_eval_c_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  do {
                    out.write("\n");
                    out.write("\t\t\t\t\t\t\t    <div class=\"row\">\n");
                    out.write("\t\t\t\t\t\t\t    \t  <div class=\"col-lg-7\">\n");
                    out.write("\t\t\t\t\t\t\t\t\t    \t<a href=\"");
                    out.print(basePath4left );
                    out.write("/product/");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("/productDetail\" class=\"thumbnail\">\n");
                    out.write("\t\t\t\t\t\t                  \t\t<img src=\"");
                    out.print(basePath4left );
                    out.write("/assets/images/product/");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.mainImgPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("\"/>\n");
                    out.write("\t\t\t\t\t\t                   </a>\n");
                    out.write("\t\t\t\t\t\t\t    \t  </div>\n");
                    out.write("\t\t\t\t\t\t\t    \t  <div class=\"col-lg-5\" align=\"left\">\n");
                    out.write("\t\t\t\t\t\t\t    \t  \t\t<p><strong ><font color=\"red\">$");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.actualSellPrice }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("</font></strong></p>\n");
                    out.write("\t\t\t\t                \t\t\t<p>Sold:");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.sales+card.baseSales}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("</p>\n");
                    out.write("\t\t\t\t\t\t\t    \t  </div>\n");
                    out.write("\t\t\t\t\t\t\t\t</div>\n");
                    out.write("\t\t\t\t\t\t        <div class=\"row\">\n");
                    out.write("\t\t\t\t\t\t              <p align=\"center\" class=\"common\"><a href=\"");
                    out.print(basePath4left );
                    out.write("/product/");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("/productDetail\" title=\"");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.productName }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write('"');
                    out.write('>');
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.shortName }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("</a></p>\n");
                    out.write("\t\t\t\t\t\t        </div>\n");
                    out.write("\t\t\t\t\t\t\t\t<br>\n");
                    out.write("\t\t\t\t\t\t   ");
                    if (_jspx_meth_c_005fset_005f1(_jspx_th_c_005fif_005f2, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
                      return;
                    out.write("\n");
                    out.write("\t\t\t\t       \t   ");
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
                out.write("    \n");
                out.write("\t\t\t\t\t\t   ");
                int evalDoAfterBody = _jspx_th_c_005fforEach_005f2.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
            }
            if (_jspx_th_c_005fforEach_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              return;
            }
          } catch (Throwable _jspx_exception) {
            while (_jspx_push_body_count_c_005fforEach_005f2[0]-- > 0)
              out = _jspx_page_context.popBody();
            _jspx_th_c_005fforEach_005f2.doCatch(_jspx_exception);
          } finally {
            _jspx_th_c_005fforEach_005f2.doFinally();
            _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f2);
          }
          out.write("\n");
          out.write("\t\t\t\t\t\t   <div class=\"row\">\n");
          out.write("\t\t\t              \t\t<p class=\"text-right\"><a href=\"");
          out.print(basePath4left );
          out.write("/product/viewMoreProduct?type=1\">View More &gt;&gt;</a></p>\n");
          out.write("\t\t\t               </div>\n");
          out.write("\t\t\t\t\t  </div>\n");
          out.write("\t\t\t\t</div>\n");
          out.write("\t\t\t\t");
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
      out.write("\n");
      out.write(" \t\t\t\t<!-- 新产品 -->\n");
      out.write("\t\t          ");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f3 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_005fif_005f3.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f3.setParent(null);
      // /WEB-INF/view/product/left.jsp(80,12) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f3.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty newProducts }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
      int _jspx_eval_c_005fif_005f3 = _jspx_th_c_005fif_005f3.doStartTag();
      if (_jspx_eval_c_005fif_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("\t\t          <div class=\"panel panel-default\">\n");
          out.write("\t\t\t\t\t  <div class=\"panel-heading\">\n");
          out.write("\t\t\t\t\t    <h3 class=\"panel-title\">New Products</h3>\n");
          out.write("\t\t\t\t\t  </div>\n");
          out.write("\t\t\t\t\t  <div class=\"panel-body\">\n");
          out.write("\t\t\t\t\t\t  \t");
          if (_jspx_meth_c_005fset_005f2(_jspx_th_c_005fif_005f3, _jspx_page_context))
            return;
          out.write("\n");
          out.write("\t\t\t\t\t\t\t");
          //  c:forEach
          org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f3 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
          _jspx_th_c_005fforEach_005f3.setPageContext(_jspx_page_context);
          _jspx_th_c_005fforEach_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f3);
          // /WEB-INF/view/product/left.jsp(87,7) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
          _jspx_th_c_005fforEach_005f3.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/view/product/left.jsp(87,7) '${newProducts }'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${newProducts }",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
          // /WEB-INF/view/product/left.jsp(87,7) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_c_005fforEach_005f3.setVar("card");
          int[] _jspx_push_body_count_c_005fforEach_005f3 = new int[] { 0 };
          try {
            int _jspx_eval_c_005fforEach_005f3 = _jspx_th_c_005fforEach_005f3.doStartTag();
            if (_jspx_eval_c_005fforEach_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              do {
                out.write("\n");
                out.write("\t\t\t\t\t\t\t");
                //  c:if
                org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f4 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
                _jspx_th_c_005fif_005f4.setPageContext(_jspx_page_context);
                _jspx_th_c_005fif_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f3);
                // /WEB-INF/view/product/left.jsp(88,7) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                _jspx_th_c_005fif_005f4.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${newProducts_index != (newSize+1) }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
                int _jspx_eval_c_005fif_005f4 = _jspx_th_c_005fif_005f4.doStartTag();
                if (_jspx_eval_c_005fif_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  do {
                    out.write("\n");
                    out.write("\t\t\t\t\t\t\t    <div class=\"row\">\n");
                    out.write("\t\t\t\t\t\t\t    \t  <div class=\"col-lg-7\">\n");
                    out.write("\t\t\t\t\t\t\t\t\t    \t<a href=\"");
                    out.print(basePath4left );
                    out.write("/product/");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("/productDetail\" class=\"thumbnail\">\n");
                    out.write("\t\t\t\t\t\t                  \t\t<img src=\"");
                    out.print(basePath4left );
                    out.write("/assets/images/product/");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.mainImgPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("\"/>\n");
                    out.write("\t\t\t\t\t\t                   </a>\n");
                    out.write("\t\t\t\t\t\t\t    \t  </div>\n");
                    out.write("\t\t\t\t\t\t\t    \t  <div class=\"col-lg-5\" align=\"left\">\n");
                    out.write("\t\t\t\t\t\t\t    \t  \t\t<p><strong ><font color=\"red\">$");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.actualSellPrice }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("</font></strong></p>\n");
                    out.write("\t\t\t\t                \t\t\t<p>Sold:");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.sales+card.baseSales}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("</p>\n");
                    out.write("\t\t\t\t\t\t\t    \t  </div>\n");
                    out.write("\t\t\t\t\t\t\t\t</div>\n");
                    out.write("\t\t\t\t\t\t        <div class=\"row\">\n");
                    out.write("\t\t\t\t\t\t              <p align=\"center\" class=\"common\"><a href=\"");
                    out.print(basePath4left );
                    out.write("/product/");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("/productDetail\" title=\"");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.productName }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write('"');
                    out.write('>');
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.shortName }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("</a></p>\n");
                    out.write("\t\t\t\t\t\t        </div>\n");
                    out.write("\t\t\t\t\t\t\t\t<br>\n");
                    out.write("\t\t\t\t\t\t   ");
                    if (_jspx_meth_c_005fset_005f3(_jspx_th_c_005fif_005f4, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f3))
                      return;
                    out.write("\n");
                    out.write("\t\t\t\t       \t   ");
                    int evalDoAfterBody = _jspx_th_c_005fif_005f4.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                }
                if (_jspx_th_c_005fif_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f4);
                  return;
                }
                _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f4);
                out.write("    \n");
                out.write("\t\t\t\t\t\t   ");
                int evalDoAfterBody = _jspx_th_c_005fforEach_005f3.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
            }
            if (_jspx_th_c_005fforEach_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              return;
            }
          } catch (Throwable _jspx_exception) {
            while (_jspx_push_body_count_c_005fforEach_005f3[0]-- > 0)
              out = _jspx_page_context.popBody();
            _jspx_th_c_005fforEach_005f3.doCatch(_jspx_exception);
          } finally {
            _jspx_th_c_005fforEach_005f3.doFinally();
            _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f3);
          }
          out.write("\n");
          out.write("\t\t\t\t\t\t   <div class=\"row\">\n");
          out.write("\t\t\t              \t\t<p class=\"text-right\"><a href=\"");
          out.print(basePath4left );
          out.write("/product/viewMoreProduct?type=2\">View More &gt;&gt;</a></p>\n");
          out.write("\t\t\t               </div>\n");
          out.write("\t\t\t\t\t  </div>\n");
          out.write("\t\t\t\t</div>\n");
          out.write("\t\t\t\t");
          int evalDoAfterBody = _jspx_th_c_005fif_005f3.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f3);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f3);
      out.write("\n");
      out.write("\n");
      out.write("\t\t\t\t\t<!-- 特价商品 -->\n");
      out.write("\t\t          ");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f5 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_005fif_005f5.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f5.setParent(null);
      // /WEB-INF/view/product/left.jsp(115,12) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f5.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty discountProducts }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
      int _jspx_eval_c_005fif_005f5 = _jspx_th_c_005fif_005f5.doStartTag();
      if (_jspx_eval_c_005fif_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("\t\t          <div class=\"panel panel-default\">\n");
          out.write("\t\t\t\t\t  <div class=\"panel-heading\">\n");
          out.write("\t\t\t\t\t    <h3 class=\"panel-title\">Discount Products</h3>\n");
          out.write("\t\t\t\t\t  </div>\n");
          out.write("\t\t\t\t\t  <div class=\"panel-body\">\n");
          out.write("\t\t\t\t\t\t  \t");
          if (_jspx_meth_c_005fset_005f4(_jspx_th_c_005fif_005f5, _jspx_page_context))
            return;
          out.write("\n");
          out.write("\t\t\t\t\t\t\t");
          //  c:forEach
          org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f4 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
          _jspx_th_c_005fforEach_005f4.setPageContext(_jspx_page_context);
          _jspx_th_c_005fforEach_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f5);
          // /WEB-INF/view/product/left.jsp(122,7) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
          _jspx_th_c_005fforEach_005f4.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/view/product/left.jsp(122,7) '${discountProducts }'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${discountProducts }",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
          // /WEB-INF/view/product/left.jsp(122,7) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_c_005fforEach_005f4.setVar("card");
          int[] _jspx_push_body_count_c_005fforEach_005f4 = new int[] { 0 };
          try {
            int _jspx_eval_c_005fforEach_005f4 = _jspx_th_c_005fforEach_005f4.doStartTag();
            if (_jspx_eval_c_005fforEach_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              do {
                out.write("\n");
                out.write("\t\t\t\t\t\t\t");
                //  c:if
                org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f6 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
                _jspx_th_c_005fif_005f6.setPageContext(_jspx_page_context);
                _jspx_th_c_005fif_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f4);
                // /WEB-INF/view/product/left.jsp(123,7) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                _jspx_th_c_005fif_005f6.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${discountProducts_index != (discountSize+1) }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
                int _jspx_eval_c_005fif_005f6 = _jspx_th_c_005fif_005f6.doStartTag();
                if (_jspx_eval_c_005fif_005f6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  do {
                    out.write("\n");
                    out.write("\t\t\t\t\t\t\t    <div class=\"row\">\n");
                    out.write("\t\t\t\t\t\t\t    \t  <div class=\"col-lg-7\">\n");
                    out.write("\t\t\t\t\t\t\t\t\t    \t<a href=\"");
                    out.print(basePath4left );
                    out.write("/product/");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("/productDetail\" class=\"thumbnail\">\n");
                    out.write("\t\t\t\t\t\t                  \t\t<img src=\"");
                    out.print(basePath4left );
                    out.write("/assets/images/product/");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.mainImgPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("\"/>\n");
                    out.write("\t\t\t\t\t\t                   </a>\n");
                    out.write("\t\t\t\t\t\t\t    \t  </div>\n");
                    out.write("\t\t\t\t\t\t\t    \t  <div class=\"col-lg-5\" align=\"left\">\n");
                    out.write("\t\t\t\t\t\t\t    \t  \t\t<p><strong ><font color=\"red\">$");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.actualSellPrice }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("</font></strong></p>\n");
                    out.write("\t\t\t\t                \t\t\t<p>Sold:");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.sales+card.baseSales}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("</p>\n");
                    out.write("\t\t\t\t\t\t\t    \t  </div>\n");
                    out.write("\t\t\t\t\t\t\t\t</div>\n");
                    out.write("\t\t\t\t\t\t        <div class=\"row\">\n");
                    out.write("\t\t\t\t\t\t              <p align=\"center\" class=\"common\"><a href=\"");
                    out.print(basePath4left );
                    out.write("/product/");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("/productDetail\" title=\"");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.productName }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write('"');
                    out.write('>');
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.shortName }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("</a></p>\n");
                    out.write("\t\t\t\t\t\t        </div>\n");
                    out.write("\t\t\t\t\t\t\t\t<br>\n");
                    out.write("\t\t\t\t\t\t   ");
                    if (_jspx_meth_c_005fset_005f5(_jspx_th_c_005fif_005f6, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f4))
                      return;
                    out.write("\n");
                    out.write("\t\t\t\t       \t   ");
                    int evalDoAfterBody = _jspx_th_c_005fif_005f6.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                }
                if (_jspx_th_c_005fif_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f6);
                  return;
                }
                _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f6);
                out.write("    \n");
                out.write("\t\t\t\t\t\t   ");
                int evalDoAfterBody = _jspx_th_c_005fforEach_005f4.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
            }
            if (_jspx_th_c_005fforEach_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              return;
            }
          } catch (Throwable _jspx_exception) {
            while (_jspx_push_body_count_c_005fforEach_005f4[0]-- > 0)
              out = _jspx_page_context.popBody();
            _jspx_th_c_005fforEach_005f4.doCatch(_jspx_exception);
          } finally {
            _jspx_th_c_005fforEach_005f4.doFinally();
            _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f4);
          }
          out.write("\n");
          out.write("\t\t\t\t\t\t   <div class=\"row\">\n");
          out.write("\t\t\t              \t\t<p class=\"text-right\"><a href=\"");
          out.print(basePath4left );
          out.write("/product/viewMoreProduct?type=3\">View More &gt;&gt;</a></p>\n");
          out.write("\t\t\t               </div>\n");
          out.write("\t\t\t\t\t  </div>\n");
          out.write("\t\t\t\t</div>\n");
          out.write("\t\t\t\t");
          int evalDoAfterBody = _jspx_th_c_005fif_005f5.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f5);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f5);
      out.write("\n");
      out.write("\t\t          \n");
      out.write("\t\t          \n");
      out.write("\t\t          ");
      out.write("\n");
      out.write("   \t\t  </div><!--/span2-->\n");
      out.write("\n");
      out.write("    \t  <div class=\"col-lg-9\">\n");
      out.write("\n");
      out.write("          <h2>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.productName }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</h2>\n");
      out.write("          <hr>    \t\t\n");
      out.write("    \t\t <div class=\"row\">\n");
      out.write("              <div class=\"col-lg-4\">\n");
      out.write("                <div class=\"row\">\n");
      out.write("\t\t\t\t\t\t<a href='");
      out.print(basePath );
      out.write("/assets/images/product/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.mainImgPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("' class = 'cloud-zoom' id='zoom1' rel=\"adjustX: 10, adjustY:-4\">\n");
      out.write("\t\t\t            \t<img src=\"");
      out.print(basePath );
      out.write("/assets/images/product/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.mainImgPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" alt=''/>\n");
      out.write("\t\t\t        \t</a>\n");
      out.write("                </div>\n");
      out.write("\t                \n");
      out.write("                <div class=\"row\">  \n");
      out.write("                  <div class=\"col-lg-2\">\n");
      out.write("                  \t  <div><p>\n");
      out.write("                      <a href='");
      out.print(basePath );
      out.write("/assets/images/product/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.mainImgPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("' class='cloud-zoom-gallery'\n");
      out.write("\t\t\t        \trel=\"useZoom: 'zoom1', smallImage: '");
      out.print(basePath );
      out.write("/assets/images/product/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.mainImgPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("' \">\n");
      out.write("\t\t\t            <img src=\"");
      out.print(basePath );
      out.write("/assets/images/product/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.mainImgPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\n");
      out.write("\t\t\t          </a>  </p>\n");
      out.write("\t\t\t          </div>\n");
      out.write("                  </div>\n");
      out.write("                 ");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f7 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_005fif_005f7.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f7.setParent(null);
      // /WEB-INF/view/product/productDetail.jsp(51,17) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f7.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty product.zoomImg1 }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
      int _jspx_eval_c_005fif_005f7 = _jspx_th_c_005fif_005f7.doStartTag();
      if (_jspx_eval_c_005fif_005f7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                 \t<div class=\"col-lg-2\">\n");
          out.write("\t                 \t<a href='");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg1 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("' class='cloud-zoom-gallery'\n");
          out.write("\t\t\t\t        \trel=\"useZoom: 'zoom1', smallImage: '");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg1 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("' \">\n");
          out.write("\t\t\t\t            <img src=\"");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg1 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\"/>\n");
          out.write("\t\t\t\t        </a> \n");
          out.write("                    </div> \n");
          out.write("                 ");
          int evalDoAfterBody = _jspx_th_c_005fif_005f7.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f7);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f7);
      out.write("    \n");
      out.write("                 ");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f8 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_005fif_005f8.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f8.setParent(null);
      // /WEB-INF/view/product/productDetail.jsp(59,17) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f8.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty product.zoomImg2 }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
      int _jspx_eval_c_005fif_005f8 = _jspx_th_c_005fif_005f8.doStartTag();
      if (_jspx_eval_c_005fif_005f8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                 \t<div class=\"col-lg-2\">\n");
          out.write("\t                 \t<a href='");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg2 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("' class='cloud-zoom-gallery'\n");
          out.write("\t\t\t\t        \trel=\"useZoom: 'zoom1', smallImage: '");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg2 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("' \">\n");
          out.write("\t\t\t\t            <img src=\"");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg2 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\"/>\n");
          out.write("\t\t\t\t        </a>    \n");
          out.write("                    </div>\n");
          out.write("                 ");
          int evalDoAfterBody = _jspx_th_c_005fif_005f8.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f8);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f8);
      out.write(" \n");
      out.write("                 ");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f9 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_005fif_005f9.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f9.setParent(null);
      // /WEB-INF/view/product/productDetail.jsp(67,17) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f9.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty product.zoomImg3 }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
      int _jspx_eval_c_005fif_005f9 = _jspx_th_c_005fif_005f9.doStartTag();
      if (_jspx_eval_c_005fif_005f9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                 \t<div class=\"col-lg-2\">\n");
          out.write("\t                 \t<a href='");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg3 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("' class='cloud-zoom-gallery'\n");
          out.write("\t\t\t\t        \trel=\"useZoom: 'zoom1', smallImage: '");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg3 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("' \">\n");
          out.write("\t\t\t\t            <img src=\"");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg3 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\"/>\n");
          out.write("\t\t\t\t        </a>    \n");
          out.write("                    </div>\n");
          out.write("                 ");
          int evalDoAfterBody = _jspx_th_c_005fif_005f9.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f9);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f9);
      out.write(" \n");
      out.write("                 ");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f10 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_005fif_005f10.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f10.setParent(null);
      // /WEB-INF/view/product/productDetail.jsp(75,17) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f10.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty product.zoomImg4 }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
      int _jspx_eval_c_005fif_005f10 = _jspx_th_c_005fif_005f10.doStartTag();
      if (_jspx_eval_c_005fif_005f10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                 \t<div class=\"col-lg-2\">\n");
          out.write("\t                 \t<a href='");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg4 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("' class='cloud-zoom-gallery'\n");
          out.write("\t\t\t\t        \trel=\"useZoom: 'zoom1', smallImage: '");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg4 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("' \">\n");
          out.write("\t\t\t\t            <img src=\"");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg4 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\"/>\n");
          out.write("\t\t\t\t        </a>    \n");
          out.write("                    </div>\n");
          out.write("                 ");
          int evalDoAfterBody = _jspx_th_c_005fif_005f10.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f10);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f10);
      out.write(" \n");
      out.write("                 ");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f11 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_005fif_005f11.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f11.setParent(null);
      // /WEB-INF/view/product/productDetail.jsp(83,17) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f11.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty product.zoomImg5 }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
      int _jspx_eval_c_005fif_005f11 = _jspx_th_c_005fif_005f11.doStartTag();
      if (_jspx_eval_c_005fif_005f11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                \t <div class=\"col-lg-2\">\n");
          out.write("\t                 \t<a href='");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg5 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("' class='cloud-zoom-gallery'\n");
          out.write("\t\t\t\t        \trel=\"useZoom: 'zoom1', smallImage: '");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg5 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("' \">\n");
          out.write("\t\t\t\t            <img src=\"");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg5 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\"/>\n");
          out.write("\t\t\t\t        </a>    \n");
          out.write("                    </div> \n");
          out.write("                 ");
          int evalDoAfterBody = _jspx_th_c_005fif_005f11.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f11);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f11);
      out.write("   \n");
      out.write("                 </div>\n");
      out.write("                 <div class=\"row\">\n");
      out.write("                  ");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f12 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_005fif_005f12.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f12.setParent(null);
      // /WEB-INF/view/product/productDetail.jsp(93,18) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f12.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty product.zoomImg6 }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
      int _jspx_eval_c_005fif_005f12 = _jspx_th_c_005fif_005f12.doStartTag();
      if (_jspx_eval_c_005fif_005f12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                 \t <div class=\"col-lg-2\">\n");
          out.write("\t                 \t<a href='");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg6 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("' class='cloud-zoom-gallery'\n");
          out.write("\t\t\t\t        \trel=\"useZoom: 'zoom1', smallImage: '");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg6 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("' \">\n");
          out.write("\t\t\t\t            <img src=\"");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg6 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\"/>\n");
          out.write("\t\t\t\t        </a>     \n");
          out.write("                     </div>\n");
          out.write("                 ");
          int evalDoAfterBody = _jspx_th_c_005fif_005f12.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f12);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f12);
      out.write("   \n");
      out.write("                  ");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f13 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_005fif_005f13.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f13.setParent(null);
      // /WEB-INF/view/product/productDetail.jsp(101,18) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f13.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty product.zoomImg7 }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
      int _jspx_eval_c_005fif_005f13 = _jspx_th_c_005fif_005f13.doStartTag();
      if (_jspx_eval_c_005fif_005f13 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                  \t<div class=\"col-lg-2\">\n");
          out.write("\t                 \t<a href='");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg7 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("' class='cloud-zoom-gallery'\n");
          out.write("\t\t\t\t        \trel=\"useZoom: 'zoom1', smallImage: '");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg7 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("' \">\n");
          out.write("\t\t\t\t            <img src=\"");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg7 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\"/>\n");
          out.write("\t\t\t\t        </a>      \n");
          out.write("                    </div>\n");
          out.write("                 ");
          int evalDoAfterBody = _jspx_th_c_005fif_005f13.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f13);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f13);
      out.write("   \n");
      out.write("                  ");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f14 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_005fif_005f14.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f14.setParent(null);
      // /WEB-INF/view/product/productDetail.jsp(109,18) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f14.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty product.zoomImg8 }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
      int _jspx_eval_c_005fif_005f14 = _jspx_th_c_005fif_005f14.doStartTag();
      if (_jspx_eval_c_005fif_005f14 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                 \t <div class=\"col-lg-2\">\n");
          out.write("\t                 \t<a href='");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg8 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("' class='cloud-zoom-gallery'\n");
          out.write("\t\t\t\t        \trel=\"useZoom: 'zoom1', smallImage: '");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg8 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("' \">\n");
          out.write("\t\t\t\t            <img src=\"");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg8 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\"/>\n");
          out.write("\t\t\t\t        </a>      \n");
          out.write("                    </div>\n");
          out.write("                 ");
          int evalDoAfterBody = _jspx_th_c_005fif_005f14.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f14);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f14);
      out.write("   \n");
      out.write("                 ");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f15 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_005fif_005f15.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f15.setParent(null);
      // /WEB-INF/view/product/productDetail.jsp(117,17) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f15.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty product.zoomImg9 }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
      int _jspx_eval_c_005fif_005f15 = _jspx_th_c_005fif_005f15.doStartTag();
      if (_jspx_eval_c_005fif_005f15 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                 \t<div class=\"col-lg-2\">\n");
          out.write("\t                 \t<a href='");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg9 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("' class='cloud-zoom-gallery'\n");
          out.write("\t\t\t\t        \trel=\"useZoom: 'zoom1', smallImage: '");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg9 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("' \">\n");
          out.write("\t\t\t\t            <img src=\"");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg9 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\"/>\n");
          out.write("\t\t\t\t        </a>      \n");
          out.write("                    </div>\n");
          out.write("                 ");
          int evalDoAfterBody = _jspx_th_c_005fif_005f15.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f15);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f15);
      out.write("   \n");
      out.write("                  ");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f16 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_005fif_005f16.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f16.setParent(null);
      // /WEB-INF/view/product/productDetail.jsp(125,18) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f16.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty product.zoomImg10 }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
      int _jspx_eval_c_005fif_005f16 = _jspx_th_c_005fif_005f16.doStartTag();
      if (_jspx_eval_c_005fif_005f16 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                  \t<div class=\"col-lg-2\">\n");
          out.write("\t                 \t<a href='");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg10 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("' class='cloud-zoom-gallery'\n");
          out.write("\t\t\t\t        \trel=\"useZoom: 'zoom1', smallImage: '");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg10 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("' \">\n");
          out.write("\t\t\t\t            <img src=\"");
          out.print(basePath );
          out.write("/assets/images/product/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.zoomImg10 }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\"/>\n");
          out.write("\t\t\t\t        </a>      \n");
          out.write("                    </div>\n");
          out.write("                 ");
          int evalDoAfterBody = _jspx_th_c_005fif_005f16.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f16);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f16);
      out.write("    \n");
      out.write("                  \n");
      out.write("                </div>\n");
      out.write("              </div>\n");
      out.write("              <div class=\"col-lg-8\">\n");
      out.write("\t                <br/> \n");
      out.write("\t                 <div class=\"row\">\n");
      out.write("\t\t                  <div class=\"col-lg-3\"><p class=\"text-right\"><strong>ProductNo:</strong></p></div>\n");
      out.write("\t\t                  <div class=\"col-lg-9\"><p class=\"text-left\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.productNo }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</p></div>\n");
      out.write("\t                 </div>\n");
      out.write("\t                 ");
      if (_jspx_meth_c_005fchoose_005f1(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t                \n");
      out.write("\t                <div class=\"row\">\n");
      out.write("\t\t                  <div class=\"col-lg-3\"><p class=\"text-right\"><strong>Name:</strong></p></div>\n");
      out.write("\t\t                  <div class=\"col-lg-9\"><p class=\"text-left\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.productName }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</p>\n");
      out.write("\t                  </div>\n");
      out.write("\t                </div>\n");
      out.write("\t                <div class=\"row\">\n");
      out.write("\t\t                  <div class=\"col-lg-3\"><p class=\"text-right\"><strong>Category:</strong></p></div>\n");
      out.write("\t\t                  <div class=\"col-lg-9\"><p class=\"text-left\"> <a onclick=\"javascript:window.open('");
      out.print(basePath );
      out.write("/product/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.categoryId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/listProductByCategory');\" > ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.categoryName }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</a></p></div>\n");
      out.write("\t                </div>\n");
      out.write("\t                <div class=\"row\">\n");
      out.write("\t\t                  <div class=\"col-lg-3\"><p class=\"text-right\"><strong>Weight:</strong></p></div>\n");
      out.write("\t\t                  <div class=\"col-lg-9\"><p class=\"text-left\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.weight }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write(" KGS</p></div>\n");
      out.write("\t                </div>\n");
      out.write("\t                <div class=\"row\">\n");
      out.write("\t\t                  <div class=\"col-lg-3\"><p class=\"text-right\"><strong>Availability:</strong></p></div>\n");
      out.write("\t\t                  <div class=\"col-lg-9\"><p class=\"text-left\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.available }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</p></div>\n");
      out.write("\t                </div>\n");
      out.write("\t                <div class=\"row\">\n");
      out.write("\t\t                  <div class=\"col-lg-3\"><p class=\"text-right\"><strong>Sold:</strong></p></div>\n");
      out.write("\t\t                  <div class=\"col-lg-9\"><p class=\"text-left\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.sales+product.baseSales }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("&nbsp;&nbsp;&nbsp;&nbsp;<img src=\"");
      out.print(basePath );
      out.write("/assets/images/product/common/IcoRating");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.star }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write(".gif\"/></p></div>\n");
      out.write("\t                </div>\n");
      out.write("\t                \n");
      out.write("\t                <div class=\"row\">\n");
      out.write("\t\t                  <div class=\"col-lg-3\"><p class=\"text-right\"><strong>Quantity:</strong></p></div>\n");
      out.write("\t\t                  <div class=\"col-lg-9\"><p class=\"text-left\">\n");
      out.write("\t\t\t\t\t\t\t\t<select id=\"item_amount\" class=\"span2\">\n");
      out.write("\t\t\t                        ");
      if (_jspx_meth_c_005fforEach_005f5(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t                           </select>\n");
      out.write("\t \t\t\t\t\t\t\t&nbsp;&nbsp;\n");
      out.write("\t \t\t\t\t\t\t\t<a href=\"javascript:addToCart2(");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write(',');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.actualSellPrice}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write(");\"><img src=\"");
      out.print(basePath );
      out.write("/assets/icos/product/addToCart.jpg\" /></a>\n");
      out.write("\t \t\t\t\t\t  </div>\n");
      out.write("\t                </div>\n");
      out.write("               \n");
      out.write("              </div>            \n");
      out.write("          </div><!--/row-->  \n");
      out.write("\n");
      out.write("          <hr>   \t\t \n");
      out.write("\n");
      out.write("          <div class=\"row\">\n");
      out.write("            <h4>Product Description</h4>\n");
      out.write("            <p>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.desc}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write(" </p>\n");
      out.write("            <br/>\n");
      out.write("          </div><!--/Product Description-->\n");
      out.write("\n");
      out.write("          <hr>\n");
      out.write("\n");
      out.write("       ");
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
	String basePath4footcustomerlike = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();   

      out.write("  \n");
      out.write(" <h4>Customers Who Viewed This Product Also Viewed</h4>\n");
      out.write("          \n");
      out.write("          <div class=\"row\">\n");
      out.write("\t           ");
      //  c:forEach
      org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f6 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
      _jspx_th_c_005fforEach_005f6.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f6.setParent(null);
      // /WEB-INF/view/product/foot4customerlike.jsp(14,12) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f6.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/view/product/foot4customerlike.jsp(14,12) '${customersLikes }'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${customersLikes }",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
      // /WEB-INF/view/product/foot4customerlike.jsp(14,12) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f6.setVar("card");
      int[] _jspx_push_body_count_c_005fforEach_005f6 = new int[] { 0 };
      try {
        int _jspx_eval_c_005fforEach_005f6 = _jspx_th_c_005fforEach_005f6.doStartTag();
        if (_jspx_eval_c_005fforEach_005f6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\n");
            out.write("\t          \t\t\t<div class=\"col-lg-3\">\n");
            out.write("\t\t\t\t              <a href=\"");
            out.print(basePath4footcustomerlike );
            out.write("/product/");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("/productDetail\" class=\"thumbnail\">\n");
            out.write("\t\t\t\t                <img src=\"");
            out.print(basePath4footcustomerlike );
            out.write("/assets/images/product/");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.mainImgPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\"/>\n");
            out.write("\t\t\t\t              </a>\n");
            out.write("\t\t\t\t              <p class=\"text-center common\">\n");
            out.write("\t\t\t\t              <a href=\"");
            out.print(basePath4footcustomerlike );
            out.write("/product/");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("/productDetail\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.productName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</a>\n");
            out.write("\t\t\t\t              </p>\n");
            out.write("\t\t\t\t              ");
            if (_jspx_meth_c_005fchoose_005f2(_jspx_th_c_005fforEach_005f6, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f6))
              return;
            out.write("\n");
            out.write("\t\t\t\t              <div>\n");
            out.write("\t\t\t\t              \t\t<p class=\"text-left span6\">&nbsp;&nbsp;<img src=\"");
            out.print(basePath4footcustomerlike );
            out.write("/assets/images/product/common/IcoRating5.gif\"/></p> \n");
            out.write("\t\t\t\t\t\t\t\t\t<a href=\"javascript:addToCart(");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write(',');
            out.write('1');
            out.write(',');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.actualSellPrice }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write(");\"><img src=\"");
            out.print(basePath4footcustomerlike );
            out.write("/assets/icos/product/addToCart.jpg\"/></a>\n");
            out.write("\t\t\t\t              </div>\n");
            out.write("\t             \t\t</div>\n");
            out.write("\t          \t");
            int evalDoAfterBody = _jspx_th_c_005fforEach_005f6.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_c_005fforEach_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return;
        }
      } catch (Throwable _jspx_exception) {
        while (_jspx_push_body_count_c_005fforEach_005f6[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_c_005fforEach_005f6.doCatch(_jspx_exception);
      } finally {
        _jspx_th_c_005fforEach_005f6.doFinally();
        _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f6);
      }
      out.write("\n");
      out.write("\t          \t\n");
      out.write("          </div><!--/row-fluid-->");
      out.write("\n");
      out.write("\n");
      out.write("          <hr>\n");
      out.write("    \t\t  \n");
      out.write("    \t\t</div><!--/span8-->\n");
      out.write("\n");
      out.write("      </div><!-- row-fluid -->\n");
      out.write("\n");
      out.write("    </div><!-- container-fluid -->\n");
      out.write("    \t<script type=\"text/javascript\" language=\"javascript\">\n");
      out.write("   \tfunction addToCart2(productId,unitPrice){\n");
      out.write("\n");
      out.write("\t   \t var obj = document.getElementById(\"item_amount\"); //定位id\n");
      out.write("\t   \t var index = obj.selectedIndex; // 选中索引\n");
      out.write("\t   \t var amount = obj.options[index].value; // 选中值\n");
      out.write("\t   \t\n");
      out.write("\t   \taddToCart(productId,amount,unitPrice) ;\n");
      out.write("   \t}\n");
      out.write("   \t\n");
      out.write("\t</script> \n");
      out.write("\t\n");
      out.write("  </body>\n");
      out.write("</html>\n");
      out.write("\n");
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

  private boolean _jspx_meth_c_005fset_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f1);
    // /WEB-INF/view/product/left.jsp(51,9) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setVar("hotProducts_index");
    // /WEB-INF/view/product/left.jsp(51,9) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setValue(new org.apache.jasper.el.JspValueExpression("/WEB-INF/view/product/left.jsp(51,9) '1'",_el_expressionfactory.createValueExpression("1",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    int _jspx_eval_c_005fset_005f0 = _jspx_th_c_005fset_005f0.doStartTag();
    if (_jspx_th_c_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f2, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f1 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f2);
    // /WEB-INF/view/product/left.jsp(69,9) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f1.setVar("hotProducts_index");
    // /WEB-INF/view/product/left.jsp(69,9) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f1.setValue(new org.apache.jasper.el.JspValueExpression("/WEB-INF/view/product/left.jsp(69,9) '${hotProducts_index+1 }'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${hotProducts_index+1 }",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    int _jspx_eval_c_005fset_005f1 = _jspx_th_c_005fset_005f1.doStartTag();
    if (_jspx_th_c_005fset_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f2 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f3);
    // /WEB-INF/view/product/left.jsp(86,9) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f2.setVar("newProducts_index");
    // /WEB-INF/view/product/left.jsp(86,9) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f2.setValue(new org.apache.jasper.el.JspValueExpression("/WEB-INF/view/product/left.jsp(86,9) '1'",_el_expressionfactory.createValueExpression("1",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    int _jspx_eval_c_005fset_005f2 = _jspx_th_c_005fset_005f2.doStartTag();
    if (_jspx_th_c_005fset_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f4, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f3)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f3 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f4);
    // /WEB-INF/view/product/left.jsp(104,9) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f3.setVar("newProducts_index");
    // /WEB-INF/view/product/left.jsp(104,9) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f3.setValue(new org.apache.jasper.el.JspValueExpression("/WEB-INF/view/product/left.jsp(104,9) '${newProducts_index+1 }'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${newProducts_index+1 }",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    int _jspx_eval_c_005fset_005f3 = _jspx_th_c_005fset_005f3.doStartTag();
    if (_jspx_th_c_005fset_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f3);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f4 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f4.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f5);
    // /WEB-INF/view/product/left.jsp(121,9) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f4.setVar("discountProducts_index");
    // /WEB-INF/view/product/left.jsp(121,9) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f4.setValue(new org.apache.jasper.el.JspValueExpression("/WEB-INF/view/product/left.jsp(121,9) '1'",_el_expressionfactory.createValueExpression("1",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    int _jspx_eval_c_005fset_005f4 = _jspx_th_c_005fset_005f4.doStartTag();
    if (_jspx_th_c_005fset_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f4);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f6, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f4)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f5 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f5.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f6);
    // /WEB-INF/view/product/left.jsp(139,9) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f5.setVar("discountProducts_index");
    // /WEB-INF/view/product/left.jsp(139,9) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f5.setValue(new org.apache.jasper.el.JspValueExpression("/WEB-INF/view/product/left.jsp(139,9) '${discountProducts_index+1 }'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${discountProducts_index+1 }",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    int _jspx_eval_c_005fset_005f5 = _jspx_th_c_005fset_005f5.doStartTag();
    if (_jspx_th_c_005fset_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f5);
    return false;
  }

  private boolean _jspx_meth_c_005fchoose_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f1 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f1.setParent(null);
    int _jspx_eval_c_005fchoose_005f1 = _jspx_th_c_005fchoose_005f1.doStartTag();
    if (_jspx_eval_c_005fchoose_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("\t\t\t\t\t\t\t");
        if (_jspx_meth_c_005fwhen_005f1(_jspx_th_c_005fchoose_005f1, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("\t\t\t\t\t\t\t");
        if (_jspx_meth_c_005fotherwise_005f1(_jspx_th_c_005fchoose_005f1, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f1 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f1);
    // /WEB-INF/view/product/productDetail.jsp(143,7) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.actualSellPrice < product.preSellPrice}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f1 = _jspx_th_c_005fwhen_005f1.doStartTag();
    if (_jspx_eval_c_005fwhen_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("\t\t\t\t\t\t\t\t<div class=\"row\">\n");
        out.write("\t\t\t\t\t                  <div class=\"col-lg-3\"><p class=\"text-right\"><strong>Price:</strong></p></div>\n");
        out.write("\t\t\t\t\t                  <div class=\"col-lg-9\"><p class=\"text-left\"><del>$");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.preSellPrice }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</del></p></div>\n");
        out.write("\t\t\t\t                </div>\n");
        out.write("\t\t\t\t                <div class=\"row\">\n");
        out.write("\t\t\t\t\t                  <div class=\"col-lg-3\"><p class=\"text-right\"><strong>New Price:</strong></p></div>\n");
        out.write("\t\t\t\t\t                  <div class=\"col-lg-9\"><p class=\"text-left\"><font color=\"red\">$");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.actualSellPrice }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</font></p></div>\n");
        out.write("\t\t\t\t                </div>\n");
        out.write("\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f1 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f1);
    int _jspx_eval_c_005fotherwise_005f1 = _jspx_th_c_005fotherwise_005f1.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("\t\t\t\t\t\t\t\t<div class=\"row\">\n");
        out.write("\t\t\t\t\t                  <div class=\"col-lg-3\"><p class=\"text-right\"><strong>Price:</strong></p></div>\n");
        out.write("\t\t\t\t\t                  <div class=\"col-lg-9\"><p class=\"text-left\"><font color=\"red\">$");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${product.actualSellPrice }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</font></p></div>\n");
        out.write("\t\t\t\t                </div>\n");
        out.write("\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f5(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f5 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fend_005fbegin.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f5.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f5.setParent(null);
    // /WEB-INF/view/product/productDetail.jsp(187,27) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f5.setVar("i");
    // /WEB-INF/view/product/productDetail.jsp(187,27) name = begin type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f5.setBegin(1);
    // /WEB-INF/view/product/productDetail.jsp(187,27) name = end type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f5.setEnd(30);
    int[] _jspx_push_body_count_c_005fforEach_005f5 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f5 = _jspx_th_c_005fforEach_005f5.doStartTag();
      if (_jspx_eval_c_005fforEach_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("\t\t\t\t\t\t\t\t\t\t<option value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write('"');
          out.write('>');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</option>\n");
          out.write("\t\t\t\t\t\t\t\t\t");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f5.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f5[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f5.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f5.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fend_005fbegin.reuse(_jspx_th_c_005fforEach_005f5);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fchoose_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f6, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f6)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f2 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f6);
    int _jspx_eval_c_005fchoose_005f2 = _jspx_th_c_005fchoose_005f2.doStartTag();
    if (_jspx_eval_c_005fchoose_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_c_005fwhen_005f2(_jspx_th_c_005fchoose_005f2, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f6))
          return true;
        out.write("\n");
        out.write("\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_c_005fotherwise_005f2(_jspx_th_c_005fchoose_005f2, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f6))
          return true;
        out.write("\n");
        out.write("\t\t\t\t \t\t    ");
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f2, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f6)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f2 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f2);
    // /WEB-INF/view/product/foot4customerlike.jsp(23,9) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.preSellPrice > card.actualSellPrice}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f2 = _jspx_th_c_005fwhen_005f2.doStartTag();
    if (_jspx_eval_c_005fwhen_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("\t\t\t\t\t\t\t\t\t\t<font color=\"red\">&nbsp;&nbsp;$");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.actualSellPrice }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</font>&nbsp;&nbsp;&nbsp;&nbsp;<del>$");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.preSellPrice }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</del>\n");
        out.write("\t\t\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f2, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f6)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f2 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f2);
    int _jspx_eval_c_005fotherwise_005f2 = _jspx_th_c_005fotherwise_005f2.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("\t\t\t\t\t\t\t\t\t\t<font color=\"red\">&nbsp;&nbsp;$");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.actualSellPrice }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</font>\n");
        out.write("\t\t\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f2);
    return false;
  }
}
