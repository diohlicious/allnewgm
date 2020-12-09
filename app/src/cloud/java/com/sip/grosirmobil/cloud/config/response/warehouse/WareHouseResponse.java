package com.sip.grosirmobil.cloud.config.response.warehouse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;

import java.util.List;

public class WareHouseResponse extends GeneralResponse {

    @SerializedName("data")
    @Expose
    private List<DataWareHouseResponse> data;

    public List<DataWareHouseResponse> getData() {
        return data;
    }
}
