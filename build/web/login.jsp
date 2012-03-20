<%-- 
    Document   : index
    Created on : 12-Mar-2012, 10:23:43 AM
    Author     : Zhengyi
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/includes/header.html"/>
<h1>
    Welcome, Please login
</h1><br>
<c:if test="${requestScope.error != null}">
    <font color="red">${requestScope.error}</font>
    <% request.removeAttribute("error"); %>
</c:if>
<c:if test="${requestScope.registered != null}">
    <font color="green">${requestScope.registered}</font>
    <% request.removeAttribute("registered"); %>
</c:if>
    <form action="login" method="post">
        <table cellspacing="5" border="0">
            <tr>
                <td align="right">Email address:</td>
                <td><input type="text" name="emailAddress"></td>
            </tr>
            <tr>
                <td align="right">Password:</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <input style="width:77px" type="submit" value="Login">
                    <a href="register.jsp"><input style="width:77px" type="button" value="Register"></a>
                </td>
            </tr> 
        </table>
    </form>
<jsp:include page="/includes/footer.jsp"/>