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
    <script src="/mywebbudget.com/resources/js/addcategory.js" type="text/javascript"></script>
</head>
<body onload="">
    <c:url var="categoriesUrl" value="/categories" />
    
    <!-- Header -->
    <%@ include file="includes/header.jsp" %>

    <!-- Menu -->
    <%@ include file="includes/menu.jsp" %>
    
    <!-- Main -->
    <div id="mainDivId" class="container maxWidth maxHeight">
        
        <div class="allCategoriesDiv left inlineBlock">
            <div class="container">
                <h1>Categories</h1>
                <button id="" class="addCategoryButton noBorder hoverBg mainColor right" onclick="setVisible()">add new</button>
            </div>

			<div class="error inputColor textLeft">
				<div style="float:right">
					<c:if test="${categoryNameError == true}">
						category is already exist
					</c:if>
				</div>
			</div>

            <div id="accounts_list">
                <c:forEach items="${categories}" var="category">
               		<br>
                    <h2><b><c:out value="${category.name}" /></b></h2>
                    <br>
                </c:forEach>
            </div>
        </div>

        <div class="container vLine maxHeight left"></div>

		<div id="addCategoryDivId" class="addCategoryDiv left">
			<form:form modelAttribute="newCategory" action="${categoriesUrl}"
				method="POST" autocomplete="off" onsubmit="return validateAccForm()">
				<table>
					<tr>
						<td colspan="2"><h1>New category</h1></td>
					</tr>
					<tr>
						<td><h2>Category name</h2></td>
						<td><form:input id="nameId"
								class="inputSmall inputColor noBorder roundAll" path="name"
								type="text" name="categoryName" placeholder="category name"
								onchange="validateName(this.value)" /></td>
						<td>
							<div id="categoryNameErrorId" class="error inputColor textLeft">
								<c:if test="${categoryNameError == true}">
									category is already exist
								</c:if>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="2"><input class="button roundAll noBorder"
							type="submit" value="Add category"></td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>   
</body>
</html>
