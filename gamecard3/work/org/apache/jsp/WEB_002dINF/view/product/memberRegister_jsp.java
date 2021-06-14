package org.apache.jsp.WEB_002dINF.view.product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class memberRegister_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("  <body >\n");
      out.write("  <div class=\"container\">\n");
      out.write("  \n");
      out.write("  \t<form id=\"memberRegisterForm\" action=\"memberRegister\" method=\"post\"> \n");
      out.write("\t<div class=\"panel panel-warning\">\n");
      out.write("\t\t<div class=\"panel-heading\">\n");
      out.write("\t\t\ttype your base info\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"panel-body\">\n");
      out.write("\t\t\t <div class=\"row form-group\">\n");
      out.write("\t\t              <span class=\"control-label col-lg-2 col-lg-offset-1\">My e-mail<font color=\"red\">&nbsp;*</font>:</span>\n");
      out.write("\t\t              <div class=\"col-lg-3\">\n");
      out.write("\t\t                 <input class=\"form-control input-sm\" type=\"text\" maxlength=\"50\" id=\"email\"  name=\"email\">\n");
      out.write("\t\t              </div>\n");
      out.write("\t\t              <span id=\"email-help\" class=\"col-lg-5\"></span>\n");
      out.write("\t\t     </div>\n");
      out.write("\t\t     <div class=\"row form-group\">\n");
      out.write("\t\t              <span class=\"control-label col-lg-2 col-lg-offset-1\">Your password<font color=\"red\">&nbsp;*</font>:</span>\n");
      out.write("\t\t              <div class=\"col-lg-3\">\n");
      out.write("\t\t                 <input class=\"form-control input-sm\" id=\"password\"  name=\"password\" type=\"password\" maxlength=\"50\">\n");
      out.write("\t\t              </div>\n");
      out.write("\t\t              <span id=\"password-help\" class=\"col-lg-5\"></span>\n");
      out.write("\t\t     </div>\n");
      out.write("\t\t     <div class=\"row form-group\">\n");
      out.write("\t\t              <span class=\"control-label col-lg-2 col-lg-offset-1\">Type it again<font color=\"red\">&nbsp;*</font>:</span>\n");
      out.write("\t\t              <div class=\"col-lg-3\">\n");
      out.write("\t\t                 <input class=\"form-control input-sm\" id=\"repassword\" type=\"password\" maxlength=\"50\">\n");
      out.write("\t\t              </div>\n");
      out.write("\t\t              <span id=\"repassword-help\" class=\"col-lg-5\"></span>\n");
      out.write("\t\t     </div>\n");
      out.write("\t\t     <div class=\"row form-group\">\n");
      out.write("\t\t              <span class=\"control-label col-lg-2 col-lg-offset-1\">First name<font color=\"red\">&nbsp;*</font>:</span>\n");
      out.write("\t\t              <div class=\"col-lg-3\">\n");
      out.write("\t\t                 <input class=\"form-control input-sm\" type=\"text\" maxlength=\"50\" id=\"firstName\" name=\"firstName\">\n");
      out.write("\t\t              </div>\n");
      out.write("\t\t              <span id=\"firstName-help\" class=\"col-lg-5\"></span>\n");
      out.write("\t\t     </div>\n");
      out.write("\t\t     <div class=\"row form-group\">\n");
      out.write("\t\t              <span class=\"control-label col-lg-2 col-lg-offset-1\">Last name<font color=\"red\">&nbsp;*</font>:</span>\n");
      out.write("\t\t              <div class=\"col-lg-3\">\n");
      out.write("\t\t                 <input class=\"form-control input-sm\" type=\"text\" maxlength=\"50\" id=\"lastName\" name=\"lastName\">\n");
      out.write("\t\t              </div>\n");
      out.write("\t\t              <span id=\"lastName-help\" class=\"col-lg-5\"></span>\n");
      out.write("\t\t     </div>\n");
      out.write("\t\t     <div class=\"row form-group\">\n");
      out.write("\t\t              <span class=\"control-label col-lg-2 col-lg-offset-1\">Mobile phone (Optional):</span>\n");
      out.write("\t\t              <div class=\"col-lg-3\">\n");
      out.write("\t\t                 <input class=\"form-control input-sm\" type=\"text\" maxlength=\"30\" id=\"phoneNumber\" name=\"phoneNumber\">\n");
      out.write("\t\t              </div>\n");
      out.write("\t\t              <span id=\"phoneNumber-help\" class=\"col-lg-5\"></span>\n");
      out.write("\t\t     </div>\n");
      out.write("\t\t     <div class=\"row form-group\">\n");
      out.write("\t\t              <div class=\"col-lg-3 col-lg-offset-3\">\n");
      out.write("\t\t                  <button class=\"btn btn-lg btn-primary btn-block\" type=\"button\" onclick=\"verifySubmit()\">Register</button>\n");
      out.write("\t\t              </div>\n");
      out.write("\t\t     </div>\n");
      out.write("\t\t </div><!-- end panel-body -->\n");
      out.write("\t</div>\n");
      out.write("\t</form>\n");
      out.write("\n");
      out.write("</div>\n");
      out.write(" \n");
      out.write("  \n");
      out.write("   <script type=\"text/javascript\">   \n");
      out.write("   function refreshCode() {\n");
      out.write("\t   $('#checkcodeImg').attr(\"src\",\"user!checkCode.action?\"+Math.random()+new Date());        \n");
      out.write("   }\n");
      out.write("\n");
      out.write("   $(document).ready(function(){ \n");
      out.write("\t   \n");
      out.write("\t  // $(\"#checkcodeImg\").attr(\"src\",\"user!checkCode.action?\"+Math.random()+new Date());\n");
      out.write("\t   \n");
      out.write("\t   $(\"#email\").blur(function(){\tverfiyEmail();    });  \n");
      out.write("\t   \n");
      out.write("\t   $(\"#password\").blur(function (){  verfiyPass();  });\n");
      out.write("\t   \n");
      out.write("\t   $(\"#repassword\").blur(function (){  verfiyRePass();    });\n");
      out.write("\t   \n");
      out.write("\t   $(\"#firstName\").blur(function (){  verfiyFirstName();  });\n");
      out.write("\t   \n");
      out.write("\t   $(\"#lastName\").blur(function (){   verfiyLastName();  });\n");
      out.write("\t   \n");
      out.write("\t   });   \n");
      out.write("   \n");
      out.write("function verfiyEmail(){\n");
      out.write("\t \n");
      out.write("\tvar email = trim($(\"#email\").val());\t\t         \n");
      out.write("    if(email == \"\"){\n");
      out.write("\t   \t $(\"#email-help\").html(\"<i class='icon-remove'></i><font color='red'>Please input your e-mail</font>\");\n");
      out.write("\t   \treturn false;\n");
      out.write("    }\n");
      out.write("    var rule=/^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$/i;\n");
      out.write("\t   if (!rule.test(email))  {\n");
      out.write("\t     $(\"#email-help\").html(\"<i class='icon-remove'></i><font color='red'>email formate error</font>\");  \t\t  \t        \n");
      out.write("\t        return false;  \n");
      out.write("\t    }       \n");
      out.write("\t   \n");
      out.write("   \t$.ajax({\n");
      out.write("\t\t  type:\"post\",\n");
      out.write("\t\t  cache:false,    \n");
      out.write("\t\t  dataType : \"json\",\n");
      out.write("\t\t  url:\"checkEmail\",\n");
      out.write("\t\t  data: {\"email\":email},\n");
      out.write("\t\t  success:function(data){\n");
      out.write("\t\t\t  if(data.success == \"ok\"){\t    \t\t\n");
      out.write("\t    \t\t   $(\"#email-help\").html(\"<i class='icon-remove'></i><font color='red'>email has been registered</font>\");\t    \t\t    \n");
      out.write("\t    \t\t   return false;\n");
      out.write("\t    \t   }else{\t    \t\t  \t    \t\t \n");
      out.write("\t    \t\t   var html = \"<i class='icon-ok icon-green'></i>\";\n");
      out.write("\t    \t\t   $(\"#email-help\").html(\"\");\n");
      out.write("\t    \t\t   return true; \n");
      out.write("\t    \t   }\n");
      out.write("\t\t  }\n");
      out.write("\t  });\n");
      out.write("}\n");
      out.write("\n");
      out.write("function verfiyPass(){\t\n");
      out.write("\t   var pwd = trim($(\"#password\").val());\t\t  \n");
      out.write("\t   if(pwd==\"\" ){\n");
      out.write("\t\t   var html = \"<i class='icon-remove'></i><font color='red'>Please input your new password</font>\";\n");
      out.write("\t\t   $(\"#password-help\").html(html);\n");
      out.write("\t\t   return false;\n");
      out.write("\t   }else{\n");
      out.write("\t\t   var html = \"<i class='icon-ok'></i>\";\n");
      out.write("\t\t   $(\"#password-help\").html(\"\");\n");
      out.write("\t\t   return true;\n");
      out.write("\t   }\n");
      out.write("}\n");
      out.write("\n");
      out.write("function verfiyRePass(){\n");
      out.write("\t   var pwd = trim($(\"#password\").val());\n");
      out.write("\t   var repwd = trim($(\"#repassword\").val());\n");
      out.write("\t   if(repwd== \"\"){\n");
      out.write("\t\t   var html = \"<i class='icon-remove'></i><font color='red'>Please input your confirm password</font>\";\n");
      out.write("\t\t   $(\"#repassword-help\").html(html);\n");
      out.write("\t\t   return false;\n");
      out.write("\t   }\n");
      out.write("\t   if(pwd!=repwd){\n");
      out.write("\t\t   var html = \"<i class='icon-remove'></i><font color='red'>Please Check that your passwords match and try again</font>\";\n");
      out.write("\t\t   $(\"#repassword-help\").html(html);\n");
      out.write("\t\t   return false;\n");
      out.write("\t   }else{\n");
      out.write("\t\t   var html = \"<i class='icon-ok'></i>\";\n");
      out.write("\t\t   $(\"#repassword-help\").html(\"\");\n");
      out.write("\t\t   return true;\n");
      out.write("\t   }\n");
      out.write("}\n");
      out.write("//校验姓 \n");
      out.write("function verfiyFirstName(){\t\n");
      out.write("\t var firstName = trim($(\"#firstName\").val());\t\t  \n");
      out.write("\t   if(firstName==\"\" ){\n");
      out.write("\t\t   var html = \"<i class='icon-remove'></i><font color='red'>Please input your first name</font>\";\n");
      out.write("\t\t   $(\"#firstName-help\").html(html);\n");
      out.write("\t\t   return false;\n");
      out.write("\t   }else{\n");
      out.write("\t\t   var html = \"<i class='icon-ok'></i>\";\n");
      out.write("\t\t   $(\"#firstName-help\").html(\"\");\n");
      out.write("\t\t   return true;\n");
      out.write("\t   }\n");
      out.write("}\n");
      out.write("\n");
      out.write("//校验名字 \n");
      out.write("function verfiyLastName(){\t\n");
      out.write("\t var lastName = trim($(\"#lastName\").val());\t\t  \n");
      out.write("\t   if(lastName==\"\" ){\n");
      out.write("\t\t   var html = \"<i class='icon-remove'></i><font color='red'>Please input your last name</font>\";\n");
      out.write("\t\t   $(\"#lastName-help\").html(html);\n");
      out.write("\t\t   return false;\n");
      out.write("\t   }else{\n");
      out.write("\t\t   var html = \"<i class='icon-ok'></i>\";\n");
      out.write("\t\t   $(\"#lastName-help\").html(\"\");\n");
      out.write("\t\t   return true;\n");
      out.write("\t   }\n");
      out.write("}\n");
      out.write("\n");
      out.write("//提交表单 \n");
      out.write("function verifySubmit(){\n");
      out.write("\tvar email = trim($(\"#email\").val());\t\t         \n");
      out.write("    if(email == \"\"){\n");
      out.write("\t   \t $(\"#email-help\").html(\"<i class='icon-remove'></i><font color='red'>Please input your e-mail</font>\");\n");
      out.write("\t   \treturn false;\n");
      out.write("    }\n");
      out.write("\t$.ajax({\n");
      out.write("\t\t  type:\"post\",\n");
      out.write("\t\t  cache:false,    \n");
      out.write("\t\t  dataType : \"json\",\n");
      out.write("\t\t  url:\"checkEmail\",\n");
      out.write("\t\t  data: {\"email\":email},\n");
      out.write("\t\t  success:function(data){\n");
      out.write("\t\t\t  if(data.success == \"ok\"){\t    \t\t\n");
      out.write("\t    \t\t   $(\"#email-help\").html(\"<i class='icon-remove'></i><font color='red'>email has been registered</font>\");\t    \t\t    \n");
      out.write("\t    \t\t   return false;\n");
      out.write("\t    \t   }else{\t    \t\t  \t    \t\t \n");
      out.write("\t    \t\t   var html = \"<i class='icon-ok icon-green'></i>\";\n");
      out.write("\t    \t\t   $(\"#email-help\").html(\"\");\n");
      out.write("\t    \t\t   \n");
      out.write("\t    \t\t   if(verfiyPass()&&verfiyRePass()&&verfiyFirstName()&&verfiyLastName()){ \n");
      out.write("\t    \t\t\t \t$(\"#memberRegisterForm\").submit();\n");
      out.write("\t    \t\t\t }\t\n");
      out.write("\t    \t\t   return true; \n");
      out.write("\t    \t   }\n");
      out.write("\t\t  }\n");
      out.write("\t  });\n");
      out.write("\t\n");
      out.write("}\n");
      out.write("   </script>     \n");
      out.write("   </body>\n");
      out.write("   \n");
      out.write("</html>\n");
      out.write("\n");
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
}
