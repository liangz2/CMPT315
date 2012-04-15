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
            <div class="description">
                <p><%= activeProjects.get(ids[i]).getDescription() %>
                </p>
            </div>
        </div>
    </div>
    <%}%>
</div>

