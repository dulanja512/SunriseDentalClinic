package com.sunrise.dental.controller;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import com.sunrise.dental.util.SessionUtil;
@WebServlet("/logs") public class LogsController extends HttpServlet {
    protected void doGet(HttpServletRequest r,HttpServletResponse s)throws ServletException,java.io.IOException {
        if(SessionUtil.user(r)==null||!"ADMIN".equals(SessionUtil.user(r).getRole())) {
            s.sendError(403);
            return;
        }
        r.setAttribute("logs",AppContext.LOG.all());
        r.getRequestDispatcher("/WEB-INF/view/logs.jsp").forward(r,s);
    }
}
