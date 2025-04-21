<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Регистрация</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<form method="post" action="sign-up">
    <span class="sing-up-tittle">
<h1>Форма регистрации</h1>
</span>
    <input type="text" name="login" placeholder="Имя пользователя" required>
    <input type="password" name="password" placeholder="Пароль" required>
    <button type="submit">Зарегистрироваться</button>
</form>
</body>
</html>
