package dao.model;

import java.time.LocalDateTime;
import java.util.UUID;


public class Session {


    private UUID id;


    private Long userId;


    private LocalDateTime expiresAt;

    public Session(UUID id, Long userId, LocalDateTime expiresAt) {
        this.id = id;
        this.userId = userId;
        this.expiresAt = expiresAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
}

