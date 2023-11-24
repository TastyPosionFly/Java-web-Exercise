package com.tastypoisonfly.exercise;

import com.tastypoisonfly.exercise.Data.HobbiesData;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.naming.Context;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MVCDemo", value = "/MVCDemo")
public class MVCDemo extends HttpServlet {
    private List<HobbiesData> hobbiesDatas = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        String address = request.getParameter("address");
        String hobby = request.getParameter("hobby");
        ServletContext context = request.getServletContext();

        if (username != null && sex != null && age != null && address != null && hobby !=null){
            HobbiesData hobbiesData = new HobbiesData(username,sex,age,address,hobby);
            hobbiesDatas.add(hobbiesData);
            context.setAttribute("userHobbyData",hobbiesDatas);
            request.getRequestDispatcher("./jsp/ShowUserHobbies.jsp").forward(request, response);
        }

    }
}