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
<title>My Account</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/css/main.css" />
<!-- change to match your file/naming structure -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="app.js"></script>
<!-- change to match your file/naming structure -->
</head>
<body>
	<div class="container">
		
	<div class="centered_header">
			<div class="flex-3">
				<h1 class="eerie_glow" style="text-shadow: 2px 2px 5px black">Your Cart</h1>
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
    
                
    
    <div class="list_container">
        <h2 class="list_title">Your Order</h2>
        <div class="one_order"> <c:forEach var="beverage" items="${order.getBeverages() }">
            <div class="order_bold_bar">
           
                <div class="flex-2">
                    
                    <div class="order_main_item">${beverage.getTemperatureDrinkSize().getTemperatureDrinkSize().getDrinkSize().getName() } ${beverage.getName() } </div>
                </div>
                <div class="flex-1">${beverage.getTemperatureDrinkSize().getBasePrice() } </div>
                <div class="flex-1">
                    <div class="fav_box">
                    	<span class="base_price"></span>
                    	<form:form action ="/order/${beverage.getId() }" method="POST">
             				<input type="hidden" name="_method" value="DELETE" />
                    <button onmouseover="eerieGlow(this)" onmouseout="removeGlow(this)" >❌</button>
                    </form:form>
                    </div>
            </div>

                </div>
            </c:forEach>
        </div>
        <div class="order_total"><h4 class="order_main_item">order total:</h4> <h4 class="order_main_item"></h4></div>
        <div class="form_field_submit">
            <button><div class="fav_button" style="font-size:16px">checkout</div></button>
            
            
        </div>
    </div>

</div>
	<script type="text/javascript" src="app.js"></script>
	</div>
</body>
</html>

