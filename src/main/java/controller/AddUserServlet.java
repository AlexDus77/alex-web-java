package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        PrintWriter pw = resp.getWriter();
        try {
            if (ChecksUsers.addUser(login, password)) {
                ServletContext servletContext = getServletContext();
                RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/login");
                requestDispatcher.forward(req, resp);
            } else {
                String userExist = "" +
                        "" +
                        "<html>" +
                        "<body>" +
                        "<h3>" + "User name is exist!" + "</h3>" +
                        "<form method=\"GET\" action=\"/signIn\">\n" +
                        "  <input type=\"submit\" value=\"Registration\">\n" +
                        "</body>" +
                        "</html>";
                pw.println(userExist);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
