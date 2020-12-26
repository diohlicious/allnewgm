package com.sip.grosirmobil.cloud.config.request.buynow;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BuyNowRequest {

    @SerializedName("open_house_id")
    @Expose
    private String openHouseId;

    @SerializedName("kik")
    @Expose
    private String kik;

    @SerializedName("agreement_no")
    @Expose
    private String agreementNo;

    @SerializedName("bid_price")
    @Expose
    private String bidPrice;

    public BuyNowRequest(String openHouseId, String kik, String agreementNo, String bidPrice) {
        this.openHouseId = openHouseId;
        this.kik = kik;
        this.agreementNo = agreementNo;
        this.bidPrice = bidPrice;
    }
}
