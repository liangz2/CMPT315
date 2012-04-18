<%-- 
    Document   : create_project
    Created on : Dec 31, 2009, 11:21:40 PM
    Author     : Icewill
--%>
<%@page import="business.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    User user = (User) request.getSession().getAttribute("user");
    boolean loggedIn = false;
    if (user != null && !user.getEmail().isEmpty())
        loggedIn = true;
%>
<script type="text/javascript">
    function checkForm (form) {
        if (form.projectName.value == '' || form.projectDescription.value == '') {
            alert('Must complete all fields')
            return false;
        }
        return true;
    }
    function enableFields(loggedIn) {
        document.getElementById('name').disabled = !loggedIn;
        document.getElementById('description').disabled = !loggedIn;
        document.getElementById('submitProject').disabled = !loggedIn;
        if (!loggedIn) {
            document.getElementById('description').style.fontStyle = 'italic';
            document.getElementById('description').innerHTML = 'You must login to create your project';
        }    
    }
</script>
<%--
<ul id="nav">
    <li><a href="<%= response.encodeURL("projects") %>" id="homeButton">H</a></li>
    <li><a href="<%= response.encodeURL("main.jsp?requestedPage=create_project.jsp") %>"
            id="projectButton" class="selected">P</a></li>
    <li><a href="#" id="requestButton">R</a></li>
    <li><a href="#" id="uploadButton">U</a></li>
</ul>--%>
<div id="pageTitle">
    <h2>Create New Project</h2>
</div>
<body  onload="enableFields(<%= loggedIn %>)">
    <form action="createProject" method="post" onsubmit="return checkForm(this)">
        <table id="table" cellspacing="2" border="0">
            <c:choose>
                <c:when test="${success != null && success == true}">
                    <tr align="center">
                        <td colspan="2">
                            <font color="green">Project Create Successful</font>
                        </td>
                    </tr>
                </c:when>
                <c:when test="${success != null && success == false}">
                    <tr align="center">
                        <td colspan="2">
                            <font color="red">Project Create Unsuccessful</font>
                        </td>
                    </tr>
                </c:when>
            </c:choose>
            <tr align="right">
                <td>Project Name</td>
                <td><textarea id="name" name="projectName" rows="1" cols="35"></textarea></td>
            </tr>
            <tr align="right">
                <td>Project Description</td>
                <td><textarea id="description" name="projectDescription" rows="6" cols="35"></textarea></td>
            </tr>
            <tr>
                <td align="right">My Role</td>
                <td align="center">Administrator
                    <input type="checkbox" checked="true" disabled="true"></td>
            </tr>
            <tr>
                <td></td>
                <td align="right"><input id="submitProject" type="submit" value="s"></td>
            </tr>
        </table>
    </form>
</body>
