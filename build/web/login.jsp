<%-- 
    Document   : index
    Created on : 12-Mar-2012, 10:23:43 AM
    Author     : Zhengyi
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="business.Project"%>
<%@page import="java.util.HashMap"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/includes/header.jsp"/>
<jsp:useBean id="user" scope="session" class="business.User"/>
<%
    HashMap<Integer, Project> activeProjects = 
            (HashMap<Integer, Project>)request.getSession().getAttribute("activeProjects");
    Integer[] ids = activeProjects.keySet().toArray(new Integer[0]);
%>
<ul id="projectList">
    <%for (int i = 0; i < ids.length; i++) {%>
    <li><a href="projectDetail?projectId=<%= ids[i] %>"><%=activeProjects.get(ids[i]).getName()%></a></li>
    <%}%>
</ul>
    
    <jsp:include page="/includes/footer.jsp"/>
</div>
