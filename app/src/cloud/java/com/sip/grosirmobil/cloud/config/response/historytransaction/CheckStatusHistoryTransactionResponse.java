package com.sip.grosirmobil.cloud.config.response.historytransaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;

public class CheckStatusHistoryTransactionResponse extends GeneralResponse {

    @SerializedName("data")
    @Expose
    private DataCheckStatusHistoryTransactionResponse dataCheckStatusHistoryTransactionResponse;

    public DataCheckStatusHistoryTransactionResponse getDataCheckStatusHistoryTransactionResponse() {
        return dataCheckStatusHistoryTransactionResponse;
    }
}
