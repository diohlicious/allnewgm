package com.sip.grosirmobil.cloud.config.response.homecomingsoon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeComingSoonResponse {

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    @SerializedName("data")
    @Expose
    private DataPageHomeComingSoonResponse dataPageHomeComingSoonResponse;

    public DataPageHomeComingSoonResponse getDataPageHomeComingSoonResponse() {
        return dataPageHomeComingSoonResponse;
    }

}
