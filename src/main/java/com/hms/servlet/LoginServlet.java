package com.hms.servlet;

import java.io.IOException;

import com.hms.dao.UserDAO;
import com.hms.model.User;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO dao = new UserDAO();
        User user = dao.login(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            if (user.getRole().equals("ADMIN")) {
                response.sendRedirect("adminDashboard");
            } else if (user.getRole().equals("STAFF")) {
                response.sendRedirect("staffDashboard");
            } else if (user.getRole().equals("DOCTOR")) {
                response.sendRedirect("doctorDashboard");
            }

        } else {
            response.getWriter().println("Invalid ");
        }
    }
}