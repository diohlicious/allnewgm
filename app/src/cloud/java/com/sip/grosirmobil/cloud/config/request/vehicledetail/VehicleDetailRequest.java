package com.sip.grosirmobil.cloud.config.request.vehicledetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleDetailRequest {

    @SerializedName("ohid")
    @Expose
    private String openHouseId;

    @SerializedName("kik")
    @Expose
    private String kik;

    public VehicleDetailRequest(String openHouseId, String kik) {
        this.openHouseId = openHouseId;
        this.kik = kik;
    }
}
