package by.alst;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/userById")
public class UserByIdServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idString = req.getParameter("id").trim();
        resp.setContentType("text/html");
        var writer = resp.getWriter();
        writer.write("<html><body>");
        if (!idString.matches("[0-9]+")) {
            writer.write("<h1> Введите номер id для получения пользователя </h1>");
        } else {
            long id = Long.parseLong(idString);
            UserDto user = userService.getUser(id).isPresent()
                    ? userService.getUser(id).get() : new UserDto();
            if (!user.equals(new UserDto())) {
                writer.write("<h1> Пользователь: </h1>");
                writer.write("<h2>" + user + "<h2>");
            } else {
                writer.write("<h1> Пользователь: </h1>");
                writer.write("<h2>" + "Нет пользователя с id=" + id + "<h2>");
            }
        }
        writer.println("<br/>");
        writer.println("<br/>");

        writer.println("<h3>Вернуться на страницу выбора</h3>");
        writer.println("<h3><a href=\"index.jsp\">Back</a></h3>");
        writer.write("</body></html>");
        writer.close();
    }
}
