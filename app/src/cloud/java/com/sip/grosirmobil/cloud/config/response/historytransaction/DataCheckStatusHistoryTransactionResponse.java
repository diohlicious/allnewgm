package com.sip.grosirmobil.cloud.config.response.historytransaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataCheckStatusHistoryTransactionResponse {

    @SerializedName("status")
    @Expose
    private String status;

    public String getStatus() {
        return status;
    }
}
