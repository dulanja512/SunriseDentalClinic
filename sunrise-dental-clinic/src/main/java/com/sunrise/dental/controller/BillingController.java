package com.sunrise.dental.controller;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import com.sunrise.dental.util.SessionUtil;
@WebServlet("/billing") public class BillingController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest r,HttpServletResponse s)throws ServletException,java.io.IOException {
        if(SessionUtil.user(r)==null) {
            s.sendRedirect(r.getContextPath()+"/login");
            return;
        }
        try {
            int id=Integer.parseInt(r.getParameter("appointmentId"));
            r.setAttribute("bill",AppContext.BILL.generate(id));
           
        } catch(Exception e) {
            r.setAttribute("error",e.getMessage());
        }
        r.getRequestDispatcher("/WEB-INF/view/billing.jsp").forward(r,s);
    }

    private String requestedAppointmentNumber(int id) {
        return AppContext.APPT.all().stream()
                .filter(a -> a.getAppointmentId() == id)
                .map(com.sunrise.dental.model.Appointment::getAppointmentNumber)
                .findFirst()
                .orElse("");
    }
}
