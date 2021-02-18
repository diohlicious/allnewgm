package com.sip.grosirmobil.cloud.config.response.nego;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralNegoAndBuyNowResponse {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("pesan")
    @Expose
    private String description = null;

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
