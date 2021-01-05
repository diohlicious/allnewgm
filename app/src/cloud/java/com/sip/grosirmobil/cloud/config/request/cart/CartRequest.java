package com.sip.grosirmobil.cloud.config.request.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartRequest {
    @SerializedName("ohid")
    @Expose
    private String ohid;
    
    @SerializedName("kik")
    @Expose
    private String kik;
    
    @SerializedName("agreement_no")
    @Expose
    private String agreementNo;
    
    @SerializedName("bid_price")
    @Expose
    private String bidPrice;
    
}
