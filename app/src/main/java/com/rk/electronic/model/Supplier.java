package com.rk.electronic.model;

public class Supplier {
    private String batchno;
    private String address;
    private String name;
    private String phone;
    private String province;

    public Supplier() {
        // Default constructor required for Firebase
    }

    public Supplier(String batchNo, String address, String name, String phone, String province) {
        this.batchno = batchNo;
        this.address = address;
        this.name = name;
        this.phone = phone;
        this.province = province;
    }

    public String getBatchNo() {
        return batchno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
