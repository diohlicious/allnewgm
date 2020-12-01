package com.sip.grosirmobil.cloud.config.response.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("logged_in_user")
    @Expose
    private LoggedInUserResponse loggedInUserResponse;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("authUser")
    @Expose
    private boolean authUser;

    public LoggedInUserResponse getLoggedInUserResponse() {
        return loggedInUserResponse;
    }

    public String getToken() {
        return token;
    }

    public boolean isAuthUser() {
        return authUser;
    }
}
