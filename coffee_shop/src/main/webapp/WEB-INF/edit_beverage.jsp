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
<title>edit beverage - <c:out
		value="${existingBeverage.getName() }"></c:out></title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${jakarta.servlet.jsp.PageContext}/js/app.js"></script>

<!-- change to match your file/naming structure -->
</head>
<body>
	<div class="container">
		<div class="centered_header">
			<div class="flex-3">
				<h1 class="centered_header_title">
					edit beverage -
					<c:out value="${existingBeverage.getName() }"></c:out>
				</h1>
			</div>
			<div class="right_top_links_BAR">
				<div class="right_top_links_bar_ROW">
					<h5>
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
			<div class="one_forms_styling">
				<form:form action="/beverages/${existingBeverage.getId() }/edit"
					method="POST" modelAttribute="existingBeverage" divClass="one_form">
					<input type="hidden" name="_method" value="PUT" />
					
					<form:input type="hidden" path="isTemplate" value="true" />
					<h4 class="form_title">Edit Name & Description</h4>
					<div class="form_field">
						<div class="form_field_label">
							<form:label path="name">Name: </form:label>
						</div>
						<div class="form_field_input">
							<form:input path="name" name="name"></form:input>
						</div>
					</div>
					<div class="error">
						<form:errors path="name" />
					</div>

					<div class="form_field">
						<div class="form_field_label">
							<form:label path="description">Description: </form:label>
						</div>
						<div class="form_field_input">
							<form:textarea path="description"></form:textarea>
						</div>
					</div>

					<div class="error">
						<form:errors path="description" />
					</div>
					<div class="form_field_submit">
			<input type="submit" value="update" />
		</div>
		
					</form:form>
					</div>
					
					
					<div class="one_forms_styling">
					
				
					<h4 class="form_title">Set Prices for Existing Temperatures and Sizes</h4>
					
					<c:forEach var="oneBevTempDS" items="${existingBeverage.getPossibleTemperaturesDrinkSizes() }">
					
					<form:form action="/bevTempDS/${oneBevTempDS.getId() }" method="POST" modelAttribute="bevTempDS" class="one_form" onMouseOver="makeActive(this)" onMouseOut="deActivate(this)" >
					
						<input type="hidden" name="_method" value="PUT" />
						
						<form:input path="beverage" value="${existingBeverage.getId() }" type="hidden"/>
						<input type="hidden" value="${existingBeverage.getId() }" name="beverage_id" />
						<div class="flex_container">
						<div class="flex-2">
						<form:label path="temperatureDrinkSize" class="subtitle" >${oneBevTempDS.getTemperatureDrinkSize().getTemperature().getName() }, ${oneBevTempDS.getTemperatureDrinkSize().getDrinkSize().getName() } <div class="subinfo">(${oneBevTempDS.getTemperatureDrinkSize().getDrinkSize().getVolume() } oz)</div></form:label>
						</div>
						
						<form:input path="temperatureDrinkSize" value="${oneBevTempDS.getTemperatureDrinkSize().getId() }" type="hidden"/>
						<form:input path="id" value="${oneBevTempDS.getId() }" type="hidden" />
						
						<span class="subtitle">$ </span>
						
						<div class="flex-1">
							<c:if test="${oneBevTempDS.getBasePrice() == null }"> 
							<form:input path="basePrice" value="0" style="width:60px"  />
							</c:if>
							<c:if test="${oneBevTempDS.getBasePrice() != null }">
							<form:input path="basePrice" value="${oneBevTempDS.getBasePrice() }" style="width:60px"  /></c:if>
					</div>
					<div class="flex-2">
						<input type="submit" value="set this price"  /></div>
						</div>
					</form:form >
					
					</c:forEach>
				
					
					
				</div></div>
<!-- 					<div class="form_container"> -->
<!-- 					<div class="one_forms_styling"> -->
					
				
<!-- 					<h4 class="form_title">Set Available Drink Sizes</h4> -->
					
					
					
<%-- 					<form:form action="/beverages/${existingBeverage.getId() }/edit" method="POST" modelAttribute="existingBeverage" class="one_form" > --%>
					
<!-- 						<input type="hidden" name="_method" value="PUT" /> -->
						
<%-- 						<form:input path="id" value="${existingBeverage.getId() }" type="hidden" /> --%>
<%-- 						<form:input path="name" value="${existingBeverage.getName() }" type="hidden"/> --%>
<%-- 						<form:input path="description" value="${existingBeverage.getDescription() }" type="hidden" /> --%>
<!-- 						<div class="flex_container"> -->
						
						
<%-- 							<c:forEach var="oneTemperature" items="${temperatures }"> --%>
							
<!-- 								<div class="flex-1" > -->
<%-- 								<div class="subtitle">${oneTemperature.getName() } sizes</div> --%>
							
<!-- 											<div> -->
<%-- 											<c:forEach var="oneTemperatureDrinkSize" items="${temperaturesDrinkSizes }"> --%>
<!-- 											<div> -->
<%-- 													<c:if test="${oneTemperatureDrinkSize.getTemperature().equals(oneTemperature)}"> --%>
														
<%-- 														<input type="checkbox" name="temperatureDrinkSizes_ids" value="${oneTemperatureDrinkSize.getId() }"/> --%>
														
<%-- 														<span class="lowercase">${oneTemperatureDrinkSize.getDrinkSize().getName()} --%>
														
<%-- 													<div class="subinfo">${oneTemperatureDrinkSize.getDrinkSize().getVolume() }oz</div></span> --%>
													
													
											
<%-- 													</c:if> --%>
<!-- 													</div> -->
													
													
											
<%-- 											</c:forEach></div></div> --%>
										
<%-- 								</c:forEach> --%>
<!-- 						</div> -->
						
<!-- 					<div class="flex-2"> -->
<!-- 						<input type="submit" value="submit"  /> -->
<!-- 						</div> -->
						
<%-- 					</form:form > --%>
					
<!-- 					</div> -->
				
					
					
				</div>
					
					
					
					
					
					
					
					
					

						
		
	
</body>

</html>

