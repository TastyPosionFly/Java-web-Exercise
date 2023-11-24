package com.tastypoisonfly.exercise;

import Service.Messages;
import Service.Users;
import com.sun.net.httpserver.HttpContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "SendMessages", value = "/SendMessages")
public class SendMessages extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        Users users = new Users();
        Messages messages = new Messages();
        String title = request.getParameter("title");
        String text = request.getParameter("text");
        String username = (String) session.getAttribute("username");
        int userID = users.getUserID(username);

        //正常输入的业务逻辑
        if (!title.isEmpty() && !text.isEmpty()){
            //检测是否成功录入数据库
            if (messages.newMessage(userID,title,text)){
                List<String> allTitle = messages.getAllTitle();
                context.setAttribute("allTitle",allTitle);
                request.getRequestDispatcher("./jsp/MessageBoard.jsp").forward(request, response);
            }
            else {
                showAlert(response,"数据库错误");
            }
        }
        else {
            showAlert(response,"输入框不能为空");
        }
    }

    //错误显示的通用代码
    private void showAlert(HttpServletResponse response,String errorName) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>警告</title></head><body>");
        out.println("<script type=\"text/javascript\">");
        out.println("alert('"+errorName+"');");
        out.println("window.location.reload();");
        out.println("</script>");
        out.println("</body></html>");
    }
}