package com.sip.grosirmobil.cloud.config.response.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;

public class CartResponse extends GeneralResponse {

    @SerializedName("data")
    @Expose
    private DataOtoJsonResponse dataOtoJsonResponse;

    public DataOtoJsonResponse getDataOtoJsonResponse() {
        return dataOtoJsonResponse;
    }

}
