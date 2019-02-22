<%--
  Created by IntelliJ IDEA.
  User: edona
  Date: 21.02.2019
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Messenger</title>
</head>
<body>
<c:set var="userFriend" value="${requestScope.get('userFriend')}"/>
<c:set var="user" value="${sessionScope.get('user')}"/>
<h3>Welcome to the Messenger</h3>

<h4><a href="/user/toHome">Home Page</a></h4>

<div>

<c:forEach var="message" items="${requestScope.get('messages')}">
    <c:if test="${message != null}">
    <table border="1">
        From : ${message.fromId.name}
        To : ${message.toId.name}<br>
        Time : ${message.date}<br><br>

            ${message.text}
        <hr>

    </table>


    </c:if>
</c:forEach>
</div>

<form action="/user/sendMessage">

    To   : ${userFriend.name}<br>
    From : ${user.name}<br> <br>

    <input type="number" value="${userFriend.id}" name="toId" hidden>
    <textarea name="text"></textarea><br><br>
    <input type="submit" name="Send">

</form>
</body>
</html>
