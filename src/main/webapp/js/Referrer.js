function checkPreviousPage() {
    // 获取前一页面的 URL
    var referrer = document.referrer;

    // 判断前一页面是否是登录页面
    if (referrer.includes("/SqlSeverConnection.html")) {
        // 前一页面是登录页面，继续执行当前页面的逻辑
        alert("Welcome, " + '<%= session.getAttribute("username") %>');
    } else {
        // 前一页面不是登录页面，跳转到登录页面
        alert("Illegal Access");
        window.location.href = "/Exercise_war_exploded/SqlSeverConnection.html";
    }
}

// 在页面加载时调用检测函数
window.onload = checkPreviousPage;