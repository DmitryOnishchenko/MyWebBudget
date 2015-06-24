
function validateAccForm() {
    var valid = true;

    if (!validateName(document.getElementById('nameId').value)) {
        valid = false;
    }

    return valid;
}

function validateName(name) {
    var re = /^[A-Za-z]+[0-9]*$/i;
    var div = document.getElementById('categoryNameErrorId');
    var valid = true;
    
    if (!re.test(name)) {
        div.innerHTML = 'wrong name';
        valid = false;
    } else {
        div.innerHTML = '<img src="/mywebbudget.com/resources/img/circle_green_small.png" width="18" height="18" style="padding-top:4px">';
    }
    
    return valid;
}

function setVisible() {
    var div = document.getElementById('addCategoryDivId');
    div.style.visibility = 'visible';
}
