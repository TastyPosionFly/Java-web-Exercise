function checkPreviousPage() {
    // ��ȡǰһҳ��� URL
    var referrer = document.referrer;

    // �ж�ǰһҳ���Ƿ��ǵ�¼ҳ��
    if (referrer.includes("/SqlSeverConnection.html")) {
        // ǰһҳ���ǵ�¼ҳ�棬����ִ�е�ǰҳ����߼�
        alert("Welcome, " + '<%= session.getAttribute("username") %>');
    } else {
        // ǰһҳ�治�ǵ�¼ҳ�棬��ת����¼ҳ��
        alert("Illegal Access");
        window.location.href = "/Exercise_war_exploded/SqlSeverConnection.html";
    }
}

// ��ҳ�����ʱ���ü�⺯��
window.onload = checkPreviousPage;