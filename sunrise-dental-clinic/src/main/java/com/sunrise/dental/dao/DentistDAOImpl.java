package com.sunrise.dental.dao;
import com.sunrise.dental.model.Dentist;
import java.sql.*;
import java.util.*;
public class DentistDAOImpl implements DentistDAO {
    public List<Dentist> findAll() {
        List<Dentist> x=new ArrayList<>();
        try(Connection c=DBConnectionFactory.getConnection();PreparedStatement p=c.prepareStatement("SELECT * FROM dentists WHERE is_available=TRUE ORDER BY full_name")) {
            ResultSet r=p.executeQuery();
            while(r.next())x.add(map(r));
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return x;
    }
    public Dentist findById(int id) {
        try(Connection c=DBConnectionFactory.getConnection();PreparedStatement p=c.prepareStatement("SELECT * FROM dentists WHERE dentist_id=?")) {
            p.setInt(1,id);
            ResultSet r=p.executeQuery();
            return r.next()?map(r):null;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private Dentist map(ResultSet r)throws SQLException {
        Dentist d=new Dentist();
        d.setDentistId(r.getInt("dentist_id"));
        d.setFullName(r.getString("full_name"));
        d.setSpecialization(r.getString("specialization"));
        d.setContactNumber(r.getString("contact_number"));
        d.setAvailable(r.getBoolean("is_available"));
        return d;
    }
}
