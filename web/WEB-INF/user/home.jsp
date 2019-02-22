<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: edona
  Date: 18.02.2019
  Time: 1:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>

<c:set var="user" value="${sessionScope.get('user')}"/>

<h3>Hello my friend ${user.name}
</h3>
<h4><a href="/user/logout"><b>LOGOUT</b></a></h4>

<div style="width: 250px">
    <img src="/getImage?picName=${user.picUrl}" width="200">
</div>
<br> <br>

<div style="border: black 1px dashed; width: 150px">
    ${user.name} ${user.surname}
</div><br>



<div>
    <h3>All USERS: </h3>
    <c:forEach var="users" items="${requestScope.get('users')}">
    <c:if test="${users != null}">

    <table border="1">
        <tr>
            <td>Name</td>
            <td>Surname</td>
            <td>Photo</td>
            <td>Request</td>
        </tr>

        <tr>

            <td>${users.name}</td>
            <td>${users.surname}</td>
            <td><img src="/getImage?picName=${users.picUrl}" width="70 px"></td>
            <td><a href="/user/sendFriendRequest?to_id=${users.id}">SEND</a></td>

        </tr>
    </table>
</div>
</c:if>
</c:forEach>


<div>
    <h3>FRIEND REQUESTS: </h3>

    <c:forEach var="reqUsers" items="${requestScope.get('requestUsers')}">
    <c:if test="${reqUsers != null}">

    <table border="1">
        <tr>
            <td>Name</td>
            <td>Surname</td>
            <td>Photo</td>
            <td>Request</td>
        </tr>

        <tr>

            <td>${reqUsers.name}</td>
            <td>${reqUsers.surname}</td>
            <td><img src="/getImage?picName=${reqUsers.picUrl}" width="70 px"></td>
            <td><a href="/user/acceptOrReject?from_id=${reqUsers.id}&action=Accept">Accept</a></td>
            <td><a href="/user/acceptOrReject?from_id=${reqUsers.id}&action=Reject">Reject</a></td>

        </tr>
    </table>
</div>
</c:if>
</c:forEach>


<div>
    <h3> ALL FRIENDS: </h3>

    <c:forEach var="friends" items="${requestScope.get('friends')}">
    <c:if test="${friends != null}">

    <table border="1">
        <tr>
            <td>Name</td>
            <td>Surname</td>
            <td>Photo</td>
            <td>Message</td>
            <td>Request</td>
        </tr>

        <tr>

            <td>${friends.name}</td>
            <td>${friends.surname}</td>
            <td><img src="/getImage?picName=${friends.picUrl}" width="70 px"></td>
            <td><a href="/user/getFriend?to_id=${friends.id}"><b>Messages</b></a></td>
            <td style="width: 50px"><a href="/user/deleteFriend?from_id=${friends.id}"> <p style="border: red 1px solid">DELETE</p>  </a></td>

        </tr>
    </table>
</div>
</c:if>
</c:forEach>


</body>
</html>
