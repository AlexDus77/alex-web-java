package database.ZADANIE;

import java.sql.Connection;
import java.util.Scanner;

public class Programm {
    database.ZADANIE.DaoImpl dao = new database.ZADANIE.DaoImpl();
    Scanner scanner = new Scanner(System.in);

    Connection connection;
    private final String url = "jdbc:mysql://127.0.0.1:3306/daniel";
    private final String user = "root";
    private final String password = "ALFA77";
    public void start() {
        dao.connect();
        System.out.println("Здравствуйте");
        System.out.println("Для входа нажмите - 1");
        System.out.println("Для регистрации нажмите - 2");
        int d = scanner.nextInt();
        if (d == 1) {
            dao.signIn();
        } else if (d == 2) {
            dao.signUp();
            dao.signIn();
        }
        dao.disconnect();
    }
}
