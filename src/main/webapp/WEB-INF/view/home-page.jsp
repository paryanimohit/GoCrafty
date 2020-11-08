<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp"%>
<%@ include file="footer.jsp"%>

<! DOCTYPE html>
<html>
<head>
	<title>Go Crafty</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/logo.png" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<body>
	<div class = content>
		<div class="button">
			<input class="button-student" type = "button" value = "For Students" onclick="location.href = 'home/showUserLogin?role=student';"/> 
			<input class="button-instructor" type = "button" value = "For Instructors" onclick="location.href = 'home/showUserLogin?role=instructor';"/>
		</div>
		<div id="about">
			Hey You Are at about!
		</div>
		<div id="services">
			Hey You Are at services!
		</div>
		<div id="contactus">
			Hey You Are at contact us!
		</div>
	</div>
</body>
</html>