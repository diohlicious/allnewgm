package com.sip.grosirmobil.cloud.config.request.kabupaten;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KabupatenRequest {

    @SerializedName("province_code")
    @Expose
    private String provinceCode;

    public KabupatenRequest(String provinceCode) {
        this.provinceCode = provinceCode;
    }
}
