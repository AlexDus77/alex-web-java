package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    String form = "" +
            "" +
            "<html>" +
            "<body>" +
            "<h1>Sign in page</h2>" +
            "<form method=\"POST\" action=\"/addUser\">\n" +
            "  <label for=\"fname\">Login:</label><br>\n" +
            "  <input type=\"text\" id=\"login\" name=\"login\" value=\"\"><br>\n" +
            "  <label for=\"lname\">Password:</label><br>\n" +
            "  <input type=\"text\" id=\"password\" name=\"password\" value=\"\"><br><br>\n" +
            "  <input type=\"submit\" value=\"Submit\">\n" +
            "</form>"+
            "</body>" +
            "</html>";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.println(form);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}

