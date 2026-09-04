package com.sunrise.dental.controller;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
@WebServlet("/login") public class LoginPageController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest r,HttpServletResponse s)throws ServletException,java.io.IOException {
        r.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(r,s);
    }
}
