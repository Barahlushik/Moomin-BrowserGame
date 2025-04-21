package service;

import dao.SessionDao;
import dao.impl.GameSessionDao;
import dao.impl.UserSessionDao;
import dao.model.Session;
import java.time.LocalDateTime;
import java.util.*;


public class SessionService {

    private final SessionDao<Session> userSessionDao = UserSessionDao.getInstance();
    private final SessionDao<Session> gameSessionDao = GameSessionDao.getInstance();

    private static final SessionService INSTANCE = new SessionService();


    public void scheduleSessionDeletion(Date time, long interval) {
        SessionDeletionTask sessionDeletionTask = new SessionDeletionTask();
        Timer timer = new Timer();
        timer.schedule(sessionDeletionTask, time, interval);
    }
    public void scheduleGameSessionDeletion(Date time, long interval) {
        class GameSessionDeletionTask extends TimerTask{
            @Override
            public void run() {
                gameSessionDao.deleteSessionExpiredAtTime(LocalDateTime.now());
            }
        }
        GameSessionDeletionTask sessionDeletionTask = new GameSessionDeletionTask();
        Timer timer = new Timer();
        timer.schedule(sessionDeletionTask, time, interval);
    }

    private class SessionDeletionTask extends TimerTask {
        @Override
        public void run() {
            userSessionDao.deleteSessionExpiredAtTime(LocalDateTime.now());
        }
    }



    public static SessionService getInstance() {
        return INSTANCE;
    }


}
