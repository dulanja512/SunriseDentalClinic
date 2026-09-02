package com.sunrise.dental.service;
import com.sunrise.dental.dao.*;
import com.sunrise.dental.model.User;
import com.sunrise.dental.util.PasswordHasher;
public class AuthService {
    private final UserDAO dao;
    public AuthService(UserDAO d) {
        dao=d;
    }
    public User login(String u,String p) {
        User x=dao.findByUsername(u);
        if(x==null||!PasswordHasher.matches(p,x.getPasswordHash()))throw new IllegalArgumentException("Invalid username or password");
        return x;
    }
}
