<%-- 
    Document   : create_project
    Created on : Dec 31, 2009, 11:21:40 PM
    Author     : Icewill
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
    function checkForm (form) {
        if (form.projectName.value == '' || form.projectDescription.value == '') {
            alert('Must complete all fields')
            return false;
        }
        return true;
    }
</script>
<form action="createProject" method="post" onsubmit="return checkForm(this)">
    <table id="createProject" cellspacing="2" border="0">
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
        <tr align="center">
            <td colspan="2"><h2>Create New Project</h2></td>
        </tr>
        <tr align="right">
            <td>Project Name</td>
            <td><textarea id="name" name="projectName" rows="1" cols="35"></textarea></td>
        </tr>
        <tr align="right">
            <td>Project Description</td>
            <td><textarea id="description"name="projectDescription" rows="6" cols="35"></textarea></td>
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
