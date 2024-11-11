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
    <title>User Modify Page</title>
</head>
<body>

<div>
    <nav>
        <a href="${pageContext.request.contextPath}/">На главную</a>
        <a href="${pageContext.request.contextPath}/users">Назад</a>
    </nav>
    <p>Изменение пользователя</p>
</div>
<form action="${pageContext.request.contextPath}/user_modify" method="post">
    <input type="hidden" name="id" value="${user.id}">
    <div><label>Имя <input type="text" name="username" value="${user.userName}"></label></div>
    <div><label>Почта <input type="text" name="email" value="${user.email}"></label></div>
    <div>
        <button>Применить</button>
    </div>
</form>
<c:if test="${error eq 1}">
    <h4 class="p4">Поля не должны быть пустыми!</h4>
</c:if>
</body>
</html>