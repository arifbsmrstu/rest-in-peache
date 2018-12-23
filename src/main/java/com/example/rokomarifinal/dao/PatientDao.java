package com.example.rokomarifinal.dao;

import com.example.rokomarifinal.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientDao extends JpaRepository<Patient,Long> {

}
