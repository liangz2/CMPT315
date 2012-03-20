<%-- 
    Document   : login_retry
    Created on : Mar 12, 2012, 11:49:59 AM
    Author     : Zhengyi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/includes/header.html"/>
    <h1>Please enter your info again</h1><br>
    <form action="loginCheck" method="post">
        <table cellspacing="5" border="0">
            <tr>
                <td align="right">Email address:</td>
                <td><input type="text" name="emailAddress"></td>
            </tr>
            <tr>
                <td align="right">Password:</td>
                <td><input type="password" name="password"><input type="submit" value="Login"></td>
            </tr>
        </table>
    </form>
    <p>
        If you are not registered, please <br>        
        <a href="register.jsp"><input type="button" value="Register"></a>
    </p>
<jsp:include page="/includes/footer.jsp"/>