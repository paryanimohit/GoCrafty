<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="footer.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/logo.png" />
<meta charset="ISO-8859-1">
<title>${instructorlist.firstName} - Profile</title>
</head>
<body>
<div id="profileHeader">
	<div id="logo">
		<a href="${pageContext.request.contextPath}/home/instructor/viewProfile">
		<img class="plo"src="${pageContext.request.contextPath}/resources/images/TBS.png"></a>
	</div> 
	<div id="header-links">
		<div id="headerLink"><a href="logOut">Log Out</a></div>
		<div id="headerLink"><a href="showEditProfile">Edit Profile</a></div>	
		<div id="headerLink"><a href="#deleteForm" onclick="showPasswordPrompt();"id="deleteButton">Delete Profile</a></div>
		<input class="buttonprofile"type="button" onclick="location.href ='${pageContext.request.contextPath}/courses/showCategories?'; buttonAnimation();" value="Browse Course Catalog"/>
		<div id="headerLink"><a href="addCourse">Add Course</a></div>
	</div>
	<div id="name">
		Hello ${instructorlist.firstName}
	</div>
	<div id="pp">
		<c:if test="${img == 'failed'}">
    		<a href="#ppedit" onclick="showForm();"><img class="psize" src = "${pageContext.request.contextPath}/resources/images/profile-picture.jpg"></a>
		</c:if>
		<c:if test="${img != 'failed'}">
   			<a href="#ppedit" onclick="showForm();"><img class="psize"src="data:image/jpg;base64,${img}"/>	</a>
		</c:if>
	</div>
	<div id = "ppedit" style="visibility: hidden;">
        <form method="post" action="${pageContext.request.contextPath}/home/instructor/doUpload" enctype="multipart/form-data" >
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
</div>
<div id = "deleteForm" style="visibility: hidden">
	<form action="deleteProfile" method="get">
		<label>Password</label>
		<input id="formd" type="password" name = "password">
		<input class="genericbutton" type="submit" value="Confirm">
	</form>
</div>
				
<div id="container" onclick="hideForm();">
	<div id="Profile">
		<div id="namelgo">User Name:${instructorist.firstName} ${instructorlist.lastName} </div>
 		<div id="emaillgo">Email Id:${instructorlist.email} </div>
	</div>
</div>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
</body>
</html>