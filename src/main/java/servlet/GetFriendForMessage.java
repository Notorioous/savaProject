package servlet;

import manager.MessageManager;
import manager.UserManager;
import model.Message;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/user/getFriend")
public class GetFriendForMessage extends HttpServlet {

    private UserManager userManager = new UserManager();
    private MessageManager messageManager = new MessageManager();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String toIdStr = req.getParameter("to_id");
        int toId = Integer.parseInt(toIdStr);

        User userFriend = userManager.getUserById(toId);

        User user = (User) req.getSession().getAttribute("user");

      List<Message> messages = messageManager.getReceivedMessage(user.getId(),toId);


        req.setAttribute("userFriend",userFriend);
        req.setAttribute("messages",messages);
        req.getRequestDispatcher("/WEB-INF/user/messenger.jsp").forward(req,resp);



    }
}
