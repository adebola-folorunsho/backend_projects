package user.exceptions;

public class IncorrectPasswordException extends LoginException {
    public IncorrectPasswordException(String message) {
        super(String.format("Incorrect password", message));
    }
}
