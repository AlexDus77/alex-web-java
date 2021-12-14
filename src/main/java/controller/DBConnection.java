package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    public Connection getConn() {
        return conn;
    }

    private Connection conn;

    {
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/test", "root", "admin");
            Statement statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
