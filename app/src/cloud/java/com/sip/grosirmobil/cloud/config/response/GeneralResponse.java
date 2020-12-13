package com.sip.grosirmobil.cloud.config.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralResponse {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("description")
    @Expose
    private String description = null;

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
