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
				<h1 class="eerie_glow" style="text-shadow: 2px 2px 5px black">Welcome
					to Hallowed Ground!</h1>
			</div>
			<div class="right_top_links_BAR">
			<div class="right_top_links_bar_ROW">
				<h5 class="subtitle">hello, <c:out value="${user.getFirstName() }"></c:out></h5>
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
		



	

		<div class="body_container">
		
			<div class="grid_container">
			
				<div class="flex-1">
				<div class="bottom_links">
				<a href="/menu"><span class="button"><button>Back to the menu</button></span></a>
				</div>
				<div class="one_forms_styling">

					<div class="one_form">
					

						<div class="form_field"> 
						<h1 class="form_title"><c:out value="${beverage.getName() }"></c:out></h1>
<!-- 							<span class="form_field_label">Beverage Name:</span> -->
<%-- 							<span class="form_field_input"><c:out value="${beverage.getName() }"></</c:out></span> --%>
						</div>
						
						<div class="form_field">
						<span class="form_field_label">Description:</span>
						<div class="form_field_input"><div class="size_item"><c:out value="${beverage.getDescription() }"></c:out></div>
						</div>
						</div>
						

						<div class="form_field">
						
						<div class="form_field_label">Possible sizes:</div> 
							<div class="form_field_input">
							<c:forEach var="oneTemperatureDrinkSize" items="${beverage.getPossibleTemperaturesDrinkSizes() }">
							
								
								
								<div class="size_item">
								
							
							
								<li style="list-style-type:none" class="lowercase">
								<div>
								
									<c:if test="${oneTemperatureDrinkSize.getTemperatureDrinkSize().getTemperature().getId().equals(1) }"><span class="subtitle">🔥</span></c:if>
									<c:if test="${oneTemperatureDrinkSize.getTemperatureDrinkSize().getTemperature().getId().equals(2) }"><span class="subtitle">🧊</span></c:if>
									<c:if test="${oneTemperatureDrinkSize.getTemperatureDrinkSize().getTemperature().getId().equals(3) }"><span class="subtitle">🥤</span></c:if>
									${oneTemperatureDrinkSize.getTemperatureDrinkSize().getDrinkSize().getName() } 
									<span class="subinfo" style="padding-right:5px;">
										(${oneTemperatureDrinkSize.getTemperatureDrinkSize().getDrinkSize().getVolume() } oz, 
										${oneTemperatureDrinkSize.getTemperatureDrinkSize().getTemperature().getName() })
									</span> 
									-<span style="padding-left:5px;">$ ${oneTemperatureDrinkSize.getBasePrice() }</span>
									
								</li>
								</div>
								</c:forEach>
						</div>
						
						</div>
						


					</div>
				
					
			<div class="one_form">		
			


<div class="bottom_links"><a href="/beverages/${beverage.getId() }/edit"><span class="button"><button>Edit Beverage</button></span></a>

<form:form action="/beverages/${beverage.getId() }" method="POST">
	<input type="hidden" name="_method" value="DELETE" />
	
	<span class="button"><button>Delete Beverage</button></span>
	</form:form></div>

		</div>
			</div>
		</div>
		
	</div>
	</div>
</body>


</html>