<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/logo.png" />
<link href='https://fonts.googleapis.com/css?family=Fredericka the Great' rel='stylesheet'>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<title>Select Category</title>
</head>
<body >
<c:if test="${tempSession.id == 'temp'}">
<%@ include file="header.jsp" %>
</c:if>
<c:if test="${tempSession.id != 'temp'}">
<%@ include file="user-header.jsp"%>
</c:if>


<br><br><br><br><br><br><br><br><br><br>
<div id = left>

	<c:forEach var="cat" items="${category}">
	<br>
		<div id="shop"> 
			<form action = "${pageContext.request.contextPath}/home/course/showCourse" method = "post">
			<input type="hidden" value="${cat}" name="category">
			<div id="butt"><input type="submit" value="${cat}" id="sbutton" ></div></form>
		</div>
	</c:forEach>
	</div>
<%@ include file="footer.jsp" %>
</body>
</html>