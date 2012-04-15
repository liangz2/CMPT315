<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="business.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="business.Project"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    User user = (User) request.getSession().getAttribute("user");
    String requestedPage = (String) request.getAttribute("requestedPage");
    if (requestedPage == null)
        requestedPage = request.getParameter("requestedPage");
    String projects = "/projects.jsp";
    String createProject = "create_project.jsp";
    
    if (user == null && requestedPage.equals(createProject)) {
        requestedPage = projects;
        request.setAttribute("notLogin", "You must login to create your project, or "
                + "<a href='main.jsp?requestedPage=register.jsp'>register</a>");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <title>WIKI Project Management</title>
    </head>
    <body>
    <style type="text/css">
        <%@include file="/CSS/wiki.css" %>
    </style>
    <div id="wrapper">
        <div id="header"><div id="logo"></div>
            <c:choose>
                <c:when test="<%= (user == null || user.getEmail().isEmpty()) %>">
                <form action="login" method="post">
                    <div id="login">
                    <table cellspacing="5" border="0">
                        <tr>
                            <td align="right"><div id="emailAddr"></div></td>
                            <td><input style="width:180px" type="text" name="emailAddress"></td>
                        </tr>
                        <tr>
                            <td align="right"><div id="password"></div></td>
                            <td><input style="width:180px" type="password" name="password"></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td align="right">
                                <input id="loginButton" type="submit" value="Login">
                                <a href="main.jsp?requestedPage=register.jsp" id="registerButton"></a>
                            </td>
                        </tr> 
                    </table>
                    </div>
                </form>
                </c:when>
                <c:otherwise>
                    <ul id="options">
                        <li><a href="logout">Logout</a></li>
                        <li><a href="password_update.jsp">Update Password</a></li>
                        <li><a href="info_updat.jsp">Update Personal Info</a></li>
                    </ul>
                </c:otherwise>
            </c:choose>
        </div>
        
        <ul id="nav">
            <li><a href="<%= response.encodeURL("activeProjects") %>" id="homeButton"
                   <c:if test="<%= requestedPage.equals(projects) %>">
                       class="selected"
                   </c:if>>H</a></li>
            <li><a href="<%= response.encodeURL("main.jsp?requestedPage=create_project.jsp") %>"
                   id="projectButton"
                   <c:if test="<%= requestedPage.equals(createProject) %>">
                       class="selected"
                   </c:if>>P</a></li>
            <li><a href="#" id="requestButton">R</a></li>
            <li><a href="#" id="uploadButton">U</a></li>
        </ul>
        <div class="clear"></div>

        <!-- end of header part -->

        <!-- content part -->
        <div id="warning">
            <c:if test="${notLogin != null}">
                <p><font color="red">${notLogin}</font></p>
            </c:if>
        </div>
        <div id="leftContent">
            <jsp:include page="<%= requestedPage %>" />
            <div id="search">
            </div>
        </div>
        <div class="clear"></div>

        <div id="rightContent">
            
        </div>
        <div class="clear"></div>

        <!-- end of content part -->
        
        <!-- footer part -->
        <%
            // initialize the current year that's used in the copyright notice
            Date currentDate = new Date();
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            DateFormat shortDate = DateFormat.getDateInstance(DateFormat.LONG);
            String currentDateFormatted = shortDate.format(currentDate);
        %>

        <br><br><br>
        <div id="footer">
            <%= currentDateFormatted %> <br>
            Term Project for CMPT315 by Zhengyi Liang in <%= currentYear %> 
            All rights reserved
        </div>
        <div class="clear"></div>
    </div>
    </body>
</html>
