package com.sip.grosirmobil.cloud.config.response.filter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataMerekResponse {

    @SerializedName("merek")
    @Expose
    private String merek;

    public String getMerek() {
        return merek;
    }
}
