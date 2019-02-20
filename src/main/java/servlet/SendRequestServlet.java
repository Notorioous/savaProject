package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;


@WebServlet(urlPatterns = "/user/sendFriendRequest")
public class SendRequestServlet extends HttpServlet {

    private UserManager userManager = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");

        String to_id = req.getParameter("to_id");
        int inTo = Integer.parseInt(to_id);

        userManager.sendRequest(user.getId(),inTo);

        resp.sendRedirect("/user/toHome");



    }
}
