package com.hms.servlet;

import java.io.IOException;
import java.util.List;

import com.hms.dao.AppointmentDAO;
import com.hms.model.Appointment;
import com.hms.model.User;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class DoctorDashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User doctor = (User) session.getAttribute("user");

        if (doctor == null || !doctor.getRole().equals("DOCTOR")) {
            response.sendRedirect("login.jsp");
            return;
        }        
        
        AppointmentDAO dao = new AppointmentDAO();

        List<Appointment> list =
                dao.getAppointmentsByDoctor(doctor.getId());

        int pending = dao.countPendingByDoctor(doctor.getId());
        int completed = dao.countCompletedByDoctor(doctor.getId());
        int cancelled = dao.countCancelByDoctor(doctor.getId());

        request.setAttribute("appointments", list);
        request.setAttribute("pendingCount", pending);
        request.setAttribute("completedCount", completed);
        request.setAttribute("cancelledCount", cancelled);
        
        RequestDispatcher rd =
                request.getRequestDispatcher("doctorDashboard.jsp");
        rd.forward(request, response);
    }
}