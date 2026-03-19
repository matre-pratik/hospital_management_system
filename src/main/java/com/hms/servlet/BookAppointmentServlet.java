package com.hms.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import com.hms.dao.AppointmentDAO;
import com.hms.model.Appointment;
import com.hms.model.User;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class BookAppointmentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int patientId = Integer.parseInt(request.getParameter("patientId"));
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));

        Date date = Date.valueOf(request.getParameter("date"));
        Time time = Time.valueOf(request.getParameter("time") + ":00");

        HttpSession session = request.getSession();
        User staff = (User) session.getAttribute("user");

        int createdBy = staff.getId();   

        Appointment a = new Appointment();
        a.setPatientId(patientId);
        a.setDoctorId(doctorId);
        a.setAppointmentDate(date);
        a.setAppointmentTime(time);
        a.setCreatedBy(createdBy);

        AppointmentDAO dao = new AppointmentDAO();
        
        boolean status = dao.addAppointment(a);

        if (status) {
        	response.sendRedirect("staffDashboard");
        } else {
            response.getWriter().println("Error Booking Appointment");
        }
    }
}