package com.tastypoisonfly.exercise;

import com.sun.net.httpserver.HttpContext;
import com.tastypoisonfly.exercise.Data.AllUser;
import com.tastypoisonfly.exercise.Data.UserMessagesData;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        /*
        System.out.println(username);
        System.out.println(message);
        System.out.println(userIndex.get(username));
        System.out.println(userIndex.get(receiver));
        */
        if (username == null){
            request.getRequestDispatcher("./Loin.html");//会话超时处理
        }
        //输入消息不为空时进行处理
        if (message != null){
            if ("全体".equals(receiver)){
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
}