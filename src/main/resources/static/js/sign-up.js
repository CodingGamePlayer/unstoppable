{
    const mailCheck = document.querySelector('#mail-Check-btn');


    let authResult = false;
    let authNum = 0;


    function checkAuthNumFn() {
        const mailCheckInput = document.querySelector('.mail-check-input').value;
        const mailCheckWarn = document.getElementById('mail-check-warn');

        console.log(mailCheckInput);
        console.log(mailCheckWarn);
        console.log(authNum);

        if (mailCheckInput != authNum) {
            mailCheckWarn.textContent = "  인증번호가 다릅니다.";
            mailCheckWarn.style.color = 'red';
            return;
        } else {
            mailCheckWarn.textContent = "  인증되었습니다.";
            mailCheckWarn.style.color = 'blue';
            authResult = true;
            return;
        }
    }


    mailCheck.addEventListener("click", () => {

        const name = document.querySelector('#username').value;
        const freeFix = document.querySelector('#freefix').value;

        const email = name + freeFix;
        const user = {
            username: email
        }

        const url = "api/mailcheck";
        fetch(url, {
            method: "POST",
            body: JSON.stringify(user),
            headers: {
                "Content-Type": "application/json"
            }
        })
            .then((response) => response.json())
            .then((json) => {

                if (json !== null) {
                    alert("인증메일이 전송 되었습니다.");
                    authNum = json;
                    console.log(authNum);
                    document.querySelector('.mail-check-input').removeAttribute('disabled');
                } else {
                    alert("인증메일 전송에 실패 하였습니다.");
                }
            });
    });


    const signupBtn = document.querySelector('#signup-button');

    signupBtn.addEventListener("click", () => {
        const name = document.querySelector('#username').value;
        const freeFix = document.querySelector('#freefix').value;

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

        if(authResult !== true){
            alert("메일인증 해주시기 바랍니다.")
            return;
        }
        const user = {
            username: name + freeFix,
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
        })

    });

}