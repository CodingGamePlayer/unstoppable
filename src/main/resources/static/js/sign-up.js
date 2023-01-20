{
    const signupBtn = document.querySelector('#signup-button');


    signupBtn.addEventListener("click", () => {

        const checkbox = document.querySelector('#flexCheckDefault');
        const userName = document.querySelector("#username").value;
        const password = document.querySelector("#password").value;
        const nickname = document.querySelector("#nickname").value;

        console.log(userName);
        console.log(password);
        console.log(nickname);

        if (userName === "" || password === "" || nickname === "") {
            alert("모든 칸을 채워주시기 바랍니다.");
            return;
        }

        if (!checkbox.checked) {
            alert("약관에 동의 해주시기 바랍니다.");
            return;
        }
        const user = {
            username: userName,
            password: password,
            nickname: nickname
        }
        console.log(user);

        const url = "/api/signup";

        fetch(url, {
            method: "POST",
            body: JSON.stringify(user),
            headers: {
                "Content-Type": "application/json"
            }
        }).then(response => {
            const msg = (response.ok) ? "회원가입이 완료 되었습니다." : "회원가입에 문제가 발생했습니다.";
            alert(msg);

            window.location.href = "http://localhost:8888/login";
        });
    });

}