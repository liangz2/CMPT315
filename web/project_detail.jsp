<%-- 
    Document   : project_detail
    Created on : Mar 26, 2012, 1:04:45 PM
    Author     : Zhengyi
--%>

<%@page import="business.Project"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/includes/header.jsp"/>

<h1><%= project.getName() %></h1>

<jsp:include page="includes/footer.jsp"/>