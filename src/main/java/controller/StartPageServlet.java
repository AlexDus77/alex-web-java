package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**Это страртовая страница, где всего лишь две кнопки выбора действий:
 * <p>
 * Login - перенаправляет на {@link LoginPageServlet}
 * <p>
 *  Sign - перенаправляет на {@link SignInServlet}
 */


@WebServlet("/hello")
public class StartPageServlet extends HttpServlet {
    String form = "" +
            "" +
            "<html>" +
            "<body>" +
            "<form method=\"POST\" action=\"/login\">\n" +
            "  <input type=\"submit\" value=\"Login\">\n" +
            "</form>"+
            "<form action=\"/signIn\">\n" +
            "  <input type=\"submit\" value=\"Sign in\">\n" +
            "</form>"+
            "</body>" +
            "</html>";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        PrintWriter ps = resp.getWriter();
        ps.println(form);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
    }
}
