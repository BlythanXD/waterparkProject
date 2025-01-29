package com.example.waterparkgrid;

public class Booking {

    private String id;
    private String name;
    private String email;
    private String phone;

    // Default constructor required for Firebase
    public Booking() {}

    // Parameterized constructor to initialize Booking object
    public Booking(String id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
