document.getElementById("loginForm").addEventListener("submit", async function(event) {

    event.preventDefault();

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const message = document.getElementById("message");

    try {

        const response = await fetch("/api/user_auth/login", {
            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({
                userEmail: email,
                password: password
            })
        });

        const text = await response.text();

        console.log("Status:", response.status);
        console.log("Response:", text);

        let data = {};

        try {
            data = JSON.parse(text);
        } catch (e) {
            data = {
                message: text
            };
        }

        if (response.ok) {

            localStorage.setItem("token", data.token);

            message.style.color = "green";
            message.innerText = data.message || "Login Successful";

            // Dashboard par redirect
            setTimeout(function() {
                window.location.href = "/index.html";
            }, 500);

        } else {

            message.style.color = "red";
            message.innerText =
                data.message || "Login failed. Status: " + response.status;
        }

    } catch (error) {

        console.error("LOGIN ERROR:", error);

        message.style.color = "red";
        message.innerText =
            "Unable to connect to server.";
    }

});