<%-- 
    Document   : project_detail
    Created on : Mar 26, 2012, 1:04:45 PM
    Author     : Zhengyi
--%>

<%@page import="business.Project"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/includes/header.jsp"/>
<jsp:useBean id="selectedProject" scope="request" class="business.Project" />

<h1>Project#: ${selectedProject.id} - ${selectedProject.name}<br>
    <font size="4">Your role in this project: ${selectedProject.myRole}
</h1>
<h3>
    Project Description
</h3>
    ${selectedProject.description}
    <br><br>
    <c:choose>
        <c:when test="${selectedProject.myRole != 'Pending' && selectedProject.myRole != 'N/A'}">
            <font color="green">Here are currently active users in the project</font>
            <table cellspace="2" border="1">
                <tr align="center">
                    <td style="width:120px">First Name</td>
                    <td style="width:120px">Last Name</td>
                    <td>Email Address</td>
                </tr>
                <c:forEach var="user" items="${selectedProject.users}">
                    <tr align="center">
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>

        </c:otherwise>
    </c:choose>
    
            <p><font size="2"><a href="logged_in_index.jsp">Back to project selection page</a></font></p>

<jsp:include page="includes/footer.jsp"/>