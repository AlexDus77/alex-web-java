package database.ZADANIE;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DaoImpl implements database.ZADANIE.DAO {

    /***jdbc select example
     * jdbc preparedstatement example
     * https://www.geeksforgeeks.org/how-to-use-preparedstatement-in-java/***/


    Connection connection;
    private final String url = "jdbc:mysql://127.0.0.1:3306/daniel";
    private final String user = "root";
    private final String password = "root";
    Scanner scanner = new Scanner(System.in);
    database.ZADANIE.Users usery;


    @Override
    public boolean connect() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to daniel succesfull!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean disconnect() {
        try {
            connection.close();
            System.out.println("Connection close");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean signIn() {
        System.out.println("Введите логин");
        String login = scanner.nextLine();
        System.out.println("Введите пароль");
        String password = scanner.nextLine();

        Statement statement = null;
        Statement statement1 = null;
        try {
            statement = connection.createStatement();
            statement1 = connection.createStatement();



            ResultSet resultSet = statement.executeQuery("select * from users  where login = '" + login + "' and " +
                    "password = '" + password + "'");
            ResultSet resultSet1 = statement1.executeQuery("select * from users  where login = '" + login + "' and " +
                    "password = '" + password + "' and role = 'admin'");

            if (resultSet1.next()){
                System.out.println("Админ тут");
                isContain("name", "login", "password");
            } else if (resultSet.next()) {
                System.out.println("Добро пожаловать");

            } else {
                System.out.println("Данные введены неправильно");
                System.out.println("Повторите ввод");

                return signIn();
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.toString());
        }
        return true;

    }


    public void signUp() {
        System.out.println("Введите данные для регистрации.");
        System.out.println("Введите свое имя - ");
        String name = scanner.nextLine();
        System.out.println("Введите новый логин - ");
        String login = scanner.nextLine();
        System.out.println("Введите новый пароль - ");
        String password = scanner.nextLine();

        String sql = "INSERT INTO users (name, login, password) Values (?, ?, ?)";
        PreparedStatement preparedStatement = null;
        //PreparedStatement proverka = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, password);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List isContain(String field, String field1, String field2) {

        List fieldElements = new ArrayList();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select " + field + ", " + field1 + ", " + field2 +" from users");
            while (rs.next()) {//fieldElements.add(field);
                fieldElements.add(rs.getString(1));
                fieldElements.add(rs.getString(2));
                fieldElements.add(rs.getString(3));
            }System.out.println(fieldElements);
            return fieldElements;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(fieldElements);
        return fieldElements;
    }
    /**public Users searchus(String field, String field1, String field2) throws SQLException {
     Statement statement = null;
     statement = connection.createStatement();
     ResultSet resultSet = statement.executeQuery("select " + field + ", " + field1 + ", " + field2 +" from users");
     while (resultSet.next()) {
     usery.setName(field);

     }

     }**/

}