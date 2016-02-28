<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><head>
</head>
<t:casualpage>
	<jsp:attribute name="header">
Registration</jsp:attribute>
	<jsp:body>
	<link rel="stylesheet" href="_css/login.css">
	<div class="register_container">
		<div class = "form_container">
		<c:choose>
		<c:when test="${not empty errorMessage }">
		<p class = "register_text">${errorMessage }</p>
		</c:when>
		<c:otherwise>		
		<p class = "register_text">Register on site</p>
		</c:otherwise>
		</c:choose>
		<form action="register" method="post">
			<label  class = "register_label">Nickname</label> <br>
			<input type="text" required autofocus class="text_input"
					name="nickname"></input><br> 
					<label class = "register_label">Email</label> <br>
					<input class="text_input"
					type="email" required autofocus name="email"></input><br> 
					<label class = "register_label">Password</label><br>
			<input type="password" required name="password" class="text_input"></input><br> 
			<label class = "register_label">Confirm password</label> <br>
			<input type="password" required name="confirmpassword" class="text_input"></input><br>
			<input class="register_btn" type="submit" value="Register!"></input><br>
		</form>
		</div>
	</div>
	</jsp:body>
</t:casualpage>