<%-- 
    Document   : project_detail
    Created on : Mar 26, 2012, 1:04:45 PM
    Author     : Zhengyi
--%>

<%@page import="business.Role"%>
<%@page import="business.User"%>
<%@page import="business.Project"%>
<%@page import="java.util.HashMap"%>
<%@page import="business.Constants" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    Project selectedProject = (Project) request.getSession().getAttribute("selectedProject");
    User user = (User) (request.getSession().getAttribute("user"));
    String leftContent = (String) request.getSession().getAttribute("leftContent");
    String myProjects = "my_projects.jsp";
    String projectDetail = "project_detail.jsp";
    Role myRole = null;
%>

<%if (selectedProject == null) {%>
    <h2 id="pageTitle">Select a project to view details</h2>
<%return;} else {myRole = selectedProject.getMyRole();%>
    <h2 id="pageTitle">Project#  <%= selectedProject.getId() %></h2>
<%}%>
<div id="projectList">
    <div id="projectDetail">
        <div class="title">
            <span style="font-size: 25px"><%= selectedProject.getName() %></span>
        </div>
        <div class="description">
            <%= selectedProject.getDescription() %> 
            <p style="float: right; font-size: 13px; font-style: italic; color:white">
                Number of users: <%= selectedProject.getUsers().size() %></p>
            <div class="clear"><hr></div>
        </div>
        <c:if test="<%= leftContent.equals(myProjects) %>">
            <c:choose>
            <c:when test="<%= !myRole.equals(Constants.PENDING) && !myRole.equals(Constants.NONE) %>">
                <font color="green">Here are currently active users in the project</font>
                <form action="removeUser" method="post" onsubmit="return getUser()">
                <table id="table" cellspace="2" border="0">
                    <tr align="center">
                        <td style="width:120px">First Name</td>
                        <td style="width:120px">Last Name</td>
                        <td>Email Address</td>
                        <td>Role</td>
                        <c:if test="<%= myRole.equals(Constants.ADMIN) %>">
                            <td>Remove</td>
                        </c:if>
                    </tr>
                    <% for (User u: selectedProject.getUsers()) { %>
                        <c:choose>
                            <c:when test="<%= u.getEmail().equals(user.getEmail()) %>" ><tr align="center" style="color:red"></c:when>
                            <c:otherwise><tr align="center"></c:otherwise>
                        </c:choose>
                            <td><%= u.getFirstName() %></td>
                            <td><%= u.getLastName() %></td>
                            <td><%= u.getEmail() %></td>
                            <td><%= u.getRelativeRole() %></td>
                            <c:if test="<%= myRole.equals(Constants.ADMIN) %>">
                                <td>
                                    <input name="removeEmail"
                                        <c:if test="<%= u.getEmail() == user.getEmail() %>">
                                        disabled="true"
                                        </c:if>
                                        type="checkbox" value="<%= u.getEmail() %>"/>
                                </td>
                            </c:if>
                        </tr>
                        <%}%>
                    </table>
                    <c:if test="<%= myRole.equals(Constants.ADMIN)%>">
                        <p><input type="submit" value="R" /></p>
                    </c:if>    
                </form>
            </c:when>
            </c:choose>
        </c:if>
        <div class="listFooter">
            <c:choose>
                <c:when test="<%= myRole == null %>">
                    You have to login to see more options
                </c:when>
                <c:when test="<%= myRole.equals(Constants.ADMIN)%>">
                    You are a <i>Administrator</i>
                    <a id="editButton" href="projectDetail?queryType=projectDetail">
                        E
                    </a>
                </c:when>
                <c:when test="<%= myRole.equals(Constants.NONE) && !leftContent.equals(projectDetail) %>">
                    You are not in this project
                    <a id="joinButton" href="projects?queryType=joinProject">
                        j
                    </a>
                </c:when>
            </c:choose>
        </div>
    </div>
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
    <%--
    function forwardPage() {
        <jsp:forward page="projectDetail?projectId=<%= selectedProject.getId() %>">
            <jsp:param name="selectedProject" value="<%= selectedProject %>" /> 
        </jsp:forward>
    }--%>
</script>