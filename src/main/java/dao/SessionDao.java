package dao;

import dao.model.Session;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

// Интерфейс параметризован для работы с различными видами сессий.
public interface SessionDao<T> {
    void deleteSessionExpiredAtTime(LocalDateTime currentTime);

    Optional<T> findById(UUID id);

    void save (T session);


}
