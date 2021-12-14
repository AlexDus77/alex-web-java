package controller;

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
 * Страница с формой авторизации. При нажатии кнопки Login переходит на {@linkplain CheckLoginServlet}
 */

@WebServlet("/login")
public class LoginPageServlet extends HttpServlet {
    String form = "" +
            "" +
            "<html>" +
            "<body>" +
            "<h1>Login page</h2>" +
            "<form method=\"POST\" action=\"/checkLogin\">\n" +
            "  <label for=\"login\">Login:</label><br>\n" +
            "  <input type=\"text\" id=\"login\" name=\"login\" value=\"\"><br>\n" +
            "  <label for=\"password\">Password:</label><br>\n" +
            "  <input type=\"text\" id=\"password\" name=\"password\" value=\"\"><br><br>\n" +
            "  <input type=\"submit\" value=\"Login\">\n" +
            "</form>" +
            "</body>" +
            "</html>";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.println(form);
    }
}