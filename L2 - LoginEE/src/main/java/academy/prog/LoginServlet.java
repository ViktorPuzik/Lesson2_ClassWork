package academy.prog;

import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginServlet extends HttpServlet {
    static final String LOGIN = "admin";
    static final String PASS = "admin";
    static final String EMPTY = "empty";
    static final String ERROR = "error";
    static final String YOUNG = "young";
    static final String SHORT = "short";

    /*
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()â€"[{}]://;',?/*~$^+=<>]).{8,20}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public static boolean isValid(final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
*/
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String age = request.getParameter("age");
        boolean flag = true;

        HttpSession session = null;
        session = request.getSession(true);
        session.removeAttribute("user_login");
        session.removeAttribute("login");
        session.removeAttribute("age");
        session.removeAttribute("pass");

        if (age.equals("")) {
            session.setAttribute("age", EMPTY);
            flag = false;
        }

        if (!age.equals("")) {
            try {
                int k = Integer.parseInt(age);
            }
            catch ( NumberFormatException e){
                session.setAttribute("age", ERROR);
                flag = false;
            }
        }

        if (login.equals("")) {
            session.setAttribute("login", EMPTY);
            flag = false;
        }

        if (password.equals("")) {
            session.setAttribute("pass", EMPTY);
            flag = false;
        }

        if (flag){
            if (LOGIN.equals(login) && PASS.equals(password)) {
                if (Integer.parseInt(age) >= 18) {
                    session.setAttribute("user_login", login);
                    if (password.length() < 10){
                        session.setAttribute("pass", SHORT);
                    }
                } else {
                    session.setAttribute("age", YOUNG);
                }
           } else{
                if (!LOGIN.equals(login)){
                    session.setAttribute("login", ERROR);
                }
                if (!PASS.equals(password)){
                    session.setAttribute("pass", ERROR);
                }
                if (password.length() < 10){
                    session.setAttribute("pass", SHORT);
                }
            }
        }
        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String a = request.getParameter("a");
        HttpSession session = request.getSession(false);

        if ("exit".equals(a) && (session != null))
            session.removeAttribute("user_login");

        response.sendRedirect("index.jsp");

    }



}
