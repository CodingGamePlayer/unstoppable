document.addEventListener('DOMContentLoaded', function () {
    document.getElementById("listView").style.display = "none";
});
let cardBtn = document.getElementById("cardViewBtn");
let listBtn = document.getElementById("listViewBtn");
let cardView = document.getElementById("cardView");
let listView = document.getElementById("listView");
cardBtn.addEventListener('click', function (){
    cardView.style.display = "";
    listView.style.display = "none";
});
listBtn.addEventListener('click', function (){
    cardView.style.display = "none";
    listView.style.display = "";
});