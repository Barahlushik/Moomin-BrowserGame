package servlets.auth;

import dao.impl.MoominDao;
import dao.model.MoominSon;
import dao.model.Session;
import exception.UserExistsException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlets.MoominBaseServlet;
import util.JspHelper;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.UUID;

public class SignUpServlet  extends MoominBaseServlet {
    private final MoominDao userDao = MoominDao.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(JspHelper.getPath("sign-up"));
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login == null || login.isBlank()) {
            throw new InvalidParameterException("Неправильно указан логин! Допускается логин, содержащий букву(ы) и цифру (ы).");
        }

        if (userDao.findByLogin(login).isPresent()) {
            throw new UserExistsException("Такой Муми-сын уже существует!");
        }

        if (password == null || password.isBlank()) {
            throw new InvalidParameterException("Неправильно указан пароль! Допускается пароль, содержащий букву(ы) и цифру (ы).");
        }

        MoominSon son = new MoominSon(login,password);

        userDao.save(son);

        Session session = new Session(UUID.randomUUID(), son.getId(), LocalDateTime.now().plusHours(24));
        userSessionDao.save(session);

        Cookie cookie = new Cookie("sessionId", session.getId().toString());
        resp.addCookie(cookie);
        resp.sendRedirect("index.jsp");

    }

}
