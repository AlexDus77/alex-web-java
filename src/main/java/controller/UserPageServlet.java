package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Здесь, в зависимости от роли пользователя, выводим разную инфу и действия. БУДЕТ ДОПОЛНЕНО
 */

@WebServlet("/userPage")
public class UserPageServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        HttpSession session = req.getSession();
        Users user = (Users) session.getAttribute("user");
        String login = user.getLogin();
        String role = user.getRole();
        String formAdmin = "" +
                "" +
                "<html>" +
                "<body>" +
                "<h2>Hi, " + login + "</h2>" +
                "<form method=\"POST\" action=\"/listUsers\">\n" +
                "  <input type=\"submit\" value=\"List users\">\n" +
                "</form>" +
                "</body>" +
                "</html>";

        String formNotAdmin =
                "<html>" +
                        "<body>" +
                        "<h2> Hi," + login + "! Your role is: " + role + "</h2>" +
                        "<form method=\"POST\" action=\"/changeUserParam\">\n" +
                        "<input type=\"hidden\" name=\"oldLogin\" value=" + login + ">\n" +
                        "<input type=\"submit\" name=\"action\" value=\"Change login\">\n" +
                        "<form method=\"POST\" action=\"/changeUserParam\">\n" +
                        "<input type=\"submit\" name=\"action\" value=\"Change password\">\n" +
                        "</body>" +

                        "</html>";
        if (user == null) {
            pw.println("user is null");
        } else if (role.equals("admin")) {
            pw.println(formAdmin);
        } else {
            pw.println(formNotAdmin);
        }

    }
}
