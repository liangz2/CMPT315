<%-- 
    Document   : projects
    Created on : Apr 14, 2012, 1:05:22 PM
    Author     : Icewill
--%>

<%@page import="business.Project"%>
<%@page import="java.util.HashMap"%>
<%
    HashMap<Integer, Project> activeProjects = 
            (HashMap<Integer, Project>)request.getSession().getAttribute("activeProjects");
    Integer[] ids = activeProjects.keySet().toArray(new Integer[0]);
%>
<%--
<ul id="nav">
    <li><a href="<%= response.encodeURL("projects") %>" id="homeButton"
                class="selected">H</a></li>
    <li><a href="<%= response.encodeURL("main.jsp?requestedPage=create_project.jsp") %>"
            id="projectButton"
                class="selected">P</a></li>
    <li><a href="#" id="requestButton">R</a></li>
    <li><a href="#" id="uploadButton">U</a></li>
</ul>--%>
<div id="pageTitle">
    <h2>Currently Active Projects</h2>
</div>
<div id="projectList">
    <%for (int i = 0; i < ids.length; i++) {%>
    <div id="projectDetail">
        <div class="title">
            <a href="<%= response.encodeURL("projectDetail?projectId=" + ids[i]) %>">
                <%= activeProjects.get(ids[i]).getName() %>
            </a>
            <div class="date">
                <%= activeProjects.get(ids[i]).getCreationTime() %>
            </div>
        </div>
        <div class="description">
            <p><%= activeProjects.get(ids[i]).getDescription() %>
            </p><hr>
        </div>
        <div class="listFooter">
            Created By: 
            <i><%= activeProjects.get(ids[i]).getCreator() %></i>
        </div>
    </div>        
    <%}%>
</div>

