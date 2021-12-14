package controller;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Сервлет, который проверяет введённые пароль и пользователя.
 * <p>
 * Создаётся экземпляр пользователя и сесисю
 * <p>
 * Сначала идёт проверка на пустоые поля,
 * если такие есть, то отправляем пользователя обратно на страницу авторизации.
 * <p>
 * Затем проверяем есть ли пользователь с таким логином методом {@link ChecksUsers#checkUsernameExist(ResultSet, String)}
 * <p>Затем проверяем на соответствие логина и пароля методом {@link ChecksUsers#checkPasswordForUser(ResultSet, String, String)},
 * который сразу нам возвращает роль этого пользователя.
 * <p>Данную роль присваеваем пользователю, сохраняем в сессию и форвардим на {@link UserPageServlet}
 */

@WebServlet("/checkLogin")
public class CheckLoginServlet extends HttpServlet {

    String toLogin = "" +
            "" +
            "<html>" +
            "<body>" +
            "<h3>" + "login or password incorrect" + "</h3>" +
            "<form method=\"POST\" action=\"/login\">\n" +
            "  <input type=\"submit\" value=\"Login\">\n" +

            "</body>" +
            "</html>";
    String userNotFound = "" +
            "" +
            "<html>" +
            "<body>" +
            "<h3>" + "User not found" + "</h3>" +
            "<form method=\"GET\" action=\"/signIn\">\n" +
            "  <input type=\"submit\" value=\"Registration\">\n" +
            "</body>" +
            "</html>";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            user = new Users();
        }
        ChecksUsers checksUsers = new ChecksUsers();
        PrintWriter pw = resp.getWriter();
        Connection connection = null;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String role = null;
        /*
          Сначала проверяем что поля логина и пароля не пустые,
          иначе возвращаем пользователя на странизу авторизации
         */

        if (login.isEmpty() || password.isEmpty()) {
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/login");//исли пусто - форвардим на страницу с логином. БЫЛОБ НЕ ПЛОХО ПИСАТЬ ЧТО НЕ ТАК
            requestDispatcher.forward(req, resp);
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:mysql://127.0.0.1:3306/test", "root", "admin");
                Statement statement = connection.createStatement();
                ResultSet resultSet;
                if (checksUsers.checkUsernameExist(login)) { //Проверяме есть ли такой юзер
                    //pw.println("user exist");
                    resultSet = statement.executeQuery("SELECT * FROM users");
                    role = checksUsers.checkPasswordForUser(resultSet, login, password);//смотрим его роль
                    // pw.println(role);
                    if (role != null) {
                        //pw.println("password is correct");// Если всё ОК, то надо как то отправить роль в след сервлет????????????????????????
                        user.setRole(role);
                        user.setLogin(login);
                        session.setAttribute("user", user);
                        ServletContext servletContext = getServletContext();
                        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/userPage");
                        requestDispatcher.forward(req,resp);
                    } else {
                        pw.println(toLogin);// Вывод в случае неверного логина или пароля
                    }
                } else {
                    pw.println(userNotFound); //Вывод если не найден пользователь и кнопка с преходом на страницу регистрции
                }

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
