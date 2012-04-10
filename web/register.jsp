<%-- 
    Document   : register
    Created on : Mar 12, 2012, 10:43:19 AM
    Author     : Zhengyi
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="includes/login_header.html"/>
<div id="background">
    <center>
    <div id="data">
        <div id="header">
            <h1>New User Register</h1><br>
        </div>
        <c:if test="${requestScope.error != null}">
            <font color="red">${requestScope.error}</font><br>
        </c:if>
        <form name="data" action="register" method="post" onsubmit="return checkFields (this)">
            <table cellspacing="5" border="0">
                <tr>
                    <td align="right">First name:</td>
                    <td><input type="text" name="firstName" value="${requestScope.firstName}"</td>
                </tr>
                <tr>
                    <td align="right">Last name:</td>
                    <td><input type="text" name="lastName" value="${requestScope.lastName}"></td>
                </tr>
                <tr>
                    <td align="right">Email address:</td>
                    <td><input type="text" name="emailAddress" value="${requestScope.emailAddress}"></td>
                </tr>
                <tr>
                    <td align="right">Password:</td>
                    <td><input name="p1" type="password"></td>
                </tr>
                <tr>
                    <td align="right">Retype Password:</td>
                    <td><input name="p2" type="password"></td>
                    <td><input type="submit" name="submit" value="Register"></td>
                </tr>
            </table>
        </form>
        <p><a href="login.jsp">[Back to login screen]</a></p>
        <jsp:include page="includes/footer.jsp"/>
    </div>
    </center>
</div>
<script type="text/javascript">
    function checkFields (data) {
    var errorMsg = "";
    var p1 = data.p1.value;
    var p2 = data.p2.value;
    var fName = data.firstName.value;
    var lName = data.lastName.value;
    var email = data.emailAddress.value;

    isPassword = /^[\x21-\x7E]{6,12}$/;
    isEmail = /^[A-Za-z\d]+@[A-Za-z\d]+\.[A-Za-z]+$/;

    if (fName == "" || lName == "") {
        errorMsg = "Pleaes fill in your name";
        alert(errorMsg);
        return false;
    }
    if (email == "") {
        errorMsg = "Please enter your email address\n"
            + "It is also your login name on this website";
        alert(errorMsg);
        return false;
    }
    else {
        if (!isEmail.test(email)) {
            errorMsg = "Email Address does not have the right format";
            alert(errorMsg);
            return false;
        }
    }
    if (isPassword.test(p1)) {
        if (p2 != p1) {
            errorMsg = "Two password did not patch";
            alert(errorMsg);
            return false;
        }
    }
    else {
        errorMsg = "Password can only consist lower, upper cases and symbols,\n"
            + "also must be within 6 to 12 characters long";
        alert(errorMsg);
        return false;
    }
    return true;
}
</script>
        