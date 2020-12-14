package com.sip.grosirmobil.cloud.config.request.resendotp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResendOtpRequest {

    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("tokenType")
    @Expose
    private String tokenType;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("namalengkap")
    @Expose
    private String namaLengkap;

    public ResendOtpRequest(String userId, String tokenType, String phone, String email, String namaLengkap) {
        this.userId = userId;
        this.tokenType = tokenType;
        this.phone = phone;
        this.email = email;
        this.namaLengkap = namaLengkap;
    }
}
