package com.sunrise.dental.dao;
import com.sunrise.dental.model.User;
import java.sql.*;
public class UserDAOImpl implements UserDAO {
    public User findByUsername(String u) {
        String q="SELECT * FROM users WHERE username=? AND is_active=TRUE";
        try(Connection c=DBConnectionFactory.getConnection();PreparedStatement p=c.prepareStatement(q)) {
            p.setString(1,u);
            ResultSet r=p.executeQuery();
            if(r.next())return map(r);
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public User findById(int id) {
        try(Connection c=DBConnectionFactory.getConnection();PreparedStatement p=c.prepareStatement("SELECT * FROM users WHERE user_id=?")) {
            p.setInt(1,id);
            ResultSet r=p.executeQuery();
            return r.next()?map(r):null;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private User map(ResultSet r)throws SQLException {
        return new User(r.getInt("user_id"),r.getString("username"),r.getString("password_hash"),r.getString("role"),r.getString("full_name"),r.getString("email"));
    }
}
