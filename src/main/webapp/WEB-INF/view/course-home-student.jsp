<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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


</body>
</html>