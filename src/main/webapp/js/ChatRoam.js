const message = document.querySelector("#window");
const messageInput = document.querySelector("#message-input");
const button = document.querySelector("#message-button");

button.addEventListener("click",()=>{
    let messageText = messageInput.value.trim();
    if (message !== ""){
        let tempMessage = document.createElement("div");
        tempMessage.className = "message";
        tempMessage.textContent = messageText;

        message.appendChild(tempMessage);

        messageInput.value = '';

    }
});
messageInput.addEventListener('keydown', (event) => {
    if (event.key === 'Enter') {
        button.click();
    }
});