package com.hms.servlet;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import com.hms.dao.PatientDAO;
import com.hms.model.Patient;

public class EditPatientServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        PatientDAO dao = new PatientDAO();

        for (Patient p : dao.getAllPatients()) {
            if (p.getId() == id) {
                req.setAttribute("patient", p);
                break;
            }
        }

        RequestDispatcher rd = req.getRequestDispatcher("editPatient.jsp");
        rd.forward(req, resp);
    }
}