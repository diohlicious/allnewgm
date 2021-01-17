package com.sip.grosirmobil.cloud.config.request.generateva;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PilihUnitBayarRequest {

    @SerializedName("kik")
    @Expose
    private String kik;

    @SerializedName("bayar")
    @Expose
    private long Bayar;

    public PilihUnitBayarRequest(String kik, long bayar) {
        this.kik = kik;
        Bayar = bayar;
    }
}
