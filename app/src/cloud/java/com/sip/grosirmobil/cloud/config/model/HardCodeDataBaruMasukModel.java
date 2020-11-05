package com.sip.grosirmobil.cloud.config.model;

public class HardCodeDataBaruMasukModel {

    private String vehicleName;
    private String platNumber;
    private String city;
    private String price;
    private String expiredDate;

    public HardCodeDataBaruMasukModel(String vehicleName, String platNumber, String city, String price, String expiredDate) {
        this.vehicleName = vehicleName;
        this.platNumber = platNumber;
        this.city = city;
        this.price = price;
        this.expiredDate = expiredDate;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public String getPlatNumber() {
        return platNumber;
    }

    public String getCity() {
        return city;
    }

    public String getPrice() {
        return price;
    }

    public String getExpiredDate() {
        return expiredDate;
    }
}
