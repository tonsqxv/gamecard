package org.apache.jsp.WEB_002dINF.view.businessdata;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class taskEmail_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
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

      out.write('\r');
      out.write('\n');

String path = request.getContextPath();    
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path; 


      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title></title>\r\n");
      out.write("\r\n");
      out.write("\t<!-- Ext -->\r\n");
      out.write(" \t<link rel=\"stylesheet\" type=\"text/css\" \thref=\"");
      out.print(basePath );
      out.write("/assets/ext-3.4.0/resources/css/ext-all.css\" />\r\n");
      out.write("\t<script type=\"text/javascript\" \tsrc=\"");
      out.print(basePath );
      out.write("/assets/ext-3.4.0/adapter/ext/ext-base.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.print(basePath );
      out.write("/assets/ext-3.4.0/ext-all.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.print(basePath );
      out.write("/assets/ext-3.4.0/src/locale/ext-lang-zh_CN.js\"></script>\r\n");
      out.write("\t\r\n");
      out.write("\t<!-- common -->\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" \thref=\"");
      out.print(basePath );
      out.write("/assets/css/common/ext-common.css\" />\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.print(basePath );
      out.write("/assets/js/common/ext-common.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.print(basePath );
      out.write("/assets/js/common/util.js\"></script>\r\n");
      out.write("\t\r\n");
      out.write("\t<!-- my js file -->\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.print(basePath );
      out.write("/assets/js/businessdata/taskEmail.js\"></script>\r\n");
      out.write("\t\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("</body>\r\n");
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
}