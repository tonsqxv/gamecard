package org.apache.jsp.WEB_002dINF.view.product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class about_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(3);
    _jspx_dependants.add("/WEB-INF/view/product/../common/taglibs.jsp");
    _jspx_dependants.add("/WEB-INF/view/product/head.jsp");
    _jspx_dependants.add("/WEB-INF/view/product/left.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fchoose;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fotherwise;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;

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
      out.write('\r');
      out.write('\n');

	String path = request.getContextPath();    
	//获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:    
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path; 

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("  <head>\r\n");
      out.write("    <script src=\"");
      out.print(basePath );
      out.write("/assets/js/common/util.js\"></script>\r\n");
      out.write("  </head>\r\n");
      out.write("  <body>\r\n");
      out.write("  ");
      out.write(" \r\n");
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
	String basePath4head = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();   

      out.write("  \r\n");
      out.write("\r\n");
      out.write("\t<div class=\"navbar\">\r\n");
      out.write("\t\t   <div class=\"navbar-inner\">\r\n");
      out.write("\t\t        <a class=\"brand\" href=\"#\">NAVIGATOR</a>\r\n");
      out.write("\t\t        <ul class=\"nav nav-tabs\">\r\n");
      out.write("\t\t          <li class=\"active\"><a href=\"");
      out.print(basePath4head );
      out.write("/index\">Home</a></li>\r\n");
      out.write("\t\t          <li class=\"divider-vertical\"></li>  \r\n");
      out.write("\t\t           <li><a href=\"");
      out.print(basePath4head);
      out.write("/help/about\">About Us</a></li>\r\n");
      out.write("\t\t           <li><a href=\"");
      out.print(basePath4head);
      out.write("/help/contact\">Contact Us</a></li>\r\n");
      out.write("\t\t           <li><a href=\"");
      out.print(basePath4head);
      out.write("/help/shippingAndReturn\">Shipping&amp;Returns</a></li>\r\n");
      out.write("\t\t           <li class=\"divider-vertical\"></li>  \r\n");
      out.write("\t\t           <li><a href=\"");
      out.print(basePath4head);
      out.write("/help/\">Help&amp;Faqs</a></li>\r\n");
      out.write("\t\t        </ul>\r\n");
      out.write("\t\t        <form class=\"navbar-form pull-right\" action=\"");
      out.print(basePath4head);
      out.write("/product/searchBy\">\r\n");
      out.write("\t\t           <input type=\"hidden\" name=\"searchBy_type\" value=\"search\">\r\n");
      out.write("                   <input type=\"text\" name=\"searchByParam_productName\" class=\"span3\" placeholder=\"key word\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${searchByParam_productName }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">\r\n");
      out.write("                   <button type=\"submit\" class=\"btn\">Search</button>\r\n");
      out.write("                 </form>\r\n");
      out.write("\t\t   </div>\r\n");
      out.write("    </div>");
      out.write("\r\n");
      out.write("   ");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("    \r\n");
      out.write("    <div class=\"container-fluid\">\r\n");
      out.write("      <div class=\"row-fluid\">\r\n");
      out.write("    \t\t<div class=\"span2 offset1\" >\r\n");
      out.write("   \t\t\t\t\t");
      out.write(" \r\n");
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
	String basePath4left = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();   
	

      out.write("  \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<!-- 分类树 -->\r\n");
      out.write("\t\t\t\t<div class=\"dropdown clearfix\">\r\n");
      out.write("\t\t              <ul class=\"dropdown-menu nav nav-list btn-block\" style=\"display: block; position: static; margin-bottom: 5px; *width: 180px;\">\r\n");
      out.write("\t\t                <!-- 类别标题 -->\r\n");
      out.write("\t\t                <h4 align=\"center\">CATEGORIES</h4>\r\n");
      out.write("\t\t                <!-- 分割线 -->\r\n");
      out.write("\t\t                <li class=\"divider\"></li>\r\n");
      out.write("\t\t                <!-- 遍历类别 -->\r\n");
      out.write("\t\t     \t\t\t");
      //  c:forEach
      org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
      _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f0.setParent(null);
      // /WEB-INF/view/product/left.jsp(22,10) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/view/product/left.jsp(22,10) '${categoryTree}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${categoryTree}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
      // /WEB-INF/view/product/left.jsp(22,10) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setVar("t");
      int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
      try {
        int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
        if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            out.write("\t\t     \t\t\t");
            //  c:choose
            org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f0 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
            _jspx_th_c_005fchoose_005f0.setPageContext(_jspx_page_context);
            _jspx_th_c_005fchoose_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
            int _jspx_eval_c_005fchoose_005f0 = _jspx_th_c_005fchoose_005f0.doStartTag();
            if (_jspx_eval_c_005fchoose_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              do {
                out.write("\r\n");
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
                    out.write("\r\n");
                    out.write("\t\t\t\t\t\t\t\t\t<li class=\"dropdown-submenu\"><a tabindex=\"-1\" href=\"");
                    out.print(basePath4left );
                    out.write("/product/");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${t.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("/listProductByCategory\">");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${t.name }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("</a></li>\r\n");
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
                out.write("\r\n");
                out.write("\t\t\t\t\t\t\t\t");
                //  c:otherwise
                org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f0 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
                _jspx_th_c_005fotherwise_005f0.setPageContext(_jspx_page_context);
                _jspx_th_c_005fotherwise_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
                int _jspx_eval_c_005fotherwise_005f0 = _jspx_th_c_005fotherwise_005f0.doStartTag();
                if (_jspx_eval_c_005fotherwise_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  do {
                    out.write("\r\n");
                    out.write("\t\t\t\t\t\t\t\t\t<li class=\"dropdown-submenu\">\r\n");
                    out.write("\t\t\t\t\t\t\t\t\t<a>");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${t.name }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("</a>\r\n");
                    out.write("\t\t\t\t\t\t\t\t\t<ul class=\"dropdown-menu\">\r\n");
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
                          out.write("\r\n");
                          out.write("\t\t\t\t\t\t\t\t\t\t<li><a tabindex=\"-1\" href=\"");
                          out.print(basePath4left );
                          out.write("/product/");
                          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${c.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                          out.write("/listProductByCategory\">");
                          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${c.name}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                          out.write("</a></li>\r\n");
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
                    out.write("\r\n");
                    out.write("\t\t\t\t\t\t\t\t\t</ul>\r\n");
                    out.write("\t\t\t\t\t\t\t\t\t</li>\r\n");
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
                out.write("\r\n");
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
            out.write("\r\n");
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
      out.write("\r\n");
      out.write("\t\t                <li class=\"divider\"></li>\r\n");
      out.write("\t\t              </ul>\r\n");
      out.write("\t\t          </div>\r\n");
      out.write("\t\t          \r\n");
      out.write("\t\t          <!-- 热门产品 -->\r\n");
      out.write("\t\t          ");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f1.setParent(null);
      // /WEB-INF/view/product/left.jsp(44,12) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty hotProducts }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
      int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
      if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t          <div class=\"dropdown clearfix\">\r\n");
          out.write("\t\t     \t\t<ul class=\"dropdown-menu  nav nav-list  btn-block\"  style=\"display: block; position: static; margin-bottom: 5px; *width: 180px;\">\r\n");
          out.write("\t\t     \t\t\t<h4 align=\"center\">Hot Products</h4>\r\n");
          out.write("\t\t     \t\t\t<li class=\"divider\"></li>\r\n");
          out.write("\t\t     \t\t\t");
          if (_jspx_meth_c_005fset_005f0(_jspx_th_c_005fif_005f1, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("\t\t\t\t\t\t");
          //  c:forEach
          org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f2 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
          _jspx_th_c_005fforEach_005f2.setPageContext(_jspx_page_context);
          _jspx_th_c_005fforEach_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f1);
          // /WEB-INF/view/product/left.jsp(50,6) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
          _jspx_th_c_005fforEach_005f2.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/view/product/left.jsp(50,6) '${hotProducts }'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${hotProducts }",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
          // /WEB-INF/view/product/left.jsp(50,6) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_c_005fforEach_005f2.setVar("card");
          int[] _jspx_push_body_count_c_005fforEach_005f2 = new int[] { 0 };
          try {
            int _jspx_eval_c_005fforEach_005f2 = _jspx_th_c_005fforEach_005f2.doStartTag();
            if (_jspx_eval_c_005fforEach_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              do {
                out.write("\r\n");
                out.write("\t\t\t\t\t\t");
                //  c:if
                org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f2 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
                _jspx_th_c_005fif_005f2.setPageContext(_jspx_page_context);
                _jspx_th_c_005fif_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f2);
                // /WEB-INF/view/product/left.jsp(51,6) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                _jspx_th_c_005fif_005f2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${hotProducts_index != (hotSize+1) }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
                int _jspx_eval_c_005fif_005f2 = _jspx_th_c_005fif_005f2.doStartTag();
                if (_jspx_eval_c_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  do {
                    out.write("\r\n");
                    out.write("\t\t\t\t\t\t\t<div class=\"row-fluid\">\r\n");
                    out.write("\t\t\t\t              <div class=\"span5\">\r\n");
                    out.write("\t\t\t\t                <a href=\"");
                    out.print(basePath4left );
                    out.write("/product/");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("/productDetail\" class=\"thumbnail\">\r\n");
                    out.write("\t\t\t\t                  <img src=\"");
                    out.print(basePath4left );
                    out.write("/assets/images/product/");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.mainImgPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("\"/>\r\n");
                    out.write("\t\t\t\t                </a>\r\n");
                    out.write("\t\t\t\t              </div><!--/span-->\r\n");
                    out.write("\t\t\t\t              <div class=\"span7\">\r\n");
                    out.write("\t\t\t\t                <p><strong ><font color=\"red\">$");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.actualSellPrice }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("</font></strong></p>\r\n");
                    out.write("\t\t\t\t                <p>Sold:");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.sales+card.baseSales}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("</p>\r\n");
                    out.write("\t\t\t\t              </div><!--/span-->\r\n");
                    out.write("\t\t\t\t              <div class=\"span12\">\r\n");
                    out.write("\t\t\t\t                <a href=\"");
                    out.print(basePath4left );
                    out.write("/product/");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("/productDetail\" title=\"");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.productName }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write('"');
                    out.write('>');
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.shortName }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("</a>\r\n");
                    out.write("\t\t\t\t              </div><!--/span-->\r\n");
                    out.write("\t\t\t\t            </div><!--/row-fluid-->\r\n");
                    out.write("\t\t\t\t            <br>\r\n");
                    out.write("\t\t\t\t        ");
                    if (_jspx_meth_c_005fset_005f1(_jspx_th_c_005fif_005f2, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
                      return;
                    out.write("\r\n");
                    out.write("\t\t\t\t        ");
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
                out.write("    \r\n");
                out.write("\t\t\t\t\t\t");
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
          out.write("\r\n");
          out.write("\t\t\t\t\t\t<div>\r\n");
          out.write("\t\t\t              <p class=\"text-right\"><a href=\"");
          out.print(basePath4left );
          out.write("/product/viewMoreProduct?type=1\">View More &gt;&gt;</a></p>\r\n");
          out.write("\t\t\t            </div>\r\n");
          out.write("\t\t\t          </ul>\r\n");
          out.write("\t\t          </div>\r\n");
          out.write("\t\t          ");
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
      out.write("\t\t          \r\n");
      out.write("\t\t           <!-- 最新产品 -->\r\n");
      out.write("\t\t          ");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f3 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_005fif_005f3.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f3.setParent(null);
      // /WEB-INF/view/product/left.jsp(78,12) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f3.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty newProducts }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
      int _jspx_eval_c_005fif_005f3 = _jspx_th_c_005fif_005f3.doStartTag();
      if (_jspx_eval_c_005fif_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t          <div class=\"dropdown clearfix\">\r\n");
          out.write("\t\t     \t\t<ul class=\"dropdown-menu  nav nav-list btn-block\"  style=\"display: block; position: static; margin-bottom: 5px; *width: 180px;\">\r\n");
          out.write("\t\t     \t\t\t<h4 align=\"center\">New Products</h4>\r\n");
          out.write("\t\t     \t\t\t<li class=\"divider\"></li>\r\n");
          out.write("\t\t     \t\t\t");
          if (_jspx_meth_c_005fset_005f2(_jspx_th_c_005fif_005f3, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("\t\t\t\t\t\t");
          //  c:forEach
          org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f3 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
          _jspx_th_c_005fforEach_005f3.setPageContext(_jspx_page_context);
          _jspx_th_c_005fforEach_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f3);
          // /WEB-INF/view/product/left.jsp(84,6) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
          _jspx_th_c_005fforEach_005f3.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/view/product/left.jsp(84,6) '${newProducts }'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${newProducts }",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
          // /WEB-INF/view/product/left.jsp(84,6) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_c_005fforEach_005f3.setVar("card");
          int[] _jspx_push_body_count_c_005fforEach_005f3 = new int[] { 0 };
          try {
            int _jspx_eval_c_005fforEach_005f3 = _jspx_th_c_005fforEach_005f3.doStartTag();
            if (_jspx_eval_c_005fforEach_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              do {
                out.write("\r\n");
                out.write("\t\t\t\t\t\t");
                //  c:if
                org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f4 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
                _jspx_th_c_005fif_005f4.setPageContext(_jspx_page_context);
                _jspx_th_c_005fif_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f3);
                // /WEB-INF/view/product/left.jsp(85,6) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                _jspx_th_c_005fif_005f4.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${newProducts_index != (newSize+1) }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
                int _jspx_eval_c_005fif_005f4 = _jspx_th_c_005fif_005f4.doStartTag();
                if (_jspx_eval_c_005fif_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  do {
                    out.write("\r\n");
                    out.write("\t\t\t\t\t\t\t<div class=\"row-fluid\">\r\n");
                    out.write("\t\t\t\t              <div class=\"span5\">\r\n");
                    out.write("\t\t\t\t                <a href=\"");
                    out.print(basePath4left );
                    out.write("/product/");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("/productDetail\" class=\"thumbnail\">\r\n");
                    out.write("\t\t\t\t                  <img src=\"");
                    out.print(basePath4left );
                    out.write("/assets/images/product/");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.mainImgPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("\"/>\r\n");
                    out.write("\t\t\t\t                </a>\r\n");
                    out.write("\t\t\t\t              </div><!--/span-->\r\n");
                    out.write("\t\t\t\t              <div class=\"span7\">\r\n");
                    out.write("\t\t\t\t                <p><strong ><font color=\"red\">$");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.actualSellPrice }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("</font></strong></p>\r\n");
                    out.write("\t\t\t\t                <p>Sold:");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.sales+card.baseSales}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("</p>\r\n");
                    out.write("\t\t\t\t              </div><!--/span-->\r\n");
                    out.write("\t\t\t\t              <div class=\"span12\">\r\n");
                    out.write("\t\t\t\t                <a href=\"");
                    out.print(basePath4left );
                    out.write("/product/");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("/productDetail\" title=\"");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.productName }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write('"');
                    out.write('>');
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.shortName }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("</a>\r\n");
                    out.write("\t\t\t\t              </div><!--/span-->\r\n");
                    out.write("\t\t\t\t            </div><!--/row-fluid-->\r\n");
                    out.write("\t\t\t\t            <br>\r\n");
                    out.write("\t\t\t\t        ");
                    if (_jspx_meth_c_005fset_005f3(_jspx_th_c_005fif_005f4, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f3))
                      return;
                    out.write("\r\n");
                    out.write("\t\t\t\t        ");
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
                out.write("    \r\n");
                out.write("\t\t\t\t\t\t");
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
          out.write("\r\n");
          out.write("\t\t\t\t\t\t<div>\r\n");
          out.write("\t\t\t              <p class=\"text-right\"><a href=\"");
          out.print(basePath4left );
          out.write("/product/viewMoreProduct?type=2\">View More &gt;&gt;</a></p>\r\n");
          out.write("\t\t\t            </div>\r\n");
          out.write("\t\t\t         </ul>\r\n");
          out.write("\t\t          </div>\r\n");
          out.write("\t\t          ");
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
      out.write("\r\n");
      out.write("\t\t          \r\n");
      out.write("\t\t          <!-- 特价商品 -->\r\n");
      out.write("\t\t          ");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f5 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_005fif_005f5.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f5.setParent(null);
      // /WEB-INF/view/product/left.jsp(112,12) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f5.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty discountProducts }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
      int _jspx_eval_c_005fif_005f5 = _jspx_th_c_005fif_005f5.doStartTag();
      if (_jspx_eval_c_005fif_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t          <div class=\"dropdown clearfix\">\r\n");
          out.write("\t\t          \t<ul class=\"dropdown-menu  nav nav-list btn-block\"  style=\"display: block; position: static; margin-bottom: 5px; *width: 180px;\">\r\n");
          out.write("\t\t     \t\t\t<h4 align=\"center\">Discount Products</h4>\r\n");
          out.write("\t\t     \t\t\t<li class=\"divider\"></li>\r\n");
          out.write("\t\t     \t\t\t");
          if (_jspx_meth_c_005fset_005f4(_jspx_th_c_005fif_005f5, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("\t\t\t\t\t\t");
          //  c:forEach
          org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f4 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
          _jspx_th_c_005fforEach_005f4.setPageContext(_jspx_page_context);
          _jspx_th_c_005fforEach_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f5);
          // /WEB-INF/view/product/left.jsp(118,6) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
          _jspx_th_c_005fforEach_005f4.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/view/product/left.jsp(118,6) '${discountProducts }'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${discountProducts }",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
          // /WEB-INF/view/product/left.jsp(118,6) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_c_005fforEach_005f4.setVar("card");
          int[] _jspx_push_body_count_c_005fforEach_005f4 = new int[] { 0 };
          try {
            int _jspx_eval_c_005fforEach_005f4 = _jspx_th_c_005fforEach_005f4.doStartTag();
            if (_jspx_eval_c_005fforEach_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              do {
                out.write("\r\n");
                out.write("\t\t\t\t\t\t");
                //  c:if
                org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f6 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
                _jspx_th_c_005fif_005f6.setPageContext(_jspx_page_context);
                _jspx_th_c_005fif_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f4);
                // /WEB-INF/view/product/left.jsp(119,6) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                _jspx_th_c_005fif_005f6.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${discountProducts_index != (discountSize+1) }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
                int _jspx_eval_c_005fif_005f6 = _jspx_th_c_005fif_005f6.doStartTag();
                if (_jspx_eval_c_005fif_005f6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  do {
                    out.write("\r\n");
                    out.write("\t\t\t\t\t\t\t<div class=\"row-fluid\">\r\n");
                    out.write("\t\t\t\t              <div class=\"span5\">\r\n");
                    out.write("\t\t\t\t                <a href=\"");
                    out.print(basePath4left );
                    out.write("/product/");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("/productDetail\" class=\"thumbnail\">\r\n");
                    out.write("\t\t\t\t                  <img src=\"");
                    out.print(basePath4left );
                    out.write("/assets/images/product/");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.mainImgPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("\"/>\r\n");
                    out.write("\t\t\t\t                </a>\r\n");
                    out.write("\t\t\t\t              </div><!--/span-->\r\n");
                    out.write("\t\t\t\t              <div class=\"span7\">\r\n");
                    out.write("\t\t\t\t                <p><strong ><font color=\"red\">$");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.actualSellPrice }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("</font></strong></p>\r\n");
                    out.write("\t\t\t\t                <p>Sold:");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.sales+card.baseSales}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("</p>\r\n");
                    out.write("\t\t\t\t              </div><!--/span-->\r\n");
                    out.write("\t\t\t\t              <div class=\"span12\">\r\n");
                    out.write("\t\t\t\t                <a href=\"");
                    out.print(basePath4left );
                    out.write("/product/");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("/productDetail\" title=\"");
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.productName }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write('"');
                    out.write('>');
                    out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.shortName }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                    out.write("</a>\r\n");
                    out.write("\t\t\t\t              </div><!--/span-->\r\n");
                    out.write("\t\t\t\t            </div><!--/row-fluid-->\r\n");
                    out.write("\t\t\t\t            <br>\r\n");
                    out.write("\t\t\t\t        ");
                    if (_jspx_meth_c_005fset_005f5(_jspx_th_c_005fif_005f6, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f4))
                      return;
                    out.write("\r\n");
                    out.write("\t\t\t\t        ");
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
                out.write("    \r\n");
                out.write("\t\t\t\t\t\t");
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
          out.write("\r\n");
          out.write("\t\t\t\t\t\t<div>\r\n");
          out.write("\t\t\t              <p class=\"text-right\"><a href=\"");
          out.print(basePath4left );
          out.write("/product/viewMoreProduct?type=3\">View More &gt;&gt;</a></p>\r\n");
          out.write("\t\t\t            </div>\r\n");
          out.write("\t\t\t         </ul>\r\n");
          out.write("\t\t          </div>\r\n");
          out.write("\t\t          ");
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
      out.write("\r\n");
      out.write("\t\t          \r\n");
      out.write("\t\t          \r\n");
      out.write("\t\t          ");
      out.write("\r\n");
      out.write("   \t\t\t</div><!--/span2-->\r\n");
      out.write("    \t\t<div class=\"span8\">\r\n");
      out.write("\t\t    \t  <ul class=\"breadcrumb\">\r\n");
      out.write("\t\t    \t\t  <li><a href=\"");
      out.print(basePath );
      out.write("/index\">Home</a> <span class=\"divider\">&gt;&gt; about us</span></li>\r\n");
      out.write("\t\t    \t  </ul>\r\n");
      out.write("\t\t\t\t \r\n");
      out.write("\t\t          <h4><font color=\"orange\">About us</font></h4>\r\n");
      out.write("\t\t\t\t  <h5>Welcome to our store</h5>\r\n");
      out.write("\t\t\t\t  <p>\r\n");
      out.write("\t\t\t\t\t<a href=\"http://www.n3ds-card.com\"><u>www.n3ds-card.com</u></a>, established in 2005, is a professional supplier of Nintendo Flash Cards, we are located in Hua’qiang North, Shenzhen, China, where is considered to be the biggest electronics Market of the world. We only supply genuine Nintendo flash card, including \r\n");
      out.write("\t\t\t\t\t");
      //  c:forEach
      org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f5 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
      _jspx_th_c_005fforEach_005f5.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f5.setParent(null);
      // /WEB-INF/view/product/about.jsp(37,5) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f5.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/view/product/about.jsp(37,5) '${categories}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${categories}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
      // /WEB-INF/view/product/about.jsp(37,5) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f5.setVar("t");
      int[] _jspx_push_body_count_c_005fforEach_005f5 = new int[] { 0 };
      try {
        int _jspx_eval_c_005fforEach_005f5 = _jspx_th_c_005fforEach_005f5.doStartTag();
        if (_jspx_eval_c_005fforEach_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            out.write("\t\t\t\t\t\t<u><a onmouseover=\"color:red\" onmouseout=\"\"  href=\"");
            out.print(basePath4left );
            out.write("/product/");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${t.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("/listProductByCategory\"><i>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${t.name }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</i></a></u>,\r\n");
            out.write("\t\t\t\t\t");
            int evalDoAfterBody = _jspx_th_c_005fforEach_005f5.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_c_005fforEach_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return;
        }
      } catch (Throwable _jspx_exception) {
        while (_jspx_push_body_count_c_005fforEach_005f5[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_c_005fforEach_005f5.doCatch(_jspx_exception);
      } finally {
        _jspx_th_c_005fforEach_005f5.doFinally();
        _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f5);
      }
      out.write("\r\n");
      out.write("\t\t\t\t\tKingston and Sandisk  Memory Card and so on.\r\n");
      out.write("\t\t\t\t  </p>\r\n");
      out.write("\t\t\t\t  <p>\r\n");
      out.write("\t\t\t\t  \tIt is due to the best service that our Team and Products are recognized by our customers from all over the world, we have and still will spare no efforts to supply the best service for more and more customers. In order to give you a good shopping experience, we have designed the very simple website. We hope you can easily buy the cards you need. We are very confident of our cards. If you have any problems and questions when shopping, please don’t hesitate to let us know. We will give you a quickly and satisfied answer as soon as possible. \r\n");
      out.write("\t\t\t\t  </p>\r\n");
      out.write("\r\n");
      out.write("\t\t          <h5>Promise</h5>\r\n");
      out.write("\t\t          <p>Take it easy to register here, we take serious of the information of our customers, only the specified staffs are allowed to get access to.</p>\r\n");
      out.write("\t\t\t\t  <p>We only sell Genuine R4i / R4 Cards and other Flash Cards, best in quality and service.</p>\r\n");
      out.write("\t\t\t\t  <p>All Flash Cards are free shipping to worldwide, what’s more, we will supply a tracking number for your package, and of course, it is free, Nevertheless, the price is competitive.</p>\r\n");
      out.write("\t\t\t\t  <p>All Products we sell are 100% tested and Firmware updated before shipping, shipped out within 24 hours (except the holidays).</p>\r\n");
      out.write("\t\t\t\t  <p>If the package not received over 35 days after payment, please contact us, you can get the full refund in 2 workdays or re-shipping the order within 24 hours.</p>\r\n");
      out.write("\t\t          \r\n");
      out.write("\t\t          <h5>Payment</h5>\r\n");
      out.write("\t\t          <p>We can accept Paypal and any other paying methods. the tracking number will upload to Paypal, please to check it for your order.</p>\r\n");
      out.write("\t\t\t\t \r\n");
      out.write("\t\t\t\t  <h5>Have a good shopping</h5>\r\n");
      out.write("\t\t          <p>Enjoying your shopping here and have a good day！</p>\r\n");
      out.write("\t\t          <p>Kind regards</p>\r\n");
      out.write("\t\t          <p>Jack Williams, the N3DS-Card CEO</p>\r\n");
      out.write("\t\t\t     \r\n");
      out.write("    \t\t</div><!--/span8-->\r\n");
      out.write("    \t\t\r\n");
      out.write("\r\n");
      out.write("      </div><!-- row-fluid -->\r\n");
      out.write("\r\n");
      out.write("    </div><!-- container-fluid -->\r\n");
      out.write(" \r\n");
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
    // /WEB-INF/view/product/about.jsp(17,3) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty msg }", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("    \t<script type=\"text/javascript\"> \r\n");
        out.write("    \talert('");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${msg }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("');\r\n");
        out.write("    \t</script>\r\n");
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

  private boolean _jspx_meth_c_005fset_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f1);
    // /WEB-INF/view/product/left.jsp(49,10) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setVar("hotProducts_index");
    // /WEB-INF/view/product/left.jsp(49,10) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setValue(new org.apache.jasper.el.JspValueExpression("/WEB-INF/view/product/left.jsp(49,10) '1'",_el_expressionfactory.createValueExpression("1",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
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
    // /WEB-INF/view/product/left.jsp(67,12) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f1.setVar("hotProducts_index");
    // /WEB-INF/view/product/left.jsp(67,12) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f1.setValue(new org.apache.jasper.el.JspValueExpression("/WEB-INF/view/product/left.jsp(67,12) '${hotProducts_index+1 }'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${hotProducts_index+1 }",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
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
    // /WEB-INF/view/product/left.jsp(83,10) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f2.setVar("newProducts_index");
    // /WEB-INF/view/product/left.jsp(83,10) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f2.setValue(new org.apache.jasper.el.JspValueExpression("/WEB-INF/view/product/left.jsp(83,10) '1'",_el_expressionfactory.createValueExpression("1",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
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
    // /WEB-INF/view/product/left.jsp(101,12) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f3.setVar("newProducts_index");
    // /WEB-INF/view/product/left.jsp(101,12) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f3.setValue(new org.apache.jasper.el.JspValueExpression("/WEB-INF/view/product/left.jsp(101,12) '${newProducts_index+1 }'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${newProducts_index+1 }",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
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
    // /WEB-INF/view/product/left.jsp(117,10) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f4.setVar("discountProducts_index");
    // /WEB-INF/view/product/left.jsp(117,10) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f4.setValue(new org.apache.jasper.el.JspValueExpression("/WEB-INF/view/product/left.jsp(117,10) '1'",_el_expressionfactory.createValueExpression("1",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
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
    // /WEB-INF/view/product/left.jsp(135,12) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f5.setVar("discountProducts_index");
    // /WEB-INF/view/product/left.jsp(135,12) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f5.setValue(new org.apache.jasper.el.JspValueExpression("/WEB-INF/view/product/left.jsp(135,12) '${discountProducts_index+1 }'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${discountProducts_index+1 }",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    int _jspx_eval_c_005fset_005f5 = _jspx_th_c_005fset_005f5.doStartTag();
    if (_jspx_th_c_005fset_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f5);
    return false;
  }
}