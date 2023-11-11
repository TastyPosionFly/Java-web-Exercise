function updateUsersList() {
    // ʹ��AJAX����������������û��б�
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            // ����JSON����
            let responseJOSN = JSON.parse(xhr.responseText);

            let userListArray = responseJOSN.userLists;

            let selectElement = document.querySelector("#userListsWrapper select");

            // ���û����Ԫ�أ�ֱ�����
            if (!selectElement.firstChild) {
                let allUserOption = document.createElement("option");
                allUserOption.text = "AllUser";
                allUserOption.value = "allUser";
                selectElement.add(allUserOption);
                for (var i = 0; i < userListArray.length; i++) {
                    // ��ȡÿ���û�����
                    let userObject = userListArray[i];

                    // ��ȡuserName��userIndex
                    let userName = userObject.userName;
                    let userIndex = userObject.userIndex;
                    let optionElement = document.createElement("option");
                    optionElement.text = userName;
                    optionElement.id = userIndex;
                    selectElement.add(optionElement);
                }
            } else {
                // ����Ƴ�<option>Ԫ��
                while (selectElement.firstChild) {
                    selectElement.removeChild(selectElement.firstChild);
                }
                let allUserOption = document.createElement("option");
                allUserOption.text = "AllUser";
                allUserOption.value = "allUser";
                selectElement.add(allUserOption);
                // ������<option>Ԫ��
                for (var i = 0; i < userListArray.length; i++) {
                    // ��ȡÿ���û�����
                    let userObject = userListArray[i];

                    // ��ȡuserName��userIndex
                    let userName = userObject.userName;
                    let userIndex = userObject.userIndex;
                    let optionElement = document.createElement("option");
                    optionElement.text = userName;
                    optionElement.id = userIndex;
                    selectElement.add(optionElement);
                }
            }
        }
    };
    xhr.open("GET", "/Exercise_war_exploded/UpdateUserLists", true);
    xhr.send();
}


// ���ڸ����û��б���������Ϊÿ5�����һ��
setInterval(updateUsersList, 5000);

// ��ʼ��ҳ��ʱ�����û��б�
window.onload = function() {
    updateUsersList();
};
