package com.tastypoisonfly.exercise;

import Service.Messages;
import com.tastypoisonfly.exercise.Data.MessageData;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ShowDetails", value = "/ShowDetails")
public class ShowDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        Messages messages = new Messages();

        MessageData messageData = messages.getDetailMessage(title);

        request.setAttribute("messageData",messageData);

        request.getRequestDispatcher("./jsp/Details.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}