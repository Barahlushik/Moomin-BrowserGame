package servlets;

import dao.SessionDao;
import dao.impl.GameSessionDao;
import dao.impl.UserSessionDao;
import dao.model.Session;
import exception.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.JspHelper;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

public abstract class MoominBaseServlet extends HttpServlet {

    protected static final SessionDao<Session> userSessionDao = UserSessionDao.getInstance();

    protected static final SessionDao<Session> gameSessionDao = GameSessionDao.getInstance();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        try {
            super.service(req, resp);
        } catch (GameSessionExpired e) {
            req.getRequestDispatcher(JspHelper.getPath("lose"))
                    .forward(req, resp);
        } catch (SessionExpiredException | UserExistsException | CookieNotFoundException | WrongPasswordException e) {
            resp.sendRedirect(req.getContextPath() + "/sign-in");

        }  catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher(JspHelper.getPath("error"))
                    .forward(req, resp);
        }
    }

    private static Optional<Cookie> findCookieByName(Cookie[] cookies, String cookieName) {
        if (cookies == null || cookies.length < 1) {
            return Optional.empty();
        }

        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName()
                        .equals(cookieName))
                .findFirst();
    }

    protected static Session validateAndReturnSession(HttpServletRequest req, String cookieName) throws CookieNotFoundException, SessionExpiredException, GameSessionExpired {

        Cookie[] cookies = req.getCookies();
        Cookie cookie = findCookieByName(cookies, cookieName) // sessionId / gameSessionId
                .orElseThrow(() -> new CookieNotFoundException("Cookie не найдена!"));

        UUID sessionId = UUID.fromString(cookie.getValue());
        Session session = null;
        if (cookieName.equals("sessionId")) {
            session = userSessionDao.findById(sessionId).orElseThrow(() -> new SessionExpiredException("Session: " + sessionId + " has expired"));
            if (isSessionExpired(session)) {
                throw new SessionExpiredException("Session: " + sessionId + " истекла");
            }
        } else {
            session = gameSessionDao.findById(sessionId).orElseThrow(() -> new GameSessionExpired("Session: " + sessionId + " истекла"));
            if (isSessionExpired(session)) {
                throw new GameSessionExpired("Session: " + sessionId + " истекла");
            }
        }

        return session;


    }


    // Проверка не просрочена ли сессии.
    private static boolean isSessionExpired(Session session) {
        LocalDateTime expiresAt = session.getExpiresAt();
        LocalDateTime currentTime = LocalDateTime.now();

        return currentTime.isAfter(expiresAt);
    }
}
