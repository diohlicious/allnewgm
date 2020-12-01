package com.sip.grosirmobil.cloud.config.response.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoggedInUserResponse {

    @SerializedName("user")
    @Expose
    private UserResponse userResponse;

    @SerializedName("profil")
    @Expose
    private ProfilResponse profilResponse;

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public ProfilResponse getProfilResponse() {
        return profilResponse;
    }
}
