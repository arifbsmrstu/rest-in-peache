package com.example.rokomarifinal.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String mobile;
    private int age;
    private String gender;
    private String occupation;
    private String symptom_summary;


    public Patient(){}

    public Patient(String name, String mobile, int age, String gender, String occupation, String symptom_summary) {
        this.name = name;
        this.mobile = mobile;
        this.age = age;
        this.gender = gender;
        this.occupation = occupation;
        this.symptom_summary = symptom_summary;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getSymptom_summary() {
        return symptom_summary;
    }

    public void setSymptom_summary(String symptom_summary) {
        this.symptom_summary = symptom_summary;
    }
}
