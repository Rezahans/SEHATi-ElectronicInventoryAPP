package com.rk.electronic.model;

public class Profile {
    private String name;
    private String email;

    public Profile() {
        // Empty constructor required for Firebase Realtime Database
    }

    public Profile(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
