package com.hms.servlet;

import java.io.IOException;
import java.util.List;

import com.hms.dao.PatientDAO;
import com.hms.model.Patient;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class ViewPatientsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        PatientDAO dao = new PatientDAO();

        List<Patient> patients = dao.getAllPatients();

        request.setAttribute("patients", patients);

        RequestDispatcher rd =
                request.getRequestDispatcher("patientList.jsp");

        rd.forward(request, response);
    }
}