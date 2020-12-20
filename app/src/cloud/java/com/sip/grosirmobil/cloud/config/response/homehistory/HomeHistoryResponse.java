package com.sip.grosirmobil.cloud.config.response.homehistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;

public class HomeHistoryResponse extends GeneralResponse {

    @SerializedName("data")
    @Expose
    private DataPageHomeHistoryResponse dataPageHomeHistoryResponse;

    public DataPageHomeHistoryResponse getDataPageHomeHistoryResponse() {
        return dataPageHomeHistoryResponse;
    }

}
