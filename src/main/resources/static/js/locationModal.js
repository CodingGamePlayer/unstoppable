document.addEventListener('DOMContentLoaded', function () {
    document.querySelector('.alert-custom').style.display = "none";
    let myModal = new bootstrap.Modal(document.getElementById('staticBackdrop'), {})
    myModal.toggle()
});
let hiddenValue;
let inputValue;

document.querySelector('input[list]').addEventListener('input', function(e) {
    document.querySelector('.alert-custom').style.display = "none";
    hiddenValue = null;
    let input = e.target,
        list = input.getAttribute('list'),
        options = document.querySelectorAll('#' + list + ' option'),
        hiddenInput = document.getElementById(input.getAttribute('id') + '-hidden'),
        label = input.value;

    hiddenInput.value = label;
    inputValue = label;

    for(let i = 0; i < options.length; i++) {
        let option = options[i];

        if(option.value === label) {
            hiddenInput.value = option.getAttribute('data');
            hiddenValue = hiddenInput.value;
            console.log("hidden value :" + hiddenValue);
            break;
        }
    }
});

let loginForm = document.getElementById('locationForm');
loginForm.addEventListener('submit', (e)=>{

    if(isNotEmpty(hiddenValue)){
        hiddenValue = parseInt(hiddenValue);
    }

    let inputPlace = document.getElementById('place');
    inputValue = inputPlace.value;
    if (!isNotEmpty(inputValue) || !isNotEmpty(hiddenValue)) {
        e.preventDefault();
        document.querySelector('.alert-custom').style.display = "block";
        this.focus();
    } else if(isNotEmpty(inputValue) && Number.isInteger(hiddenValue)) {
        /*e.defaultPrevented;*/
    }
});

function isNotEmpty(str){
    if(typeof str == "undefined" || str == null || str == "")
        return false;
    else
        return true ;
}