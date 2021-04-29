<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Manage Users - Evergreen BookStore Administration</title>
	<link rel="shortcut icon" href="./../favicon.ico?" type="image/x-icon" />
	<link rel="stylesheet" href="../css/style.css" />
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h1 class="pageheading">Users Management</h1>
		<a href="user_form.jsp">Create New User</a>
	</div>
	
	<c:if test = "${not empty message}">
		<div align="center">
			<h4><i>${message}</i></h4>
		</div>
	</c:if>
	
	<div align="center">
		<table> <!-- border="1" cellpadding="5" -->
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>Email</th>
				<th>Full Name</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="user" items="${listUsers}" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${user.userId}</td>
					<td>${user.email}</td>
					<td>${user.fullName}</td>
					<td>
						<a href="edit_user?id=${user.userId}">Edit</a>
						<!-- <a href="delete_user?id=${user.userId}" >Delete</a> -->
						<a href="javascript:confirmDelete(${user.userId})" >Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">
	function confirmDelete(userId) {
		if(confirm("Do you want to delete user with ID "+userId+"?")) {
			window.location = 'delete_user?id='+userId;
		}
	}
</script>
</html>