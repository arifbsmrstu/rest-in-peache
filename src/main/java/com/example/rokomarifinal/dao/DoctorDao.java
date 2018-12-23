package com.example.rokomarifinal.dao;

import com.example.rokomarifinal.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorDao extends JpaRepository<Doctor,Long> {
}
