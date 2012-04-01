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
public class ProjectServlet extends HttpServlet {

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
        ArrayList<Project> activeProjects = new ArrayList<Project>();
        Connection connection = null;
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            ServletContext sc = this.getServletContext();
            String sqlStatement = "select projectname, projectdescription "
                    + "from project where projectisactive=true";

            // connect to data base
            ConnectionPool pool = ConnectionPool.getInstance();
            connection = pool.getConnection();
            
            // obtain the user info to query for projects
            User user = (User) request.getAttribute("user");
            
            // get the statement from db connection and query for active projects
            Statement statement = connection.createStatement ();
            ResultSet resultSet = statement.executeQuery (sqlStatement);
            
            while (resultSet.next ()) {
                Project p = new Project ();
                p.setName (resultSet.getString(1));
                p.setDescription (resultSet.getString (2));
                activeProjects.add(p);
            }
            
            resultSet = statement.executeQuery("select project.projectid, "
                    + "projectname, projectdescription, role from project "
                    + "inner join wikirecord on project.projectid=wikirecord.projectid "
                    + "where useremail='" + user.getEmail() + "' "
                    + "and projectisactive=true");
            
            while (resultSet.next()) {
                Project p = new Project();
                p.setId(Integer.parseInt(resultSet.getString(1)));
                p.setName(resultSet.getString(2));
                p.setDescription(resultSet.getString(3));
                p.setMyRole(resultSet.getString(4));
                // add projects to the user for future usage
                user.addProject(p.getId(), p);
            }
            resultSet.close ();
            statement.close ();
            connection.close ();
                   
            HttpSession session = request.getSession ();
            session.setAttribute ("user", user);
            session.setAttribute ("activeProjects", activeProjects);

            RequestDispatcher dispatcher = 
                    sc.getRequestDispatcher ("/index.jsp");
            dispatcher.forward (request, response);
        } catch (SQLException ex) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>SQLException Cought!</title>");            
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
