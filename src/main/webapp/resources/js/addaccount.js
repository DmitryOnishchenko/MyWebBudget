
function validateAccForm() {
    var valid = true;

    if (!validateName(document.getElementById('nameId').value)) {
        valid = false;
    }

    if (!validateNumberInput(document.getElementById('balanceId').value)) {
        valid = false;
    }

    return valid;
}

function validateName(name) {
    var re = /^[A-Za-z]+[0-9]*$/i;
    var div = document.getElementById('accountNameErrorId');
    var valid = true;
    
    if (!re.test(name)) {
        div.innerHTML = 'wrong name';
        valid = false;
    } else {
        div.innerHTML = '<img src="/mywebbudget.com/resources/img/circle_green_small.png" width="18" height="18" style="padding-top:4px">';
    }
    
    return valid;
}

function validateNumberInput(value) {
    var regexp = /^([1-9]+[0-9]*)[,.]?[0-9]{0,2}$/;
    
    if (regexp.test(value)) {
        var last = value.slice(-1);

        if (last.localeCompare(',') == 0) {
            value = value.substring(0, value.length - 1) + '.';
        }

        return value;
    }

    return value.substring(0, value.length - 1);

}

function setVisible() {
    var div = document.getElementById('addAccountDivId');
    div.style.visibility = 'visible';
}
