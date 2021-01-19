package com.sip.grosirmobil.cloud.config.request.history;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryTransactionRequest {

    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("max")
    @Expose
    private int max;

    @SerializedName("Ismenang")
    @Expose
    private String isMenang;

    public HistoryTransactionRequest(int page, int max, String isMenang) {
        this.page = page;
        this.max = max;
        this.isMenang = isMenang;
    }
}
