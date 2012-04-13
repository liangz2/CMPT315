<%-- 
    Document   : header
    Created on : Mar 20, 2012, 10:42:33 AM
    Author     : Zhengyi
--%>

<%@page import="business.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    User user = (User) request.getSession().getAttribute("user");
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
                    <a href="logout">[Logout]</a>
                    <a href="password_update.jsp">[Update Password]</a>
                    <a href="info_updat.jsp">[Update Personal Info]</a>
                </c:otherwise>
            </c:choose>
        </div>
        <ul id="nav">
            <li><a href="activeProjects" id="homeButton">H</a></li>
            <li><a href="#" id="projectButton">P</a></li>
            <li><a href="#" id="requestButton">R</a></li>
            <li><a href="#" id="uploadButton">U</a></li>
        </ul>