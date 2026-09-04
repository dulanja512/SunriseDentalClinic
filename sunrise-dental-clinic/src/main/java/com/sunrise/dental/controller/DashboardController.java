package com.sunrise.dental.controller;

import com.sunrise.dental.model.Appointment;
import com.sunrise.dental.model.User;
import com.sunrise.dental.util.SessionUtil;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest r, HttpServletResponse s) throws ServletException, IOException {
        User current = SessionUtil.user(r);
        if (current == null) { s.sendRedirect(r.getContextPath() + "/login"); return; }
        r.setAttribute("stats", AppContext.REPORT.dashboard());
        List<Appointment> all = AppContext.APPT.all();
        r.setAttribute("recentAppointments", all.subList(0, Math.min(5, all.size())));
        if ("ADMIN".equals(current.getRole())) {
            long activeStaff = AppContext.USER_DAO.findAll().stream().filter(User::isActive).count();
            r.setAttribute("activeStaff", activeStaff);
        }
        r.getRequestDispatcher("/WEB-INF/view/dashboard.jsp").forward(r, s);
    }
}
