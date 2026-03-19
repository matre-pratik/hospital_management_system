package com.hms.servlet;

import java.io.IOException;

import com.hms.dao.PatientDAO;
import com.hms.model.Patient;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class AddPatientServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        Patient p = new Patient();
        p.setName(name);
        p.setAge(age);
        p.setGender(gender);
        p.setPhone(phone);
        p.setAddress(address);

        PatientDAO dao = new PatientDAO();
        boolean status = dao.addPatient(p);

        if (status) {
            response.getWriter().println("Patient Added Successfully");
        } else {
            response.getWriter().println("Error Adding Patient");
        }
    }
}