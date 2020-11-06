<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<! DOCTYPE html>
<html>
<head>
	<title>The Barber Shop</title>
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/logo.png" />
<!-- 	<link href='https://fonts.googleapis.com/css?family=Fredericka the Great' rel='stylesheet'> -->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<body>
<%-- <%@ include file="header.jsp"%> --%>
	<div class = content>
		<div class="button">
			<input class="buttonuser" type = "button" value = "Are You a User ??" onclick="location.href = 'home/showUserLogin';"/> 
			<input class="buttonbarber" type = "button" value = "Are You a Barber ??" onclick="location.href = 'home/showBarberLogin';"/>
		</div>
	</div>
		<div class="parallax1">
	</div>
	<div id="about">
		Hey You Are at about!
	</div>
	<div class="parallax2">
	</div>
	<div id="services">
		Hey You Are at services!
	</div>
	<div class="parallax3">
	</div>
	<div id="contactus">
		Hey You Are at contact us!
	</div>
	<div class="parallax4">
	</div>
<%-- <%@ include file="footer.jsp" %> --%>
</body>
</html>