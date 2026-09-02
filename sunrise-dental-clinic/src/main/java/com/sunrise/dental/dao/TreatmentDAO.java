package com.sunrise.dental.dao;
import com.sunrise.dental.model.Treatment;
import java.util.*;
public interface TreatmentDAO {
    List<Treatment> findAll();
    Treatment findById(int id);
}
