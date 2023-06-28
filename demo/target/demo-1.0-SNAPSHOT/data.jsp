<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: labib
  Date: 6/20/23
  Time: 12:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Parsed Data</title>
  <style>
    table {
      border-collapse: collapse;
      width: 100%;
      border-radius: 10px;
      overflow: hidden;
    }
    th, td {
      border: 1px solid darkgray;
      padding: 8px;
      text-align: left;
    }
    th {
      background-color: lightgray;
    }
    .goback-button {
      float: right;
      padding: 10px 30px;
      font-size: 30px;
    }
    .button-container {
      margin-right: 30px;
      font-size: 20px;
    }
    .back-button {
      font-size: 24px;
      padding: 15px 30px;
    }
  </style>
</head>
<body>
<table>
  <thead>
  <c:set var="columnNames" value="${sessionScope.parsedData.get(0)}" />
  <tr>
    <c:forEach var="columnName" items="${columnNames}">
      <th>${columnName}</th>
    </c:forEach>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="row" items="${sessionScope.parsedData}" begin="1">
    <tr>
      <c:forEach var="cell" items="${row}">
        <td>${cell}</td>
      </c:forEach>
    </tr>
  </c:forEach>
  </tbody>
</table>
<div style="display: flex; justify-content: center; align-items: center">
  <div class="button-container">
    <label>Drop down list of the columns:</label>
    <c:set var="columnNames" value="${sessionScope.parsedData.get(0)}" />
    <select name="columns" id="columns">
      <c:forEach var="columnName" items="${columnNames}">
        <option value="${columnName}">${columnName}</option>
      </c:forEach>
    </select>
  </div>
  <div class="button-container">
    <form action="opColumn" method="post">
      <input type="hidden" name="columns" value="">
      <input type="hidden" name="operation" value="sum">
      <button class="getSum-button" type="submit" onclick="setColumnValue()">Generate Sum</button>
    </form>
  </div>
  <div class="button-container">
    <form action="opColumn" method="post">
      <input type="hidden" name="columns" value="">
      <input type="hidden" name="operation" value="avg">
      <button class="getAvg-button" type="submit" onclick="setColumnValue()">Generate Avg</button>
    </form>
  </div>
  <div class="button-container">
    <form action="getColumn" method="post">
      <input type="hidden" name="columns" value="">
      <button class="get-button" type="submit" onclick="setColumnValue()">Show data separately</button>
    </form>
  </div>
  <div class="button-container">
    <form action="getHistory" method="post">
      <button class="get-button" type="submit">Show History Table</button>
    </form>
  </div>
  <form action="previous-page-url" method="post">
    <input type="hidden" name="fileName" value="index.jsp">
    <button class="back-button" type="submit">Back</button>
  </form>
</div>
<script>
  function setColumnValue() {
    var selectedValue = document.getElementById("columns").value;
    document.getElementsByName("columns").forEach(function(element) {
      element.value = selectedValue;
    });
  }
</script>
</body>
</html>
