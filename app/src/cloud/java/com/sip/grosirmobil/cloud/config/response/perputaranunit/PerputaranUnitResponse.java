package com.sip.grosirmobil.cloud.config.response.perputaranunit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;

import java.util.List;

public class PerputaranUnitResponse extends GeneralResponse {

    @SerializedName("data")
    @Expose
    private List<DataPerputaranUnitResponse> data;

    public List<DataPerputaranUnitResponse> getData() {
        return data;
    }
}
