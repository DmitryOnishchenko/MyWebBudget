/**
 * 
 */

function validateRegForm() {
    var valid = true;

    if (!validateUsername(document.getElementById('usernameId').value)) {
        valid = false;
    }

    if (!validatePassword(document.getElementById('passwordId').value)) {
        valid = false;
    }
    
    if (!validateEmail(document.getElementById('emailId').value)) {
        valid = false;
    }

    return valid;
}

function validateUsername(username) {
    var regex = /^[A-Za-z]+[0-9]*$/i;
    var errorDiv = document.getElementById('usernameErrorId');
    var input = document.getElementById('usernameId');
    var valid = true;

    if (username.length < 5) {
    	errorDiv.innerHTML = 'at least 5 letters';
		valid = false;
    } else if (!regex.test(username)) {
        errorDiv.innerHTML = 'first letters, then the numbers';
        valid = false;
    } else {
        errorDiv.innerHTML = '<img src="/mywebbudget.com/resources/img/circle_green_small.png" width="18" height="18" style="padding-top:4px">';
    }
    
    return valid;
}

function validatePassword(password) {
    var re = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
    var errorDiv = document.getElementById('passwordErrorId');
    var input = document.getElementById('passwordId');
    var valid = true;
    
    if (password.length < 6) {
        errorDiv.innerHTML = 'minimum 6 symbols';
        valid = false;
    } else {
        errorDiv.innerHTML = '<img src="/mywebbudget.com/resources/img/circle_green_small.png" width="18" height="18" style="padding-top:4px">';
    }
    
    return valid;
}

function validateEmail(email) {
    var regex = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
    var errorDiv = document.getElementById('emailErrorId');
    var input = document.getElementById('emailId');
    var valid = true;
    
    if (!regex.test(email)) {
    	errorDiv.innerHTML = 'wrong format';
        valid = false;
    } else {
    	errorDiv.innerHTML = '<img src="/mywebbudget.com/resources/img/circle_green_small.png" width="18" height="18" style="padding-top:4px">';
    }
    
    return valid;
}
