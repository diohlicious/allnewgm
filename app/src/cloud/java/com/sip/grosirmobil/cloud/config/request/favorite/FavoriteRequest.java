package com.sip.grosirmobil.cloud.config.request.favorite;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FavoriteRequest {

    @SerializedName("kik")
    @Expose
    private String kik;

    public FavoriteRequest(String kik) {
        this.kik = kik;
    }
}
