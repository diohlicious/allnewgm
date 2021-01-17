package com.sip.grosirmobil.cloud.config.request.invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InvoiceVaRequest {

    @SerializedName("ref")
    @Expose
    private String ref;

    public InvoiceVaRequest(String ref) {
        this.ref = ref;
    }
}
