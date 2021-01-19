package com.sip.grosirmobil.cloud.config.response.vehicledetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserBidResponse {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("date_bid")
    @Expose
    private String dateBid;

    @SerializedName("price_bid")
    @Expose
    private String priceBid;

    public String getName() {
        return name;
    }

    public String getDateBid() {
        return dateBid;
    }

    public String getPriceBid() {
        return priceBid;
    }
}
