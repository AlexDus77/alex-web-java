package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Сервлет изменения пользователя. В зависимости от принятыз параметров производится действие и КУДАТО ПЕРЕХОДИМ
 */

@WebServlet("/changeUserParam")
public class ChangeUserParamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldLogin = req.getParameter("oldLogin");
        String changeLogin = "<html>\n" +
                "<body>\n" +
                "<form method=\"POST\" action=\"/changeLogin\">\n" +
                "  <label for=\"login\">New login:</label><br>\n" +
                "  <input type=\"text\" id=\"newLogin\" name=\"newLogin\" value=\"\"><br>\n" +
                "  <input type=\"hidden\" id=\"oldLogin\" name=\"oldLogin\" value=" + oldLogin + "><br>\n" +
                "</body>\n" +
                "</html>";

        String changePassword = "<html>\n" +
                "<body>\n" +
                "<form method=\"POST\" action=\"/changePassword\">\n" +
                "  <label for=\"login\">New password:</label><br>\n" +
                "  <input type=\"text\" id=\"newPassword\" name=\"newPassword\" value=\"\"><br>\n" +
                "  <input type=\"hidden\" id=\"login\" name=\"oldPassword\" value=" + oldLogin + "><br>\n" +
                "</body>\n" +
                "</html>";

        String changeRole = "<html>\n" +
                "<body>\n" +
                "<form method=\"POST\" action=\"/changeRole\">\n" +
                "  <label for=\"login\">New role:</label><br>\n" +
                "  <input type=\"text\" id=\"newRole\" name=\"newRole\" value=\"\"><br>\n" +
                "  <input type=\"hidden\" id=\"login\" name=\"login\" value=" + oldLogin + "><br>\n" +
                "</body>\n" +
                "</html>";


        PrintWriter pw = resp.getWriter();
        //pw.println(oldLogin);
        String action = req.getParameter("action");
        if (action.equals("Change login")) {
            pw.println(changeLogin);
        } else if (action.equals("Change password")) {
            pw.println(changePassword);
        } else if (action.equals("Change role")) {
            pw.println(changeRole);
        }
    }
}

