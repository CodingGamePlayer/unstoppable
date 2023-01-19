{
    const signupBtn = document.querySelector('#signup-button');

    signupBtn.addEventListener("click", () => {
        const checkbox = document.querySelector('#flexCheckDefault');

        if(!checkbox.checked){
            alert("약관에 동의 해주시기 바랍니다.");
        } else {
            const user = {
                userid : document.querySelector("#userid").value,
                nickname : document.querySelector("#nickname").value,
                email : document.querySelector("#email").value,
                password : document.querySelector("#userid").password
            }
            console.log(user);

            const url = "/api/user/signup";

            fetch(url, {
                method : "POST",
                body : JSON.stringify(user),
                headers :{
                    "Content-Type" : "application/json"
                }
            }).then(response => {
                const msg = (response.ok) ? "회원가입이 완료 되었습니다." : "회원가입에 문제가 발생했습니다.";
                alert(msg);

                window.location.href = "http://localhost:8888/articles";
            });
        }
    });
}