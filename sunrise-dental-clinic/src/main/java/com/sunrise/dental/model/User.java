package com.sunrise.dental.model;

public class User {
    private int userId;
    private String username;
    private String passwordHash;
    private String role;
    private String fullName;
    private String email;
    private boolean active;

    public User() {}

    public User(int id, String username, String passwordHash, String role, String fullName, String email) {
        this(id, username, passwordHash, role, fullName, email, true);
    }

    public User(int id, String username, String passwordHash, String role, String fullName, String email, boolean active) {
        this.userId = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.fullName = fullName;
        this.email = email;
        this.active = active;
    }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
