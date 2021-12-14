package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/names")
public class SignInAcceptServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("fname");
        String role = req.getParameter("lname");
        PrintWriter pw = resp.getWriter();
/*        pw.println("Registration confirmed!");
        pw.println("Your login: " + firstaName);
        pw.println("Your role: " +secondName);
        pw.println("" +
                "");*/
        String form = "" +
                "" +
                "<html>" +
                "<body>" +
                "<h1>" + "Registration confirmed!" + "</h1>" +
                "<h2>" + "Your login: " + login + "</h2>" +
                "<h2>" + "Your role: " + role + "</h2>" +
                "<form action=\"/hello\">\n" +
                "  <input type=\"submit\" value=\"Start page\">\n" +
                "</form>" +
                "</body>" +
                "</html>";
        pw.println(form);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

