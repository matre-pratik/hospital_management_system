package com.hms.servlet;

import java.io.IOException;
import java.util.List;

import com.hms.dao.PatientDAO;
import com.hms.model.Patient;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class SearchPatientServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String keyword = request.getParameter("keyword");

		PatientDAO dao = new PatientDAO();

		List<Patient> list = dao.searchPatients(keyword);

		request.setAttribute("patients", list);

		RequestDispatcher rd = request.getRequestDispatcher("patientList.jsp");

		rd.forward(request, response);
	}
}