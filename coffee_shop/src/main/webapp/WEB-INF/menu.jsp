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
<title><c:out value="${beverage.getName() }"></c:out></title>
<link rel="stylesheet" type="text/css" href="/css/main.css" />
</head>
<body>
	<div class="container">
		<div class="centered_header">
			<div class="flex-3">
				<h1 class="eerie_glow" style="text-shadow: 2px 2px 5px black">menu</h1>
			</div>
			<div class="right_top_links_BAR">
				<div class="right_top_links_bar_ROW">
					<h5 class="subtitle">
						hello,
						<c:out value="${user.getFirstName() }"></c:out>
					</h5>
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
								onmouseout="removeGlow(this)">order (<c:out value="${orderItemsCount }"></c:out>)</a>
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





		<div class="body_container">
			<div class="menu_container">
				<div class="menu_sidebar">
					<h3>menu</h3>
					<ul>
						<li class="category_name"><span class="active">coffee</span></li>
						<li class="category_name">tea</li>
						<li class="category_name">other</li>
					</ul>
				</div>
			</div>
			<div class="card_container">
				<c:forEach var="oneBeverage" items="${allBeverages }">
				
					<div class="one_card">
						<div class="emoji" onmouseover="eerieGlow(this)"
							onmouseout="removeGlow(this)">
							
							<h4 class="item_title">${oneBeverage.getName() }</h4>
							
							<form:form action="/order/add_item" method="POST" modelAttribute="beverageToAdd">
							
								<input type="hidden" name="_method" value="PUT" />
								<form:input path="parentBeverageId" value="${oneBeverage.getId() }" class="hidden" />
								<form:input path="name" value="${oneBeverage.getName() }" class="hidden" />
								<form:input path="description" value="${oneBeverage.getDescription() }" class="hidden" />
								
								<div class="select">
									<form:select path="temperatureDrinkSize">
									<c:forEach var="oneBevTempDS" items="${oneBeverage.getPossibleTemperaturesDrinkSizes() }">
										
										<option value="${oneBevTempDS.getId() }">${oneBevTempDS.getTemperatureDrinkSize().getTemperature().getName() }, ${oneBevTempDS.getTemperatureDrinkSize().getDrinkSize().getName() }</option>
										</c:forEach>
									</form:select>
								</div>
							
								
									<div class="button"><button onmouseover="eerieGlow(this)"
								onmouseout="removeGlow(this)">
										<a href="/beverages/${oneBeverage.getId() }">details</a></button>
						</div>
								
								<div class="fav_button">
									<input type="submit" style="background: none!important;
    border: none;
    padding: 0;
    /*optional*/
    font-family: arial, sans-serif;
    /*input has OS specific font-family*/
    text-decoration:none;
    cursor: pointer;" value="add to order" />
								</div>
								
								
							</form:form>


						</div>
					</div>
				</c:forEach>


			</div>


		</div>

		<script type="text/javascript" src="app.js"></script>
	</div>
</body>
</html>
