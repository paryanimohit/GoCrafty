<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- <%@ include file="instructor-header.jsp" %> --%>
<%@ include file="footer.jsp" %> 
   
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/logo.png" />
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<meta charset="ISO-8859-1">
<title>${myCourse.name}</title>
</head>
<body>
<c:if test="${tempSession.id != 'temp'}">	
<header>
	<div id="header-links">
		<div id="profilePic">
			<c:if test="${img == 'failed'}">
    			<img class="profilePicProps" src = "${pageContext.request.contextPath}/resources/images/profile-picture.jpg">
			</c:if>
			<c:if test="${img != 'failed'}">
   				<img class="profilePicProps"src="data:image/jpg;base64,${img}"/>
			</c:if>
			Hello ${instructor.firstName}
		</div>
		<div id="headerLink"><a href="#deleteForm" onclick="showPasswordPrompt();"id="deleteButton">Delete Course</a></div>
		<div id="headerLink"><a href = "${pageContext.request.contextPath}/home/course/showCategories">Modify Course</a></div>
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
				COURSE ANALYTICS
 			</div>
 			<div id="rightContainer">
 				Number of Students Enrolled
 			</div>
 		</div>				
 </div>
</c:if>
</body>
</html>