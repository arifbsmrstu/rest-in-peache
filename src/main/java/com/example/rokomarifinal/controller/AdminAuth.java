package com.example.rokomarifinal.controller;

import com.example.rokomarifinal.dao.DoctorDao;
import com.example.rokomarifinal.dao.PatientDao;
import com.example.rokomarifinal.dao.RoleDao;
import com.example.rokomarifinal.dao.UserDao;
import com.example.rokomarifinal.model.ApplicationUser;
import com.example.rokomarifinal.model.Doctor;
import com.example.rokomarifinal.model.Patient;
import com.example.rokomarifinal.model.Users;
import com.example.rokomarifinal.security.JWTAuthenticationFilter;
import com.example.rokomarifinal.security.JWTAuthorizationFilter;
import com.example.rokomarifinal.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@Controller
public class AdminAuth {
    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    DoctorDao doctorDao;
    @Autowired
    PatientDao patientDao;

    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("/admin_login")
    public String adminLogin(ModelMap modelMap)
    {
        modelMap.addAttribute("user",new ApplicationUser());
        return "admin_login";
    }

    @PostMapping("/admin_login")
    public String adminLogin(@ModelAttribute("user") ApplicationUser applicationUser)
    {
        Users u = userDao.findByUsername(applicationUser.getEmail());
        System.out.println(u.getRole().getName());

        if(u == null || u.getRole().getName().equals("ROLE_USER"))
        {
            return "redirect:/admin_login";
        }
        else {
            //Role should be checked here
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    applicationUser.getEmail(),
                    applicationUser.getPassword()
            ));

            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);

            if (context.getAuthentication().isAuthenticated()) {
                return "redirect:/admin_home";
            } else {
                return "redirect:/admin_login";
            }
        }

    }

    @GetMapping("/admin_home")
    public String adminHome(ModelMap modelMap)
    {
        List<Doctor> doctorList = doctorDao.findAll();
        List<Patient> patientList = patientDao.findAll();

        modelMap.addAttribute("doctorList",doctorList);
        modelMap.addAttribute("patientList",patientList);

        return "admin_home";
    }
}
