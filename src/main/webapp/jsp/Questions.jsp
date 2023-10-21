<%@ page import="java.util.Map, java.util.Map.Entry" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: w1300
  Date: 2023/10/20
  Time: 23:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>统计信息</title>
</head>
<body>
    <h1>统计信息</h1>

    <h3>不同兴趣爱好的人数</h3>
    <ul>
        <% for (Entry<String,Integer> entry : ((Map<String,Integer>) request.getAttribute("hobbiesCounts")).entrySet()) { %>
        <li><%= entry.getKey() %>: <%= entry.getValue() %> 人</li>
        <% } %>
    </ul>

    <h3>问卷男女性别人数</h3>
    <ul>
        <% for (Entry<String,Integer> entry:((Map<String,Integer>)request.getAttribute("sexCounts")).entrySet()) { %>
        <li><%= entry.getKey() %>: <%= entry.getValue()%>人 </li>
        <% } %>
    </ul>

    <h3>参与调查人员</h3>
    <ul>
        <% for (String name:((List<String>)request.getAttribute("nameCounts"))) { %>
        <li><%= name%></li>
        <% } %>
    </ul>

</body>
</html>
