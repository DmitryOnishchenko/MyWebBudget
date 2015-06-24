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
    <!-- <script language="javascript" type="text/javascript" src="js/javascript.js"></script> -->
</head>
<body onload="">
    <c:url var="accountsUrl" value="/accounts" />
    
    <!-- Header -->
    <%@ include file="includes/header.jsp" %>

    <!-- Menu -->
    <%@ include file="includes/menu.jsp" %>
    
    <!-- Main -->
    <div id="mainDivId" class="container maxWidth maxHeight inlineBlock">
        
        <!-- Accounts -->
        <div id="accountsDivId" class="container accountsDiv left">
            <div class="container">
                <h1>Accounts</h1>
                <a class="addAccountButton hoverBg mainColor right" href="${accountsUrl}">add new</a>
            </div>
            <br>
            <div class="container">
                <h1><fmt:formatNumber value="${totalBalance}" type="number" minFractionDigits="2" /></h1><br>
                <h3><i>Total balance</i></h3>
            </div>
            <br>

            <div class="container">
                <c:forEach items="${accounts}" var="account">
                    <h3><b><c:out value="${account.name}" /></b></h3>
                    <br>
                    <h3><b><fmt:formatNumber value="${account.money}" type="number" minFractionDigits="2"/></b></h3>
                    <br><br>
                </c:forEach>
            </div>
        </div>

        <div class="container vLine maxHeight left"></div>
    </div>

</body>
</html>