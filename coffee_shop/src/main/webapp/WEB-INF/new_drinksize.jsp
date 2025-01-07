<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add a new size to the menu!</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/app.js"></script>
<!-- change to match your file/naming structure -->
</head>
<body>
	<div class="container">
		
	<div class="centered_header">
		<h1>Welcome!</h1>
		
	</div>
		<div class="form_container">
			<div>
				<form:form action="/drinksizes" method="POST" modelAttribute="newDrinkSize"
					divClass="one_form">
					<h2>Add a New Size to the Menu</h2>

					<div class="form_field">
						<form:label path="name">Name: </form:label>
						<form:input path="name" />
			<%-- 			
					<form:select path="name">
    <c:forEach var="oneName" items="${existingNames}">
            <!--- Each option VALUE is the id of the person --->
            <form:option value="${oneName}" path="name">
            <!--- This is what shows to the user as the option --->
                <c:out value="${oneName}"/>
                
            </form:option>
    </c:forEach>
   </form:select> --%>
   </div>
					<div class="error">
						<form:errors path="name" />
					</div>
					
					
					<%-- <form:label path="volume">Existing volume options (oz): </form:label>
					
					 <form:select path="volume">
    <c:forEach var="oneVolume" items="${existingVolumes}">
            <!--- Each option VALUE is the id of the person --->
            <form:option value="${oneVolume}" path="volume">
            <!--- This is what shows to the user as the option --->
                <c:out value="${oneVolume}"/>
                
            </form:option>

    </c:forEach>
    
   </form:select> --%>
  
   
   <div class="form_field">
   <h6>Enter a new volume(oz):</h6>
    <form:input type="text" path="volume" /></div>
					<div class="error">
						<form:errors path="volume" />
					</div>

					
					<div class="form_field">
					<form:label path="possibleTemperatures">Temperature:</form:label>
					<div>
						<input type="checkbox" value="1" name="temperature_ids"> 
						<form:label path="possibleTemperatures">Hot</form:label>
						<input type="checkbox" value="2" name="temperature_ids"> 
						<form:label path="possibleTemperatures">Iced</form:label>
						<input type="checkbox" value="3" name="temperature_ids">
						<form:label path="possibleTemperatures">Blended</form:label></div>
					</div>
					<div class="error">
						<form:errors path="possibleTemperatures" />
					</div>
				
		
					<div>
						<input type="submit" value="Add" />
					</div>
				</form:form>
			</div>
			
	</div>
	</div>
	<script type="text/javascript" src="/js/app.js"></script>
</body>
</html>

