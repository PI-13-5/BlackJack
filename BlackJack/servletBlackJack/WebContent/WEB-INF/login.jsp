<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:casualpage>
<jsp:attribute name="header">Log in</jsp:attribute>
	<jsp:body>
	<link rel="stylesheet" href="_css/login.css">
	<div class="register_container" id="login_container">
	<div class="form_container" id = "form_login">
		<form action="login" method="post">
		<br/>
			<label class="register_label">Login</label><br/> 
			<input type="text" name="username" required
				autofocus class="text_input"></input><br> 
				<label class = "register_label">Password</label></br> 
				<input
				type="password" name="password" required class="text_input"></input><br/>
				<input class="register_btn" type="submit" value="Log in"></input><br/>
		</form>
		<label class="register_label"> ${error}</label><br/>
	</div>
	</div>
	</jsp:body>
</t:casualpage>
