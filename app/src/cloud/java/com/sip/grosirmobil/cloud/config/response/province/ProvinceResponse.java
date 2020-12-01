package com.sip.grosirmobil.cloud.config.response.province;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;

import java.util.List;

public class ProvinceResponse extends GeneralResponse {

    @SerializedName("data")
    @Expose
    private List<DataProvinceResponse> data;

    public List<DataProvinceResponse> getData() {
        return data;
    }
}
