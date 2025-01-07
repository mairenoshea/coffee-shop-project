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
<title></title>
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
		<h1>All Drink Sizes</h1>
		
	</div>
		<div class="table_container">
		<div>
			<table>
				<thead>
					<tr>
						<th>Size Name</th>
						<th>Volume (oz)</th>
						<th>Temperatures Available</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="oneDrinkSize" items="${allDrinkSizes }"> 
						<tr>
							<td><c:out value="${oneDrinkSize.getName()}"></c:out></td>
							<td><c:out value="${oneDrinkSize.getVolume() }"></c:out></td>
							<td><c:forEach var="oneTemperatureDrinkSize" items="${oneDrinkSize.getPossibleTemperatures() }">
						
									${oneTemperatureDrinkSize.getTemperature().getName() }
					
							</c:forEach></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="/js/app.js"></script>
</body>
</html>

