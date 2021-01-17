package com.sip.grosirmobil.cloud.config.response.generateva;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenerateVaResponse {

    @SerializedName("status")
    @Expose
    private String message;

    @SerializedName("orderno")
    @Expose
    private String orderNo;

    public String getMessage() {
        return message;
    }

    public String getOrderNo() {
        return orderNo;
    }
}
