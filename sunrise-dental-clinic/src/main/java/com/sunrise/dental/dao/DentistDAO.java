package com.sunrise.dental.dao;
import com.sunrise.dental.model.Dentist;
import java.util.*;
public interface DentistDAO {
    List<Dentist> findAll();
    Dentist findById(int id);
}
