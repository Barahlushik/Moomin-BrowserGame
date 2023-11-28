<%--
  Created by IntelliJ IDEA.
  User: gendo
  Date: 11/25/23
  Time: 6:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Таблица с победителями</h1>
<table>
    <tr>
        <td>Имя Пользователя</td>
        <td>Кол-во секунд</td>
    </tr>
    <c:forEach var="winner" items="${requestScope.winners}">
        <tr>
            <td>${winner.login}</td>
            <td>${winner.seconds}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
