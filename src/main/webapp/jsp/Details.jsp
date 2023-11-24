<%--
  Created by IntelliJ IDEA.
  User: w1300
  Date: 2023/11/24
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>详细信息</title>
    <link rel="stylesheet" href="./css/Details.css">
</head>
<body>
    <h1>详细信息</h1>
    <h3>标题：</h3>
    <c:out value="${messageData.title}"/><br>
    <h3>作者：</h3>
    <c:out value="${messageData.author}"/><br>
    <h3>发布时间：</h3>
    <c:out value="${messageData.publishTime}"/><br>
    <h3>内容：</h3>
    <c:out value="${messageData.text}"/><br>

    <a href="./jsp/SQLShow.jsp">返回</a>
</body>
</html>
