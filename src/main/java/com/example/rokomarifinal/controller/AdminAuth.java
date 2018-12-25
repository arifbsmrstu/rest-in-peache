package com.example.rokomarifinal.controller;

import com.example.rokomarifinal.dao.DoctorDao;
import com.example.rokomarifinal.dao.PatientDao;
import com.example.rokomarifinal.dao.RoleDao;
import com.example.rokomarifinal.dao.UserDao;
import com.example.rokomarifinal.message.request.SignUpForm;
import com.example.rokomarifinal.message.response.SignUpResponse;
import com.example.rokomarifinal.model.*;
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
    public String adminLogin(@ModelAttribute("user") ApplicationUser applicationUser,ModelMap modelMap)
    {

        Users u = userDao.findByUsername(applicationUser.getEmail());
        //Role should be checked here
        if(u == null || u.getRole().getName().equals("ROLE_USER"))
        {
            return "redirect:/admin_login";
        }
        else {

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

    /*
    Admin Register
     */
    @GetMapping("/admin_register")
    public String adminRegister(ModelMap modelMap)
    {
        modelMap.addAttribute("user",new SignUpForm());
        return "admin_reg";
    }

    @PostMapping("/admin_register")
    public String adminRegister(@ModelAttribute("user") SignUpForm signUpForm)
    {
        Users u = new Users();
        //UserName = Email
        Users temUsers = userDao.findByUsername(signUpForm.getEmail());

        if(temUsers != null)
        {
            return "redirect:/admin_register";
        }
        else {
            u.setFirst_name(signUpForm.getFirst_name());
            u.setLast_name(signUpForm.getLast_name());
            u.setUsername(signUpForm.getEmail());
            u.setMobile(signUpForm.getMobile());
            u.setPassword(signUpForm.getPassword());

            Role role = roleDao.findByName("ROLE_ADMIN");
            u.setRole(role);

            u.setEnable(true);

            userDao.save(u);


            return "redirect:/admin_login";

        }

    }
}
