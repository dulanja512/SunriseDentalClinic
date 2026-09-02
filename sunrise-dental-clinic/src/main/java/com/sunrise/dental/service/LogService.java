package com.sunrise.dental.service;
import com.sunrise.dental.dao.*;
import com.sunrise.dental.model.SystemLog;
import java.util.*;
public class LogService {
    private final LogDAO dao;
    public LogService(LogDAO d) {
        dao=d;
    }
    public void log(int user,String action,String details,String ip) {
        SystemLog l=new SystemLog();
        l.setUserId(user);
        l.setAction(action);
        l.setDetails(details);
        l.setIpAddress(ip);
        dao.save(l);
    }
    public List<SystemLog> all() {
        return dao.findAll();
    }
}
