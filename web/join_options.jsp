<%-- 
    Document   : join_options
    Created on : Apr 19, 2012, 2:16:31 AM
    Author     : Icewill
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="business.Role"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<Role> roles = (ArrayList<Role>) request.getAttribute("roles");
%>
<select id="roleMenu">
    <% for (Role role: roles) {%>
        <option value="<%= role.getId() %>"><%= role.getName() %></option>
    <%}%>
</select>
