package com.sip.grosirmobil.cloud.config.response.homelive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;

public class HomeLiveResponse extends GeneralResponse {

    @SerializedName("data")
    @Expose
    private DataPageHomeLiveResponse dataPageHomeLiveResponse;

    public DataPageHomeLiveResponse getDataPageHomeLiveResponse() {
        return dataPageHomeLiveResponse;
    }

}
