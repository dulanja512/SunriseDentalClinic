package com.sunrise.dental.controller;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.time.*;
import com.sunrise.dental.model.*;
import com.sunrise.dental.util.SessionUtil;
@WebServlet("/appointments") public class AppointmentController extends HttpServlet {
    protected void doGet(HttpServletRequest r,HttpServletResponse s)throws ServletException,java.io.IOException {
        if(SessionUtil.user(r)==null) {
            s.sendRedirect(r.getContextPath()+"/login");
            return;
        }
        r.setAttribute("appointments",AppContext.APPT.all());
        r.setAttribute("dentists",AppContext.DENTIST_DAO.findAll());
        r.setAttribute("treatments",AppContext.TREATMENT_DAO.findAll());
        r.getRequestDispatcher("/WEB-INF/view/appointments.jsp").forward(r,s);
    }
    protected void doPost(HttpServletRequest r,HttpServletResponse s)throws ServletException,java.io.IOException {
        User u=SessionUtil.user(r);
        try {
            if("create".equals(r.getParameter("action"))) {
                Patient p=new Patient();
                p.setFullName(r.getParameter("name"));
                p.setAddress(r.getParameter("address"));
                p.setContactNumber(r.getParameter("contact"));
                p.setEmail(r.getParameter("email"));
                String n=AppContext.APPT.create(p,Integer.parseInt(r.getParameter("dentistId")),Integer.parseInt(r.getParameter("treatmentId")),LocalDate.parse(r.getParameter("date")),LocalTime.parse(r.getParameter("time")),u.getUserId());
                AppContext.LOG.log(u.getUserId(),"CREATE_APPOINTMENT",n,r.getRemoteAddr());
                r.setAttribute("message","Appointment created: "+n);
            } else if("cancel".equals(r.getParameter("action"))) {
                AppContext.APPT.cancel(Integer.parseInt(r.getParameter("id")));
                AppContext.LOG.log(u.getUserId(),"CANCEL_APPOINTMENT",r.getParameter("id"),r.getRemoteAddr());
                r.setAttribute("message","Appointment cancelled successfully");
            } else if("complete".equals(r.getParameter("action"))) {
                AppContext.APPT.complete(Integer.parseInt(r.getParameter("id")));
                r.setAttribute("message","Appointment completed");
            }
        } catch(Exception e) {
            r.setAttribute("error",e.getMessage());
        }
        doGet(r,s);
    }
}
