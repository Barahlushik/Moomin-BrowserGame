<%--
  Created by IntelliJ IDEA.
  User: gendo
  Date: 11/26/23
  Time: 8:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Ошибки</title>
</head>
<body>
<div class="container">
<p>Описание муми-ошибки - ${requestScope.error}</p>
</div>
<div class="button-bottom">
    <p><a href="/index.jsp">Вернуться назад</a></p>
</div>
</body>
</html>
