<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html charset="UTF-8">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="/mywebbudget.com/resources/css/styles.css">
	<script src="/mywebbudget.com/resources/js/jquery-2.1.4.min.js" type="text/javascript"></script>
	<script src="/mywebbudget.com/resources/js/transactions.js" type="text/javascript"></script>
</head>
<body>
	<c:url var="transactionsUrl" value="/transactions" />
	
	<!-- Header -->
	<%@ include file="includes/header.jsp"%>

	<!-- Menu -->
	<%@ include file="includes/menu.jsp"%>

	<div id="mainDivId" class="container maxWidth maxHeight inlineBlock">

		<div id="addTransactionDivId" class="addTransactionDiv inlineBlock">
			<button class="showAddTransactionButton noBorder hoverBg">
				<h2>Add transaction</h2>
			</button>

			<div id="transactionInputDiv" class="">
				<form action="${transactionsUrl}" method="POST"
					onsubmit="return validateTransactionForm()">
					<table>
						<tr>
							<td>
								<button type="button" class="typeButton noBorder roundAll"
									onclick="incomeButton()">income</button>
							</td>
							<td>
								<button type="button" class="typeButton noBorder roundAll"
									onclick="expenseButton()">expense</button>
							</td>
							<td>
								<button type="button" class="typeButton noBorder roundAll">transfer</button>
							</td>
							<td>
								<button type="button" class="typeButton noBorder roundAll">Button4</button>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<div class="inlineBlock ">
									<input id="dateInput"
										class="transactionInput inputColor roundAll" type="date"
										name="date"/>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<div id="sign" class="inlineBlock">+</div>
								<input id="valueInput" class="transactionInput inputColor roundAll"
								type="text" autocomplete="off" name="amount" placeholder="0.00"
								oninput="this.value = validateNumberInput(this.value)">
							</td>
						</tr>
						<tr>
							<td colspan="4"><select class="transactionInput inputColor roundAll" name="categoryId">
								<c:forEach items="${categories}" var="category">
									<option value="${category.categoryId}"><c:out value="${category.name}" /></option>
								</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td colspan="4">
							<select class="transactionInput inputColor roundAll" name="accountId">
								<c:forEach items="${accounts}" var="account">
									<option value="${account.accountId}"><c:out value="${account.name}" /></option>
								</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td colspan="4" class="">
								<textarea rows="1"
									class="transactionInput inputColor" name="comment"
									placeholder="comment"></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="4"><input
								class="addTransactionButton roundAll noBorder" type="submit"
								value="Add">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>

		<div id="allTransactions" class="">
			<h1>Last transactions</h1>
			<table>
				<div id="result"></div>
				<br><br>
				<c:forEach items="${transactions}" var="transaction">
					<tr>
						<table class="transactionRow">
							<tr>
								<td><fmt:formatDate value="${transaction.date}" pattern="dd/MM/yyyy"/></td>
								<td><c:out value="${transaction.category.name}"></c:out></td>
								<td><fmt:formatNumber value="${transaction.amount}" type="number" minFractionDigits="2"/></td>
								<td><c:out value="${transaction.account.name}"></c:out></td>
								<td style="width: 30px">
									<img src="/mywebbudget.com/resources/img/pencil_small.png"
									width="18px" height="18px">
								</td>
							</tr>
							<tr>
								<td colspan="4" class="inputColor">
									<h3><c:out value="${transaction.comment}"></c:out></h3>
								</td>
								<c:url var="deleteUrl" value="/transactions/delete?id=" />
								<td style="width: 30px"><a href="${deleteUrl}${transaction.transactionId}"><img src="/mywebbudget.com/resources/img/delete_small.png"
									width="18px" height="18px"></a>
								</td>
							</tr>
						</table>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>