package com.sunrise.dental.dao;
import com.sunrise.dental.model.*;
import java.sql.*;
import java.time.*;
import java.util.*;
public class AppointmentDAOImpl implements AppointmentDAO {
    public String create(Appointment a,String n,Patient p) {
        try(Connection c=DBConnectionFactory.getConnection()) {
            CallableStatement cs=c.prepareCall("{call sp_create_appointment(?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1,p.getFullName());
            cs.setString(2,p.getAddress());
            cs.setString(3,p.getContactNumber());
            cs.setString(4,p.getEmail());
            cs.setInt(5,a.getDentistId());
            cs.setInt(6,a.getTreatmentId());
            cs.setDate(7,java.sql.Date.valueOf(a.getAppointmentDate()));
            cs.setTime(8,Time.valueOf(a.getAppointmentTime()));
            cs.setInt(9,a.getCreatedBy());
            cs.registerOutParameter(10,Types.VARCHAR);
            cs.execute();
            return cs.getString(10);
        } catch(SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    public Appointment findByNumber(String n) {
        String q="SELECT a.*,p.full_name patient_name,p.address,p.contact_number,p.email,d.full_name dentist_name,d.specialization,t.treatment_name,t.base_cost,t.description FROM appointments a JOIN patients p ON p.patient_id=a.patient_id JOIN dentists d ON d.dentist_id=a.dentist_id JOIN treatments t ON t.treatment_id=a.treatment_id WHERE a.appointment_number=?";
        try(Connection c=DBConnectionFactory.getConnection();PreparedStatement p=c.prepareStatement(q)) {
            p.setString(1,n);
            ResultSet r=p.executeQuery();
            return r.next()?map(r):null;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Appointment> findAll() {
        List<Appointment>x=new ArrayList<>();
        String q="SELECT a.*,p.full_name patient_name,p.address,p.contact_number,p.email,d.full_name dentist_name,d.specialization,t.treatment_name,t.base_cost,t.description FROM appointments a JOIN patients p ON p.patient_id=a.patient_id JOIN dentists d ON d.dentist_id=a.dentist_id JOIN treatments t ON t.treatment_id=a.treatment_id ORDER BY a.appointment_date,a.appointment_time";
        try(Connection c=DBConnectionFactory.getConnection();PreparedStatement p=c.prepareStatement(q)) {
            ResultSet r=p.executeQuery();
            while(r.next())x.add(map(r));
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return x;
    }
    public boolean isSlotTaken(int d,LocalDate date,LocalTime time) {
        try(Connection c=DBConnectionFactory.getConnection();PreparedStatement p=c.prepareStatement("SELECT COUNT(*) FROM appointments WHERE dentist_id=? AND appointment_date=? AND appointment_time=? AND status='BOOKED'")) {
            p.setInt(1,d);
            p.setDate(2,java.sql.Date.valueOf(date));
            p.setTime(3,Time.valueOf(time));
            ResultSet r=p.executeQuery();
            r.next();
            return r.getInt(1)>0;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean updateStatus(int id,Appointment.Status s) {
        try(Connection c=DBConnectionFactory.getConnection();PreparedStatement p=c.prepareStatement("UPDATE appointments SET status=? WHERE appointment_id=? AND status='BOOKED'")) {
            p.setString(1,s.name());
            p.setInt(2,id);
            return p.executeUpdate()>0;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int countByDate(LocalDate d) {
        try(Connection c=DBConnectionFactory.getConnection();PreparedStatement p=c.prepareStatement("SELECT COUNT(*) FROM appointments WHERE appointment_date=? AND status<>'CANCELLED'")) {
            p.setDate(1,java.sql.Date.valueOf(d));
            ResultSet r=p.executeQuery();
            r.next();
            return r.getInt(1);
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private Appointment map(ResultSet r)throws SQLException {
        Appointment a=new Appointment();
        a.setAppointmentId(r.getInt("appointment_id"));
        a.setPatientId(r.getInt("patient_id"));
        a.setDentistId(r.getInt("dentist_id"));
        a.setTreatmentId(r.getInt("treatment_id"));
        a.setAppointmentNumber(r.getString("appointment_number"));
        a.setAppointmentDate(r.getDate("appointment_date").toLocalDate());
        a.setAppointmentTime(r.getTime("appointment_time").toLocalTime());
        a.setStatus(Appointment.Status.valueOf(r.getString("status")));
        Patient p=new Patient();
        p.setPatientId(a.getPatientId());
        p.setAppointmentNumber(a.getAppointmentNumber());
        p.setFullName(r.getString("patient_name"));
        p.setAddress(r.getString("address"));
        p.setContactNumber(r.getString("contact_number"));
        p.setEmail(r.getString("email"));
        a.setPatient(p);
        Dentist d=new Dentist();
        d.setDentistId(a.getDentistId());
        d.setFullName(r.getString("dentist_name"));
        d.setSpecialization(r.getString("specialization"));
        a.setDentist(d);
        Treatment t=new Treatment();
        t.setTreatmentId(a.getTreatmentId());
        t.setTreatmentName(r.getString("treatment_name"));
        t.setBaseCost(r.getDouble("base_cost"));
        t.setDescription(r.getString("description"));
        a.setTreatment(t);
        return a;
    }
}
