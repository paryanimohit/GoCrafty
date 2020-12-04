<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${theCourse.getName()}</title>
</head>
<body>

<br>Course Name: ${theCourse.getName()}<br>
	Estimated time to complete:  ${theCourse.getEstimatedTimeToComplete()}<br>
	Instructor name: ${instructorName.get(String.valueOf(theCourse.getInstructor_id()))}<br>
	Category: ${theCourse.getCategory()}<br>
	Description: ${theCourse.getDescription() }<br>


<h2>Videos</h2>
<br>
<c:forEach items="${videos}" var="theVideo">
  <a href="${pageContext.request.contextPath}/home/course/course-home-student?courseId=${courseId}&vId=${theVideo.key}">
  Title: ${theVideo.key}</a><br>
</c:forEach>

<c:if test="${fn:containsIgnoreCase(param.vId, 'Video')}">
<iframe width="560" height="315" src="https://www.youtube.com/embed/${embededLink}" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
</c:if>
<c:if test="${fn:containsIgnoreCase(param.vId, 'Quiz')}">
<iframe src="https://docs.google.com/forms/d/e/${embededLink}/viewform?embedded=true" width="560" height="315" frameborder="0" marginheight="0" marginwidth="0"></iframe>
</c:if>
</body>
</html>