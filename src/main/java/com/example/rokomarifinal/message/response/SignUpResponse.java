package com.example.rokomarifinal.message.response;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignUpResponse {

    @NotBlank
    @Size(min = 3, max = 50)
    String first_name;
    @NotBlank
    @Size(min = 3, max = 50)
    String last_name;
    //Basically Email
    @NotBlank
    @Size(min = 3, max = 50)
    String email;
    @NotBlank
    @Size(min = 3, max = 13)
    String mobile;
    @NotBlank
    @Size(min = 3, max = 20)
    String status;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
