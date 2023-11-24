<%--
  Created by IntelliJ IDEA.
  User: w1300
  Date: 2023/11/24
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>留言板</title>
    <link rel="stylesheet" href="./css/MessageBoard.css">
</head>
<%--防止用户非法访问界面--%>
<script src="../js/Referrer.js"></script>
<body>
    <iframe src="./jsp/SQLShow.jsp" width="50%" height="30%"></iframe>
    <form name="SendMessage" action="./SendMessages" method="post">
        <h3>欢迎您，用户${sessionScope.username}</h3>
        <h3>标题</h3>
        <input type="text" name="title"><br>
        <h3>内容</h3>
        <input type="text" name="text"><br>
        <input type="submit" value="发送">
    </form>
</body>
</html>
