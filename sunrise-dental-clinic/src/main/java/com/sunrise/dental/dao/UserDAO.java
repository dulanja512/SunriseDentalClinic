package com.sunrise.dental.dao;

import com.sunrise.dental.model.User;
import java.util.List;

public interface UserDAO {
    User findByUsername(String username);
    User findById(int id);
    List<User> findAll();
    int create(User user);
    boolean setActive(int id, boolean active);
    boolean updatePassword(int id, String passwordHash);
}
