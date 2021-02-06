package com.sip.grosirmobil.cloud.config.response.infomenu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;

import java.util.List;

public class InfoMenuResponse extends GeneralResponse {

    @SerializedName("data")
    @Expose
    private List<DataInfoMenuResponse> dataInfoMenuResponseList;

    public List<DataInfoMenuResponse> getDataInfoMenuResponseList() {
        return dataInfoMenuResponseList;
    }
}
