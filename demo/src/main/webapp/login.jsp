<%--
  Created by IntelliJ IDEA.
  User: labib
  Date: 6/26/23
  Time: 12:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <div>
        <h2>User Login</h2>
        <form action="login" method="post">
            <input type="text" name="username" placeholder="Username" required><br>
            <input type="text" name="password" placeholder="Password" required><br><br>
            <input type="submit" value="Login">
        </form>
    </div>
</body>
</html>
