/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.Project;
import business.User;
import database.DBUtil;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "CreateProjectServlet", urlPatterns = {"/createProject"})
public class CreateProjectServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        String url = "/main.jsp?requestedPage=create_project.jsp";
        String projectName = request.getParameter("projectName");
        String projectDescription = request.getParameter("projectDescription");
        User user = (User) session.getAttribute("user");
        // create the project
        Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setDescription(projectDescription);
        // add to the DB and check for error
        if (DBUtil.createProject(newProject, user.getEmail()))
            request.setAttribute("success", true);
        else
            request.setAttribute("success", false);
        
        RequestDispatcher dispatcher = 
                request.getRequestDispatcher(url);
        // forward to the main page
        dispatcher.forward(request, response);
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
