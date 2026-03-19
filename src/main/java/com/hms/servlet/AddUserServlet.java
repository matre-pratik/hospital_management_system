package com.hms.servlet;

import java.io.IOException;

import com.hms.dao.UserDAO;
import com.hms.model.User;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class AddUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        HttpSession session = request.getSession();

        UserDAO dao = new UserDAO();

        // check email first
        if (dao.emailExists(email)) {

            session.setAttribute("errorMsg",
                    "Email already registered. Please use another email.");

            response.sendRedirect("addUser.jsp");
            return;
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);

        boolean status = dao.addUser(user);

        if (status) {

            session.setAttribute("successMsg",
                    "User added successfully!");

        } else {

            session.setAttribute("errorMsg",
                    "Something went wrong. User not added.");
        }

        response.sendRedirect("addUser.jsp");
    }
}