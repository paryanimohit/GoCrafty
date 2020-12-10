<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="student-header.jsp"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/logo.png" />
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<title>${theCourse.getName()}</title>
</head>
<body>
<div class = "content">

<h2 align="center">${theCourse.getName()}</h2>

<div class = "leftCourse">
<c:if test="${fn:containsIgnoreCase(param.vId, 'Video')}">
<iframe width="800" height="480" src="https://www.youtube.com/embed/${embededLink}" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
</c:if>
<c:if test="${fn:containsIgnoreCase(param.vId, 'Quiz')}">
<iframe src="https://docs.google.com/forms/d/e/${embededLink}/viewform?embedded=true" width="800" height="480" frameborder="0" marginheight="0" marginwidth="0"></iframe>
</c:if>
</div>

<div id = rightbox>

	<c:if test="${videos.containsKey('null')}">
	<div class = "rightCourse">
	Seems like Instructor has not added videos yet!
	</div><br>
	</c:if>

	<c:if test="${ !videos.containsKey('null')}">
	<c:forEach items="${videos}" var="theVideo">
	<div class = "rightCourse">
  	<a href="${pageContext.request.contextPath}/home/course/course-home-student?courseId=${courseId}&vId=${theVideo.key}">
  	Title: ${theVideo.key}</a>
  	</div><br>
	</c:forEach>
	</c:if>
<div class = "rightCourse">
<c:if test="${videos.containsKey('null')}">
Seems like Instructor has not added videos yet!
</c:if>
<c:if test="${ !videos.containsKey('null')}">
<c:forEach items="${videos}" var="theVideo">
  <a href="${pageContext.request.contextPath}/home/course/course-home-student?courseId=${courseId}&vId=${theVideo.key}&certificate=${certificate}">
  Title: ${theVideo.key}</a><br>
</c:forEach>
</c:if>
</div><br>

<div class = "rightCourse">
	Course Duration: ${theCourse.getEstimatedTimeToComplete()}<br>
	Instructor: ${instructorName.get(String.valueOf(theCourse.getInstructor_id()))}<br>
	Category: ${theCourse.getCategory()}<br>
	Description: ${theCourse.getDescription() }<br>
</div><br>

<div class = "rightCourse">
<a href="${pageContext.request.contextPath}/home/course/generateCertificate?courseId=${courseId}&vId=${param.vId}"><h3 align="center">Complete and Generate Certificate</h3></a>
<c:if test="${certificate == 'yes'}">
<a href="${pageContext.request.contextPath}/resources/certificate.pdf"}">Download Certificate</a>
</c:if>
<c:if test="${certificate == 'no'}">
<a>Cannot download Certificate. Contact Admin!</a>
</c:if>
</div><br>

<div class="rightCourse">
Contact Professor!<br>
<a href="mailto:name@rapidtables.com">Send mail</a>
</div><br>
</div>
</div>
</body>
</html>