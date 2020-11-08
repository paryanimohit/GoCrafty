<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/logo.png" />
	
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<%@ include file="footer.jsp" %>
<meta charset="ISO-8859-1">
<title>${studentlist.firstName} - Profile</title>
</head>
<body>
<div id="profilelinks">
	<div id="plogo">
		<a href="${pageContext.request.contextPath}/home/student/viewProfile">
		<img class="plo"src="${pageContext.request.contextPath}/resources/images/TBS.png"></a>
	</div> 
	<div id="pl">
	<div id="link"><a href="logOut">Log Out</a></div>
	<div id="link"><a href="showEditProfile">Edit Profile</a></div>
	<div id="link"><a href="#deleteForm" onclick="showPasswordPrompt();"id="deleteButton">Delete Profile</a></div>
	<input class="buttonprofile"type="button" onclick="location.href ='${pageContext.request.contextPath}/home/barberShop/showBarberShops?city=${userlist.city}'; buttonAnimation();" value="Book a Reservation"/>
	<div id="link"><a href="viewAppointments">View Appointments</a></div>
	</div>
	<div id="name">
	Hello ${studentlist.firstName}
		</div>
	<div id="pp"><c:if test="${img == 'failed'}">
    				<a href="#ppedit" onclick="showForm();"><img class="psize" src = "${pageContext.request.contextPath}/resources/images/profile-picture.jpg"></a>
				</c:if>
				<c:if test="${img != 'failed'}">
   					<a href="#ppedit" onclick="showForm();"><img class="psize"src="data:image/jpg;base64,${img}"/>	</a>
				</c:if>
	</div>
		
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
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
</body>
</html>