package com.sip.grosirmobil.cloud.config.response.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;

public class LoginResponse extends GeneralResponse {

    @SerializedName("data")
    @Expose
    private DataLoginResponse dataLoginResponse;

    public DataLoginResponse getDataLoginResponse() {
        return dataLoginResponse;
    }
}
