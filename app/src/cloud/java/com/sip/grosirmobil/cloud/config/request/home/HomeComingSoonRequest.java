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

    @SerializedName("grade")
    @Expose
    private String grade;

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

    @SerializedName("category")
    @Expose
    private String category;

    public HomeComingSoonRequest(int page, int max, String grade, String lokasi, int tahunStart, int tahunEnd, long hargaStart, long hargaEnd, String merek, String category) {
        this.page = page;
        this.max = max;
        this.grade = grade;
        this.lokasi = lokasi;
        this.tahunStart = tahunStart;
        this.tahunEnd = tahunEnd;
        this.hargaStart = hargaStart;
        this.hargaEnd = hargaEnd;
        this.merek = merek;
        this.category = category;
    }
}
