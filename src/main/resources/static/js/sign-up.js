{


    function passwordCheckFunc() {
        const passwordCheck1 = document.getElementById('password').value;
        const passwordCheckWarn = document.getElementById('password-check-warn');

        const passwordCheck2 = document.getElementById('password-auth').value;
        const num = passwordCheck1.search(/[0-9]/g);
        const eng = passwordCheck1.search(/[a-z]/ig);
        const spe = passwordCheck1.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

        if (passwordCheck1.length < 8 || passwordCheck1.length > 20) {
            passwordCheckWarn.textContent = "8자리 ~ 20자리 이내로 입력해주세요.";
            passwordCheckWarn.style.color = 'red';
            return false;
        } else if (passwordCheck1.search(/\s/) != -1) {
            passwordCheckWarn.textContent = "비밀번호는 공백 없이 입력해주세요.";
            passwordCheckWarn.style.color = 'red';
            return false;
        } else if (num < 0 || eng < 0 || spe < 0) {
            passwordCheckWarn.textContent = "영문,숫자, 특수문자를 혼합하여 입력해주세요.";
            passwordCheckWarn.style.color = 'red';
            return false;
        } else {
            passwordCheckWarn.textContent = "사용할 수 있는 비밀번호 입니다.";
            passwordCheckWarn.style.color = 'blue';

            return true;
        }

    }

    function passwordAuthFunc() {
        const passwordCheck1 = document.getElementById('password').value;
        const passwordCheck2 = document.getElementById('password-auth').value;
        const passwordCheckWarn = document.getElementById('password-auth-warn');

        if (passwordCheck1 != passwordCheck2) {
            passwordCheckWarn.textContent = '비밀번호가 같지 않습니다.';
            passwordCheckWarn.style.color = 'red';
            return false;
        } else {
            passwordCheckWarn.textContent = '인증되었습니다.';
            passwordCheckWarn.style.color = 'blue';
            return true;
        }
    }

    const mailCheck = document.querySelector('#mail-Check-btn');

    let authResult = false;
    let authNum = 0;


    function checkAuthNumFn() {
        const mailCheckInput = document.querySelector('.mail-check-input').value;
        const mailCheckWarn = document.getElementById('mail-check-warn');

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
            }).catch(() => {
            alert("인증 메일을 보내지 못하였습니다.");
        });
    });


    const signupBtn = document.querySelector('#signup-button');

    signupBtn.addEventListener("click", () => {
        const name = document.querySelector('#username').value;
        const freeFix = document.querySelector('#freefix').value;

        const userName = document.querySelector("#username").value;
        const password = document.querySelector("#password").value;
        const nickname = document.querySelector("#nickname").value;
        const booleanPassword1 = passwordCheckFunc();
        const booleanPassword2 = passwordAuthFunc();

        console.log(userName);
        console.log(password);
        console.log(nickname);

        if (booleanPassword1 != true || booleanPassword2 != true) {
            alert('비밀번호를 인증 받으시기 바랍니다.');
            return;
        }
        if (userName === "" || password === "" || nickname === "") {
            alert("모든 칸을 채워주시기 바랍니다.");
            return;
        }

        if (authResult !== true) {
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
            if (response.ok) {

                const msg = "회원가입이 완료 되었습니다.";
                alert(msg);

                window.location.href = "http://localhost:8888/login";
            } else {
                const msg = "회원가입에 문제가 발생하였습니다.";
                alert(msg);

                window.location.href = "http://localhost:8888/signup";

            }

        });

    });

}