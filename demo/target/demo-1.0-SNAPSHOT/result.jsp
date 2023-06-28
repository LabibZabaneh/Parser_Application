<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: labib
  Date: 6/21/23
  Time: 9:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Result Page</title>
    <style>
        /* Updated CSS styles */
        .result-box {
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
            /* Center the result vertically and horizontally */
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }

        .result-box #result {
            font-size: 24px; /* Increase the font size */
        }

        .label {
            font-weight: bold;
            margin-right: 5px;
        }
    </style>
</head>
<body>
<form action="previous-page-url" method="post">
    <input type="hidden" name="fileName" value="data.jsp">
    <button type="submit">Back</button>
</form>
<div class="result-box">
    <div class="label">Result:</div>
    <div id="result">${requestScope.result}</div>
</div>
</body>
</html>
