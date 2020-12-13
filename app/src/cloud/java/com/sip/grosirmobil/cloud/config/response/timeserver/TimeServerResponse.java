package com.sip.grosirmobil.cloud.config.response.timeserver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;

public class TimeServerResponse extends GeneralResponse {

    @SerializedName("data")
    @Expose
    private DataTimeServerResponse data;

    public DataTimeServerResponse getData() {
        return data;
    }
}
