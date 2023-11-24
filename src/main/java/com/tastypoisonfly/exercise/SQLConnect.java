package com.tastypoisonfly.exercise;

import Service.Messages;
import Service.Users;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SQLConnect", value = "/SQLConnect")
public class SQLConnect extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String newUsername = request.getParameter("newUsername");
        String newPassword = request.getParameter("newPassword");
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        List<String> allUsername;
        Users users = new Users();
        Messages messages = new Messages();

        //检测是否没有输入任何用户名或密码就直接登入
        if ((!username.isEmpty() && !password.isEmpty()) || (!newUsername.isEmpty() && !newPassword.isEmpty())){
            //用户登入的逻辑判断
            if(!username.isEmpty() && !password.isEmpty()){
                //获取对应用户列表
                allUsername = users.getAllUsernames();
                if(allUsername.contains(username)){
                    //获取对应用户的密码
                    String userPassword = users.getUserPassword(username);
                    if (password.equals(userPassword)) {
                        //设置session中的用户名
                        session.setAttribute("username", username);
                        //读取最新的数据库数据
                        List<String> allTitle = messages.getAllTitle();
                        context.setAttribute("allTitle",allTitle);
                        request.getRequestDispatcher("./jsp/MessageBoard.jsp").forward(request, response);
                    }
                    //密码不正确的处理
                    else {
                        showAlert(response,"密码或用户名错误，重新输入","SqlSeverConnection.html");
                    }
                }
                //未注册用户
                else {
                    showAlert(response,"未注册用户，请注册","SqlSeverConnection.html");
                }
            }
            //注册用户的处理
            if (!newUsername.isEmpty() && !newPassword.isEmpty()){
                users.newUser(newUsername,newPassword);
                session.setAttribute("username",newUsername);
                //读取最新的数据库数据
                List<String> allTitle = messages.getAllTitle();
                context.setAttribute("allTitle",allTitle);
                request.getRequestDispatcher("./jsp/MessageBoard.jsp").forward(request, response);
            }
        }
        else {
            showAlert(response,"用户名和密码不能为空，请重新输入！","SqlSeverConnection.html");
        }
    }

    //错误显示的通用代码
    private void showAlert(HttpServletResponse response,String errorName,String website) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>警告</title></head><body>");
        out.println("<script type=\"text/javascript\">");
        out.println("alert('"+errorName+"');");
        out.println("window.location.href='/Exercise_war_exploded/"+website+"';");
        out.println("</script>");
        out.println("</body></html>");
    }
}