<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Login</title>
</head>
<body>

<form:form action="${role}/login" modelAttribute="theUser" method="POST">
		<table id="lgtbl">
			<tbody>
				<tr>
					<td class="email"><label>Email</label></td>
					<td><form:input  path="email" name="email" placeholder="Enter your e-mail" class="emailbox" required="required"/></td>
				</tr>
				<tr>	
					<td class="password"><label>Password</label></td>
					<td><form:password maxlength="20" path="password" name="password" placeholder="Enter your password" class="passwordbox" required="required"/></td>
				</tr>
					<tr>	
					<td><input class="loginbutton" type="Submit" value= "Log In"/></td>
				</tr>
				<tr>
					<td class="linklog"><a href = "student/addStudent">Create an account</a></td>
				</tr>
			
			</tbody>
		</table>
	</form:form>

</body>
</html>