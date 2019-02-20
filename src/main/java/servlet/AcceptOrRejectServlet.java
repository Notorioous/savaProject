package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/acceptOrReject")
public class AcceptOrRejectServlet extends HttpServlet {

    private UserManager userManager = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String from_id = req.getParameter("from_id");
        String action = req.getParameter("action");
        int fromId = Integer.parseInt(from_id);

       User user = (User) req.getSession().getAttribute("user");

       if(action.equals("Accept")){
           userManager.addToFriendList(fromId,user.getId());
           userManager.removeRequest(fromId,user.getId());
          resp.sendRedirect("/user/toHome");
       } else if(action.equals("Reject")){
           userManager.removeRequest(fromId,user.getId());
           resp.sendRedirect("/user/toHome");
       } else {
           resp.sendRedirect("login.jsp");
       }


    }
}
