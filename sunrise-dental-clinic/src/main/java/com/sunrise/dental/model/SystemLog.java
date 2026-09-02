package com.sunrise.dental.model;
import java.time.LocalDateTime;
public class SystemLog {
    private int logId,userId;
    private String action,details,ipAddress;
    private LocalDateTime createdAt;
    public int getLogId() {
        return logId;
    }
    public void setLogId(int v) {
        logId=v;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int v) {
        userId=v;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String v) {
        action=v;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String v) {
        details=v;
    }
    public String getIpAddress() {
        return ipAddress;
    }
    public void setIpAddress(String v) {
        ipAddress=v;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime v) {
        createdAt=v;
    }
}
