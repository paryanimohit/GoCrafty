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
<title>${student.firstName} - Profile</title>
</head>
<body>
<c:if test="${tempSession.id != 'temp'}">	
<header>
	<div id="leftHeader">
		<a href="${pageContext.request.contextPath}/home/student/viewProfile">
		<img class="logoprop"src="${pageContext.request.contextPath}/resources/images/logo.png"></a>
	</div> 
	<div id="header-links">
		<div id="profilePic">
			<c:if test="${img == 'failed'}">
    			<a href="#ppedit" onclick="showForm();"><img class="profilePicProps" src = "${pageContext.request.contextPath}/resources/images/profile-picture.jpg"></a>
			</c:if>
			<c:if test="${img != 'failed'}">
   				<a href="#ppedit" onclick="showForm();"><img class="profilePicProps"src="data:image/jpg;base64,${img}"/>	</a>
			</c:if>
			Hello ${student.firstName}
		</div>
		<div id="headerLink"><a href="/GoCrafty/home/authentication/logOut">Log Out</a></div>
		<div id="headerLink"><a href="showEditProfile">Edit Profile</a></div>
		<div id="headerLink"><a href="#deleteForm" onclick="showPasswordPrompt();"id="deleteButton">Delete Profile</a></div>
		<div id="headerLink"><a href = "${pageContext.request.contextPath}/home/course/showCategories">Browse Course Catalog</a></div>
	</div>
</header>
<div class = "content" onclick="hideForm();">
<div id = "ppedit" style="visibility: hidden;">
        		<form method="post" action="${pageContext.request.contextPath}/home/student/doUpload" enctype="multipart/form-data" >
          		  <table id="upload">
                <tr>
                    <td><input type="file" name="fileUpload" accept='image/*'  /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Upload" /></td>
                </tr>
            </table>
        	</form>
    	</div>			
		<div id = "deleteForm" style="visibility: hidden">
			<form action="deleteProfile" method="get">
				<label>Password</label>
				<input id="formd" type="password" name = "password">
				<input class="genericbutton" type="submit" value="Confirm">
			</form>
		</div>
		
	<div id="profileContainer" onclick="hideForm();">
				<div id="namelgo">Hello ${student.firstName} ${student.lastName}, Welcome back!</div>
 				<div id="emaillgo">Your registered email address is: <b>${student.email}</b> </div>
 				<c:if test="${student.applyForJob =='1'}">
 					<div id="jobslgo">Your Active Services: 
 						<ol>
 							<li>Apply For Jobs: <b>Active</b> </li>
 						</ol>
 					</div>
 				</c:if>
 				<div id="birthlgo">Birthday: <b>${student.birthDate}.</b><br> 
 				<i>Get exciting offers from team GoCrafty on your Birthday! </i></div>
 				<div id="logslgo">Last Login: ${student.logs}</div>
 		</div>

			<c:forEach var="theCourse" items="${enrolledCourses}">
			<br>Course Name: ${theCourse.getName()}<br>
				Estimated time to complete:  ${theCourse.getEstimatedTimeToComplete()}<br>
				Instructor name: ${instructorName.get(String.valueOf(theCourse.getInstructor_id()))}<br>
				Category: ${theCourse.getCategory()}
				
			
			</c:forEach> 		
 </div>
</c:if>
</body>
</html>