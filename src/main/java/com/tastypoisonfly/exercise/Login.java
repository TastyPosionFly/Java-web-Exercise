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
    private Map<String,String> userSessionId;//用于保存对于索引用户的sessionId,第二个String保存的是用户名

    private List<String> welcomeAndLeaveMessages;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDatas  =new ArrayList<>();
        userIndex = new HashMap<>();
        userSessionId = new HashMap<>();
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
       String selectAnimal = request.getParameter("animalType");//用户选择的动物种类

       HttpSession session = request.getSession();
       ServletContext context = request.getServletContext();
       String sessionId = session.getId();//获取当前的sessionId
       String correctAnimalType = String.valueOf((Integer) session.getAttribute("animalType"));
       session.setAttribute("username",username);



       //如果验证码选择正确
       if (selectAnimal.equals(correctAnimalType)) {
           //更新用户最近刷新的时间
           updateUserLastAccessedTime(sessionId);
           //欢迎消息的存储
           if (!userIndex.containsKey(username)) {
               String welcomeMessage = "欢迎" + username + "进入聊天室";
               welcomeAndLeaveMessages.add(welcomeMessage);
               userSessionId.put(sessionId, username);
               context.setAttribute("welcomeMessage", welcomeAndLeaveMessages);
           }

           //用户消息的存储
           if (!userIndex.containsKey(username)) {
               userDatas.add(new UserMessagesData(username));
               userIndex.put(username, userDatas.size() - 1);

               context.setAttribute("userIndex", userIndex);
               session.setAttribute("userIndex", userIndex);
               context.setAttribute("userDatas", userDatas);
               context.setAttribute("userSessionID",userSessionId);
           }

           request.getRequestDispatcher("./jsp/Messages.jsp").forward(request, response);
       }
       else {
           session.setAttribute("correct","-1");
           response.sendRedirect("/Exercise_war_exploded/Login.html");
       }

    }

    // 更新用户信息表中的lastAccessedTime
    private void updateUserLastAccessedTime(String sessionId){
        // 获取当前时间
        long currentTime = System.currentTimeMillis();

        // 在 userSessionId 中查找对应的用户名
        String username = userSessionId.get(sessionId);

        // 更新用户信息表中的lastAccessedTime
        if (username != null && userIndex.containsKey(username)) {
            int index = userIndex.get(username);
            UserMessagesData userMessagesData = userDatas.get(index);
            userMessagesData.setLastAccessedTime(currentTime);
        }
    }
}