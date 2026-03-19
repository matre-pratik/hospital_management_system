<%@page import="com.hms.model.Patient"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Patient List</title>
<link rel="stylesheet" href="css/style.css">
</head>

<body>

	<div class="navbar">
		<h2>Hospital System</h2>

		<div>
			<a href="staffDashboard">Dashboard</a> 
			<a href="bookAppointment.jsp">Book Appointment</a> 
			<a href="logout">Logout</a>
		</div>
	</div>

	<div class="container">
		<div class="card">

			<h2>Patients</h2>

			<h3>Search Patient</h3>

			<form action="searchPatient" method="get" class="search-box">
				<input type="text" name="keyword"
					placeholder="Search by name or phone"
					pattern=".{2,}">
				<button type="submit">Search</button>
				<a href="patientList">Reset</a>
			</form>

			<%
			List<Patient> list = (List<Patient>) request.getAttribute("patients");
			%>

			<table>

				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Age</th>
					<th>Gender</th>
					<th>Phone</th>
					<th>Address</th>
				</tr>

				<%
				if (list != null) {
					for (Patient p : list) {
				%>

				<tr>
					<td><%=p.getId()%></td>
					<td><%=p.getName()%></td>
					<td><%=p.getAge()%></td>
					<td><%=p.getGender()%></td>
					<td><%=p.getPhone()%></td>
					<td><%=p.getAddress()%></td>
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