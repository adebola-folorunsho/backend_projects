package user.dtos.requests;

public class UserInputValidator {

    private static final String NAME_PATTERN = "^[a-zA-Z\\s\\-']+$";
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9._]+$";

    public static void validate(RegisterUserDto registerUserDto) {
        validateName(registerUserDto.getName());
        validatePassword(registerUserDto.getRawPassword());
        validateUserName(registerUserDto.getUserName());
    }

    public static void validateString(String string) {
        validateName(string);
    }

    private static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null, empty, or blank.");
        }

        if (!name.matches(NAME_PATTERN)) {
            throw new IllegalArgumentException("Name can only contain letters, spaces, hyphens, and apostrophes.");
        }
    }

    private static void validateUserName(String userName) {
        if (userName == null || userName.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null, empty, or blank.");
        }

        if (!userName.matches(USERNAME_PATTERN)) {
            throw new IllegalArgumentException("Username can only contain letters, numbers, underscores, and periods.");
        }
    }

    private static void validatePassword(String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be left blank.");
        }
    }

}
