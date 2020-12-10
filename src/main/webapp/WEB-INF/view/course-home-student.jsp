<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="student-header.jsp"%>
<%@ include file="footer.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${theCourse.getName()}</title>
</head>
<body>
<div class = "coursecontent">

<h2 align="center">${theCourse.getName()}</h2>

<div class = "leftCourse">
<c:if test="${fn:containsIgnoreCase(param.vId, 'Video')}">
<iframe width="800" height="480" src="https://www.youtube.com/embed/${embededLink}" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
</c:if>
<c:if test="${fn:containsIgnoreCase(param.vId, 'Quiz')}">
<iframe src="https://docs.google.com/forms/d/e/${embededLink}/viewform?embedded=true" width="800" height="480" frameborder="0" marginheight="0" marginwidth="0"></iframe>
</c:if>
</div>

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
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>