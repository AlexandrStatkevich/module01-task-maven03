package by.alst;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/replaceUserNameById")
public class ReplaceUserNameByIdServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String resultReplacement;

        String idString = req.getParameter("id").trim();
        long id = 0;
        if (idString.matches("[0-9]+")) {
            id = Long.parseLong(idString);
        }

        String userName = req.getParameter("name").trim();

        if (id == 0) {
            resultReplacement = "Введите номер id для замены имени пользователя";
        } else if (userName.isEmpty()) {
            resultReplacement = "Введите новое имя пользователя для замены имени";
        } else {
            resultReplacement = userService.replaceUserNameById(id, userName);
        }

        resp.setContentType("text/html");
        var writer = resp.getWriter();
        writer.write("<html><body>");
        writer.write("<h1> Результат замены имени: </h1>");
        writer.write("<h2>" + resultReplacement + "<h2>");
        writer.println("<br/>");
        writer.println("<br/>");

        writer.println("<h3>Вернуться на страницу выбора</h3>");
        writer.println("<h3><a href=\"index.jsp\">Back</a></h3>");
        writer.write("</body></html>");
        writer.close();
    }
}
