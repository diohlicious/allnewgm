package com.sip.grosirmobil.cloud.config.model;

public class BannerDataModel {

    private String description;
    private String imageNumber;

    public BannerDataModel(String description, String imageNumber) {
        this.description = description;
        this.imageNumber = imageNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getImageNumber() {
        return imageNumber;
    }

}
