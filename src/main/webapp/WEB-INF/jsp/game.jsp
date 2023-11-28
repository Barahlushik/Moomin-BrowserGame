<%--
  Created by IntelliJ IDEA.
  User: gendo
  Date: 11/26/23
  Time: 11:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script>
<!--
var timeout = ${sessionScope.secondsRemaining};
function timer() {
if( --timeout > 0 ) {
    document.forma.clock.value = timeout;
    window.setTimeout( "timer()", 1000 );
} else {
    document.forma.clock.value = "Time over";
///disable submit-button etc
}
}
//-->
</script>
<body>



<h1>Уровень - ${sessionScope.level}</h1>

<form action="<%=request.getRequestURL()%>" name="forma">
    Seconds remaining: <input type="text" name="clock" value="${sessionScope.secondsRemaining}" style="border:0px solid white">
    ...
</form>

<p>${sessionScope.task}</p>
<p> Введи букву в поле, чтобы разгадать!</p>
<p>${sessionScope.answer_view}</p>
<form method="post" action="game">
    <input type="text" id="symbol" name="symbol" required>
    <button type="submit">Открыть букву!</button>
</form>
<script>
<!--
timer();
//-->
</script>
</body>
</html>
