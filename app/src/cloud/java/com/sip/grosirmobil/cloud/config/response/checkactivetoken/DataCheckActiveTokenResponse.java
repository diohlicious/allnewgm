package com.sip.grosirmobil.cloud.config.response.checkactivetoken;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sip.grosirmobil.cloud.config.response.login.LoggedInUserResponse;

public class DataCheckActiveTokenResponse {

    @SerializedName("logged_in_user")
    @Expose
    private LoggedInUserResponse loggedInUserResponse;

    public LoggedInUserResponse getLoggedInUserResponse() {
        return loggedInUserResponse;
    }
}
