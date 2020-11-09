<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/logo.png" />
<meta charset="UTF-8">
<title>Create an account</title>
</head>
<body>
<%@ include file="header.jsp" %>
	
	<div id="Profile">
	<form:form action="${pageContext.request.contextPath}/home/student/studentSignup" modelAttribute="theStudent" method="post">
		<div id="leftSgn"><label>First Name: </label></div>
		<div id="rightSgn"><form:input id="formd" path="firstName" required="required"/></div>
		<br>
		<div id="leftSgn"><label>Last Name: </label></div>
		<div id="rightSgn"><form:input id="formd" path="lastName" required="required"/></div>
		<br>
		<div id="leftSgn"><label>E-mail: </label></div>
		<div id="rightSgn"><form:input id="formd" path="email" required="required"/></div>
		<br>
		<div id="leftSgn"><label>Password: </label></div>
		<div id="rightSgn"><form:password id="formd" path="password" required="required"/></div>
		<br>
		<div id="leftSgn"><label>Birth Date: </label></div>
		<div id="rightSgn"><form:input id="formd" type="date" path="birthDate" required="required"/></div>
		
		<div id="leftSgn"><label>Apply for Job </label></div>
		<div id="rightSgn"><form:checkbox path="applyForJob" value="1" /></div>
		
		<div id="leftSgn"><input class="genericbutton" type="submit" value="Create Account"></div>
	</form:form> 
	</div>
<%@ include file="footer.jsp" %>
</body>
</html>