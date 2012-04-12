<%-- 
    Document   : index
    Created on : 12-Mar-2012, 10:23:43 AM
    Author     : Zhengyi
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/includes/login_header.html"/>
<style type="text/css">
    <%@include file="/CSS/wiki.css" %>
</style>
<div class="wrapper">
    <div id="header"><div id="logo"></div>
    <c:if test="${requestScope.error != null}">
        <font color="red">${requestScope.error}</font>
        <% request.removeAttribute("error"); %>
    </c:if>
    <c:if test="${requestScope.registered != null}">
        <font color="green">${requestScope.registered}</font>
        <% request.removeAttribute("registered"); %>
    </c:if>
        <form action="login" method="post">
            <div id="register">
            <table cellspacing="5" border="0">
                <tr>
                    <td align="right"><div id="emailAddr"></div></td>
                    <td><input style="width:180px" type="text" name="emailAddress"></td>
                </tr>
                <tr>
                    <td align="right"><div id="password"></div></td>
                    <td><input style="width:180px" type="password" name="password"></td>
                </tr>
                <tr>
                    <td></td>
                    <td align="right">
                        <input id="loginButton" type="submit" value="Login">
                        <a href="register.jsp" id="registerButton"></a>
                    </td>
                </tr> 
            </table>
            </div>
        </form>
    </div>
    <div id="nav">
        
    </div>
    <jsp:include page="/includes/footer.jsp"/>
</div>
