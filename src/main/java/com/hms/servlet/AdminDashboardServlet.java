package com.hms.servlet;

import java.io.IOException;

import com.hms.dao.AppointmentDAO;
import com.hms.dao.PatientDAO;
import com.hms.dao.UserDAO;
import com.hms.model.User;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class AdminDashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

    	PatientDAO patientDAO = new PatientDAO();
    	UserDAO userDAO = new UserDAO();
        AppointmentDAO appointmentDAO = new AppointmentDAO();
        
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        if (user == null || !user.getRole().equals("ADMIN")) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        request.setAttribute("appointments", 
        		appointmentDAO.getAllAppointments());
       
        request.setAttribute("totalPatients",
        		patientDAO.countTotalPatients());
        
        request.setAttribute("totalDoctors",
        		userDAO.countTotalDoctors());
        
        request.setAttribute("totalAppointments",
                appointmentDAO.countTotalAppointments());

        request.setAttribute("completedAppointments",
                appointmentDAO.countTotalCompletedAppointments());

        request.setAttribute("cancelledAppointments",
                appointmentDAO.countTotalCancelledAppointments());
      
        request.setAttribute("pendingCount",
                appointmentDAO.countPendingAppointments());
        
        RequestDispatcher rd =
        request.getRequestDispatcher("adminDashboard.jsp");

        rd.forward(request, response);
    }
}