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
<title>Add a new drink to the menu!</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${jakarta.servlet.jsp.PageContext}/js/app.js"></script>

<!-- change to match your file/naming structure -->
</head>
<body>
	<div class="container">
		
		<div class="centered_header">
			<div class="flex-3">
				<h1 class="eerie_glow" style="text-shadow: 2px 2px 5px black">Hallowed Ground</h1>
			</div>
			<div class="right_top_links_BAR">
			<div class="right_top_links_bar_ROW">
				<h5>hello, <c:out value="${user.getFirstName() }"></c:out></h5>
			</div>
			<div class="right_top_links_bar_ROW">
				<div class="navlink">
					<div class="button">
						<div class="active">
							<a href="/dashboard" onmouseover="eerieGlow(this)"
								onmouseout="removeGlow(this)">home</a>
						</div>
					</div>
				</div>
				<div class="navlink">
					<div class="button">
						<a href="/cart" onmouseover="eerieGlow(this)"
							onmouseout="removeGlow(this)">order (0)</a>
					</div>
				</div>
				<div class="navlink">
					<div class="button">
						<a href="/account_info" onmouseover="eerieGlow(this)"
							onmouseout="removeGlow(this)">account</a>
					</div>
				</div>
				<div class="navlink">
					<div class="button">
						<a href="/logout" onmouseover="eerieGlow(this)"
							onmouseout="removeGlow(this)">logout</a>
					</div>
				</div>
				</div>
			</div>
		</div>
		
		<div class="form_container">
			<div>
				<form:form action="/beverages" method="POST" modelAttribute="newBeverage"
					divClass="one_form">
					
					<h2 class="form_title">Add a Beverage to the Menu</h2>

					<div class="form_field">
					<div class="form_field_label">
						<form:label path="name">Name: </form:label></div>
						<div class="form_field_input"><form:input path="name" name="name"></form:input></div>
					</div>
					<div class="error">
						<form:errors path="name" />
					</div>
					
					<div class="form_field">
						<div class="form_field_label"><form:label path="description">Description: </form:label></div>
						<div class="form_field_input"><form:input path="description" name="description"></form:input></div>
					</div>
					
					<div class="error">
						<form:errors path="description" />
					</div>
					
					<div class="form_field">

						<div class="form_field_label"><form:label path="possibleTemperaturesDrinkSizes">Available in:</form:label></div>
						<div class="form_field_input_special">
							<c:forEach var="oneTemperature" items="${temperatures }">
							
								<form:label path="" class="subtitle">${oneTemperature.getName() } sizes</form:label>
		
											<div>
											<c:forEach var="oneTemperatureDrinkSize" items="${temperaturesDrinkSizes }">
											<div>
													<c:if test="${oneTemperatureDrinkSize.getTemperature().equals(oneTemperature)}">
														
														<input type="checkbox" name="temperatureDrinkSizes_ids" value="${oneTemperatureDrinkSize.getId() }"/>
														
														<form:label path="possibleTemperaturesDrinkSizes" class="lowercase">${oneTemperatureDrinkSize.getDrinkSize().getName()}
														
													<div class="subinfo">${oneTemperatureDrinkSize.getDrinkSize().getVolume() }oz</div></form:label>
													
													
											
													</c:if>
													</div>
													
													
											
											</c:forEach></div>
										
								</c:forEach>
						</div>
					</div>
					<div class="error">
						<form:errors path="possibleTemperaturesDrinkSizes" />
					</div>

					
				
		
					<div>
						<input type="submit" value="Add" />
					</div>
				</form:form>
			</div>
			
	</div>
	</div>

</body>

</html>

