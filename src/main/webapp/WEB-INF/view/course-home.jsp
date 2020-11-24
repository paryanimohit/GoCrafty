<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="footer.jsp" %> 
   
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/logo.png" />
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<meta charset="ISO-8859-1">
<title>${course.name}</title>
</head>
<body>
<c:if test="${tempSession.id != 'temp'}">	
<header>
<div id="leftHeader">
		<a href="${pageContext.request.contextPath}/home/course/showCourseHomeToInstructor">
		<img class="logoprop"src="${pageContext.request.contextPath}/resources/images/logo.png"></a>
</div>
	<div id="header-links">
	<div id="profilePic">
		Hello ${tempSession.firstName}
	</div>
		<div id="headerLink"><a href="#deleteForm" onclick="showPasswordPrompt();"id="deleteButton">Delete Course</a></div>
		<div id="headerLink"><a href = "${pageContext.request.contextPath}/home/instructor/showModifyCourse">Modify Course</a></div>
	</div>
</header>
<div class = "content">
		<div id = "deleteForm" style="visibility: hidden">
			<form action="deleteCourse" method="get">
				<label>Password</label>
				<input id="formd" type="password" name = "password">
				<input class="genericbutton" type="submit" value="Confirm">
			</form>
		</div>
	
		<div id="courseContainer" onclick="hideForm();">
			<div id="courseHead">Welcome to the ${course.name}</div>
			<div id="leftContainer">
				<div id="course-analytics">
				COURSE ANALYTICS
					<div>Course Description: ${course.description}</div>
					<div>Course Duration: ${course.estimatedTimeToComplete}</div>
					<div>Category: ${course.category}</div>
 				</div>
 			</div>
 			<div id="rightContainer">
 				Number of Students Enrolled
 			</div>
 		</div>				
 </div>
</c:if>
</body>
</html>