package com.sunrise.dental.dao;
import com.sunrise.dental.model.User;
public interface UserDAO {
    User findByUsername(String username);
    User findById(int id);
}
