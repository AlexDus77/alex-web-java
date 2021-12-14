package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/usersChange")
public class ChangeUserServlet extends HttpServlet {

    String form = "<html>\n" +
            "<body>\n" +
            "<form method=\"POST\" name=\"filter\" action=\"/changeUserParam?login=132131\">\n" +
            "<input type=\"submit\" value=\"Action\">\n" +
            "</form>\n" +
            "</body>\n" +
            "</html>";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Users user = (Users) session.getAttribute("user");
        String login = req.getParameter("login");
        user.setUserForChange(login);
        session.setAttribute("user",user );
        String changeUserMenu = "" +
                "" +
                "<html>" +
                "<body>" +
                "<h3>" + "Change user: " + login + "</h3>" +
                "<form method=\"POST\" action=\"/changeUserParam\">\n" +
                "<input type=\"hidden\" name=\"oldLogin\" value=" + login + ">\n" +
                "<input type=\"submit\" name=\"action\" value=\"Change login\">\n" +
                "<form method=\"POST\" action=\"/changeUserParam\">\n" +
                "<input type=\"submit\" name=\"action\" value=\"Change password\">\n" +
                "<form method=\"POST\" action=\"/changeUserParam\">\n" +
                "<input type=\"submit\" name=\"action\" value=\"Change role\">\n" +
                "</body>" +
                "</html>";

        PrintWriter pw = resp.getWriter();
        pw.println(changeUserMenu);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
