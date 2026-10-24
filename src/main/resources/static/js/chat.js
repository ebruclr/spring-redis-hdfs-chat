document.addEventListener("DOMContentLoaded", () => {
    const chatForm = document.getElementById("chat-form");
    const userInput = document.getElementById("user-input");
    const chatBox = document.getElementById("chat-box");
    const sendBtn = document.getElementById("send-btn");

    chatForm.addEventListener("submit", async (e) => {
        e.preventDefault();
        const message = userInput.value.trim();
        if (!message) return;

        appendMessage("You", message, "user-message");
        userInput.value = "";
        sendBtn.disabled = true;

        const loadingDiv = appendMessage("Llama 3.2", "Thinking...", "bot-message loading");

        try {
            const response = await fetch("/api/chat", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({message: message})
            });

            if (!response.ok) {
                throw new Error("Server error received");
            }

            const data = await response.json();

            loadingDiv.classList.remove("loading");
            loadingDiv.innerHTML = `<strong>Llama 3.2:</strong> ${data.response}`;
        } catch (error) {
            loadingDiv.classList.remove("loading");
            loadingDiv.innerHTML = '<strong>ERROR:</strong> No message received, (${error.message})';
        } finally {
            sendBtn.disabled = false;
            chatBox.scrollTop = chatBox.scrollHeight;
        }
    });


    function appendMessage(sender, text, className) {
        const msgDiv = document.createElement("div");
        msgDiv.className = `message ${className}`;
        msgDiv.innerHTML = `<strong>${sender}:</strong> ${text}`;
        chatBox.appendChild(msgDiv);
        chatBox.scrollTop = chatBox.scrollHeight;
        return msgDiv;
    }
});