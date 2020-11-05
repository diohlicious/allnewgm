package com.sip.grosirmobil.cloud.config.response.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("error_code")
    @Expose
    private String errorCode;

    @SerializedName("error_desc")
    @Expose
    private String errorDesc;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("expire_at")
    @Expose
    private String expireAt;

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public String getToken() {
        return token;
    }

    public String getExpireAt() {
        return expireAt;
    }
}
