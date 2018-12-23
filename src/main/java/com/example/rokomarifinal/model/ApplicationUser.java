package com.example.rokomarifinal.model;

public class ApplicationUser {
    //Username = Email
    String email;
    String password;

    public ApplicationUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public ApplicationUser() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
