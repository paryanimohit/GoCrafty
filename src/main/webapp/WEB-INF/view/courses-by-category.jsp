<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
	<br>
	Instructor name: ${instructor.get(String.valueOf(theCourse.getInstructor_id()))}
	<input type="button" id="sbutton" value="View Course" onclick="location.href = '${pageContext.request.contextPath}/home/course/viewCourse?id=${theCourse.getId()}';">
	</c:forEach>


</body>
</html>