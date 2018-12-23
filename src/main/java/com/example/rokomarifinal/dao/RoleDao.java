package com.example.rokomarifinal.dao;

import com.example.rokomarifinal.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
