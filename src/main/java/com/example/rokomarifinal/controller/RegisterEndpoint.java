package com.example.rokomarifinal.controller;

import com.example.rokomarifinal.dao.RoleDao;
import com.example.rokomarifinal.dao.UserDao;
import com.example.rokomarifinal.message.request.SignUpForm;
import com.example.rokomarifinal.message.response.SignUpResponse;
import com.example.rokomarifinal.model.Role;
import com.example.rokomarifinal.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RegisterEndpoint {

    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;

    @PostMapping
    @RequestMapping("/register")
    public Object register(@Valid @RequestBody SignUpForm signUpForm)
    {
        Users u = new Users();
        //UserName = Email
        Users temUsers = userDao.findByUsername(signUpForm.getEmail());

        if(temUsers != null)
        {
            return "This email already exist";
        }
        else {
            u.setFirst_name(signUpForm.getFirst_name());
            u.setLast_name(signUpForm.getLast_name());
            u.setUsername(signUpForm.getEmail());
            u.setMobile(signUpForm.getMobile());
            u.setPassword(signUpForm.getPassword());

            Role role = roleDao.findByName("ROLE_USER");
            u.setRole(role);

            u.setEnable(true);

            userDao.save(u);

            SignUpResponse signUpResponse = new SignUpResponse();
            signUpResponse.setFirst_name(signUpForm.getFirst_name());
            signUpResponse.setLast_name(signUpForm.getLast_name());
            signUpResponse.setEmail(signUpForm.getEmail());
            signUpResponse.setMobile(signUpForm.getMobile());
            signUpResponse.setStatus("success");

            return signUpResponse;

        }
    }

    @GetMapping
    @RequestMapping("/test")
    public String test()
    {
        return "Test Success";
    }
}
