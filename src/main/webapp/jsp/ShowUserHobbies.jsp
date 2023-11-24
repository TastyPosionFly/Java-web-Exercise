<%--
  Created by IntelliJ IDEA.
  User: w1300
  Date: 2023/11/17
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:forEach var="userData" items="${applicationScope.userHobbyData}">
        <ul>
            <li><c:out value="${userData.username}"/></li>
            <li><c:out value="${userData.sex}"/></li>
            <li><c:out value="${userData.age}"/></li>
            <li><c:out value="${userData.address}"/></li>
            <li><c:out value="${userData.hobbies}"/></li>
        </ul>
    </c:forEach>
</body>
</html>
