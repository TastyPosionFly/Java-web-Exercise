package com.tastypoisonfly.exercise;

import com.tastypoisonfly.exercise.Data.QuestionsData;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.*;

@WebServlet(name = "Questions", value = "/Questions.do")
public class Questions extends HttpServlet {
    private int count = 0;//用于存放每个人的索引
    private Map<String, Integer> interestCounts = new HashMap<>();
    private Map<String, Integer> genderCounts = new HashMap<>();//用于存放统计信息的两个数据
    private List<QuestionsData> data;//用于存放每个人的数据
    private List<String> names = new ArrayList<>();//保存参与的人员姓名

    @Override
    public void init() throws ServletException {
        data = new ArrayList<>();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String sex = request.getParameter("sex");
        String hobbies = request.getParameter("hobbies");

        count++;

        data.add(new QuestionsData(count,name,age,sex,hobbies));

        interestCounts.put(hobbies,interestCounts.getOrDefault(hobbies,0)+1);
        genderCounts.put(sex,genderCounts.getOrDefault(sex,0)+1);

        //设置超链接
        StringBuilder stringBuilder = new StringBuilder("<a href=DetailInformation?name=");
        stringBuilder.append(name);
        stringBuilder.append(">");
        stringBuilder.append(name);
        stringBuilder.append("</a>");
        names.add(stringBuilder.toString());

        //设置键值对
        request.setAttribute("questionDataLists",data);
        request.setAttribute("sexCounts",genderCounts);
        request.setAttribute("hobbiesCounts",interestCounts);
        request.setAttribute("nameCounts",names);

        getServletContext().setAttribute("questionDataLists",data);//在Servlet中通信使用

        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/Questions.jsp");
        dispatcher.forward(request,response);
    }
}