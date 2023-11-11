package com.tastypoisonfly.exercise;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "UpdateUserLists", value = "/UpdateUserLists")
public class UpdateUserLists extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取最新的用户列表数据
        ServletContext context = request.getServletContext();
        Map<String, Integer> userSet = (Map<String, Integer>) context.getAttribute("userIndex");

        // 构建JSON对象表示用户列表
        JsonObject userListJSON = new JsonObject();
        JsonArray userListArray = new JsonArray();

        if (userSet != null) {
            for (Map.Entry<String, Integer> entry : userSet.entrySet()) {
                String userName = entry.getKey();
                int userIndex = entry.getValue();

                // 创建包含用户名和索引的JSON对象
                JsonObject userObject = new JsonObject();
                userObject.addProperty("userName", userName);
                userObject.addProperty("userIndex", userIndex);

                // 将用户对象添加到数组
                userListArray.add(userObject);
            }
        }

        userListJSON.add("userLists",userListArray);

        // 返回用户列表HTML字符串
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(userListJSON.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}