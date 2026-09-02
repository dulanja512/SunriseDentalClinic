package com.sunrise.dental.util;
import javax.servlet.http.*;
import com.sunrise.dental.model.User;
public final class SessionUtil {
    private SessionUtil() {
    }
    public static void login(HttpServletRequest r,User u) {
        HttpSession s=r.getSession(true);
        s.setAttribute("user",u);
    }
    public static User user(HttpServletRequest r) {
        HttpSession s=r.getSession(false);
        return s==null?null:(User)s.getAttribute("user");
    }
    public static void logout(HttpServletRequest r) {
        HttpSession s=r.getSession(false);
        if(s!=null)s.invalidate();
    }
}
