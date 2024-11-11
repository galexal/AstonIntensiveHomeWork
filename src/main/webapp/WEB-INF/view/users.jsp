<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Users Page</title>
    <script type="text/javascript" charset="UTF-8">
        <%@include file="../js/functions.js"%>
    </script>
</head>
<body>

<div>
    <table border="1">
        <caption>Список пользователей</caption>
        <tr>
            <th></th>
            <th>Имя</th>
            <th>Почта</th>
        </tr>
        <c:forEach items="${users}" var="s">
            <tr>
                <form>
                    <label>
                        <td><input type="checkbox" value="${s.id}" name="userId"></td>
                        <td>${s.userName}</td>
                        <td>${s.email}</td>
                    </label>
                </form>
            </tr>
        </c:forEach>
    </table>
</div>
<br>
<button onclick="userPosts()"> Просмотреть сообщения выбранного пользователя</button>
<br>
<button onclick="modifyUser()">Изменить выбранного пользователя...</button>
<br>
<button onclick="deleteUsers()">Удалить выбранных пользователей</button>
<br><br>
<form action="${pageContext.request.contextPath}/user_create" method="get">
    <button>Создать пользователя...</button>
</form>

</body>
<form action="${pageContext.request.contextPath}/users_delete" method="post" id="deleteForm">
    <input type="hidden" name="idsForDelete" id="idsForDelete">
</form>
<form action="${pageContext.request.contextPath}/user_modify" method="get" id="modifyForm">
    <input type="hidden" name="idForModify" id="idForModify">
</form>
<form action="${pageContext.request.contextPath}/user_posts" method="get" id="postsForm">
    <input type="hidden" name="idForPosts" id="idForPosts">
</form>
</html>