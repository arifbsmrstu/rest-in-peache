package com.example.rokomarifinal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    private String name;
    private String dept;

    @JsonFormat(pattern="dd-MM-yyyy")
    private Date joining;

    public Doctor(){}

    public Doctor(String name, String dept, Date joining) {
        this.name = name;
        this.dept = dept;
        this.joining = joining;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Date getJoining() {
        return joining;
    }

    public void setJoining(Date joining) {
        this.joining = joining;
    }
}
