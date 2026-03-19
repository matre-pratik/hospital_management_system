<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Create User</title>
<link rel="stylesheet" href="css/style.css">
</head>

<body>

	<div class="navbar">
		<h2>Admin Panel</h2>
		<div>
			<a href="adminDashboard">Dashboard</a> 
			<a href="addPatient.jsp">Add Patient</a> 
			<a href="logout">Logout</a>
		</div>
	</div>

	<div class="container">
		<div class="card">

			<h2>Create User</h2>

			<%
			String success = (String) session.getAttribute("successMsg");
			String error = (String) session.getAttribute("errorMsg");

			if (success != null) {
			%>
				<div class="success"><%=success%></div>
			<%
			session.removeAttribute("successMsg");
			}

			if (error != null) {
			%>
				<div class="error"><%=error%></div>
			<%
			session.removeAttribute("errorMsg");
			}
			%>

			<form action="addUser" method="post">

				<div class="form-group">
					<label>Name</label>
					<input type="text" name="name" required>
				</div>

				<div class="form-group">
					<label>Email</label>
					<input type="email" name="email" required>
				</div>

				<div class="form-group">
					<label>Password</label>
					<input type="password" name="password" minlength="6" required>
				</div>

				<div class="form-group">
					<label>Role</label>
					<select name="role" required>
						<option value="">Select Role</option>
						<option value="STAFF">Staff</option>
						<option value="DOCTOR">Doctor</option>
					</select>
				</div>

				<button>Create User</button>

			</form>

		</div>
	</div>

</body>
</html>