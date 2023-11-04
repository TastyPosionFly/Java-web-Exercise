<%@ page import="com.tastypoisonfly.exercise.Data.AllUser" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tastypoisonfly.exercise.Data.UserMessagesData" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: w1300
  Date: 2023/11/3
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="refresh" content="5">
    <title>Title</title>
</head>
<body>
    <h1>全体消息</h1>
    <%
        AllUser allUser = (AllUser) application.getAttribute("allUserMessage");
        if (allUser != null){
        List<String> chatRoamMessages = allUser.getChatRoamMessage();
        for (int i=0;i<chatRoamMessages.size();i++){
    %>
    <p><%=chatRoamMessages.get(i)%></p>
    <% }
    }%>

    <h1>私聊消息</h1>
    <%
        int index = -1;
        List<UserMessagesData> userMessagesDataList = (List<UserMessagesData>) application.getAttribute("userData");
        if (userMessagesDataList!=null){
            Map<String,Integer> userIndex= (Map<String, Integer>) session.getAttribute("userIndex");
            index = userIndex.get((String) session.getAttribute("username"));
            UserMessagesData userMessagesData = userMessagesDataList.get(index);
            List<String> privateMessages = userMessagesData.getPrivateMessages();
            for (String message:privateMessages){
    %>
    <p><%=message%></p>
    <%
            }
        }
    %>

    <%
        List<String> welcomeMessages = (List<String>) application.getAttribute("welcomeMessage");
        if (welcomeMessages != null){
            for (String welcomeMessage:welcomeMessages){
    %>
    <p><%=welcomeMessage%></p>
    <%
            }
        }
    %>
</body>
</html>