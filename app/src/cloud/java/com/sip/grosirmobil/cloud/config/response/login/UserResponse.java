package com.sip.grosirmobil.cloud.config.response.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("NIK")
    @Expose
    private String nik;

    @SerializedName("Nama_Lengkap")
    @Expose
    private String namaLengkap;

    @SerializedName("NoHP")
    @Expose
    private String noHP;

    @SerializedName("IsLengkap")
    @Expose
    private String isLengkap;

    @SerializedName("verifiedby")
    @Expose
    private String verifiedBy;

    @SerializedName("IsVerifikasi")
    @Expose
    private String isVerification;

    @SerializedName("jamverifikasi")
    @Expose
    private String jamVerification;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("IsBlock")
    @Expose
    private String isBlock;

    @SerializedName("IsBidder")
    @Expose
    private String isBidder;

    @SerializedName("profile_photo_url")
    @Expose
    private String profilePhotoUrl;

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getNik() {
        return nik;
    }

    public String getNoHP() {
        return noHP;
    }

    public String getIsLengkap() {
        return isLengkap;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public String getIsVerification() {
        return isVerification;
    }

    public String getJamVerification() {
        return jamVerification;
    }

    public String getIsBlock() {
        return isBlock;
    }

    public String getIsBidder() {
        return isBidder;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }
}
