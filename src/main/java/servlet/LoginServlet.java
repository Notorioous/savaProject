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

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private UserManager userManager = new UserManager();


    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");


        User user = userManager.getUserByEmailAndPassword(email,password);
//        List<User> users = userManager.getAllUsers();

        HttpSession session = req.getSession();
        session.setAttribute("user",user);


        if(user != null){
            resp.sendRedirect("/user/toHome");

        } else {
            req.setAttribute("message","Invalid email or password!");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }

    }
}
