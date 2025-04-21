package servlets.game;

import dao.impl.MoominWinnerDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlets.MoominBaseServlet;
import util.JspHelper;

import java.io.IOException;

public class WinnersServlet extends MoominBaseServlet {

    private MoominWinnerDao winnerDao = MoominWinnerDao.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("winners",winnerDao.findAll());
        req.getRequestDispatcher(JspHelper.getPath("winners")).forward(req,resp);
    }



}
