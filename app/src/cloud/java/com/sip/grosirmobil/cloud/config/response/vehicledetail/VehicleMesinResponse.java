package com.sip.grosirmobil.cloud.config.response.vehicledetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleMesinResponse {

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

    public String getInspectionDefGroupId() {
        return inspectionDefGroupId;
    }

    public String getItem() {
        return item;
    }

    public String getValue() {
        return value;
    }

    public String getComment() {
        return comment;
    }
}
