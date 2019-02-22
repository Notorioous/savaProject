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
import java.util.Date;

@WebServlet(urlPatterns = "/user/sendMessage")
public class SendMessageServlet extends HttpServlet {

    private UserManager userManager = new UserManager();
    private MessageManager messageManager = new MessageManager();

    @Override
    protected void service (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String toIdStr = req.getParameter("toId");
        int toId = Integer.parseInt(toIdStr);

        User userById = userManager.getUserById(toId);

        User user = (User) req.getSession().getAttribute("user");

        String text = req.getParameter("text");

        Message message = new Message();
        message.setFromId(user);
        message.setToId(userById);
        message.setText(text);
        message.setDate(new Date());
        messageManager.addMessage(message);

//        resp.sendRedirect("/user/getFriend");

        req.getRequestDispatcher("/user/getFriend").forward(req,resp);


    }
}
