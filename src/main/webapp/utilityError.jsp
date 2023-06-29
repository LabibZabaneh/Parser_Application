<%--
  Created by IntelliJ IDEA.
  User: labib
  Date: 6/22/23
  Time: 9:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>error</title>
</head>
<body>
<form action="previous-page-url" method="post">
    <input type="hidden" name="fileName" value="data.jsp">
    <button type="submit">Back</button>
</form>
<h1>Error</h1>
<h2>The utility  function "${requestScope.function}" is not available for the column "${requestScope.colName}"</h2>
</body>
</html>
