package com.sunrise.dental.dao;
import com.sunrise.dental.model.SystemLog;
import java.sql.*;
import java.util.*;
public class LogDAOImpl implements LogDAO {
    public int save(SystemLog l) {
        try(Connection c=DBConnectionFactory.getConnection();PreparedStatement p=c.prepareStatement("INSERT INTO system_logs(user_id,action,details,ip_address) VALUES(?,?,?,?)",Statement.RETURN_GENERATED_KEYS)) {
            p.setInt(1,l.getUserId());
            p.setString(2,l.getAction());
            p.setString(3,l.getDetails());
            p.setString(4,l.getIpAddress());
            p.executeUpdate();
            ResultSet r=p.getGeneratedKeys();
            r.next();
            return r.getInt(1);
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<SystemLog> findAll() {
        List<SystemLog>x=new ArrayList<>();
        try(Connection c=DBConnectionFactory.getConnection();PreparedStatement p=c.prepareStatement("SELECT * FROM system_logs ORDER BY created_at DESC")) {
            ResultSet r=p.executeQuery();
            while(r.next()) {
                SystemLog l=new SystemLog();
                l.setLogId(r.getInt("log_id"));
                l.setUserId(r.getInt("user_id"));
                l.setAction(r.getString("action"));
                l.setDetails(r.getString("details"));
                l.setIpAddress(r.getString("ip_address"));
                x.add(l);
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return x;
    }
}
