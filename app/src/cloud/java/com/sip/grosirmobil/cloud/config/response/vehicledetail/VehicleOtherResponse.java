package com.sip.grosirmobil.cloud.config.response.vehicledetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleOtherResponse {
    @SerializedName("InspectionDefGroupId")
    @Expose
    private String inspectionDefGroupId;
    
    @SerializedName("Item")
    @Expose
    private String item;

    @SerializedName("Value")
    @Expose
    private String value;

    @SerializedName("Comment")
    @Expose
    private String comment;
    
}
