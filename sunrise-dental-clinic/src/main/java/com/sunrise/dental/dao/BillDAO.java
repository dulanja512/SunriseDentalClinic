package com.sunrise.dental.dao;
import com.sunrise.dental.model.Bill;
public interface BillDAO {
    int save(Bill b);
    Bill findByAppointment(int id);
}
