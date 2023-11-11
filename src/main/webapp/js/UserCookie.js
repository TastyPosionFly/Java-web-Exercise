// 检查是否存在保存的用户名的 Cookie
function checkCookie(){
    let userName = getCookie("userName")
    if (userName !== "") {
        // 如果存在Cookie，则填充用户名文本框
        document.forms["LoginData"]["userName"].value = userName;
    }
}


// 获取指定名称的 Cookie 的值
function getCookie(cookieName){
    let name = cookieName + "="
    let decodedCookie = decodeURIComponent(document.cookie)
    let cookieArray = decodedCookie.split(",")
    for (let i=0;i<cookieArray.length;i++){
        let cookie = cookieArray[i].trim();//去除开头的空格
        if (cookie.indexOf(name) === 0){
            return cookie.substring(name.length,cookie.length)
        }
    }
    return ""
}

// 在登录时创建并保存 Cookie
function setCookie(){
    let userName = document.forms["LoginData"]["userName"].value;
    document.cookie = "userName=" + userName + ";expires=" + getExpirationTime();
}

// 获取 Cookie 过期时间
function getExpirationTime() {
    let d = new Date();
    // 设置 Cookie 的过期时间为一天后
    d.setTime(d.getTime() + (24 * 60 * 60 * 1000));
    let expires = "expires=" + d.toUTCString();
    return expires;
}