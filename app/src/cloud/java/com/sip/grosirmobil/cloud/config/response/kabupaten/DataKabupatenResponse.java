package com.sip.grosirmobil.cloud.config.response.kabupaten;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataKabupatenResponse {

    @SerializedName("city")
    @Expose
    private String city;

    public String getCity() {
        return city;
    }
}
