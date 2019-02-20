<%--
  Created by IntelliJ IDEA.
  User: edona
  Date: 18.02.2019
  Time: 1:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<c:set var="message" value="${requestScope.get('message')}"/>
<c:if test="${message != null}">
    <p style="color: red">${message}</p>
</c:if>

<h3>Welcome to the Social Network</h3>

<form action="/login" method="post">

    Email: <input type="text" name="email"><br>
    Password: <input type="password" name="password"><br>
    <input type="submit" name="ok"><br>
    <h5> or<br>
        <a href="/registration"><b>REGISTER</b></a> </h5>


</form>

</body>
</html>
