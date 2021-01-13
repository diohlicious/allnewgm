package com.sip.grosirmobil.cloud.config.request.changepassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePasswordRequest {

    @SerializedName("email")
    @Expose
    private String email;
    
    @SerializedName("newpassword")
    @Expose
    private String newPassword;

    public ChangePasswordRequest(String email, String newPassword) {
        this.email = email;
        this.newPassword = newPassword;
    }
}
