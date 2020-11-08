package com.sip.grosirmobil.cloud.config.model;

public class HardCodeDataModel {

    private String dataHardCode;
    private String description;
    private String imageNumber;

    public HardCodeDataModel(String dataHardCode) {
        this.dataHardCode = dataHardCode;
    }

    public HardCodeDataModel(String description, String imageNumber) {
        this.description = description;
        this.imageNumber = imageNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getImageNumber() {
        return imageNumber;
    }

    public String getDataHardCode() {
        return dataHardCode;
    }
}
