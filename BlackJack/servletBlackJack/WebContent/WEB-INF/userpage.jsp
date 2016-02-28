<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:casualpage>
	<jsp:attribute name="header">User page</jsp:attribute>
	<jsp:body>
	<div class="register">
		<c:choose>
			<c:when test="${not empty sessionScope.user}">
<p class="label"> Your login</p>
<p class="value">${sessionScope.user.getLogin()} </p>
<p class="label"> Your money</p>
<p class="value">			${sessionScope.user.getBalance()} </p>
<p class="label">Your Email</p>
<p class="value">		${sessionScope.user.getEmail()} </p>
<p class="label"> Games Won</p>
<p class="value">		${sessionScope.user.getGames_won()} </p>
<p class="label"> Games Lost</p>
<p class="value">		${sessionScope.user.getGames_lost()} </p>
<p class="label"> Draws</p>
<p class="value">		${sessionScope.user.getGames_draws()} </p>
				<form action="game.jsp" method="get">
					<div class="chip_outer">
					<input class="chip_inner" type="submit" value="Play BlackJack!" />
					</div>
				</form>
			</c:when>
			<c:otherwise>
				<p>Please, login or register to view your profile</p>
				<a href="">Back to game</a>
			</c:otherwise>
		</c:choose>
	</div>
	</jsp:body>
</t:casualpage>
