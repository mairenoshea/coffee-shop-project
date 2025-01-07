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
<title>Login and Registration</title>
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
		<h1 class="centered_header_title">Welcome to Hallowed Ground!</h1>
		
	</div>
		<div class="form_container">
			<div class="one_forms_styling">
				<form:form action="/register" method="POST" modelAttribute="newUser"
					divClass="one_form">
					<h2 class="form_title">Register</h2>

					<div class="form_field">
					<div class="form_field_label">
						<form:label path="firstName">First Name: </form:label></div>
						<div class="form_field_input">
						<form:input path="firstName" name="firstName"></form:input></div>
					</div>
					<div class="error">
						<form:errors path="firstName" />
					</div>
					<div class="form_field">
					<div class="form_field_label">
						<form:label path="lastName">Last Name: </form:label></div>
						<div class="form_field_input"><form:input path="lastName" name="lastName"></form:input></div>
					</div>
					<div class="error">
						<form:errors path="lastName" />
					</div>

					<div class="form_field">
					<div class="form_field_label">
						<form:label path="email">Email: </form:label></div>
<div class="form_field_input">
						<form:input path="email" name="email"></form:input></div>
					</div>
					<div class="error">
						<form:errors path="email" />
					</div>
					<div class="form_field">
					<div class="form_field_label">
						<form:label path="password">Password: </form:label></div>
<div class="form_field_input">
						<form:input path="password" type="password" name="password"></form:input></div>
					</div>
					<div class="error">
						<form:errors path="password" />
					</div>
					<div class="form_field">
						<div class="form_field_label">
						<form:label path="confirm">Confirm Password: </form:label></div>
	<div class="form_field_input">
						<form:input path="confirm" type="password" name="confirm"></form:input></div>
					</div>
					<div class="error">
						<form:errors path="confirm" />
					</div>
					<div class="form_field_submit">
						<input type="submit" value="Register" />
					</div>
				</form:form>
			</div>
			<div class="one_forms_styling">

				<form:form action="/login" method="POST" modelAttribute="newLogin"
					divClass="one_form">

					<h2 class="form_title">Login</h2>
					<div class="form_field">
						<div class="form_field_label">
							<form:label path="email">Email: </form:label>
						</div>

						<div class="form_field_input">
							<form:input path="email" name="email"></form:input>
						</div>

					</div>
					<div class="error">
						<form:errors path="email" />
					</div>
					<div class="form_field">
						<div class="form_field_label">
							<form:label path="password">Password: </form:label>
						</div>

						<div class="form_field_input">
							<form:input path="password" type="password" name="password"></form:input>
						</div>
					</div>
					<div class="error">
						<form:errors path="password" />
					</div>
					<div class="form_field_submit">
						<input type="submit" value="Log In" />
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="/js/app.js"></script>
</body>
</html>

