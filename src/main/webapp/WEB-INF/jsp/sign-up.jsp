<%--
  Created by IntelliJ IDEA.
  User: gendo
  Date: 11/26/23
  Time: 6:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>

<h1>Форма регистрации</h1>
<form method="post" action="sign-up">
    <div>
        <input type="text" class="form-control" id="login" name="login" placeholder="username" required>
    </div>
    <div>
        <input type="password" class="form-control" id="password" name="password" placeholder="password" required>
    </div>

    <button type="submit">Зарегистрироваться</button>
</form>

</body>
</html>
