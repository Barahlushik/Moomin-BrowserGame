package exception;

import jakarta.servlet.ServletException;

public class CookieNotFoundException extends ServletException {

    public CookieNotFoundException(String message) {
        super(message);
    }
}
