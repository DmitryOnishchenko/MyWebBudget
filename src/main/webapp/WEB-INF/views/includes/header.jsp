<c:if test="${not empty sessionScope.user}">
	<c:out value="Hello, ${sessionScope.user.login}" />
</c:if>
<div class="header">
    MyWebBudget
</div>