<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Login</title>
<link rel="shortcut icon" href="./../favicon.ico?" type="image/x-icon" />
</head>
<body>
	<div align="center">
		<h1>Book Store Adminstration</h1>
		<h2>Admin Login</h2>
		
		<c:if test = "${not empty message}">
			<div align="center">
				<h4>${ message }</h4>	
			</div>
		</c:if>
		
		<form id="formLogin" action="login" method="post">
				<table>
					<tr>
						<td align="right">Email:</td>
						<td align="left"><input type="text" id="userEmail" name="userEmail" size="20"/></td>
					</tr>
					<tr>
						<td align="right">Password:</td>
						<td align="left"><input type="password" id="password" name="password" size="20"/></td>
					</tr>
					<tr><td>&nbsp;</td></tr>
					<tr>
						<td colspan="2" align="center">
							<button type="submit">Login</button>
						</td>
						<td></td>
					</tr>
				</table>
			</form>
			
	</div>
</body>
</html>