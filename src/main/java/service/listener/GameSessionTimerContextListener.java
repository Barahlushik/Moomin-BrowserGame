package service.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import service.SessionService;
import java.util.Calendar;
import java.util.Date;


public class GameSessionTimerContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
            SessionService sessionService = SessionService.getInstance();
            Calendar calendar = Calendar.getInstance();
            Date time = calendar.getTime();
            long interval = 1000 * 60;
            sessionService.scheduleGameSessionDeletion(time, interval);
        }
    }


