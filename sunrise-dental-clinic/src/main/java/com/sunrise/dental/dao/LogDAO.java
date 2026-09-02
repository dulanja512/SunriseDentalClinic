package com.sunrise.dental.dao;
import com.sunrise.dental.model.SystemLog;
import java.util.*;
public interface LogDAO {
    int save(SystemLog l);
    List<SystemLog> findAll();
}
