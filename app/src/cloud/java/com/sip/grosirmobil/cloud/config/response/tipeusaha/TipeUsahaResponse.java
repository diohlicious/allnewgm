package com.sip.grosirmobil.cloud.config.response.tipeusaha;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;

import java.util.List;

public class TipeUsahaResponse extends GeneralResponse {

    @SerializedName("data")
    @Expose
    private List<DataTipeUsahaResponse> data;

    public List<DataTipeUsahaResponse> getData() {
        return data;
    }
}
