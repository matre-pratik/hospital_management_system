<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.hms.model.*"%>

<!DOCTYPE html>
<html>
<head>
<title>Admin Dashboard</title>
<link rel="stylesheet" href="css/style.css">
</head>

<body>

	<div class="navbar">
		<h2>Admin Panel</h2>
		<div>
			<a href="addPatient.jsp">Add Patient</a> 
			<a href="addUser.jsp">Add User</a> 
			<a href="logout">Logout</a>
		</div>
	</div>

	<div class="container">

		<%
		User user = (User) session.getAttribute("user");
		if (user == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		%>

		<h2>Welcome, <%=user.getName()%></h2>

		<div class="dashboard">

			<div class="stat">
				<h3>Total Patients</h3>
				<p>${totalPatients}</p>
			</div>

			<div class="stat">
				<h3>Total Doctors</h3>
				<p>${totalDoctors}</p>
			</div>

			<div class="stat">
				<h3>Total Appointments</h3>
				<p>${totalAppointments}</p>
			</div>

			<div class="stat">
				<h3>Completed</h3>
				<p>${completedAppointments}</p>
			</div>

			<div class="stat">
				<h3>Cancelled</h3>
				<p>${cancelledAppointments}</p>
			</div>

			<div class="stat">
				<h3>Pending</h3>
				<p>${pendingCount}</p>
			</div>

		</div>

		<h2>Appointments</h2>

		<%
		List<Appointment> list = (List<Appointment>) request.getAttribute("appointments");
		%>

		<div class="card">
			<table>

				<tr>
					<th>ID</th>
					<th>Patient ID</th>
					<th>Doctor ID</th>
					<th>Date</th>
					<th>Time</th>
					<th>Status</th>
				</tr>

				<%
				if (list != null) {
					for (Appointment a : list) {
				%>

				<tr>
					<td><%=a.getId()%></td>
					<td><%=a.getPatientId()%></td>
					<td><%=a.getDoctorId()%></td>
					<td><%=a.getAppointmentDate()%></td>
					<td><%=a.getAppointmentTime()%></td>
					<td><%=a.getStatus()%></td>
				</tr>

				<%
				}
				}
				%>

			</table>
		</div>

	</div>

</body>
</html>