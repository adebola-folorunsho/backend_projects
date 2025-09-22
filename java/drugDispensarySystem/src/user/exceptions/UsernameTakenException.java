package user.exceptions;

public class UsernameTakenException extends RuntimeException {
    public UsernameTakenException(String message) {
        super(String.format("Username %s is already taken", message));
    }
}
