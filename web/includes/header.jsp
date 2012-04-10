<%-- 
    Document   : header
    Created on : Mar 20, 2012, 10:42:33 AM
    Author     : Zhengyi
--%>

<%@page import="business.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User user = (User) request.getSession().getAttribute("user");
    if (user == null) { 
        request.setAttribute("error", "You are not logged in");
        %>
    <jsp:forward page="/login.jsp" />
<%    }%>

<!DOCTYPE html>
<html>
    <head>
        <title>WIKI Project Management</title>
    </head>
    <body>