package com.example.rokomarifinal.message.response;

import java.util.Arrays;

public class UserLoginResponseMessage {

    private String status;
    private String name;
    private String first_name;
    private String email;
    private LoginRole[] role = {new LoginRole("USER","ACTIVE")};

    public UserLoginResponseMessage(){}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserLoginResponseMessage{" +
                "status='" + status + '\'' +
                ", name='" + name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", email='" + email + '\'' +
                ", role=" + Arrays.toString(role) +
                '}';
    }
}
