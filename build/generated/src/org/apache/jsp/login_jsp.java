package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.Vector _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
    return _jspx_dependants;
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/includes/header.html", out, false);
      out.write("\n");
      out.write("    <h1>Welcome, Please login</h1><br>\n");
      out.write("    <form action=\"loginCheck\" method=\"post\">\n");
      out.write("        <table cellspacing=\"5\" border=\"0\">\n");
      out.write("            <tr>\n");
      out.write("                <td align=\"right\">Email address:</td>\n");
      out.write("                <td><input type=\"text\" name=\"emailAddress\"></td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td align=\"right\">Password:</td>\n");
      out.write("                <td><input type=\"password\" name=\"password\"></td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td></td>\n");
      out.write("                <td>\n");
      out.write("                    <input style=\"width:77px\" type=\"submit\" value=\"Login\">\n");
      out.write("                    <a href=\"register.jsp\"><input style=\"width:77px\" type=\"button\" value=\"Register\"></a>\n");
      out.write("                </td>\n");
      out.write("            </tr> \n");
      out.write("        </table>\n");
      out.write("    </form>\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/includes/footer.jsp", out, false);
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
