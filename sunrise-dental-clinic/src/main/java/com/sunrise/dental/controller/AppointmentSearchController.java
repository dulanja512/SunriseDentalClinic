package com.sunrise.dental.controller;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
@WebServlet("/appointment-search") public class AppointmentSearchController extends HttpServlet {
    protected void doPost(HttpServletRequest r,HttpServletResponse s)throws ServletException,java.io.IOException {
        try {
            r.setAttribute("appointment",AppContext.APPT.find(r.getParameter("appointmentNumber")));
        } catch(Exception e) {
            r.setAttribute("error",e.getMessage());
        }
        r.getRequestDispatcher("/WEB-INF/view/appointment-details.jsp").forward(r,s);
    }
}
