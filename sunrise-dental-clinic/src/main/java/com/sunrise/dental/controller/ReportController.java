package com.sunrise.dental.controller;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import com.sunrise.dental.util.SessionUtil;
@WebServlet("/reports") public class ReportController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest r,HttpServletResponse s)throws ServletException,java.io.IOException {
        if(SessionUtil.user(r)==null) {
            s.sendRedirect(r.getContextPath()+"/login");
            return;
        }
        r.setAttribute("appointments",AppContext.APPT.all());
        r.getRequestDispatcher("/WEB-INF/view/reports.jsp").forward(r,s);
    }
}
