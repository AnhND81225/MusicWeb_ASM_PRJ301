<%-- 
    Document   : otpAuthenticator
    Created on : 29-Oct-2025, 18:30:28
    Author     : phant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OTP Authenticator</title>
    </head>
    <body>
        <form action="MainController" method="post">
            <input type="hidden" name="txtAction" value="verifyOTP"/>
            <input type="hidden" name="otpSend" value="${otpSend}"/>
            <input type="hidden" name="email" value="${email}"/>
            <input type="hidden" name="username" value="${username}"/>
            <input type="hidden" name="password" value="${password}"/>
            <table>
                <tr>
                    <th>Confirm OTP</th>
                    <td>
                        <input type="text" name="txtOTP" required/>
                        <a href="url">Send OTP</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <button type="submit">Submit</button>
                    </td>
                </tr>
            </table>
            <p style="color: red">${msg}</p>

        </form>
    </body>
</html>
