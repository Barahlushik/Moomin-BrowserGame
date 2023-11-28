package exception;

import jakarta.servlet.ServletException;

public class WrongPasswordException extends ServletException {
    public WrongPasswordException(String message) {
        super(message);
    }
}
