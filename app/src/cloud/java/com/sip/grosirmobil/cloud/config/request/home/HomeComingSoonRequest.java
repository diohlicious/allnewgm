package com.sip.grosirmobil.cloud.config.request.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeComingSoonRequest {

    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("max")
    @Expose
    private int max;

    @SerializedName("lokasi")
    @Expose
    private String lokasi;

    @SerializedName("tahunstart")
    @Expose
    private int tahunStart;

    @SerializedName("tahunend")
    @Expose
    private int tahunEnd;

    @SerializedName("hargastart")
    @Expose
    private long hargaStart;

    @SerializedName("hargaend")
    @Expose
    private long hargaEnd;

    @SerializedName("merek")
    @Expose
    private String merek;

    public HomeComingSoonRequest(int page, int max, String lokasi, int tahunStart, int tahunEnd, long hargaStart, long hargaEnd, String merek) {
        this.page = page;
        this.max = max;
        this.lokasi = lokasi;
        this.tahunStart = tahunStart;
        this.tahunEnd = tahunEnd;
        this.hargaStart = hargaStart;
        this.hargaEnd = hargaEnd;
        this.merek = merek;
    }
}
