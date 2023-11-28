package service.listner;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import service.SessionService;

import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.*;

public class SessionSchedulerContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        SessionService sessionService = SessionService.getInstance();

        Calendar calendar = Calendar.getInstance();

        calendar.set(HOUR_OF_DAY, 0);
        calendar.set(MINUTE, 0);
        calendar.set(SECOND, 0);

        Date time = calendar.getTime();

        // каждые 24 часа происходит удаление сессии
        long interval = 1000 * 60 * 60 * 24;

        sessionService.scheduleSessionDeletion(time, interval);
    }
}