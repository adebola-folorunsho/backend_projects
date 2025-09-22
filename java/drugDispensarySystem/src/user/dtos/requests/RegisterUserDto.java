package user.dtos.requests;

public class RegisterUserDto {

    private String name;
    private String userName;
    private String rawPassword;

    public RegisterUserDto(String name, String userName, String rawPassword) {
        this.name = name;
        this.userName = userName;
        this.rawPassword = rawPassword;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRawPassword(String password) {
        this.rawPassword = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public String getUserName() {
        return userName;
    }

}

