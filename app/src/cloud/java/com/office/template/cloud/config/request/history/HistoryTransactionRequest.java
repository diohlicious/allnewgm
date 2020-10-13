package com.office.template.cloud.config.request.history;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryTransactionRequest {

    @SerializedName("page")
    @Expose
    private int page;

    public HistoryTransactionRequest(int page) {
        this.page = page;
    }

}
