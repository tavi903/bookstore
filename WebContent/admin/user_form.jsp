<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>User Form</title>
	<link rel="shortcut icon" href="./../favicon.ico?" type="image/x-icon" />
	<link rel="stylesheet" href="../css/style.css" />
</head>
<body>

	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<c:if test="${ user == null }">
			<h1 class="pageheading">Create New User</h1>
		</c:if>
		<c:if test="${ user != null }">
			<h1 class="pageheading">Edit User</h1>
		</c:if>
	</div>
	
	<div align="center">
		<c:if test="${user==null}">
			<form action="create_user" method="post" onsubmit="return validateFormInput()">
				<table>
					<tr>
						<!-- use fn:escapeXml to escape quotation marks -->
						<td align="right">Email:</td>
						<td align="left"><input type="text" id="email" name="email" size="20"/></td>
					</tr>
					<tr>
						<td align="right">Full Name:</td>
						<td align="left"><input type="text" id="fullName" name="fullName" size="20"/></td>
					</tr>
					<tr>
						<td align="right">Password:</td>
						<td align="left"><input type="password" id="password" name="password" size="20"/></td>
					</tr>
					<tr><td>&nbsp;</td></tr>
					<tr>
						<td colspan="2" align="center">
							<button type="submit" style="margin-right: 20px; width: 66px;">Save</button>
							<button type="button" onclick="javascript:history.go(-1)">Cancel</button>
						</td>
						<td></td>
					</tr>
				</table>
			</form>
		</c:if>
		<c:if test="${user!=null}">
			<form action="edit_user" method="post" onsubmit="return validateFormInput()">
				<input hidden="true" name="userId" value="${param.id}" />
				<table>
					<tr>
						<td align="right">Email:</td>
						<td align="left"><input type="text" id="email" name="email" value="${user.email}" size="20"/></td>
					</tr>
					<tr>
						<td align="right">Full Name:</td>
						<td align="left"><input type="text" id="fullName" name="fullName" value="${user.fullName}" size="20"/></td>
					</tr>
					<tr>
						<td align="right">Password:</td>
						<td align="left"><input type="password" id="password" name="password" value="${user.password}" size="20"/></td>
					</tr>
					<tr><td>&nbsp;</td></tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="Save"/>
							<input type="button" value="Cancel" onclick="javascript:history.go(-1)"/>
						</td>
						<td></td>
					</tr>
				</table>
			</form>
		</c:if>
	</div>
	
	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">

	// RFC 2822 regular expression for email
	function validateEmail(email) {
	    const re = new RegExp("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
	    return re.test(String(email).toLowerCase());
	}
	
	function validateFormInput() {
		var fieldEmail = document.getElementById("email");
		var fieldFullName = document.getElementById("fullName");
		var fieldPassword = document.getElementById("password");
		
		if(fieldEmail.value.length == 0) {
			alert("Email is required!"); 
			fieldEmail.focus(); 
			return false;
		}
		
		if(fieldFullName.value.length == 0) {
			alert("FullName is required!");
		    fieldFullName.focus();
		    return false;
		}
		
		if(fieldPassword.value.length == 0) {
			alert("Password is required!");
			fieldPassword.focus();
			return false;
		}

		if(!validateEmail(fieldEmail.value)) {
			alert("Email is not valid!");
			return false;
		}

		return true;
	}
</script>
</html>