package com.sip.grosirmobil.cloud.config.response.kelurahan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;

import java.util.List;

public class KelurahanResponse extends GeneralResponse {

    @SerializedName("data")
    @Expose
    private List<DataKelurahanResponse> data;

    public List<DataKelurahanResponse> getData() {
        return data;
    }
}
