// ����Ƿ���ڱ�����û����� Cookie
function checkCookie(){
    let userName = getCookie("userName")
    if (userName !== "") {
        // �������Cookie��������û����ı���
        document.forms["LoginData"]["userName"].value = userName;
    }
}


// ��ȡָ�����Ƶ� Cookie ��ֵ
function getCookie(cookieName){
    let name = cookieName + "="
    let decodedCookie = decodeURIComponent(document.cookie)
    let cookieArray = decodedCookie.split(",")
    for (let i=0;i<cookieArray.length;i++){
        let cookie = cookieArray[i].trim();//ȥ����ͷ�Ŀո�
        if (cookie.indexOf(name) === 0){
            return cookie.substring(name.length,cookie.length)
        }
    }
    return ""
}

// �ڵ�¼ʱ���������� Cookie
function setCookie(){
    let userName = document.forms["LoginData"]["userName"].value;
    document.cookie = "userName=" + userName + ";expires=" + getExpirationTime();
}

// ��ȡ Cookie ����ʱ��
function getExpirationTime() {
    let d = new Date();
    // ���� Cookie �Ĺ���ʱ��Ϊһ���
    d.setTime(d.getTime() + (24 * 60 * 60 * 1000));
    let expires = "expires=" + d.toUTCString();
    return expires;
}