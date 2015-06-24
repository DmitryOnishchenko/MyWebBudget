/**
 * 
 */

function validateLoginForm() {
	var valid = true;

	if (!validateUsername(document.getElementById('usernameId').value)) {
		valid = false;
	}

	if (!validatePassword(document.getElementById('passwordId').value)) {
		valid = false;
	}

	return valid;
}

function validateUsername(username) {
	var regex = /^[A-Za-z]+[0-9]*$/i;
	var errorDiv = document.getElementById('usernameErrorId');
	var valid = true;

	if (username.length < 5) {
		errorDiv.innerHTML = 'at least 5 letters';
		valid = false;
	} else if (!regex.test(username)) {
		errorDiv.innerHTML = 'wrong format';
		valid = false;
	} else {
		errorDiv.innerHTML = '';
		valid = true;
	}

	return valid;
}

function validatePassword(password) {
	var errorDiv = document.getElementById('passwordErrorId');
	var valid = true;

	if (password.length < 6) {
		errorDiv.innerHTML = 'minimum 6 symbols';
		valid = false;
	} else {
		errorDiv.innerHTML = '';
		valid = true;
	}

	return valid;
}