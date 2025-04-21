<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Логин</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<form method="post" action="sign-in">
    <span class="sing-in-tittle">
<h1>Форма входа</h1>
</span>
    <input type="text" name="login" placeholder="Имя пользователя" required>
    <input type="password" name="password" placeholder="Пароль" required>
    <button type="submit">Войти</button>
    <p style="text-align: center;">Нет аккаунта? <a href="/sign-up">Зарегистрироваться!</a></p>
</form>
</body>
</html>