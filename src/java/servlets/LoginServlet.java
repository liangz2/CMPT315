/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.User;
import database.ConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
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
public class LoginServlet extends HttpServlet {

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
        String url = "";
        ServletContext sc = getServletContext();
        HttpSession session = request.getSession();
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;
        Connection pConnection = null;
        
        try {
            /*
             * TODO output your page here. You may use following sample code 
             */
            ConnectionPool pool = ConnectionPool.getInstance();
            pConnection = pool.getConnection();
            /*
            connection = 
                    DriverManager.getConnection (sc.getInitParameter ("dbURL"),
                    sc.getInitParameter ("dbUserName"), 
                    sc.getInitParameter ("dbPassword"));
            
            * 
            */
            // create the statement object
            statement = pConnection.createStatement ();
            
            // obtain login info
            String emailAddress = (String) request.getParameter ("emailAddress");
            String password = (String) request.getParameter ("password");
            
            resultSet = statement.executeQuery ("select * from user where "
                    + "emailAddress='" + emailAddress + "' and password='"
                    + password + "'");
            
            // if login successful
            if (resultSet.next ()) {
                // obtain user data
                User user = new User();
                url = "/displayProjects";
                user.setFirstName (resultSet.getString (1));
                user.setLastName (resultSet.getString (2));
                user.setEmail (resultSet.getString (3));
                user.setPassword (resultSet.getString (4));
                
                request.setAttribute ("user", user);
            }
            else {
                url = "/login.jsp";
                request.setAttribute ("error", "User name or password incorrect, please try again");
            }
            
            statement.close ();            
            pool.freeConnection(pConnection);
            resultSet.close ();
            RequestDispatcher dispatcher = sc.getRequestDispatcher (url);
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
