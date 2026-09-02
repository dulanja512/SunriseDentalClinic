package com.sunrise.dental.dao;
import com.sunrise.dental.model.Bill;
import java.sql.*;
public class BillDAOImpl implements BillDAO {
    public int save(Bill b) {
        String q="INSERT INTO bills(appointment_id,consultation_fee,treatment_cost,discount,total_amount,pricing_strategy) VALUES(?,?,?,?,?,?)";
        try(Connection c=DBConnectionFactory.getConnection();PreparedStatement p=c.prepareStatement(q,Statement.RETURN_GENERATED_KEYS)) {
            p.setInt(1,b.getAppointmentId());
            p.setDouble(2,b.getConsultationFee());
            p.setDouble(3,b.getTreatmentCost());
            p.setDouble(4,b.getDiscount());
            p.setDouble(5,b.getTotalAmount());
            p.setString(6,b.getPricingStrategy());
            p.executeUpdate();
            ResultSet r=p.getGeneratedKeys();
            r.next();
            return r.getInt(1);
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Bill findByAppointment(int id) {
        try(Connection c=DBConnectionFactory.getConnection();PreparedStatement p=c.prepareStatement("SELECT * FROM bills WHERE appointment_id=?")) {
            p.setInt(1,id);
            ResultSet r=p.executeQuery();
            if(!r.next())return null;
            Bill b=new Bill();
            b.setBillId(r.getInt("bill_id"));
            b.setAppointmentId(id);
            b.setConsultationFee(r.getDouble("consultation_fee"));
            b.setTreatmentCost(r.getDouble("treatment_cost"));
            b.setDiscount(r.getDouble("discount"));
            b.setTotalAmount(r.getDouble("total_amount"));
            b.setPricingStrategy(r.getString("pricing_strategy"));
            return b;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
