{
    const updateModal = document.querySelector('#article-modal-update');

    updateModal.addEventListener("click", () => {

        const article = {
            id : document.querySelector('#article-modal-id').value,
            title : document.querySelector('#article-modal-title').value,
            body : document.querySelector('#article-modal-body').value,
            userid : document.querySelector('#article-modal-userid').value
        }

        const url = "/api/article/update";
        fetch(url, {
            method : "PUT",
            body : JSON.stringify(article),
            headers : {
                "Content-Type" : "application/json"
            }
        }).then(response => {
            const msg = (response.ok) ? "수정이 되었습니다." : "수정 실패하였습니다.";
            alert(msg);

            window.location.reload();
        } );
    });
}

{
    const updateBtn = document.querySelector('#article-update-button');

    const id = document.querySelector('#id').value;

    updateBtn.addEventListener("click", () => {

        const article = {
            id : document.querySelector('#id').value,
            title : document.querySelector('#title').value,
            body : document.querySelector('#body').value,
            userid : document.querySelector('#userid').value
        }

        const url = "/api/article/update";
        fetch(url, {
            method : "PUT",
            body : JSON.stringify(article),
            headers : {
                "Content-Type" : "application/json"
            }
        }).then(response => {
            const msg = (response.ok) ? "수정이 되었습니다." : "수정 실패하였습니다.";
            alert(msg);

            window.location.href = "http://localhost:8888/article/"+ id +"/detail";
        } );
    })
}