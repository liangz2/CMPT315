/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.Project;
import business.Role;
import business.User;
import database.DBUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Zhengyi
 */
public class ProjectServelt extends HttpServlet {

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
        String queryType = request.getParameter("queryType");
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;
        HashMap<Integer, Project> projects = null;
        String url = "/main.jsp";
        String leftContent = "";
        String rightContent = "";
        String requestedPage = "";
        if (queryType == null) 
            queryType = "activeProjects";
        
        switch (queryType) {
            case "activeProjects":
                leftContent = "projects.jsp";
                rightContent = "project_detail.jsp";
                // obtain currently active projects
                projects = DBUtil.getActiveProjects();
                break;
            case "myProjects":
                leftContent = "my_projects.jsp";
                rightContent = "project_detail.jsp";
                String email = ((User) session.getAttribute("user")).getEmail();
                projects = DBUtil.getUserProjects(email);
                break;
            case "projectDetail":
                leftContent = "/project_detail.jsp";
                int pId = (int) (request.getParameter("pId").charAt(0) & 0x0f);
                break;
            case "joinProject":
                leftContent = "project_detail.jsp";
                rightContent = "join_options.jsp";
                ArrayList<Role> roles = DBUtil.getRoles();
                request.setAttribute("roles", roles);
                break;
        }
        session.setAttribute("leftContent", leftContent);
        request.setAttribute("rightContent", rightContent);
        session.setAttribute(queryType, projects);
        // forward to index page
        dispatcher = getServletContext().getRequestDispatcher(url);
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
