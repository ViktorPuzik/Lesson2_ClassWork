package academy.prog;

import jakarta.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    static final String LOGIN = "admin";
    static final String PASS = "admin";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String age = request.getParameter("age");


        HttpSession session = null;
        if (age.equals("")) {
            session = request.getSession(true);
            session.setAttribute("age", Integer.toString(-1));
            response.sendRedirect("index.jsp");
        } else {
            if (Integer.parseInt(age) > 18) {
                session = request.getSession(true);
                session.setAttribute("age", age);
                if (LOGIN.equals(login) && PASS.equals(password)) {
                 //   session = request.getSession(true);
                    session.setAttribute("user_login", login);
                }
            } else {
                session = request.getSession(true);
                session.setAttribute("age", Integer.toString(0));
            }
            response.sendRedirect("index.jsp");
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String a = request.getParameter("a");
        HttpSession session = request.getSession(false);

        if ("exit".equals(a) && (session != null))
            session.removeAttribute("user_login");

        response.sendRedirect("index.jsp");
    }
}
