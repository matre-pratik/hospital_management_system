package com.hms.servlet;

import java.io.IOException;

import com.hms.dao.AppointmentDAO;
import com.hms.model.User;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class UpdateAppointmentStatusServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
throws ServletException, IOException {

int id = Integer.parseInt(request.getParameter("id"));
String status = request.getParameter("status");

HttpSession session = request.getSession();
User doctor = (User) session.getAttribute("user");

int doctorId = doctor.getId();

AppointmentDAO dao = new AppointmentDAO();
dao.updateStatus(id, status, doctorId);

response.sendRedirect("doctorDashboard");
}
}