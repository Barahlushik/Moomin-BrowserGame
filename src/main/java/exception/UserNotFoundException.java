package exception;

import jakarta.servlet.ServletException;

public class UserNotFoundException extends ServletException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
