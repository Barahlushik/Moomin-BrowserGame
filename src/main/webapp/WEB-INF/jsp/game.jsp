<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Игра</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body onload="timer()">
<div class="container">
    <div class="centered-text">
        <h2>Уровень - ${sessionScope.level}</h2>
    </div>
    <input type="hidden" id="timeout-holder" value="${sessionScope.secondsRemaining}">
    <div class="timer-wrapper" style="position: relative;">
        <svg class="timer-svg" width="120" height="120">
            <circle class="timer-background" cx="60" cy="60" r="45"></circle>
            <circle class="timer-circle" cx="60" cy="60" r="45"></circle>
        </svg>
        <div class="timer-text" id="timer-text">${sessionScope.secondsRemaining}</div>
    </div>
    <p><strong>${sessionScope.task}</strong></p>
    <p>Введи букву в поле, чтобы разгадать!</p>
    <div class="centered-text">
        <p>${sessionScope.answer_view}</p>
    </div>
    <form method="post" action="game">
        <input type="text" id="symbol" name="symbol" required>
        <button type="submit" id="submit-button">Открыть букву!</button>
    </form>
</div>
<script src="js/timer.js"></script>
</body>
</html>
