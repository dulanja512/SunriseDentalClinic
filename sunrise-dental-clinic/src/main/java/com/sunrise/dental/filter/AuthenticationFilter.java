package com.sunrise.dental.filter;

import com.sunrise.dental.util.SessionUtil;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest r = (HttpServletRequest) request;
        HttpServletResponse s = (HttpServletResponse) response;
        String path = r.getRequestURI().substring(r.getContextPath().length());

        boolean publicPath = path.equals("/") || path.equals("/index.jsp") || path.equals("/login") || path.equals("/auth")
                || path.startsWith("/assets/") || path.startsWith("/api/");
        if (publicPath || SessionUtil.user(r) != null) {
            chain.doFilter(request, response);
            return;
        }
        s.sendRedirect(r.getContextPath() + "/login");
    }
}
