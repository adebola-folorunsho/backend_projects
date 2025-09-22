package user.exceptions;

public class NonExistentUsernameException extends RuntimeException {
    public NonExistentUsernameException(String message) {
        super(String.format("Username %s does not exist", message));
    }
}
