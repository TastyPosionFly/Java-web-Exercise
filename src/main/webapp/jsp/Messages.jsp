<%--
  Created by IntelliJ IDEA.
  User: w1300
  Date: 2023/11/3
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%><%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>聊天室</title>
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
    <iframe src="./jsp/MessageText.jsp" width="100%" height="70%"></iframe>
    <iframe src="./jsp/MessageInput.jsp" width="100%" height="25%"></iframe>
    <a href="/Exercise_war_exploded/Login">离开聊天室</a>
</body>

<script type="text/javascript">
    var inactivityTimeout = 600000;

    //当用户长时间不活动时执行的操作
    function userInactive() {
        alert("长时间未活动，离开聊天室。");
        //重定位到登入界面
        window.location.href = "http://localhost:8080/Exercise_war_exploded/Login";
    }

    var inactivityTimer; // 用于保存计时器

    function resetInactivityTimer() {
        // 重置计时器，取消之前的计时器并重新开始计时
        clearTimeout(inactivityTimer);
        inactivityTimer = setTimeout(userInactive, inactivityTimeout);
    }

    // 监听用户的交互事件，当用户活动时重置计时器
    document.addEventListener("mousemove", resetInactivityTimer);
    document.addEventListener("keydown", resetInactivityTimer);

    // 初始化计时器
    resetInactivityTimer();
</script>
</html>
