<%--
  Created by IntelliJ IDEA.
  User: labib
  Date: 6/22/23
  Time: 9:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<form action="previous-page-url" method="post">
    <input type="hidden" name="fileName" value="index.jsp">
    <button type="submit">Back</button>
</form>
  <h1>Parsing Error</h1>
  <h2>${requestScope.error}</h2>
</body>
</html>
