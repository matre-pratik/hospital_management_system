<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.hms.dao.*, com.hms.model.*"%>

<%
User u = (User) session.getAttribute("user");

if (u == null || !u.getRole().equals("STAFF")) {
	response.sendRedirect("login.jsp");
	return;
}
%>

<%
PatientDAO pdao = new PatientDAO();
List<Patient> patients = pdao.getAllPatients();
%>

<%
UserDAO userDao = new UserDAO();
List<User> doctors = userDao.getUsersByRole("DOCTOR");
%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Book Appointment</title>

<style>
body {
	font-family: Arial, sans-serif;
	background: #f1f4f9;
	margin: 0;
}

/* Navbar */
.navbar {
	background: #2c3e90;
	color: white;
	padding: 15px 40px;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.navbar h2 {
	margin: 0;
}

.navbar a {
	color: white;
	text-decoration: none;
	margin-left: 20px;
	font-weight: bold;
}

/* Form Card */
.container {
	width: 450px;
	margin: 60px auto;
	background: white;
	padding: 30px;
	border-radius: 8px;
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}

.container h2 {
	text-align: center;
	margin-bottom: 25px;
}

/* Inputs */
label {
	font-weight: bold;
	display: block;
	margin-top: 15px;
}

select, input {
	width: 100%;
	padding: 10px;
	margin-top: 5px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

/* Button */
button {
	width: 100%;
	padding: 12px;
	background: #2c3e90;
	border: none;
	color: white;
	font-size: 16px;
	margin-top: 20px;
	border-radius: 5px;
	cursor: pointer;
	transition: 0.3s;
}

button:hover {
	background: #1d2a6b;
}

/* Error Message */
.error {
	color: red;
	font-size: 14px;
	margin-top: 5px;
	display: none;
}
</style>

</head>

<body>

	<div class="navbar">

		<h2>Staff Panel</h2>

		<div>
			<a href="staffDashboard">Dashboard</a> <a href="patientList">Patients</a>
			<a href="logout">Logout</a>
		</div>

	</div>

	<div class="container">

		<h2>Book Appointment</h2>

		<form action="bookAppointment" method="post"
			onsubmit="return validateForm()">

			<label>Patient</label> <select name="patientId">

				<%
				for (Patient p : patients) {
				%>

				<option value="<%=p.getId()%>">

					<%=p.getName()%> (ID:
					<%=p.getId()%>)

				</option>

				<%
				}
				%>

			</select> <label>Doctor</label> <select name="doctorId">

				<%
				for (User d : doctors) {
				%>

				<option value="<%=d.getId()%>">

					<%=d.getName()%>

				</option>

				<%
				}
				%>

			</select> <label>Date</label> <input type="date" name="date" id="date"
				required> <label>Time</label> <input type="time" name="time"
				id="time" required>

			<p id="error" class="error">Date must be today or future.</p>

			<button type="submit">Book Appointment</button>

		</form>

	</div>

	<script>
		function validateForm() {

			let date = document.getElementById("date").value;

			let selected = new Date(date);

			let today = new Date();

			today.setHours(0, 0, 0, 0);

			if (selected < today) {

				document.getElementById("error").style.display = "block";

				return false;

			}

			return true;

		}
	</script>

</body>
</html>
