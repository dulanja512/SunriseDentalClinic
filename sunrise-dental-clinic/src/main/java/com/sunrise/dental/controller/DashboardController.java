package com.sunrise.dental.controller;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import com.sunrise.dental.util.SessionUtil;
@WebServlet("/dashboard") public class DashboardController extends HttpServlet {
    protected void doGet(HttpServletRequest r,HttpServletResponse s)throws ServletException,java.io.IOException {
        if(SessionUtil.user(r)==null) {
            s.sendRedirect(r.getContextPath()+"/login");
            return;
        }
        r.setAttribute("stats",AppContext.REPORT.dashboard());
        r.getRequestDispatcher("/WEB-INF/view/dashboard.jsp").forward(r,s);
    }
}
