package com.sunrise.dental.dao;

import com.sunrise.dental.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username=? AND is_active=TRUE";
        try (Connection c = DBConnectionFactory.getConnection(); PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, username);
            try (ResultSet r = p.executeQuery()) {
                return r.next() ? map(r) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to load user account", e);
        }
    }

    @Override
    public User findById(int id) {
        try (Connection c = DBConnectionFactory.getConnection(); PreparedStatement p = c.prepareStatement("SELECT * FROM users WHERE user_id=?")) {
            p.setInt(1, id);
            try (ResultSet r = p.executeQuery()) {
                return r.next() ? map(r) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to load user account", e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY is_active DESC, role, full_name";
        try (Connection c = DBConnectionFactory.getConnection(); PreparedStatement p = c.prepareStatement(sql); ResultSet r = p.executeQuery()) {
            while (r.next()) users.add(map(r));
            return users;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to load staff accounts", e);
        }
    }

    @Override
    public int create(User user) {
        String sql = "INSERT INTO users(username,password_hash,role,full_name,email,is_active) VALUES(?,?,?,?,?,TRUE)";
        try (Connection c = DBConnectionFactory.getConnection(); PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            p.setString(1, user.getUsername());
            p.setString(2, user.getPasswordHash());
            p.setString(3, user.getRole());
            p.setString(4, user.getFullName());
            p.setString(5, user.getEmail());
            p.executeUpdate();
            try (ResultSet r = p.getGeneratedKeys()) {
                if (r.next()) return r.getInt(1);
            }
            throw new RuntimeException("Staff account was created but no ID was returned");
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new IllegalArgumentException("That username is already in use");
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create staff account", e);
        }
    }

    @Override
    public boolean setActive(int id, boolean active) {
        try (Connection c = DBConnectionFactory.getConnection(); PreparedStatement p = c.prepareStatement("UPDATE users SET is_active=? WHERE user_id=?")) {
            p.setBoolean(1, active);
            p.setInt(2, id);
            return p.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update staff status", e);
        }
    }

    @Override
    public boolean updatePassword(int id, String passwordHash) {
        try (Connection c = DBConnectionFactory.getConnection(); PreparedStatement p = c.prepareStatement("UPDATE users SET password_hash=? WHERE user_id=?")) {
            p.setString(1, passwordHash);
            p.setInt(2, id);
            return p.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to reset password", e);
        }
    }

    private User map(ResultSet r) throws SQLException {
        return new User(
            r.getInt("user_id"), r.getString("username"), r.getString("password_hash"),
            r.getString("role"), r.getString("full_name"), r.getString("email"), r.getBoolean("is_active")
        );
    }
}
