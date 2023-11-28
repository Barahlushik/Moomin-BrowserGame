package servlets.game;

import dao.impl.MoominDao;
import dao.impl.MoominWinnerDao;
import dao.model.MoominSon;
import dao.model.Session;
import dao.model.Winner;
import exception.UserNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import servlets.MoominBaseServlet;
import util.JspHelper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

public class GameServlet extends MoominBaseServlet {


    private Game game;
    private final MoominDao userDao = MoominDao.getInstance();
    private final MoominWinnerDao winnerDao = MoominWinnerDao.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = validateAndReturnSession(req, "sessionId");
        game = new Game();
        HttpSession sessionScope = req.getSession();
        sessionScope.setAttribute("secondsRemaining", "60");
        LocalTime lt = LocalTime.now();
        sessionScope.setAttribute("timer_start", lt);
        sessionScope.setAttribute("answer_view", game.getAnswer().replaceAll("[a-яА-Я]","_"));
        sessionScope.setAttribute("level", game.getLevel());
        sessionScope.setAttribute("task", game.getTask());
        sessionScope.setAttribute("answer", game.getAnswer());
        Session gameSession = new Session(UUID.randomUUID(), session.getUserId(), LocalDateTime.now().plusMinutes(1));
        gameSessionDao.save(gameSession);
        Cookie cookie = new Cookie("gameSessionId", gameSession.getId().toString());
        resp.addCookie(cookie);
        req.getRequestDispatcher(JspHelper.getPath("game")).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sessionScope = req.getSession();
        LocalTime timerStart = (LocalTime) sessionScope.getAttribute("timer_start");
        LocalTime now = LocalTime.now();
        sessionScope.setAttribute("secondsRemaining", String.valueOf(60 - (now.toSecondOfDay() - timerStart.toSecondOfDay())));
        String answerView = (String) sessionScope.getAttribute("answer_view");
        Session session = validateAndReturnSession(req, "sessionId"); // провека на gameSeessin
        Session gameSession = validateAndReturnSession(req, "gameSessionId"); // провека на gameSeessin
        String newView = game.getTurnResult(req.getParameter("symbol"), answerView);
        sessionScope.setAttribute("answer_view", newView);
        if (newView.equals(sessionScope.getAttribute("answer"))) {
            if (game.hasNext()) {
                game.next();
                sessionScope.setAttribute("level", game.getLevel());
                sessionScope.setAttribute("task", game.getTask());
                sessionScope.setAttribute("answer", game.getAnswer());
                sessionScope.setAttribute("answer_view", game.getAnswer().replaceAll("[a-яА-Я]","_"));
            } else {
                MoominSon son = userDao.findById(session.getUserId()).orElseThrow(() -> new UserNotFoundException("Сын-победитель отсутсвуте в таблице!"));
                int secToWin = now.toSecondOfDay() - timerStart.toSecondOfDay();
                Winner winner = new Winner(son.getUsername(), now.toSecondOfDay() - timerStart.toSecondOfDay());
                winnerDao.save(winner);
                req.setAttribute("yourTime" , secToWin);
                req.getRequestDispatcher(JspHelper.getPath("win")).forward(req,resp);
                return;

            }
        }

        req.getRequestDispatcher(JspHelper.getPath("game")).forward(req,resp);
    }
}
