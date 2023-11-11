function updateUsersList() {
    // 使用AJAX向服务器请求最新用户列表
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            // 解析JSON数据
            let responseJOSN = JSON.parse(xhr.responseText);

            let userListArray = responseJOSN.userLists;

            let selectElement = document.querySelector("#userListsWrapper select");

            // 如果没有子元素，直接添加
            if (!selectElement.firstChild) {
                let allUserOption = document.createElement("option");
                allUserOption.text = "AllUser";
                allUserOption.value = "allUser";
                selectElement.add(allUserOption);
                for (var i = 0; i < userListArray.length; i++) {
                    // 获取每个用户对象
                    let userObject = userListArray[i];

                    // 获取userName和userIndex
                    let userName = userObject.userName;
                    let userIndex = userObject.userIndex;
                    let optionElement = document.createElement("option");
                    optionElement.text = userName;
                    optionElement.id = userIndex;
                    selectElement.add(optionElement);
                }
            } else {
                // 逐个移除<option>元素
                while (selectElement.firstChild) {
                    selectElement.removeChild(selectElement.firstChild);
                }
                let allUserOption = document.createElement("option");
                allUserOption.text = "AllUser";
                allUserOption.value = "allUser";
                selectElement.add(allUserOption);
                // 逐个添加<option>元素
                for (var i = 0; i < userListArray.length; i++) {
                    // 获取每个用户对象
                    let userObject = userListArray[i];

                    // 获取userName和userIndex
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


// 定期更新用户列表，这里设置为每5秒更新一次
setInterval(updateUsersList, 5000);

// 初始化页面时加载用户列表
window.onload = function() {
    updateUsersList();
};
