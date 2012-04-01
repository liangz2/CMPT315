/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.User;
import database.ConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Zhengyi
 */
@WebServlet(name = "PasswordServlet", urlPatterns = {"/password"})
public class PasswordServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
            /*
             * TODO output your page here. You may use following sample code.
             */
        String url = "";
        String sqlStatement = "";
        ServletContext sc = getServletContext();
        HttpSession session = request.getSession();
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;
        try {
            /*
             * TODO output your page here. You may use following sample code 
             */
            ConnectionPool pool = ConnectionPool.getInstance();
            connection = pool.getConnection();
            
            // create the statement object
            statement = connection.createStatement ();
            
            // obtain login info
            User user = (User) session.getAttribute("user");
            String emailAddress = user.getEmail();
            // get the passwords entered by user
            String newPassword = (String) request.getParameter ("newPassword");
            String oldPassword = (String) request.getParameter("oldPassword");
            // assemble query statement
            sqlStatement = "select password from user where emailAddress='"
                    + emailAddress + "'";
            // get the result set from query
            resultSet = statement.executeQuery(sqlStatement);
            
            if (resultSet.next()) {
                if (resultSet.getString(1).equals(oldPassword)) {
                    sqlStatement = "update user set password='" + newPassword 
                            + "' where emailAddress='" + emailAddress + "'";

                    statement.executeUpdate(sqlStatement);
                    url = "/password_confirm.jsp";
                }
                else {
                    url = "/password_denied.jsp";
                    request.setAttribute("error", "The current password you entered does not match our record");
                }
            }
            else {
                url = "/password_denied.jsp";
                request.setAttribute("error", "ERROR! You are not logged in, <a href='login.jsp'>click here to login</a>");
            }
            statement.close();            
            connection.close();
            resultSet.close();
            
            RequestDispatcher dispatcher = 
                    sc.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (SQLException ex) {            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>SQLException Cought!</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1><font color='red'>" + ex.getLocalizedMessage() + "</font></h1>");
            out.println("</body>");
            out.println("</html>");
        } catch (NullPointerException ex) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>IOException Cought!</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1><font color='red'>" + ex.getMessage() + "</font></h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
