package exception;

import jakarta.servlet.ServletException;

public class UserExistsException extends ServletException {

    public UserExistsException(String message) {
        super(message);
    }
}
