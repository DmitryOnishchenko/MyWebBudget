
$(document).ready(function() {
    $(".showAddTransactionButton").click(function() {
        $("#transactionInputDiv").slideToggle(200);

        var today = new Date();
        var year = today.getFullYear();
        var month = today.getMonth() + 1;
        if (month < 10) {
            month = '0' + month;
        }
        var day = today.getDate();
        if (day < 10) {
        	day = '0' + day;
        }
        
        dateInput.value = year + '-' + month + '-' + day;

        return false;
    });
});

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

function validateTransactionForm() {
    var valid = true;
    
    if (dateInput.value.length == 0) {
        valid = false;
    }

    if (valueInput.value.length == 0) {
        
        valid = false;
    }

    if (valid) {
        valueInput.value = sign.innerHTML + valueInput.value;
    }

    return valid;
}

function incomeButton() {
    sign.innerHTML = '+';

	return false;
}

function expenseButton() {
    sign.innerHTML = '-';

    return false;
}

function deleteTransaction(id) {
	alert("START");
	
	$.ajax({
		url: "/mywebbudget.com/transactions/delete",
		data: "id=" + id,
		type: "POST",
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		
		success: function(list) {
			var respContent = "";
			
			for (var i = 0; i < list.length; i++) {
				respContent += "<br>TEST Transaction";
			}
			
			$("#result").html(respContent);
		}
	})
}


