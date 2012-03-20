<%-- 
    Document   : password_change
    Created on : Mar 12, 2012, 12:42:57 PM
    Author     : Zhengyi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/includes/header.html"/>
    <h1>Welcome ${user.firstName}, update your password</h1><br>
    <form name="passUpdate" action="password" method="post" onsubmit="return matchPassword (this)">
        <table cellspacing="5" border="0">
            <tr align="right">
                <td>Current Password:</td>
                <td><input type="password" name="oldPassword"></td>
            </tr>
            <tr align="right">
                <td>New Password:</td>
                <td><input type="password" name="newPassword"></td>
            </tr>
                <td>Retype New Password:</td>
                <td><input type ="password" name="newPassword2"></td>
            <tr>
                <td></td>
                <td><input type="submit" value="Update Password"></td>
            </tr>
        </table>
    </form>
    
    <script type="text/javascript">
        function matchPassword (passwords) {
            var op = passwords.oldPassword.value;
            var p1 = passwords.newPassword.value;
            var p2 = passwords.newPassword2.value;
            var msg = '';
            
            isPassword = /^[\x21-\x7E]{6,12}$/;
            
            if (!isPassword.test(op) || !isPassword.test(p1) || !isPassword.test(p2)) {
                msg = "Password can only consist lower, upper cases and symbols,\n"
                    + "also must be within 6 to 12 characters long";
                alert (msg);
                return false;
            }
            else {
                if (p1 != p2) {
                    msg = "Two passwords do not match";
                    alert (msg);
                    return false;
                }
            }
            
            return true;
        }
    </script>
<jsp:include page="/includes/footer.jsp"/>