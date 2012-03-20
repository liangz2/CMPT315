<%-- 
    Document   : register_confirm
    Created on : Mar 12, 2012, 1:19:57 PM
    Author     : Zhengyi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="includes/header.html"/>
    <h1>Register success</h1><br>
    <table cellspace="5" border="0">
        <tr align="right">
            <td>Login Email:</td>
            <td>${user.emailAdress}</td>
        </tr>
        <tr align="right">
            <td>First Name:</td>
            <td>${user.firstName}</td>
        </tr>
        <tr align="right">
            <td>Last Name:</td>
            <td>${user.lastName}</td>
        </tr>
    </table>
    <p>Your complete info will be emailed to your registered email address</p>
<jsp:include page="includes/footer.jsp"/>
