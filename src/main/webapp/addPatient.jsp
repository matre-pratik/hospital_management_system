<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Add Patient</title>
<link rel="stylesheet" href="css/style.css">
</head>

<body>

	<div class="navbar">
		<h2>Admin Panel</h2>
		<div>
			<a href="adminDashboard">Dashboard</a> 
			<a href="addUser.jsp">Add User</a> 
			<a href="logout">Logout</a>
		</div>
	</div>

	<div class="container">
		<div class="card">
			<h2>Add Patient</h2>

			<form action="addPatient" method="post">

				<div class="form-group">
					<label>Name</label>
					<input type="text" name="name" pattern="[A-Za-z ]{3,}" required>
				</div>

				<div class="form-group">
					<label>Age</label>
					<input type="number" name="age" min="1" max="120" required>
				</div>

				<div class="form-group">
					<label>Gender</label>
					<select name="gender" required>
						<option value="">Select</option>
						<option>Male</option>
						<option>Female</option>
					</select>
				</div>

				<div class="form-group">
					<label>Phone</label>
					<input type="text" name="phone" pattern="[0-9]{10}" required>
				</div>

				<div class="form-group">
					<label>Address</label>
					<input type="text" name="address" required>
				</div>

				<button type="submit">Add Patient</button>

			</form>

		</div>
	</div>

</body>
</html>