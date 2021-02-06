package com.sip.grosirmobil.cloud.config.request.history;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckStatusHistoryRequest {
    @SerializedName("VA_number")
    @Expose
    private String VaNumber;

    public CheckStatusHistoryRequest(String vaNumber) {
        VaNumber = vaNumber;
    }
}
