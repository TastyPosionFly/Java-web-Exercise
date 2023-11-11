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
    <%
    // 检查session中是否存在username属性
    String username = (String) session.getAttribute("username");

    if(username == null) {
        // 如果username为null，说明用户没有通过登录页面登录，执行相应的操作
        response.sendRedirect("/Exercise_war_exploded/Login.html");
    }
    %>
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
    <div style="position: fixed; top: 0; right: 0; border: 1px solid black; padding: 10px;">

        <h1>欢迎消息</h1>
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
        <h1>在线人员</h1>
        <%
            List<UserMessagesData> userMessagesDatas = (List<UserMessagesData>) application.getAttribute("userDatas");
            if (userMessagesDatas != null){
                for (UserMessagesData userMessagesData:userMessagesDatas){
                    String user = userMessagesData.getUserName();
        %>
        <p><%=user%></p>
        <%
                }
            }
        %>
    </div>
</body>
<%--向后台不断发送GET数据，检查是否以及离开了当前页面--%>
<script>
    setInterval(function() {
        // 发起 AJAX 请求，调用后台的某个接口
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "/Exercise_war_exploded/MessageInput", true);
        xhr.send();
    }, 5000);  // 每隔5秒发起一次请求，单位是毫秒
</script>
</html>