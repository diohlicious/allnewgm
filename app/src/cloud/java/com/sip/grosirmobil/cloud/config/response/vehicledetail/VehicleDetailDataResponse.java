package com.sip.grosirmobil.cloud.config.response.vehicledetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleDetailDataResponse {

    @SerializedName("item")
    @Expose
    private String item;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("description")
    @Expose
    private String description;

    public String getItem() {
        return item;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
