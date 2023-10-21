<%@ page import="com.tastypoisonfly.exercise.Data.QuestionsData" %><%--
  Created by IntelliJ IDEA.
  User: w1300
  Date: 2023/10/21
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>问卷详情</title>
</head>
<body>
    <h1>详细信息</h1>
    <%
        QuestionsData questionsData = (QuestionsData) request.getAttribute("details");
        if (questionsData != null){
    %>
    <p>姓名: <%= questionsData.getName() %></p>
    <p>年龄: <%= questionsData.getAge() %></p>
    <p>性别: <%= questionsData.getSex() %></p>
    <p>兴趣爱好: <%= questionsData.getHobbies() %></p>
    <%
        } else {
    %>
    <p>没有找到详细信息。</p>
    <% } %>
    <a href="Questions.html">返回上一页</a>
</body>
</html>
