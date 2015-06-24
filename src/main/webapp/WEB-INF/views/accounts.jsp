<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html charset="UTF-8">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="/mywebbudget.com/resources/css/styles.css">
    <script src="/mywebbudget.com/resources/js/addaccount.js" type="text/javascript"></script>
</head>
<body onload="">
    <c:url var="accountsUrl" value="/accounts" />
    
    <!-- Header -->
    <%@ include file="includes/header.jsp" %>

    <!-- Menu -->
    <%@ include file="includes/menu.jsp" %>
    
    <!-- Main -->
    <div id="mainDivId" class="container maxWidth maxHeight">
        
        <div class="allAccountsDiv left inlineBlock">
            <div class="container">
                <h1>Accounts</h1>
                <button id="" class="addAccountButton noBorder hoverBg mainColor right" onclick="setVisible()">add new</button>
            </div>

			<div class="error inputColor textLeft">
				<div style="float:right">
					<c:if test="${accountNameError == true}">
						account is already exist
					</c:if>
				</div>
			</div>

			<div id="accounts_balance">
                <br><span class="label_big"><b><fmt:formatNumber value="${totalBalance}" type="number" minFractionDigits="2" /></b></span>
                <br><span class="label_small"><i>Total balance</i></span>
                <br><br>
            </div>

            <div id="accounts_list">
                <c:forEach items="${accounts}" var="account">
                    <h3><b><c:out value="${account.name}" /></b></h3>
                    <br>
                    <h3><b><fmt:formatNumber value="${account.money}" type="number" minFractionDigits="2" /></b></h3>
                    <br><br>
                </c:forEach>
            </div>
        </div>

        <div class="container vLine maxHeight left"></div>

		<div id="addAccountDivId" class="addAccountDiv left">
			<form:form modelAttribute="newAccount" action="${accountsUrl}"
				method="POST" autocomplete="off" onsubmit="return validateAccForm()">
				<table>
					<tr>
						<td colspan="2"><h1>New account</h1></td>
					</tr>
					<tr>
						<td><h2>Account name</h2></td>
						<td><form:input id="nameId"
								class="inputSmall inputColor noBorder roundAll" path="name"
								type="text" name="accountName" placeholder="account name"
								onchange="validateName(this.value)" /></td>
						<td>
							<div id="accountNameErrorId" class="error inputColor textLeft">
								<c:if test="${accountNameError == true}">
									account is already exist
								</c:if>
							</div>
						</td>
					</tr>
					<tr>
						<td><h2>Balance</h2></td>
						<td><form:input id="balanceId"
								class="inputSmall inputColor noBorder roundAll" path="money"
								type="text" name="balance" value="0.00" placeholder="0.00"
								oninput="this.value = validateNumberInput(this.value)" /></td>
					</tr>
					<tr>
						<td colspan="2"><input class="button roundAll noBorder"
							type="submit" value="Add account"></td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>   
</body>
</html>
