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
    <script src="/mywebbudget.com/resources/js/registration.js" type="text/javascript"></script>
</head>
<body>
    <c:url var="regUrl" value="/registration" />
    
    <!-- Header -->
    <%@ include file="../includes/header.jsp" %>
    
    <div id="mainDivId" class="container maxWidth maxHeight inlineBlock">
        
        <div class="registrationDiv">
            <form:form modelAttribute="user" action="${regUrl}" method="POST" onsubmit="return validateRegForm()">
                <table>
                    <tr>
                        <td><h2 style="font-size:20px">Username</h2></td>
                        <td><form:input id="usernameId" class="inputSmall inputColor noBorder roundAll" path="login" autocomplete="off" type="text" name="username" placeholder="username" onchange="validateUsername(this.value)" /></td>
                        <td>
                            <div id="usernameErrorId" class="error inputColor textLeft">
                                <c:if test="${usernameError == true}">
                                    username is already taken
                                </c:if>
                            </div>
                        <td>
                    </tr>
                    
                    <tr>
                        <td><h2 style="font-size:20px">Password</h2></td>
                        <td><form:input id="passwordId" class="inputSmall inputColor noBorder roundAll" path="password" type="password" name="password" placeholder="password" onchange="validatePassword(this.value)" /></td>
                        <td><div id="passwordErrorId" class="error inputColor textLeft"></div></td>
                    </tr>

                    <tr>
                        <td><h2 style="font-size:20px">E-mail</h2></td>
                        <td><form:input id="emailId" class="inputSmall inputColor noBorder roundAll" path="email" type="text" name="email" placeholder="user@cats.com" onchange="validateEmail(this.value)" /></td>
                        <td>
                            <div id="emailErrorId" class="error inputColor textLeft">
                                <c:if test="${emailError == true}">
                                    e-mail is already taken
                                </c:if>
                            </div>
                        </td>
                    </tr>
                    
                    <tr>
                        <td></td>
                        <td><input class="button roundAll noBorder" type="submit" value="Register me"></td>
                    </tr>
                </table>
            </form:form>
        </div>
        
    </div>
    
</body>
</html>
