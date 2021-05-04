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

    @SerializedName("biayaadmin")
    @Expose
    private long biayaAdmin;

    public PilihUnitBayarRequest(String kik, long bayar, long biayaadmin) {
        this.kik = kik;
        Bayar = bayar;
        biayaAdmin = biayaadmin;
    }
}
