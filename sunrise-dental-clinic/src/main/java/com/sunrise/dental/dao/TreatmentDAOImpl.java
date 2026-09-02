package com.sunrise.dental.dao;
import com.sunrise.dental.model.Treatment;
import java.sql.*;
import java.util.*;
public class TreatmentDAOImpl implements TreatmentDAO {
    public List<Treatment> findAll() {
        List<Treatment>x=new ArrayList<>();
        try(Connection c=DBConnectionFactory.getConnection();PreparedStatement p=c.prepareStatement("SELECT * FROM treatments WHERE is_active=TRUE ORDER BY treatment_name")) {
            ResultSet r=p.executeQuery();
            while(r.next())x.add(map(r));
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return x;
    }
    public Treatment findById(int id) {
        try(Connection c=DBConnectionFactory.getConnection();PreparedStatement p=c.prepareStatement("SELECT * FROM treatments WHERE treatment_id=?")) {
            p.setInt(1,id);
            ResultSet r=p.executeQuery();
            return r.next()?map(r):null;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private Treatment map(ResultSet r)throws SQLException {
        Treatment t=new Treatment();
        t.setTreatmentId(r.getInt("treatment_id"));
        t.setTreatmentName(r.getString("treatment_name"));
        t.setBaseCost(r.getDouble("base_cost"));
        t.setDescription(r.getString("description"));
        return t;
    }
}
