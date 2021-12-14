package controller;

public class Users {
    private String login;
    private String password;
    private String role;
    private String userForChange;

    public String getUserForChange() {
        return userForChange;
    }

    public void setUserForChange(String userForChange) {
        this.userForChange = userForChange;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
