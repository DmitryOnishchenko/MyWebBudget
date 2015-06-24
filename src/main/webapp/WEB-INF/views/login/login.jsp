<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="/mywebbudget.com/resources/css/styles.css">
    <script src="/mywebbudget.com/resources/js/login.js" type="text/javascript"></script>
    
</head>
<body>
    <c:url var="loginUrl" value="/login" />
    <c:url var="regUrl" value="/registration" />
    
    <!-- Header -->
    <%@ include file="../includes/header.jsp" %>
    
    <!-- Main -->
    <div id="mainDivId" class="container maxWidth maxHeight inlineBlock">

        <div class="loginDiv">
            <form:form modelAttribute="user" action="${loginUrl}" autocomplete="off" method="POST" onsubmit="return validateLoginForm()">
                <form:input id="usernameId" class="input inputColor noBorder roundAll" path="login" type="text" name="username" placeholder="username" onchange="validateUsername(this.value)" />
                <div id="usernameErrorId" class="error inputColor">
	                <c:if test="${usernameError == true}">
	                	wrong username
	                </c:if>
                </div>
                
                <form:input id="passwordId" class="input inputColor noBorder roundAll" path="password" type="password" name="password" placeholder="password" onchange="validatePassword(this.value)" />
                <div id="passwordErrorId" class="error inputColor">
	                <c:if test="${passwordError == true}">
	                	wrong password
	                </c:if>
                </div>
                <input class="button noBorder roundAll" type="submit" value="Sign in">
                
            	<a class="button noBorder roundAll inlineBlock" style="width:220px" href="${regUrl}">Registration</a>
            </form:form>
        </div>
        
    </div>
    
</body>
</html>