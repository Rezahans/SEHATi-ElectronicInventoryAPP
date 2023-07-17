package com.rk.electronic.model;

public class Item {
    private String batchno;
    private String expired;
    private String imageName;
    private String imageUrl;
    private String location;
    private String name;
    private String quantity;
    private String type;

    public Item() {
        // Constructor kosong diperlukan untuk Firebase Realtime Database
    }

    public Item(String batchNo, String expired, String imageName, String imageUrl, String location,
                String name, String quantity, String type) {
        this.batchno = batchNo;
        this.expired = expired;
        this.imageName = imageName;
        this.imageUrl = imageUrl;
        this.location = location;
        this.name = name;
        this.quantity = quantity;
        this.type = type;
    }

    public String getBatchNo() {
        return batchno;
    }

    public String getExpired() {
        return expired;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getType() {
        return type;
    }
}
