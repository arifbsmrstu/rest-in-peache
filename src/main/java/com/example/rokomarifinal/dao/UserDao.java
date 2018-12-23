package com.example.rokomarifinal.dao;

import com.example.rokomarifinal.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao  extends JpaRepository<Users,Long> {
    Users findByUsername(String e);
}
