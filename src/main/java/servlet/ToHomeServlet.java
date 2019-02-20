package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet(urlPatterns = "/user/toHome")
public class ToHomeServlet extends HttpServlet {

    private UserManager userManager = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");

        List<User> users = userManager.getAllUsers();

        Set<User> requestUsers = userManager.getFriendRequestUser(user.getId());
        Set<User> friends = userManager.getAllFriends(user.getId());

        req.setAttribute("users",users);
        req.setAttribute("requestUsers",requestUsers);
        req.setAttribute("friends",friends);
        req.getRequestDispatcher("/WEB-INF/user/home.jsp").forward(req,resp);


    }
}
