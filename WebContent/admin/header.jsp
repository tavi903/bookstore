<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<div align="center">
	<img src="../images/BookstoreAdminLogo.png"/>
	<div>
		Welcome, <c:out value="${sessionScope.userEmail}"></c:out> | <a href="logout">Logout</a>
		<br/><br/>
	</div>

	<div>
		<a href="list_users">Users</a> |
		<a href="categories">Categories</a> |
		<a href="books">Books</a> |
		<a href="customers">Customers</a> |
		<a href="reviews">Reviews</a> |
		<a href="orders">Orders</a> 
	</div>
</div>
