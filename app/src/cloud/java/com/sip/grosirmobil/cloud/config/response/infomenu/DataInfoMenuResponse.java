package com.sip.grosirmobil.cloud.config.response.infomenu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataInfoMenuResponse {

    @SerializedName("tanggal")
    @Expose
    private String tanggal;

    @SerializedName("news")
    @Expose
    private String news;

    @SerializedName("gambar")
    @Expose
    private String gambar;

    public String getTanggal() {
        return tanggal;
    }

    public String getNews() {
        return news;
    }

    public String getGambar() {
        return gambar;
    }
}
