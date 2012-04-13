<%-- 
    Document   : project_selection
    Created on : Mar 12, 2012, 12:24:43 PM
    Author     : Zhengyi
--%>
<%@page import="business.User"%>
<%@page import="business.Project"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
    <%@include file="/CSS/wiki.css" %>
</style>
<jsp:include page="/includes/header.jsp"/>
<%
    User user = (User) request.getSession().getAttribute("user");
    ArrayList<Project> currentProjects = new ArrayList(user.getProjects().values());
%>

    <form action="projectDetail" method="post" onsubmit="return getIndex(this)">
    <table id="activeProjects" cellspacing="2" border="0">
        <tr align="center">
            <td><h4>Currently Active Projects</h4></td>
            <td><h4>Description</h4>
        </tr>
        <tr align="center">
            <td>
                <select name="projectId" style="width:250px; height:121px" name="activeProject" size="${activeProjects.size()}" onchange="showDetail(this, 'apDescription')">
                    <c:forEach var="activeProject" items="${activeProjects}">
                        <option value="${activeProject.id}">${activeProject.name}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <textarea disabled="true" style="resize: none; background-color: white; color: black" 
                        wrap="true" id="apDescription" rows="7" cols="30"></textarea>
            </td>
        </tr>
    </table>
    <input type="submit" value="View Project"/>
    </form>
    <hr style="width:600px">
    <form action="projectDetail" method="post" onsubmit="return getIndex(this)">
    <table id="currentProjects" cellspacing="2" border="0">
        <tr align="center">
            <td><h4>Projects you are in</h4></td>
            <td><h4>Description</h4></td>
        </tr>
        <tr align="center">
            <td>
                <select name="projectId" style="width:250px; height:121px; " size="<%= currentProjects.size() %>" onchange="showDetail(this, 'cpDescription')">
                    <c:forEach var="currentProject" items="<%= currentProjects %>">
                        <option value="${currentProject.id}">${currentProject}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <textarea disabled="true" style="resize: none; background-color: white; color: black" 
                        wrap="true" id="cpDescription" rows="7" cols="30">

                </textarea>
            </td>
        </tr>
    </table>
    <c:forEach var="project" items="${activeProjects}">
        <input type="hidden" id="${project.id}" value="${project.description}"/>
    </c:forEach>
    <input type="submit" value="View Project"/>
    </form>
    <jsp:include page="includes/footer.jsp"/>
</div>
<script type="text/javascript">
    function showDetail (list, textarea) {
        var index = list.selectedIndex;
        document.getElementById(textarea).value =
            document.getElementById(list.options[index].value).value;
    }
    function getIndex (list) {
        if (list.projectId.selectedIndex < 0) {
            alert ('Please select a project that you are currently in');
            return false;
        }
        return true;
    }
</script>
<%--
<c:if test="${user.role == 'None'}">
<a href="requestAccess"><input type="submit" value="Request Acess"></a>
</c:if>
<c:if test="${user.role != 'None'}">
<a href="editProject"><input type="submit" value="Edit Project"></a>
<a href="list"><input type="submit" value="Pending List"</a>
</c:if>
--%>

