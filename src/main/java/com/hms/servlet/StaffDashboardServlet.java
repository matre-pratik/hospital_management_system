package com.hms.servlet;

import java.io.IOException;

import com.hms.dao.AppointmentDAO;
import com.hms.dao.PatientDAO;
import com.hms.dao.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class StaffDashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        PatientDAO patientDAO = new PatientDAO();
    	UserDAO userDAO = new UserDAO();
        AppointmentDAO appointmentDAO = new AppointmentDAO();
        
        request.setAttribute("appointments", 
        		appointmentDAO.getAllAppointments());
       
        request.setAttribute("totalPatients",
        		patientDAO.countTotalPatients());
        
        request.setAttribute("totalDoctors",
        		userDAO.countTotalDoctors());
        request.setAttribute("totalAppointments",
                appointmentDAO.countTotalAppointments());

        request.getRequestDispatcher("staffDashboard.jsp")
               .forward(request, response);
    }
}