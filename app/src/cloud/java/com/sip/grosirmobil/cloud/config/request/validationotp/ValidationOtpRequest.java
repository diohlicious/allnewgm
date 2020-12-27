package com.sip.grosirmobil.cloud.config.request.validationotp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValidationOtpRequest {

    @SerializedName("kodeOtp")
    @Expose
    private int kodeOtp;

    @SerializedName("userId")
    @Expose
    private int userId;

    public ValidationOtpRequest(int kodeOtp, int userId) {
        this.kodeOtp = kodeOtp;
        this.userId = userId;
    }
}
