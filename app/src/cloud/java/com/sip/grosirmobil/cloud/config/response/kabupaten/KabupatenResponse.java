package com.sip.grosirmobil.cloud.config.response.kabupaten;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;

import java.util.List;

public class KabupatenResponse extends GeneralResponse {

    @SerializedName("data")
    @Expose
    private List<DataKabupatenResponse> data;

    public List<DataKabupatenResponse> getData() {
        return data;
    }
}
