package com.example.rokomarifinal.controller;

import com.example.rokomarifinal.dao.DoctorDao;
import com.example.rokomarifinal.dao.PatientDao;
import com.example.rokomarifinal.model.Doctor;
import com.example.rokomarifinal.model.DoctorTwo;
import com.example.rokomarifinal.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@RequestMapping("/admin")
public class AdminFeatureController {
    @Autowired
    PatientDao patientDao;
    @Autowired
    DoctorDao doctorDao;

    /*
    Delete a patient by [ID}
     */
    @GetMapping("patient/delete/{id}")
    public String delatePatient(@PathVariable(value = "id") Long id, Model model)
    {
        Patient patient = patientDao.getOne(id);
        patientDao.delete(patient);
        return "redirect:/admin_home";
    }

    /*
    Delete a doctor by [ID}
     */
    @GetMapping("doctor/delete/{id}")
    public String deleteDoctor(@PathVariable(value = "id") Long id, Model model)
    {
        Doctor doctor = doctorDao.getOne(id);
        doctorDao.delete(doctor);
        return "redirect:/admin_home";
    }

    /*
    Add a new doctor (GET)
     */
    @GetMapping("/add_doctor")
    public String addDoctor(ModelMap modelMap)
    {
        modelMap.addAttribute("doctor",new DoctorTwo());
        return "admin_add_new_doctor";
    }

    /*
    Add a doctor (POST)
     */
    @PostMapping("/add_doctor")
    public String addDoctor( @Valid @ModelAttribute("doctor") DoctorTwo d) throws ParseException {

        Doctor doctor = new Doctor();
        doctor.setName(d.getName());
        doctor.setDept(d.getDept());
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(d.getJoining());
        doctor.setJoining(date);
        doctorDao.save(doctor);

        return "redirect:/admin_home";
    }


    /*
    Add a new patient(GET)
     */
    @GetMapping("/add_patient")
    public String addPatient(ModelMap modelMap)
    {
        modelMap.addAttribute("patient",new Patient());
        return "admin_add_new_patient";
    }

    /*
    Add a new patient(GET)
     */
    @PostMapping("/add_patient")
    public String addPatient( @ModelAttribute("patient") Patient patient)
    {
        patientDao.save(patient);

        return "redirect:/admin_home";
    }

    /*
    Update doctor by {ID}
     */
    @GetMapping("/doctor/update/{id}")
    public String updateDoctor(@PathVariable Long id, ModelMap modelMap)
    {
        Doctor doctor = doctorDao.getOne(id);
        modelMap.addAttribute("doctor",doctor);
        return "admin_update_doctor";
    }

    /*
    Update doctor by {ID}
     */
    @PostMapping("/doctor/update")
    public String updateDoctor(@ModelAttribute("doctor")DoctorTwo d) throws ParseException {
        Doctor doctor = new Doctor();
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(d.getJoining());
        doctor.setId(d.getId());
        doctor.setName(d.getName());
        doctor.setDept(d.getDept());
        doctor.setJoining(date);
        doctorDao.save(doctor);
        return "redirect:/admin_home";
    }

    /*
    Update doctor by {ID}
     */
    @GetMapping("/patient/update/{id}")
    public String updatePatient(@PathVariable Long id, ModelMap modelMap)
    {
        Patient patient = patientDao.getOne(id);
        modelMap.addAttribute("patient",patient);
        return "admin_update_patient";
    }

    /*
    Update doctor by {ID}
     */
    @PostMapping("/patient/update")
    public String updatePatient(@ModelAttribute("patient")Patient patient) throws ParseException {
        patientDao.save(patient);
        return "redirect:/admin_home";
    }
}
