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

    public HomeComingSoonRequest(int page, int max) {
        this.page = page;
        this.max = max;
    }
}
