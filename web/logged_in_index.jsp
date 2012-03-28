<%-- 
    Document   : project_selection
    Created on : Mar 12, 2012, 12:24:43 PM
    Author     : Zhengyi
--%>
<%@page import="business.User"%>
<%@page import="business.Project"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/includes/header.jsp"/>
<%
    int id = 0;
%>
<h1>Welcome ${user.firstName}</h1>
<font size="2"><a href="password_update.jsp">[Update Password]</a>
<a href="info_updat.jsp">[Update Personal Info]</a></font>
<form action="projectDetail" method="post" onsubmit="return getIndex(this)">
    <table id="projects" cellspacing="5" border="0">
        <tr align="center">
            <td><h4>Currently active projects</h4></td>
            <td></td>
            <td><h4>Projects you are in</h4></td>
        </tr>
        <tr align="center">
            <td>
                <select style="width:250px; height:100px" name="activeProject" size="${activeProjects.size()}" onchange="showDetail(this)">
                    <c:forEach var="project" items="${activeProjects}">
                        <option value="${project.id}">${project.name}</option>
                    </c:forEach>
                </select>
            </td>
            <td></td>
            <td>
                <select name="projectID" style="width:250px; height:100px" name="inProject" size="${user.projects.size()}" onchange="showDetail(this)">
                    <c:forEach var="project" items="${user.projects}">
                        <option value="${project.id}">${project}</option>
                    </c:forEach>
                </select>
            </td>
    </table>
    <c:forEach var="project" items="${user.projects}">
        <input type="hidden" id="<%= id++ %>" value="${project.description}"/>
    </c:forEach>
    <p>Project Description </p>
    <p id="description"></p>
    <input type="submit" value="View Project"/>
</form>
                    
    <script type="text/javascript">
        function showDetail (object) {
            var index = object.selectedIndex;
            document.getElementById('description').innerHTML =
                document.getElementById(index).value;
        }
        function getIndex (object) {
            if (object.projectID.selectedIndex < 0) {
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

<jsp:include page="includes/footer.jsp"/>