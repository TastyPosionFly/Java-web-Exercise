    package com.tastypoisonfly.exercise;

    import com.tastypoisonfly.exercise.Data.QuestionsData;
    import jakarta.servlet.*;
    import jakarta.servlet.http.*;
    import jakarta.servlet.annotation.*;

    import java.io.IOException;
    import java.util.List;

    @WebServlet(name = "DetailInformation", value = "/DetailInformation")
    public class DetailInformation extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String name = request.getParameter("name");

            List<QuestionsData> datas = (List<QuestionsData>)getServletContext().getAttribute("questionDataLists");
            QuestionsData questionsData = null;
            request.setCharacterEncoding("utf-8");//查询到正确的name，防止乱码
            for (QuestionsData data:datas){
                System.out.println("Checking data with name: " + data.getName()+name);
                if (data.getName().equals(name)){
                    questionsData = data;
                    break;
                }
            }

            request.setAttribute("details",questionsData);
            request.getRequestDispatcher("/jsp/DetailInformation.jsp").forward(request,response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        }
    }