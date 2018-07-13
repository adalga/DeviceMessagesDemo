package com.example.demo.model;

public class Device {
    String d_id;
    String userId;

    public Device() {
    }

    public Device(String d_id, String userId) {
        this.d_id = d_id;
        this.userId = userId;
    }

    public String getD_id() {
        return d_id;
    }

    public void setD_id(String d_id) {
        this.d_id = d_id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
