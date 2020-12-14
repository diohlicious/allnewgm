package com.sip.grosirmobil.cloud.config.response.savedataregister;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;

public class SaveDataRegisterResponse extends GeneralResponse {

    @SerializedName("userId")
    @Expose
    private String userId;

    public String getUserId() {
        return userId;
    }
}
