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

@WebServlet("/changeLogin")
public class ChangeLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Users user = (Users )session.getAttribute("user");
        String role = user.getRole();
        ChecksUsers checksUsers = new ChecksUsers();
        String newLogin = req.getParameter("newLogin");
        String oldLogin = req.getParameter("oldLogin");
        try {
            checksUsers.changeLogin(oldLogin, newLogin);
            user.setLogin(newLogin);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        ServletContext servletContext = getServletContext();
        if (role.equals("admin")) {
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/listUsers");
            requestDispatcher.forward(req, resp);
        } else {
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/userPage");
            requestDispatcher.forward(req, resp);
        }
    }
}
