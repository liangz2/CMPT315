/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.ConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Icewill
 */
@WebServlet(name = "RemoveUserServlet", urlPatterns = {"/removeUser"})
public class RemoveUserServlet extends HttpServlet {

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
        Connection connection = null;
        String url = "/projectDetail";
        String query = "delete from wikirecord where useremail=? and projectid=?";
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            ConnectionPool pool = ConnectionPool.getInstance();
            connection = pool.getConnection();
            
            String projectID = (String) request.getAttribute("projectID");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RemoveUserServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RemoveUserServlet at " + projectID + "</h1>");
            out.println("</body>");
            out.println("</html>");
            String[] emails = request.getParameterValues("removeEmail");
            statement.setString(2, projectID);
            statement = connection.prepareStatement(query);

            for (int i = 0; i < emails.length; i++) {
                statement.setString(1, emails[i]);
                statement.executeUpdate();
            }
            
            statement.close();
            pool.freeConnection(connection);
            
            request.setAttribute("projectID", projectID);
            RequestDispatcher dispatcher = 
                    request.getServletContext().getRequestDispatcher (url);
            //dispatcher.forward (request, response);
        } catch (SQLException ex) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RemoveUserServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RemoveUserServlet at " + ex.getLocalizedMessage() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
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
