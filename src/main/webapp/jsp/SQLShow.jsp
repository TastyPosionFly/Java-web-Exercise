<%--
  Created by IntelliJ IDEA.
  User: w1300
  Date: 2023/11/24
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="refresh" content="5">
    <link rel="stylesheet" href="../css/SQLshow.css">
</head>
<body>
    <h2>留言板</h2>
    <c:forEach var="title" items="${applicationScope.allTitle}">
        <a href="http://localhost:8080/Exercise_war_exploded/ShowDetails?title=${title}">${title}</a><br>
    </c:forEach>
</body>
</html>
