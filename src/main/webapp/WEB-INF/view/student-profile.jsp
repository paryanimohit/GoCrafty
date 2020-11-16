
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
<title>${studentlist.firstName} - Profile</title>
</head>
<body>
<c:if test="${tempSession.id != 'temp'}">	
<header>
	<div id="leftHeader">
		<a href="${pageContext.request.contextPath}/home/student/viewProfile">
		<img class="logoprop"src="${pageContext.request.contextPath}/resources/images/logo.png"></a>
	</div> 
<<<<<<< HEAD
	<div id="pl">
	<div id="link"><a href="logOut">Log Out</a></div>
	<div id="link"><a href="showEditProfile">Edit Profile</a></div>
	<div id="link"><a href="#deleteForm" onclick="showPasswordPrompt();"id="deleteButton">Delete Profile</a></div>
	<input class="buttonprofile"type="button" onclick="location.href ='${pageContext.request.contextPath}/home/barberShop/showBarberShops?city=${userlist.city}'; buttonAnimation();" value="Book a Reservation"/>
	<div id="link">	<a href = "${pageContext.request.contextPath}/home/course/showCategories">Browse Course Catalog</a>
</div>
	</div>
	<div id="name">
	Hello ${studentlist.firstName}
=======
	<div id="header-links">
		<div id="profilePic">
			<c:if test="${img == 'failed'}">
    			<a href="#ppedit" onclick="showForm();"><img class="psize" src = "${pageContext.request.contextPath}/resources/images/profile-picture.jpg"></a>
			</c:if>
			<c:if test="${img != 'failed'}">
   				<a href="#ppedit" onclick="showForm();"><img class="psize"src="data:image/jpg;base64,${img}"/>	</a>
			</c:if>
			Hello ${studentlist.firstName}
>>>>>>> d2d1083471fedf795b4455414f0639c2054c6d69
		</div>
		<div id="headerLink"><a href="/GoCrafty/home/logOut">Log Out</a></div>
		<div id="headerLink"><a href="showEditProfile">Edit Profile</a></div>
		<div id="headerLink"><a href="#deleteForm" onclick="showPasswordPrompt();"id="deleteButton">Delete Profile</a></div>
		<div id="headerLink"><a href = "${pageContext.request.contextPath}/home/course/showCategories">Browse Course Catalog</a></div>
		<div id="headerLink"><a href="viewAppointments">View Appointments</a></div>
	</div>
</header>
<div class = "content">
		<div id = "ppedit" style="visibility: hidden;">
        		<form method="post" action="${pageContext.request.contextPath}/home/user/doUpload" enctype="multipart/form-data" >
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
				
<div id="bd" onclick="hideForm();">
			<div id="Profile">
				<div id="namelgo">User Name:&nbsp;&nbsp;&nbsp;&nbsp;&emsp;${studentlist.firstName} ${studentlist.lastName} </div>
 				<div id="emaillgo">Email Id:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&emsp;${studentlist.email} </div>
 				<div id="birthlgo">Birth Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&emsp;${studentlist.birthDate} </div>
 				
 			</div>
 			
 		</div>
 </div>
</c:if>
</body>
</html>