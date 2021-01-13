package com.sip.grosirmobil.cloud.config.response.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartResponse {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private List<DataCartResponse> dataCartResponseList;

    public String getMessage() {
        return message;
    }

    public List<DataCartResponse> getDataCartResponseList() {
        return dataCartResponseList;
    }
}
