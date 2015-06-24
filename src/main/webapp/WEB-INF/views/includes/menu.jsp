<div id="navMenuDivId" class="container navMenuDiv maxWidth inlineBlock">
    <c:url var="homeUrl" value="/" />
    <c:url var="transactionsUrl" value="/transactions" />
    <c:url var="categoriesUrl" value="/categories" />
    <c:url var="logoutUrl" value="/logout" />
    
    <a class="menuButton mainBg left" href="${homeUrl}">Home</a>
    <a class="menuButton mainBg marginLeft1px left" href="${transactionsUrl}">Transactions</a>
    <a class="menuButton mainBg marginLeft1px roundRight left" href="${categoriesUrl}">Categories</a>
    <a class="menuButton mainBg roundLeft right" href="${logoutUrl}">Log out</a>
</div>