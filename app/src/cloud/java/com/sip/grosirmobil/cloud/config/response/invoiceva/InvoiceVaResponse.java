package com.sip.grosirmobil.cloud.config.response.invoiceva;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InvoiceVaResponse {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private DataInvoiceResponse dataInvoiceResponse;

    public String getMessage() {
        return message;
    }

    public DataInvoiceResponse getDataInvoiceResponse() {
        return dataInvoiceResponse;
    }
}
