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
import java.sql.SQLException;

@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ChecksUsers checksUsers = new ChecksUsers();
        HttpSession session = req.getSession();
        Users user = (Users )session.getAttribute("user");
        String login = user.getLogin();
        String newPassword = req.getParameter("newPassword");
        String role = user.getRole();
        try {
            checksUsers.changePassword(login, newPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ServletContext servletContext = getServletContext();
        if (role.equals("admin")) {
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/lestUser");
            requestDispatcher.forward(req, resp);
        } else {
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/userPage");
            requestDispatcher.forward(req, resp);
        }


    }
}
