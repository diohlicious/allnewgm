package com.sip.grosirmobil.cloud.config.response.historytransaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryTransactionResponse {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private DataPageHistoryTransactionResponse dataPageHistoryTransactionResponse;

    public String getMessage() {
        return message;
    }

    public DataPageHistoryTransactionResponse getDataPageHistoryTransactionResponse() {
        return dataPageHistoryTransactionResponse;
    }
}
