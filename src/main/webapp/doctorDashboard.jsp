<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.hms.model.*"%>

<!DOCTYPE html>
<html>
<head>
<title>Doctor Dashboard</title>
<link rel="stylesheet" href="css/style.css">
</head>

<body>

	<div class="navbar">
		<h2>Doctor Panel</h2>
		<div>
			<a href="logout">Logout</a>
		</div>
	</div>

	<div class="container">

		<div class="dashboard">

			<div class="stat">
				<h3>Pending</h3>
				<p>${pendingCount}</p>
			</div>

			<div class="stat">
				<h3>Cancelled</h3>
				<p>${cancelledCount}</p>
			</div>

			<div class="stat">
				<h3>Completed</h3>
				<p>${completedCount}</p>
			</div>

		</div>

		<h2>Your Appointments</h2>

		<%
		List<Appointment> list = (List<Appointment>) request.getAttribute("appointments");
		%>

		<div class="card table-wrapper">
			<table>

				<tr>
					<th>ID</th>
					<th>Patient ID</th>
					<th>Patient Name</th>
					<th>Date</th>
					<th>Time</th>
					<th>Status</th>
					<th>Action</th>
				</tr>

				<%
				if (list != null) {
					for (Appointment a : list) {
				%>

				<tr>

					<td><%=a.getId()%></td>
					<td><%=a.getPatientId()%></td>
					<td><%=a.getPatientName()%></td>
					<td><%=a.getAppointmentDate()%></td>
					<td><%=a.getAppointmentTime()%></td>
					<td><%=a.getStatus()%></td>

					<td class="action-cell">
						<%
						if (a.getStatus().equals("BOOKED")) {
						%>

						<form action="updateAppointmentStatus" method="post" class="inline-form">
							<input type="hidden" name="id" value="<%=a.getId()%>">
							<input type="hidden" name="status" value="COMPLETED">
							<button type="submit" class="btn-success">Complete</button>
						</form>

						<form action="updateAppointmentStatus" method="post" class="inline-form">
							<input type="hidden" name="id" value="<%=a.getId()%>">
							<input type="hidden" name="status" value="CANCELLED">
							<button type="submit" class="btn-danger">Cancel</button>
						</form>

						<%
						} else {
						%>
							No Action
						<%
						}
						%>
					</td>

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