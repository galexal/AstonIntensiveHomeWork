<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script>
        <%@include file="../js/functions.js"%>
    </script>
    <title>User Create Page</title>
</head>
<body>
<div>
    <nav>
        <a href="${pageContext.request.contextPath}/">На главную</a>
        <a href="${pageContext.request.contextPath}/users">Назад</a>
    </nav>
    <p>Создание пользователя</p>
</div>
<form action="${pageContext.request.contextPath}/user_create" method="post">
    <div><label>Имя <input name="username" type="text"></label></div>
    <div><label>Почта <input name="email" type="text"></label></div>
    <div>
        <button>Создать</button>
    </div>
</form>

<c:if test="${error eq 1}">
    <h4>Поля не должны быть пустыми!</h4>
</c:if>
</body>
</html>