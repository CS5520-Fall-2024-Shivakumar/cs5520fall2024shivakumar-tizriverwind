package com.example.myapplication;

public class ContactController {
    private String name;
    private String phoneNumber;

    public ContactController(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}