package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user//deleteFriend")
public class DeleteFriendServlet extends HttpServlet {

    private UserManager userManager = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String from_id = req.getParameter("from_id");
        int fromId = Integer.parseInt(from_id);

        User user = (User) req.getSession().getAttribute("user");

        userManager.deleteFriend(user.getId(),fromId);

        resp.sendRedirect("/user/toHome");




    }
}
