package com.sip.grosirmobil.cloud.config.response.vehicledetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;

public class VehicleDetailResponse extends GeneralResponse {
    @SerializedName("data")
    @Expose
    private DataVehicleDetailResponse dataVehicleDetailResponse;

    public DataVehicleDetailResponse getDataVehicleDetailResponse() {
        return dataVehicleDetailResponse;
    }
}



