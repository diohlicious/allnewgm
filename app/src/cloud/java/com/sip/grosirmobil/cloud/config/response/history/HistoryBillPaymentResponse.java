package com.sip.grosirmobil.cloud.config.response.history;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryBillPaymentResponse {

    @SerializedName("error_code")
    @Expose
    private String errorCode;

    @SerializedName("error_desc")
    @Expose
    private String errorDesc;

    @SerializedName("data")
    @Expose
    private DataResponse data;

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public DataResponse getData() {
        return data;
    }
}
