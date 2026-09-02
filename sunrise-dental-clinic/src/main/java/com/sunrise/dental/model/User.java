package com.sunrise.dental.model;
public class User {
    private int userId;
    private String username,passwordHash,role,fullName,email;
    public User() {
    }
    public User(int id,String u,String p,String r,String n,String e) {
        userId=id;
        username=u;
        passwordHash=p;
        role=r;
        fullName=n;
        email=e;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int v) {
        userId=v;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String v) {
        username=v;
    }
    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String v) {
        passwordHash=v;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String v) {
        role=v;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String v) {
        fullName=v;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String v) {
        email=v;
    }
}
