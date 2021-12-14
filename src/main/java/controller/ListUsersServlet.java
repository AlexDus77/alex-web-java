package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Сервлет выводит список пользоваелей. Клик на имя пользователя отправляет на СТРАНИЦУ РЕДАКТИРОВАНИЯ ПОЛЬЗОВАТЕЛЯ.
 */

@WebServlet("/listUsers")
public class ListUsersServlet extends HttpServlet {
    Connection connection = null;
    String start =
            "<html>" +
                    "<h4></h4>\n" +
                    "<table class=\"editorDemoTable\" style=\"vertical-align: top; width: 389px;vertical-align: top; border-style: solid;\" border=\"1px\"\">\n" +
                    "    <thead>\n" +
                    "    <tr>\n" +
                    "        <td style=\"width: 140px;\">user</td>\n" +
                    "        <td style=\"width: 119.672px;\">password</td>\n" +
                    "        <td style=\"width: 128.328px;\">role</td>\n" +
                    "    </tr>\n" +
                    "    </thead>\n" +
                    "    <tbody>";
    String finish = "    </tr>\n" +
            "<form method=\"POST\" action=\"/userPage\">\n" +
            "<input type=\"submit\" name=\"action\" value=\"Go to user page\">\n" +
            "    </tbody>\n" +
            "</table>\n" +
            "</html>";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/test", "root", "admin");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            pw.println(start);
            while (resultSet.next()) {
                pw.println("    <tr>\n" +
                        "        <td style=\"min-width: 140px; width: 140px;\"><a href=\"/usersChange?login="+resultSet.getString("userName") +"\">" + resultSet.getString("userName") + "</a></td>\n" +
                        "        <td style=\"width: 119.672px;\">" + resultSet.getString("password") + "</td>\n" +
                        "        <td style=\"width: 128.328px;\">" + resultSet.getString("role") + "</td>\n" +
                        "    </tr>");
            }
            pw.println(finish);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}