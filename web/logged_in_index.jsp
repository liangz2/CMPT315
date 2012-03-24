<%-- 
    Document   : project_selection
    Created on : Mar 12, 2012, 12:24:43 PM
    Author     : Zhengyi
--%>
<%@page import="business.Project"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/includes/header.jsp"/>
<jsp:useBean id="user" scope="session" class="business.User"/>

<h1>Welcome ${user.firstName}</h1>
<font size="2"><a href="password_update.jsp">[Update Password]</a>
<a href="info_updat.jsp">[Update Personal Info]</a></font>
<table cellspacing="5" border="0">
    <tr align="center">
        <td><h4>Currently active projects</h4></td>
        <td></td>
        <td><h4>You are in</h4></td>
    </tr>
    <tr align="center">
        <td>
            <select style="width:200px; height:100px" name="activeProject" size="${activeProjects.size()}" onchange="showDetail(this)">
                <c:forEach var="project" items="${activeProjects}">
                    <option value="${project.description}">${project.name}</option>
                </c:forEach>
            </select>
        </td>
        <td></td>
        <td>
            <select style="width:200px; height:100px" name="inProject" size="${user.projects.size()}" onchange="showDetail(this)">
                <c:forEach var="project" items="${user.projects}">
                    <option value="${project.description}">${project}</option>
                </c:forEach>
            </select>
        </td>
</table>
                <p>Project Description </p>
                <p id="description"></p>
                <script type="text/javascript">
                    function showDetail (object) {
                        document.getElementById("description").innerHTML = 
                            object.options[object.selectedIndex].value;
                    }
                </script>
            
<%--
<c:if test="${user.role == 'None'}">
<a href="requestAccess"><input type="submit" value="Request Access"></a>
</c:if>
<c:if test="${user.role != 'None'}">
<a href="editProject"><input type="submit" value="Edit Project"></a>
<a href="list"><input type="submit" value="Pending List"</a>
</c:if>
--%>

<jsp:include page="includes/footer.jsp"/>