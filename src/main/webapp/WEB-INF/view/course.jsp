<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Courses</title>
</head>
<body>

	<c:forEach var="theCourse" items="${course}">
	
	<br>Course Name: ${theCourse.getName()}
	Estimated time to complete:  ${theCourse.getEstimatedTimeToComplete()}
	Instructor Name: 
	</c:forEach>


</body>
</html>