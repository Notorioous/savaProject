<%--
  Created by IntelliJ IDEA.
  User: edona
  Date: 18.02.2019
  Time: 2:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Page</title>
</head>
<body>

<h3>Registration form:</h3>
<form action="/register" method="post" enctype="multipart/form-data">

    Name: <input type="text" name="name"><br>
    Surname: <input type="text" name="surname"><br>
    Email: <input type="text" name="email"><br>
    Password: <input type="password" name="password"><br>
    Picture: <input type="file" name="picture"><br>
    <input type="submit" name="ok">

</form>

</body>
</html>
