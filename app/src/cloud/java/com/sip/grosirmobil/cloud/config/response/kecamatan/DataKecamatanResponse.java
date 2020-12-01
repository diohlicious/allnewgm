package com.sip.grosirmobil.cloud.config.response.kecamatan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataKecamatanResponse {

    @SerializedName("sub_district")
    @Expose
    private String subDistrict;

    public String getSubDistrict() {
        return subDistrict;
    }
}
