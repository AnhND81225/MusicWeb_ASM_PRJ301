<%-- 
    Document   : register
    Created on : 28-Oct-2025, 22:42:57
    Author     : phant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <form action="MainController" method="post" onsubmit="return validateForm()">
            <input type="hidden" name="txtAction" value="register"/>
            <table>
                <tr>
                    <td colspan="2">
                        <h2>Register</h2>
                    </td>
                </tr>
                <tr>
                    <th>Username</th>
                    <td>
                        <input type="text" name="txtUsername" value="${username}" required/>
                    </td>
                </tr>
                <tr>
                    <th>Password</th>
                    <td>
                        <input type="password" name="txtPassword" required/>

                    </td>
                </tr>
                <tr>
                    <th>Confirm Password</th>
                    <td>
                        <input type="password" name="txtConfirmPassword" required/>

                    </td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td>
                        <input type="email" name="txtEmail" required placeholder="example123@gmail.com"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="1">
                        <a href="login.jsp">Login</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <button type="submit">
                            Register
                        </button>
                    </td>
                </tr>
            </table>
                    <p>${error}</p>
        </form>
        <script>
            function validateForm() {
                const password = document.querySelector('input[name="txtPassword"]').value;
                const confirmPassword = document.querySelector('input[name="txtConfirmPassword"]').value;

                if (password !== confirmPassword) {
                    alert("Password and Confirm Password do not match!");
                    return false; // prevent form submission
                }
                return true;
            }
        </script>
    </body>
</html>
