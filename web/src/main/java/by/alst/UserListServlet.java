package by.alst;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/userList")
public class UserListServlet extends HttpServlet {

    private final UserService userService = new UserService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<UserDto> list = userService.listUserDto();
        resp.setContentType("text/html");
        var writer = resp.getWriter();
        writer.write("<html><body>");
        writer.write("<h1> Список пользователей: </h1>");
        if (!list.isEmpty()) {
            list.forEach(u -> writer.println("<h3>" + u + " </h3>"));
        } else {
            writer.println("<h3> Пустой список! </h3>");
        }
        writer.println("<br/>");
        writer.println("<br/>");

        writer.println("<h3>Вернуться на страницу выбора</h3>");
        writer.println("<h3><a href=\"index.jsp\">Back</a></h3>");
        writer.write("</body></html>");
        writer.close();
    }
}
