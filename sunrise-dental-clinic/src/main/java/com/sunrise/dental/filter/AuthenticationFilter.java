package com.sunrise.dental.filter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.sunrise.dental.util.SessionUtil;
public class AuthenticationFilter implements Filter {
    public void doFilter(ServletRequest q,ServletResponse p,FilterChain c)throws IOException,ServletException {
        HttpServletRequest r=(HttpServletRequest)q;
        String path=r.getRequestURI().substring(r.getContextPath().length());
        if(path.equals("/login")||path.equals("/auth")||path.equals("/api")||path.equals("/api/appointments")) {
            c.doFilter(q,p);
            return;
        }
        if(SessionUtil.user(r)==null) {
            ((HttpServletResponse)p).sendRedirect(r.getContextPath()+"/login");
            return;
        }
        c.doFilter(q,p);
    }
}
