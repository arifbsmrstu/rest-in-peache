package com.example.rokomarifinal.security;

import com.example.rokomarifinal.dao.RoleDao;
import com.example.rokomarifinal.dao.UserDao;
import com.example.rokomarifinal.message.response.LoginRole;
import com.example.rokomarifinal.message.response.UserLoginResponseMessage;
import com.example.rokomarifinal.model.ApplicationUser;
import com.example.rokomarifinal.model.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

import static com.example.rokomarifinal.security.SecurityConstant.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    //Input : {"email":"john@rokomari.com","password":"attitudeadjustment"}
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ApplicationUser applicationUser = new ObjectMapper().readValue(request.getInputStream(), ApplicationUser.class);

            //For Login body response
            

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(applicationUser.getEmail(),applicationUser.getPassword()));
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        ZonedDateTime expirationalTimeUTC = ZonedDateTime.now(ZoneOffset.UTC).plus( EXPIRATION_TIME,ChronoUnit.MILLIS);
        String token = Jwts.builder().setSubject(((User)authResult.getPrincipal()).getUsername())
                .setExpiration(Date.from(expirationalTimeUTC.toInstant()))
                .signWith(SignatureAlgorithm.HS256,SECRET)
                .compact();

        /*
        Login response message
         */


        //Return JSON for login request
        response.getWriter().write(token);


        response.addHeader(HEADER_STRING,TOKEN_PREFIX+token);
    }
}
