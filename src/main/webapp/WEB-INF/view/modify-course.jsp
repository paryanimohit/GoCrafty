<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="instructor-header.jsp"%>
<%@ include file="footer.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/logo.png" />
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<meta charset="ISO-8859-1">
<title>${course.name} Modify Course</title>
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
		<div id="headerLink"><a href="${pageContext.request.contextPath}/home/course/showCourseHomeToInstructor"></a></div>
	</div>
</header>
<div class="content" onclick="hideForm();">
	<div class="modify-course">
		<form:form action="${pageContext.request.contextPath}/home/course/modifyCourse" modelAttribute="course" method="post">
		<div id="leftSgn"><label>Course Name </label></div>
		<div id="rightSgn"><form:input id="forminputbox" path="name" required="required" value="${course.name}"/></div>
		<div id="leftSgn"><label>Course Description </label></div>
		<div id="rightSgn"><form:input id="forminputbox" path="description" required="required" value="${course.description}"/></div>
		<div id="leftSgn"><label>Course Duration </label></div>
		<div id="rightSgn"><form:input id="forminputbox" path="estimatedTimeToComplete" required="required" value="${course.estimatedTimeToComplete}"/></div>
		<div id="leftSgn"><label>Category </label></div>
		<div id="rightSgn">
			<form:select id="category" path="category" required="required" value="${course.category}">
				<form:option id="category" value="Drawing" label="Drawing"/>
				<form:option id="category" value="Painting" label="Painting"/>
				<form:option id="category" value="Calligraphy" label="Calligraphy"/>
				<form:option id="category" value="Sketching" label="Sketching"/>
				<form:option id="category" value="Portraiture" label="Portraiture"/>
				<form:option id="category" value="Water Color" label="Water Color"/>
				<form:option id="category" value="Pencil Drawing" label="Pencil Drawing"/>
				<form:option id="category" value="Oil Painting" label="Oil Painting"/>
				<form:option id="category" value="Illustrations" label="Illustrations"/>
				<form:option id="category" value="Art for Kids" label="Art for Kids"/>
				<form:option id="category" value="Crafting" label="Crafting"/>
		   	</form:select>
		</div>
		<div id="leftSgn"><input class="signupbutton" type="submit" value="Modify Course"></div>
		</form:form>
	</div>
	
	<c:if test="${videoListSize} == '0'">
	<div class="modifyVideos">
		You Do not have any videos uploaded. Do you want to add videos? 
		</div>
	</c:if>
	
	<c:if test="${videoListSize} != '0'">
	<div class="modifyVideos">
			<form action = "modifyVideos" method = "post">
				LOOP:
				<label>Video Name</label>
				<input type="text" name="videoName" value="${videoList.videoName}"/>
				<label>Video URL</label>
				<input type="text" name="videoURL" value="${videoList.videoURL}"/>
			</form>
	 </div>
	 </c:if>
	 
	 <div class="modifyQuiz">
			<form action = "modifyQuiz" method = "post">
				LOOP:
				<label>Quiz Name</label>
				<input type="text" name="quizName" value="${quizList.quizName}"/>
				<label>Video URL</label>
				<input type="text" name="quizURL" value="${quizList.quizURL}"/>
			</form>
	 </div>
</div>
</c:if>
</body>
</html>