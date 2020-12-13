package com.sip.grosirmobil.cloud.config.response.checkactivetoken;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;

public class CheckActiveTokenResponse extends GeneralResponse {

    @SerializedName("data")
    @Expose
    private DataCheckActiveTokenResponse  data;

    public DataCheckActiveTokenResponse getData() {
        return data;
    }
}
