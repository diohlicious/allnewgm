package com.sip.grosirmobil.cloud.config.request.kecamatan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KecamatanRequest {

    @SerializedName("city")
    @Expose
    private String city;

    public KecamatanRequest(String city) {
        this.city = city;
    }
}
