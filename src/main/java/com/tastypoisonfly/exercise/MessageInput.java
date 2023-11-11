package com.tastypoisonfly.exercise;

import com.sun.net.httpserver.HttpContext;
import com.tastypoisonfly.exercise.Data.AllUser;
import com.tastypoisonfly.exercise.Data.UserMessagesData;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "MessageInput", value = "/MessageInput")
public class MessageInput extends HttpServlet {
    private AllUser allUserMessage;
    List<UserMessagesData> userMessagesDataList;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        allUserMessage = new AllUser();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //刷新最新的时间
        refreshChat(request,response);
        // 删除已经超时的用户
        removeExpiredUsers();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");


        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();

        String username = (String) session.getAttribute("username");
        String receiver = request.getParameter("sendPerson");
        String message = request.getParameter("userMessage");
        Map<String,Integer> userIndex = (Map<String, Integer>) context.getAttribute("userIndex");
        userMessagesDataList = (List<UserMessagesData>) context.getAttribute("userDatas");

//        System.out.println(username);
//        System.out.println(message);
//        System.out.println(receiver);
//        System.out.println(userIndex.get(username));
//        System.out.println(userIndex.get(receiver));

        if (username == null){
            request.getRequestDispatcher("./Loin.html").forward(request, response);//会话超时处理
        }
        //输入消息不为空时进行处理
        if (message != null){
            if (receiver.equals("allUser")){
                allUserMessage.sendAllUser(username,message);
                System.out.println("Sending message to all users: " + message);
            }
            else {
                int index = userIndex.get(receiver);
                UserMessagesData userMessagesData = userMessagesDataList.get(index);
                userMessagesData.receiverMessage(username,message);
                System.out.println(receiver+message);
            }
        }
        context.setAttribute("allUserMessage",allUserMessage);
        context.setAttribute("userData",userMessagesDataList);

        request.getRequestDispatcher("./jsp/MessageInput.jsp").forward(request,response);
    }

    // 删除已经超时的用户
    private void removeExpiredUsers() {
        //获取当前时间
        long currentTime = System.currentTimeMillis();

        //获取服务器之间的通信数据
        List<UserMessagesData> userMessagesDatas = (List<UserMessagesData>) getServletContext().getAttribute("userDatas");
        Map<String,Integer> userIndex = (Map<String, Integer>) getServletContext().getAttribute("userIndex");
        Map<String,String> userSessionId = (Map<String, String>) getServletContext().getAttribute("userSessionID");
        List<String> welcomeAndLeaveMessages = (List<String>) getServletContext().getAttribute("welcomeMessage");

        //设置最长不刷新时间
        long sessionTimeout = 40*1000;

        if (userMessagesDatas != null) {
            // 遍历用户信息表，删除超时用户
            Iterator<Map.Entry<String, Integer>> iterator = userIndex.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String,Integer> entry = iterator.next();
                String username = entry.getKey();
                int index = entry.getValue();

                UserMessagesData userMessagesData = userMessagesDatas.get(index);
                long lastAccessedTime = userMessagesData.getLastAccessedTime();

                if (currentTime - lastAccessedTime > sessionTimeout) {
                    iterator.remove(); // 从 userIndex 中删除
                    userSessionId.remove(username); // 从 userSessionId 中删除

                    // 从 userMessagesDatas 中删除
                    userMessagesDatas.remove(index);

                    //离开消息更新
                    String leaveMessage = username + "已离开聊天室。";
                    welcomeAndLeaveMessages.add(leaveMessage);
                }
            }
            // 更新服务器间通信的数据
            getServletContext().setAttribute("userDatas", userMessagesDatas);
            getServletContext().setAttribute("userIndex", userIndex);
            getServletContext().setAttribute("userSessionID", userSessionId);
        }
    }

    // 处理刷新请求
    private void refreshChat(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        long currentTime = System.currentTimeMillis();
        long sessionTimeout = 30 * 1000; // 超时时间设置为30秒，以毫秒为单位

        //System.out.println(currentTime);

        if (username != null) {
            // 获取用户信息表和用户索引
            List<UserMessagesData> userMessagesDatas = (List<UserMessagesData>) getServletContext().getAttribute("userDatas");
            Map<String,Integer> userIndex = (Map<String, Integer>) getServletContext().getAttribute("userIndex");

            // 更新用户信息表中用户的lastAccessedTime
            if (userIndex.containsKey(username)) {
                int index = userIndex.get(username);
                UserMessagesData userMessagesData = userMessagesDatas.get(index);
                // 如果距离上次刷新时间超过了超时时间，进行更新
                if (currentTime - userMessagesData.getLastAccessedTime() > sessionTimeout) {
                    userMessagesData.setLastAccessedTime(currentTime); // 假设有一个方法用于更新lastAccessedTime
                }
            }

            // 更新服务器间通信的数据
            getServletContext().setAttribute("userDatas", userMessagesDatas);
        }
    }
}