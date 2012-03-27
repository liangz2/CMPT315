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
        <style type="text/css">
            h1 {vertical-align:central; height:100px; background-color:silver; color:azure}
        </style>    
    </head>

    <body bgcolor="#CCCCCC">
        <center>
            <font face="Bookman Old Style, Book Antiqua, Garamond">
