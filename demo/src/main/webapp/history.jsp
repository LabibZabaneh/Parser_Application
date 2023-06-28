<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Data History Table</title>
    <style>
        /* Center the table on the screen */
        table {
            margin: 0 auto;
            border-collapse: collapse;
        }

        /* Increase the font size for table headers */
        th {
            font-size: 18px;
            border: 1px solid black;
            padding: 8px;
        }

        /* Increase the font size for table cells */
        td {
            font-size: 16px;
            border: 1px solid black;
            padding: 8px;
        }
    </style>
</head>
<body>
<form action="previous-page-url" method="post">
    <input type="hidden" name="fileName" value="data.jsp">
    <button class="back-button" type="submit">Back</button>
</form>
<table>
    <thead>
    <tr>
        <th>File Name</th>
        <th>Column Name</th>
        <th>Sum</th>
        <th>Average</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="row" items="${requestScope.table}">
        <tr>
            <c:forEach var="cell" items="${row}">
                <td>${cell}</td>
            </c:forEach>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
