let timeout = parseInt(document.getElementById("timeout-holder").value);
const timerCircle = document.querySelector(".timer-circle");
const timerText = document.getElementById("timer-text");
const circumference = 2 * Math.PI * 45;

timerCircle.style.strokeDasharray = `${circumference}`;
timerCircle.style.strokeDashoffset = `0`;

function updateTimerVisual(timeLeft, totalTime) {
    const offset = circumference * (1 - timeLeft / totalTime);
    timerCircle.style.strokeDashoffset = offset;
    timerText.textContent = timeLeft;
}

function timer() {
    if (--timeout > 0) {
        updateTimerVisual(timeout, parseInt(document.getElementById("timeout-holder").value));
        setTimeout(timer, 1000);
    } else {
        updateTimerVisual(0, 1);
        timerText.textContent = "⏰";
        setTimeout(() => {
            document.getElementById("symbol").value = "Время истекло!";
            document.getElementById("submit-button").click();
        }, 1000);
    }
}

window.onload = timer;