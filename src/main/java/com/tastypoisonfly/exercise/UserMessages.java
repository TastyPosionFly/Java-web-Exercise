package com.tastypoisonfly.exercise;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserMessages", value = "/UserMessages.do")
public class UserMessages extends HttpServlet {
    private final List<String> messages = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String userMessages = request.getParameter("userMessages");

        if (username!=null && userMessages!=null){
            messages.add(username+"--"+userMessages);
        }

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        for (String meg:messages){
            out.println("<p>"+meg+"</p>");
        }
        out.println("<a href="+"http://localhost:8080/Exercise_war_exploded/UserMessages.html"+">"+"返回</a>");
        out.println("</body></html>");
    }
}