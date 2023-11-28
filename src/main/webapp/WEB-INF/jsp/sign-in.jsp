<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Форма входа</h1>
<form method="post" action="sign-in">
    <div>
        <input type="text" class="form-control" id="login" name="login" placeholder="username" required>
    </div>

    <div>
        <input type="password" class="form-control" id="password" name="password" placeholder="password" required>
    </div>
    <button type="submit">Войти</button>

    <p>Нет аккаунта? - <a href="/sign-up">Зарегистрироваться!</a></p>

</form>
</body>
</html>
