package com.sunrise.dental.controller;

import com.sunrise.dental.model.Patient;
import com.sunrise.dental.model.User;
import com.sunrise.dental.util.SessionUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet("/appointments")
public class AppointmentController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest r, HttpServletResponse s) throws ServletException, IOException {
        r.setAttribute("appointments", AppContext.APPT.all());
        r.setAttribute("dentists", AppContext.DENTIST_DAO.findAll());
        r.setAttribute("treatments", AppContext.TREATMENT_DAO.findAll());
        r.setAttribute("today", LocalDate.now().toString());
        r.getRequestDispatcher("/WEB-INF/view/appointments.jsp").forward(r, s);
    }

    @Override
    protected void doPost(HttpServletRequest r, HttpServletResponse s) throws ServletException, IOException {
        User u = SessionUtil.user(r);
        if (u == null) { s.sendRedirect(r.getContextPath() + "/login"); return; }
        try {
            String action = r.getParameter("action");
            if ("create".equals(action)) {
                Patient p = new Patient();
                p.setFullName(r.getParameter("name"));
                p.setAddress(r.getParameter("address"));
                p.setContactNumber(r.getParameter("contact"));
                p.setEmail(r.getParameter("email"));
                String number = AppContext.APPT.create(p, Integer.parseInt(r.getParameter("dentistId")),
                        Integer.parseInt(r.getParameter("treatmentId")), LocalDate.parse(r.getParameter("date")),
                        LocalTime.parse(r.getParameter("time")), u.getUserId());
                AppContext.LOG.log(u.getUserId(), "CREATE_APPOINTMENT", number, r.getRemoteAddr());
                flash(r, "success", "Appointment created successfully: " + number);
            } else if ("cancel".equals(action)) {
                int id = Integer.parseInt(r.getParameter("id"));
                AppContext.APPT.cancel(id);
                AppContext.LOG.log(u.getUserId(), "CANCEL_APPOINTMENT", "Appointment ID " + id, r.getRemoteAddr());
                flash(r, "success", "Appointment cancelled successfully.");
            } else if ("complete".equals(action)) {
                int id = Integer.parseInt(r.getParameter("id"));
                AppContext.APPT.complete(id);
                AppContext.LOG.log(u.getUserId(), "COMPLETE_APPOINTMENT", "Appointment ID " + id, r.getRemoteAddr());
                flash(r, "success", "Appointment marked as completed.");
            } else {
                throw new IllegalArgumentException("Unsupported appointment action.");
            }
        } catch (Exception e) {
            String message = e.getMessage();
            flash(r, "error", message == null || message.isBlank() ? "Unable to complete the appointment action." : message);
        }
        s.sendRedirect(r.getContextPath() + "/appointments");
    }

    private void flash(HttpServletRequest r, String type, String message) {
        r.getSession(true).setAttribute("flashType", type);
        r.getSession().setAttribute("flashMessage", message);
    }
}
