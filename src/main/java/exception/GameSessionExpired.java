package exception;

import jakarta.servlet.ServletException;

public class GameSessionExpired extends ServletException {

    public GameSessionExpired(String message) {
        super(message);
    }
}
