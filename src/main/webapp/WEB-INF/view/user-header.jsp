<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>

<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/logo.png" />
	<link href='https://fonts.googleapis.com/css?family=Fredericka the Great' rel='stylesheet'>
<meta charset="ISO-8859-1">
<title>User - Header</title>
</head>
<body>
<div id=profilelinks>	
<div id="plogo">
		<a href="${pageContext.request.contextPath}/home/user/viewProfile">
		<img class="plo"src="${pageContext.request.contextPath}/resources/images/TBS.png"></a>
	</div>
	<div id="pl">
	<div id="link"><a href="${pageContext.request.contextPath}/home/user/viewProfile">Profile</a></div>
	<div id="link"><a href="${pageContext.request.contextPath}/home/user/logOut">Log Out</a></div>
	<div id="link"><a href="${pageContext.request.contextPath}/home/user/viewAppointments">View Appointments</a></div>
	</div>
</div>

</body>
</html>