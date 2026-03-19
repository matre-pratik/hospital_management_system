package com.hms.filter;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class AuthFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        com.hms.model.User user =
                (com.hms.model.User) session.getAttribute("user");

        String uri = req.getRequestURI();

        if (uri.contains("admin") && !user.getRole().equals("ADMIN")) {
            res.sendRedirect("login.jsp");
            return;
        }

        if (uri.contains("staff") && !user.getRole().equals("STAFF")) {
            res.sendRedirect("login.jsp");
            return;
        }

        if (uri.contains("doctor") && !user.getRole().equals("DOCTOR")) {
            res.sendRedirect("login.jsp");
            return;
        }

        chain.doFilter(request, response);
    }
}