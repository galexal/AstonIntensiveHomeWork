<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Posts Page</title>
    <script type="text/javascript" charset="UTF-8">
        <%@include file="../js/functions.js"%>
    </script>
</head>
<body>


<div>
    <nav>
        <a href="${pageContext.request.contextPath}/">На главную</a>
        <a href="${pageContext.request.contextPath}/users">Назад</a>
    </nav>
    <p>Сообщения пользователя:</p>
</div>

<div>
    <table border="1">
        <tr>
            <th>Имя</th>
            <th>Почта</th>
        </tr>
        <tr>
            <td>${user.userName}</td>
            <td>${user.email}</td>
        </tr>
    </table>
</div>


<div>
    <div>
        <table border="1">
            <tr>
                <th>Заголовок</th>
                <th>Содержание</th>
            </tr>
            <c:forEach items="${posts}" var="p">
                <tr>
                    <td>${p.title}</td>
                    <td>${p.content}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>