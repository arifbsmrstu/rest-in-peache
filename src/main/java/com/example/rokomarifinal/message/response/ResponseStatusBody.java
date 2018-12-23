package com.example.rokomarifinal.message.response;

public class ResponseStatusBody {
    String status;

    public ResponseStatusBody(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
