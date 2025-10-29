<%-- 
    Document   : login
    Created on : 27-Oct-2025, 10:02:59
    Author     : phant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form action="MainController" method="post">
            <input type="hidden" name="txtAction" value="login"/>
            <table>
                <tr>
                    <td colspan="2">
                        <h2>Login</h2>
                    </td>
                </tr>
                <tr>
                    <th>Username</th>
                    <td>
                        <input type="text" name="txtUsername" value="${username}"/>
                    </td>
                </tr>
                <tr>
                    <th>Password</th>
                    <td>
                        <input type="text" name="txtPassword"/>

                    </td>
                </tr>
                <tr>
                    <td colspan="1">
                        <a href="#">Forget password?</a>
                    </td>
                    <td colspan="1">
                        <a href="register.jsp">Register</a>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">

                        <p style="color: red">${msg}</p>
                    </td>
                </tr>
                <tr>
                    <td>
                        <button type="submit">
                            Login
                        </button>
                    </td>
                </tr>
            </table>

        </form>
    </body>
</html>
