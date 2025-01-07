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
<title>Hallowed Ground</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/css/main.css">
<!-- change to match your file/naming structure -->
<!-- <script src="/webjars/bootstrap/js/bootstrap.min.js" -->
<!-- 	type="text/javascript"></script> -->

<!-- change to match your file/naming structure -->
</head>
<body>
	<div class="container">

		<div class="centered_header">
			<div class="flex-3">
				<h1 class="centered_header_title">Welcome
					to Hallowed Ground!</h1>
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
		<div class="another_container">
			<div class="card_container">
				<div class="one_card">
					<a href="/menu">
						<div>
							<div class="emoji" onmouseover="eerieGlow(this)"
								onmouseout="removeGlow(this)">✨
							<h4 class="card_title">new order</h4>
							<h6 class="card_subtitle">begin anew</h6></div>
						</div>
					</a>
				</div>
				<div class="one_card">
					<a href="/order/favs"><div>
							<div class="emoji" onmouseover="eerieGlow(this)"
								onmouseout="removeGlow(this)">🔂
							<h4 class="card_title">reorder my favorite</h4>
							<h6 class="card_subtitle">add your favorite drink to your
								order</h6></div>
						</div> </a>
				</div>
				<div class="one_card">
					<a href="/order/add_item/random">
						<div>
							<div class="emoji" onmouseover="eerieGlow(this)"
								onmouseout="removeGlow(this)">🧚🏽
							<h4 class="card_title">surprise me!</h4>
							<h6 class="card_subtitle">add a random drink to your order</h6></div>
						</div>
					</a>

				</div>
				</div>

				<div class="card_container">
					<div class="one_card"
						style="flex: 1; margin-left: 15%; margin-right: 15%">
						<a href="/menu">
							<div>
								<div class="emoji" onmouseover="eerieGlow(this)"
									onmouseout="removeGlow(this)">☕️
								<h4 class="card_title">peruse the menu</h4>
								<h6 class="card_subtitle">see what strikes your fancy</h6></div>
							</div>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

	<script type="text/javascript" src="${jakarta.servlet.jsp.PageContext}/js/app.js"></script>
</html>

