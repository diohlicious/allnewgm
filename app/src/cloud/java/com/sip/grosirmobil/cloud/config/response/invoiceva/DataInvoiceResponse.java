package com.sip.grosirmobil.cloud.config.response.invoiceva;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataInvoiceResponse {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("dataVA")
    @Expose
    private DataVaResponse dataVaResponse;

    @SerializedName("detail")
    @Expose
    private List<DetailResponse> detailResponseList;

    public String getStatus() {
        return status;
    }

    public DataVaResponse getDataVaResponse() {
        return dataVaResponse;
    }

    public List<DetailResponse> getDetailResponseList() {
        return detailResponseList;
    }
}
