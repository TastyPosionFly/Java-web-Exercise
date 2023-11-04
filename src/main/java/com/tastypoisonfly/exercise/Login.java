package com.tastypoisonfly.exercise;

import com.sun.net.httpserver.HttpContext;
import com.tastypoisonfly.exercise.Data.AllUser;
import com.tastypoisonfly.exercise.Data.UserMessagesData;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.*;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    private List<UserMessagesData> userDatas;//用于保存加入聊天室的用户昵称
    private Map<String,Integer> userIndex;
    private List<String> welcomeAndLeaveMessages;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDatas  =new ArrayList<>();
        userIndex = new HashMap<>();
        welcomeAndLeaveMessages = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        String leaveMessage = username + "已离开聊天室。";

        welcomeAndLeaveMessages.add(leaveMessage);

        if (userIndex.containsKey(username)){
            userIndex.remove(username);
            System.out.println("已删除键值对 " + username);
        } else {
            System.out.println("用户" + username +"不存在");
        }

        response.sendRedirect("/Exercise_war_exploded/Login.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String username = request.getParameter("userName");
       HttpSession session = request.getSession();
       ServletContext context = request.getServletContext();
       session.setAttribute("username",username);

       //欢迎消息的存储
       if (!userIndex.containsKey(username)) {
           String welcomeMessage = "欢迎" + username + "进入聊天室";
           welcomeAndLeaveMessages.add(welcomeMessage);
           context.setAttribute("welcomeMessage", welcomeAndLeaveMessages);
       }

       //用户消息的存储
       if (!userIndex.containsKey(username)){
           userDatas.add(new UserMessagesData(username));
           userIndex.put(username,userDatas.size()-1);

           context.setAttribute("userIndex",userIndex);
           session.setAttribute("userIndex",userIndex);
           context.setAttribute("userDatas",userDatas);

       }



       request.getRequestDispatcher("./jsp/Messages.jsp").forward(request,response);

    }
}