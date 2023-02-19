"use strict";

{


    const signInBtn = document.querySelector('#signin-submit-button');

    signInBtn.addEventListener("click", () => {

        const userName = document.querySelector('#signin-username').value;
        const password = document.querySelector('#signin-password').value;


        if (userName === "" || password === ""){
            alert('아이디와 비밀번호를 채워주시기 바랍니다.');
            return;
        }
        // console.log(userName);
        // console.log(password);

        const user = {
            username : userName,
            password : password
        };

        // console.log(user);

        const url = '/api/login';

        fetch(url, {
            method : "POST",
            body : JSON.stringify(user),
            headers : {
                "Content-Type" : "application/json",
                "X-Requested-with" : "Unstoppable"
            }
        }).then(res => {
            const msg = (res.ok) ? "로그인이 되었습니다." : "로그인을 실패 하였습니다."
            alert(msg);

            window.location.href = "http://localhost:8888/user/main";
        });
    });
}