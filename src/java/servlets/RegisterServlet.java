/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.User;
import database.DBUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Zhengyi
 */
public class RegisterServlet extends HttpServlet {

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

        // obtain user info
        String firstName = (String) request.getParameter ("firstName");
        String lastName = (String) request.getParameter ("lastName");
        String emailAddress = (String) request.getParameter ("emailAddress");
        String password = (String) request.getParameter ("p1");
        // check if user exists
        User user = DBUtil.getUser(emailAddress);
        if (user == null) {
            user = new User(firstName, lastName, emailAddress, password);
            DBUtil.createUser(user);
            url = "/login.jsp";
            request.setAttribute("registered", "Thank you for joining us, please login now");
        } else {
            url = "/register.jsp";
            request.removeAttribute("password");
            request.setAttribute("error", "This email is already registered");
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("emailAddress", emailAddress);
        }

        RequestDispatcher dispatcher = sc.getRequestDispatcher(url);
                dispatcher.forward(request, response);
        out.close();       
    }
    
    private Timestamp getTime () {
        Calendar calendar = Calendar.getInstance();
        // get the date
        Date time = calendar.getTime();
        // return the timesamp with nano second set to be 0
        return new Timestamp (time.getTime());
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
