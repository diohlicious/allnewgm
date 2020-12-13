package com.sip.grosirmobil.cloud.config.response.timeserver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataTimeServerResponse {

    @SerializedName("time_server")
    @Expose
    private String timeServer;

    public String getTimeServer() {
        return timeServer;
    }
}
