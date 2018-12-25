package com.example.rokomarifinal.service;

import com.example.rokomarifinal.dao.RoleDao;
import com.example.rokomarifinal.dao.UserDao;
import com.example.rokomarifinal.model.ApplicationUser;
import com.example.rokomarifinal.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;
    String roleFixing;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        ApplicationUser u = loadApplicationUserByUsername(s);
       // return new User("arif","123456",AuthorityUtils.createAuthorityList());

        if(userDao.findByUsername(u.getEmail()).getRole().getName().equals("ROLE_USER"))
        {
            roleFixing = "USER";
        }
        else {
            roleFixing = "ADMIN";
        }

        return User.withDefaultPasswordEncoder().username(u.getEmail()).password(u.getPassword()).roles(roleFixing).build();
    }

    public ApplicationUser loadApplicationUserByUsername(String username)
    {
        Users u = userDao.findByUsername(username);

        return new ApplicationUser(u.getUsername(),u.getPassword());
    }
}
