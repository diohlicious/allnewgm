package com.sip.grosirmobil.cloud.config.request.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeHistoryRequest {

    @SerializedName("page")
    @Expose
    private int page;
    
    @SerializedName("max")
    @Expose
    private int max;
    
    @SerializedName("Ismenang")
    @Expose
    private String isMenang;

    public HomeHistoryRequest(int page, int max, String isMenang) {
        this.page = page;
        this.max = max;
        this.isMenang = isMenang;
    }
}
