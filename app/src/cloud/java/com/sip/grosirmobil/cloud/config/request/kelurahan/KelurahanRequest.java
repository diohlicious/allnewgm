package com.sip.grosirmobil.cloud.config.request.kelurahan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KelurahanRequest {

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("sub_district")
    @Expose
    private String subDistrict;

    public KelurahanRequest(String city, String subDistrict) {
        this.city = city;
        this.subDistrict = subDistrict;
    }
}
