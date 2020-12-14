package com.sip.grosirmobil.cloud.config.request.validationotp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValidationOtpRequest {

    @SerializedName("kodeOtp")
    @Expose
    private String kodeOtp;

    @SerializedName("userId")
    @Expose
    private String userId;

    public ValidationOtpRequest(String kodeOtp, String userId) {
        this.kodeOtp = kodeOtp;
        this.userId = userId;
    }
}
