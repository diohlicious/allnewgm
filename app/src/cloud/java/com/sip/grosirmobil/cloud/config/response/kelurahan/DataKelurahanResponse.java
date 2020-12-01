package com.sip.grosirmobil.cloud.config.response.kelurahan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataKelurahanResponse {

    @SerializedName("urban")
    @Expose
    private String urban;

    @SerializedName("postal_code")
    @Expose
    private String postalCode;

    public String getUrban() {
        return urban;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
