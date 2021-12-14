package controller;

import java.io.PrintWriter;
import java.sql.*;

public class ChecksUsers {
    /**
     * Метод принимает ResulSet и логин. В случае если поьзователь найден, возвращает true
     */
    public static boolean checkUsernameExist(String login) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/test", "root", "admin");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        boolean flag = false;
        while (resultSet.next()) {
            if (resultSet.getString("userName").equalsIgnoreCase(login)) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * Метод принимает ResultSet, логин и пароль. Перебирает ResultSet и если находит соответствие логина и пароля - возвращает роль этого пользователя.
     * В противном случае возвращает null
     */
    public String checkPasswordForUser(ResultSet resultSet, String login, String password) throws SQLException {
        String role = null;
        while (resultSet.next()) {
            if (resultSet.getString("userName").equals(login) && resultSet.getString("password").equals(password)) {
                role = resultSet.getString("role");
            }
        }
        return role;
    }

    public void changeLogin(String oldLogin, String newLogin) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/test", "root", "admin");
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE users SET userName = '" + newLogin + "' WHERE (userName = '" + oldLogin + "')");
    }
    public void changePassword(String login, String newPassword) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/test", "root", "admin");
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE users SET password = '" + newPassword + "' WHERE (userName = '" + login + "')");
    }
    public void changeRole(String login, String newRole) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/test", "root", "admin");
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE users SET role = '" + newRole + "' WHERE (userName = '" + login + "')");
    }
    public static boolean addUser (String login, String password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/test", "root", "admin");
        if (!checkUsernameExist(login)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO users (userName, password, role ) VALUES ('" + login + "','" + password + "', 'user')");
            return true;
        } else {
            return false;
        }
    }

}
