<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@attribute name="header" fragment="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title><jsp:invoke fragment="header"></jsp:invoke> - BlackJack</title>
<link rel="stylesheet" type="text/css" href="styles/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="_css/styles.css">
<script src="_js/lib/complaint.js"></script>
</head>
<body>
	<div id="content">
		<div id="container">
			<row>
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<row id="part">
				<div id="pageheader" class="col-md-12">
					<div class="col-md-7"></div>
					<div class="col-md-5 text-right">
						<br />
						<div>
							<c:choose>
								<c:when test="${not empty sessionScope.user }">
									<h2>You are ${sessionScope.user.getLogin() }</h2>
									<a href="logout" class="btn btn-info" role="button">Log
										out</a>
								</c:when>
								<c:otherwise>
									<form method="post" action="login">
										&nbsp&nbspLogin:<input type="text" name="username" ><br />
										Password:<input type="password" name="password" 
										style="margin-top: 10px;"><br />
											 <input type="submit" style="margin-top: 10px;" class="btn btn-info"
											value="Submit"></input>
									</form>
								</c:otherwise>
							</c:choose>
						</div>
						<br />
					</div>
				</div>
				<row>
				<div class="btn-group btn-group-justified">
					<div class="btn-group">
						<a href="game.jsp" class="btn btn-success" role="button">Play
							Game</a>
					</div>
					<c:choose>
						<c:when test="${not empty sessionScope.user }">
							<div class="btn-group">
								<a href="user" class="btn btn-success" role="button">My
									Profile</a>
							</div>
							<div class="btn-group">
								<a href="complain" class="btn btn-success" role="button">Contact
									us</a>
							</div>
						</c:when>
						<c:otherwise>
							<a href="register" class="btn btn-success" role="button">Register</a>
						</c:otherwise>
					</c:choose>
				</div>
				</row> </row>
				<row>
				<div id="body" class="col-md-12" >
					<jsp:doBody />
				</div>
				</row>
			</div>
			<div class="col-md-2"></div>
			</row>
		</div>
	</div>
</body>
</html>