package com.sunrise.dental.controller;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import com.sunrise.dental.model.User;
import com.sunrise.dental.util.SessionUtil;
@WebServlet("/auth") public class AuthController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest r,HttpServletResponse s)throws ServletException,java.io.IOException {
        String action=r.getParameter("action");
        try {
            if("login".equals(action)) {
                User u=AppContext.AUTH.login(r.getParameter("username"),r.getParameter("password"));
                SessionUtil.login(r,u);
                AppContext.LOG.log(u.getUserId(),"LOGIN","Successful login",r.getRemoteAddr());
                s.sendRedirect(r.getContextPath()+"/dashboard");
            } else {
                SessionUtil.logout(r);
                s.sendRedirect(r.getContextPath()+"/login");
            }
        } catch(Exception e) {
            r.setAttribute("error",e.getMessage());
            r.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(r,s);
        }
    }
}
