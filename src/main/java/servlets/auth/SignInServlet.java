package servlets.auth;

import dao.impl.MoominDao;
import dao.model.MoominSon;
import dao.model.Session;
import exception.UserNotFoundException;
import exception.WrongPasswordException;
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

public class SignInServlet extends MoominBaseServlet {

    private final MoominDao userDao = MoominDao.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(JspHelper.getPath("sign-in"));
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String actualPass = req.getParameter("password");

        if (login == null || login.isBlank()) {
            throw new InvalidParameterException("Неправильно указан логин! Допускается логин, содержащий букву(ы) и цифру (ы).");
        }

        if (actualPass == null || actualPass.isBlank()) {
            throw new InvalidParameterException("Неправильно указан пароль! Допускается пароль, содержащий букву(ы) и цифру (ы).");
        }

        MoominSon user = userDao.findByLogin(login)
                .orElseThrow(() -> new UserNotFoundException("Сын-тролль с логином - " + login + " не найден!"));

        String expectedPass = user.getPassword();

        if (!expectedPass.equals(actualPass)) {
            throw new WrongPasswordException("Неправильно указан пароль от Муми-Аккаунта!");
        }


        Session session = new Session(UUID.randomUUID(), user.getId(), LocalDateTime.now().plusHours(24));
        userSessionDao.save(session);


        Cookie cookie = new Cookie("sessionId", session.getId().toString());
        resp.addCookie(cookie);

        System.out.println(req.getContextPath());
        resp.sendRedirect("index.jsp");
    }


}
