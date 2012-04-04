/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.Project;
import business.User;
import database.ConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Zhengyi
 */
public class ProjectDetailServlet extends HttpServlet {
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
        String url = "/project_detail.jsp";
        ServletContext sc = request.getServletContext();
        HttpSession session = request.getSession();
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        String query = "select firstname, lastname, emailaddress, role"
                    + " from wikirecord inner join user on user.emailaddress="
                    + "wikirecord.useremail where projectid=?";
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            ConnectionPool pool = ConnectionPool.getInstance();
            connection = pool.getConnection();
            // create the statement object
            statement = connection.prepareStatement(query);
            
            String pId = request.getParameter("projectId");
            if (pId == null)
                pId = (String) request.getAttribute("projectId");
            
            int projectId = Integer.parseInt(pId);
            User user = (User) session.getAttribute("user");
            
            statement.setString(1, pId);
            
            resultSet = statement.executeQuery();
            
            ArrayList<User> users = new ArrayList<User>();
            
            while (resultSet.next()) 
                users.add(new User(resultSet.getString(1), resultSet.getString(2),
                                resultSet.getString(3), resultSet.getString(4), ""));
            
            user.setSelectedProject(projectId);
            user.getSelectedProject().setUsers(users);
            
            statement.close();
            resultSet.close();
            pool.freeConnection(connection);
            RequestDispatcher dispatcher = sc.getRequestDispatcher (url);
            dispatcher.forward (request, response);
        } catch (SQLException ex) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>SQLException Caught</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProjectDetailServlet at " + ex.getLocalizedMessage() + "</h1>");
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
