package com.sip.grosirmobil.cloud.config.response.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse {

    @SerializedName("Nama_Lengkap")
    @Expose
    private String namaLengkap;

    @SerializedName("email")
    @Expose
    private String email;

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public String getEmail() {
        return email;
    }
}
