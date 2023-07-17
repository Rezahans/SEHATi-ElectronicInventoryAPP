package com.rk.electronic.model;

public class Driver {
    private String batchno;
    private String name;
    private String phone;
    private String policeno;
    private String address;

    public Driver() {
        // Default constructor required for Firebase
    }

    public Driver(String batchno, String name, String phone, String policeno, String address) {
        this.batchno = batchno;
        this.name = name;
        this.phone = phone;
        this.policeno = policeno;
        this.address = address;
    }

    public String getBatchno() {
        return batchno;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPoliceno() {
        return policeno;
    }

    public String getAddress() {
        return address;
    }
}
