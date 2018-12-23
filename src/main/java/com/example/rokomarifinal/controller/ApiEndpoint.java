package com.example.rokomarifinal.controller;

import com.example.rokomarifinal.dao.DoctorDao;
import com.example.rokomarifinal.dao.PatientDao;
import com.example.rokomarifinal.message.response.ResponseStatusBody;
import com.example.rokomarifinal.model.Doctor;
import com.example.rokomarifinal.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiEndpoint {

    @Autowired
    DoctorDao doctorDao;
    @Autowired
    PatientDao patientDao;

    //Insert a Doctor
    @PostMapping
    @RequestMapping("/insert/doctor/new")
    public ResponseStatusBody insertDoctor(@Valid @RequestBody Doctor doctor)
    {
        doctorDao.save(doctor);

        return new ResponseStatusBody("success");
    }

    //Insert a Patient
    @PostMapping
    @RequestMapping("/insert/patient/new")
    public ResponseStatusBody insertPatient(@Valid @RequestBody Patient patient)
    {
        patientDao.save(patient);

        return new ResponseStatusBody("success");
    }

    //Get All Doctors
    @GetMapping
    @RequestMapping("/doctors")
    public List<Doctor> allDoctorList()
    {
        return doctorDao.findAll();
    }

    //Get All Patients
    @GetMapping
    @RequestMapping("/patients")
    public List<Patient> allPatientList()
    {
        return patientDao.findAll();
    }

    //Get Patient By ID
    @GetMapping
    @RequestMapping("/patients/{id}")
    public Patient getPatientById(@PathVariable(value = "id") Long patientId)
    {
        return patientDao.findById(patientId)
                .orElseThrow(()-> new RuntimeException());
    }

    //Get Doctor By ID
    @GetMapping("/doctors/{id}")
    public Doctor getDoctorById(@PathVariable(value = "id") Long doctorId)
    {
        return doctorDao.findById(doctorId)
                .orElseThrow(()-> new RuntimeException());
    }

    //Update Patient By ID
    @PutMapping
    @RequestMapping("/update/patients/{id}")
    public ResponseStatusBody updatePatientById(@PathVariable(value = "id") Long patientId,@Valid@RequestBody Patient patient)
    {
        Patient p =  patientDao.findById(patientId)
                .orElseThrow(()-> new RuntimeException());

        p.setName(patient.getName());
        p.setMobile(patient.getMobile());
        p.setAge(patient.getAge());
        p.setGender(patient.getGender());
        p.setOccupation(patient.getOccupation());
        p.setSymptom_summary(patient.getSymptom_summary());

        patientDao.save(p);

        return new ResponseStatusBody("updated");
    }

    //Update Doctor By ID
    @PutMapping("/update/doctors/{id}")
    public ResponseStatusBody updateDoctorById(@PathVariable(value = "id") Long doctorId,@Valid@RequestBody Doctor doctor)
    {
        Doctor d = doctorDao.findById(doctorId)
                .orElseThrow(()-> new RuntimeException());

        d.setName(doctor.getName());
        d.setDept(doctor.getDept());
        d.setJoining(doctor.getJoining());

        doctorDao.save(d);

        return new ResponseStatusBody("updated");
    }

    //Delete patient by ID
    @DeleteMapping("/delete/patients/{id}")
    public ResponseStatusBody deletePatientById(@PathVariable(value = "id") Long patientId)
    {
        Patient patient = patientDao.findById(patientId)
                .orElseThrow(()-> new RuntimeException());

        patientDao.delete(patient);

        return new ResponseStatusBody("deleted");
    }

    //Delete patient by ID
    @DeleteMapping("/delete/doctors/{id}")
    public ResponseStatusBody deleteDoctorById(@PathVariable(value = "id") Long doctorId)
    {
        Doctor doctor = doctorDao.findById(doctorId)
                .orElseThrow(()-> new RuntimeException());

        doctorDao.delete(doctor);

        return new ResponseStatusBody("deleted");
    }


}
