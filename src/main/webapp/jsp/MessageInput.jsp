<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.tastypoisonfly.exercise.Data.UserMessagesData" %><%--
  Created by IntelliJ IDEA.
  User: w1300
  Date: 2023/11/3
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
    <form name="MessageInput" target="hiddenFrame" action="/Exercise_war_exploded/MessageInput" method="post">
        <h1>欢迎你: <%=session.getAttribute("username")%> </h1>
        <label>选择发送的对象</label>
        <div id="userListsWrapper">
            <select name="sendPerson">
                <option name="全体">全体</option>
            </select>
        </div>
        <input type="text" name="userMessage" placeholder="需要发送的消息" autocomplete="">
        <input type="submit" placeholder="发送">
    </form>
    <iframe name="hiddenFrame" style="display: none;"></iframe>
    <script src="../js/UpdateUserLists.js"></script>
</body>

</html>
