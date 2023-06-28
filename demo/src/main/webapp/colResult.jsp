<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Column Result Page</title>
    <style>
        /* Styles for table */
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        /* Other styles */
        .result-box {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
        }

        .label {
            font-weight: bold;
            margin-bottom: 5px;
        }

        .goback-button {
            float: left;
            padding: 10px 30px;
            font-size: 30px;
        }
    </style>
</head>
<body>
<form action="previous-page-url" method="post">
<%--    <input type="hidden" name="result" value="${requestScope.result}" />--%>
    <input type="hidden" name="fileName" value="data.jsp">
    <button type="submit">Back</button>
</form>
<div class="result-box">
    <div class="label">Result:</div>
    <div id="result">
        <table>
            <thead>
            <tr>
                <th>${requestScope.colName}</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.result}" var="value">
                <tr>
                    <td>${value}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
