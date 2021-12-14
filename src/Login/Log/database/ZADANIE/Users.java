package database.ZADANIE;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Users {
    private int iduser;
    private String name;
    private String login;
    private String password;
    private String role;
    List<Users> users = new ArrayList<>();

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public Users(){}

    public Users(int iduser, String name, String login, String password, String role) {
        this.iduser = iduser;
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Users{" +
                "iduser=" + iduser +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", Password=" + password +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return iduser == users.iduser &&
                password == users.password &&
                Objects.equals(name, users.name) &&
                Objects.equals(login, users.login) &&
                Objects.equals(role, users.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iduser, name, login, password, role);
    }
}