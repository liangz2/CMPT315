<%-- 
    Document   : project_detail
    Created on : Mar 26, 2012, 1:04:45 PM
    Author     : Zhengyi
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/includes/header.jsp"/>
<jsp:useBean id="user" scope="session" class="business.User" />
<div id="background">
    <center>
    <div id="header">
        <h1>Project#  ${user.selectedProject.id} -  ${user.selectedProject.name}<br>
            <c:choose>
                <c:when test="${user.selectedProject.myRole == 'N/A'}">
                    <font size="4">
                    Your are not in this project
                    </font>
                </c:when>
                <c:when test="${user.selectedProject.myRole == 'Pending'}">
                    <font size="4">
                    You have applied for '${user.selectedProject.pendingRole}' in the project
                    </font>
                </c:when>
                <c:otherwise>
                    <font size="4">
                    Your role in this project: ${user.selectedProject.myRole}
                    </font>
                </c:otherwise>
            </c:choose>

            <font size="2">Number of registered users in this project: ${user.selectedProject.users.size()}</font>
        </h1>
    </div>
    <div id="data">
        <p><font size="2"><a href="index.jsp">[Home]</a></font></p>
        <h3>
            Project Description
        </h3>
        <p style="border:ridge; width:350px;" align="left">${user.selectedProject.description}</p>
            <br><br>
            <c:choose>
                <c:when test="${user.selectedProject.myRole != 'Pending' && user.selectedProject.myRole != 'N/A'}">
                    <font color="green">Here are currently active users in the project</font>
                    <form action="removeUser" method="post" onsubmit="return getUser()">
                    <table cellspace="2" border="1">
                        <tr align="center">
                            <td style="width:120px">First Name</td>
                            <td style="width:120px">Last Name</td>
                            <td>Email Address</td>
                            <td>Role</td>
                            <c:if test="${user.selectedProject.myRole == 'Admin'}">
                                <td>Remove</td>
                            </c:if>
                        </tr>
                        <c:forEach var="u" items="${user.selectedProject.users}">
                            <c:choose>
                                <c:when test="${u.email == user.email}" ><tr align="center" style="color:red"></c:when>
                                <c:otherwise><tr align="center"></c:otherwise>
                            </c:choose>
                                <td>${u.firstName}</td>
                                <td>${u.lastName}</td>
                                <td>${u.email}</td>
                                <td>${u.relativeRole}</td>
                                <c:if test="${user.selectedProject.myRole == 'Admin'}">
                                    <td>
                                        <input name="removeEmail"
                                            <c:if test="${u.email == user.email}">
                                            disabled="true"
                                            </c:if>
                                            type="checkbox" value="${u.email}"/>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </table>
                    <c:if test="${user.selectedProject.myRole == 'Admin'}">
                        <p><input type="submit" value="Remove Selected Users" /></p>
                    </c:if>    
                    </form>
                </c:when>
                <c:when test="${user.selectedProject.myRole == 'N/A'}">

                </c:when>
            </c:choose>
        <jsp:include page="includes/footer.jsp"/>
    </div>
    </center>
</div>
    <script type="text/javascript">
        function getUser() {
            var emails = document.getElementsByName("removeEmail");
            var users = '';
            var isChecked = false;
            var r;

            if (emails != null) {
                for (i=0; i<emails.length; i++) {
                    if (emails.item(i).checked == true) {
                        users += emails.item(i).value + '\n';
                        if (!isChecked)
                            isChecked = true;
                    }
                }
                if (isChecked) {
                    r = confirm ('Are you sure you want to remove the following user(s) from this project?\n' 
                        +  users);

                    if (r == true) {
                        return true;
                    }
                    else
                        return false;
                }
            }

            alert ('You must select at least one user to continue.')
            return false;
        }
    </script>
