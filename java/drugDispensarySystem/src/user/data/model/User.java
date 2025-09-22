package user.data.model;

public class User {
    private String name;
    private String userName;
    private String hashPassword;
    private String id;

    public User() {
    }

    public User(String name, String userName, String hashPassword, String id) {
        this.name = name;
        this.userName = userName;
        this.hashPassword = hashPassword;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHashPassword(String password) {
        this.hashPassword = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getId() {
        return id;
    }
}
