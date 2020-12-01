package com.sip.grosirmobil.cloud.config.response.kecamatan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;

import java.util.List;

public class KecamatanResponse extends GeneralResponse {

    @SerializedName("data")
    @Expose
    private List<DataKecamatanResponse> data;

    public List<DataKecamatanResponse> getData() {
        return data;
    }
}
