package com.sunrise.dental.controller;

import com.sunrise.dental.dao.UserDAO;
import com.sunrise.dental.model.User;
import com.sunrise.dental.util.PasswordHasher;
import com.sunrise.dental.util.SessionUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/staff")
public class StaffController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO dao() { return AppContext.USER_DAO; }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current = SessionUtil.user(request);
        if (current == null || !"ADMIN".equals(current.getRole())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Administrator access is required");
            return;
        }
        request.setAttribute("staff", dao().findAll());
        request.getRequestDispatcher("/WEB-INF/view/staff.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current = SessionUtil.user(request);
        if (current == null || !"ADMIN".equals(current.getRole())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Administrator access is required");
            return;
        }

        String action = clean(request.getParameter("action"));
        try {
            if ("create".equals(action)) {
                createStaff(request, current);
                flash(request, "success", "Staff account created successfully.");
            } else if ("toggle".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                boolean active = Boolean.parseBoolean(request.getParameter("active"));
                if (id == current.getUserId() && !active) throw new IllegalArgumentException("You cannot deactivate your own account while signed in.");
                if (!dao().setActive(id, active)) throw new IllegalArgumentException("Staff account not found.");
                AppContext.LOG.log(current.getUserId(), active ? "ACTIVATE_STAFF" : "DEACTIVATE_STAFF", "User ID " + id, request.getRemoteAddr());
                flash(request, "success", active ? "Staff account activated." : "Staff account deactivated.");
            } else if ("resetPassword".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                String password = clean(request.getParameter("newPassword"));
                validatePassword(password);
                if (!dao().updatePassword(id, PasswordHasher.hash(password))) throw new IllegalArgumentException("Staff account not found.");
                AppContext.LOG.log(current.getUserId(), "RESET_STAFF_PASSWORD", "User ID " + id, request.getRemoteAddr());
                flash(request, "success", "Password reset successfully.");
            } else {
                throw new IllegalArgumentException("Unsupported staff action.");
            }
        } catch (Exception e) {
            flash(request, "error", readable(e));
        }
        response.sendRedirect(request.getContextPath() + "/staff");
    }

    private void createStaff(HttpServletRequest request, User current) {
        String fullName = clean(request.getParameter("fullName"));
        String username = clean(request.getParameter("username")).toLowerCase();
        String email = clean(request.getParameter("email"));
        String role = clean(request.getParameter("role")).toUpperCase();
        String password = clean(request.getParameter("password"));

        if (fullName.length() < 2) throw new IllegalArgumentException("Enter the staff member's full name.");
        if (!username.matches("[a-z0-9._-]{3,30}")) throw new IllegalArgumentException("Username must be 3-30 characters using letters, numbers, dot, dash or underscore.");
        if (!email.isEmpty() && !email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) throw new IllegalArgumentException("Enter a valid email address.");
        if (!"ADMIN".equals(role) && !"STAFF".equals(role)) throw new IllegalArgumentException("Choose a valid role.");
        validatePassword(password);

        User user = new User();
        user.setFullName(fullName);
        user.setUsername(username);
        user.setEmail(email.isEmpty() ? null : email);
        user.setRole(role);
        user.setPasswordHash(PasswordHasher.hash(password));
        int id = dao().create(user);
        AppContext.LOG.log(current.getUserId(), "CREATE_STAFF", "User ID " + id + " (" + username + ")", request.getRemoteAddr());
    }

    private void validatePassword(String password) {
        if (password.length() < 8) throw new IllegalArgumentException("Password must contain at least 8 characters.");
    }

    private String clean(String value) { return value == null ? "" : value.trim(); }
    private String readable(Exception e) {
        String message = e.getMessage();
        return message == null || message.isBlank() ? "The request could not be completed." : message;
    }
    private void flash(HttpServletRequest request, String type, String message) {
        request.getSession(true).setAttribute("flashType", type);
        request.getSession().setAttribute("flashMessage", message);
    }
}
