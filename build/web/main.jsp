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
    HashMap<Integer, Project> activeProjects = 
            (HashMap<Integer, Project>)request.getSession().getAttribute("activeProjects");
    Integer[] ids = activeProjects.keySet().toArray(new Integer[0]);
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
                                <a href="register.jsp" id="registerButton"></a>
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
        <!-- end of header part -->
        
        <!-- content part -->
        <ul id="nav">
            <li><a href="activeProjects" id="homeButton">H</a></li>
            <li><a href="#" id="projectButton">P</a></li>
            <li><a href="#" id="requestButton">R</a></li>
            <li><a href="#" id="uploadButton">U</a></li>
        </ul>
        <ul id="projectList">
            <%for (int i = 0; i < ids.length; i++) {%>
            <li>
                <a href="<%=response.encodeURL("projectDetail?projectId=" + ids[i]) %>">
                    <%=activeProjects.get(ids[i]).getName()%>
                </a>
            </li>
            <%}%>
        </ul>
        
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
    </div>
    </body>
</html>
