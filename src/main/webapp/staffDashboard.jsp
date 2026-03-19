<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.hms.model.*"%>

<!DOCTYPE html>
<html>
<head>
<title>Staff Dashboard</title>
<link rel="stylesheet" href="css/style.css">
</head>

<body>

	<div class="navbar">
		<h2>Staff Panel</h2>
		<div>
			<a href="patientList">Patients</a> 
			<a href="bookAppointment.jsp">Book Appointment</a> 
			<a href="logout">Logout</a>
		</div>
	</div>

	<div class="container">

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

		</div>

		<div class="card">

			<h2>Recent Appointments</h2>

			<%
			List<Appointment> list = (List<Appointment>) request.getAttribute("appointments");
			%>

			<table>

				<tr>
					<th>ID</th>
					<th>Patient</th>
					<th>Doctor</th>
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