package com.sip.grosirmobil.cloud.config.response.homecomingsoon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;

public class HomeComingSoonResponse extends GeneralResponse {

    @SerializedName("data")
    @Expose
    private DataPageHomeComingSoonResponse dataPageHomeComingSoonResponse;

    public DataPageHomeComingSoonResponse getDataPageHomeComingSoonResponse() {
        return dataPageHomeComingSoonResponse;
    }

}
