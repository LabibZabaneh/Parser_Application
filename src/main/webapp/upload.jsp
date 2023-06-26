<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Csv Json Parser</title>
  <style>
    .upload-form {
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }
    .upload-form input[type="file"] {
      padding: 10px 40px;
      font-size: 16px;
      width: 300px;
    }
    .upload-form input[type="submit"] {
      padding: 15px 120px;
      font-size: 30px;
    }
  </style>
</head>
<body>
<div class="upload-form">
  <form action="uploadFile" method="post" enctype="multipart/form-data">
    <input type="file" id="file" name="file" accept=".csv, .json" placeholder="Please Select A File...">
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <input type="submit" value="Upload">
  </form>
</div>

</body>
</html>