package com.sip.grosirmobil.cloud.config.request.generateva;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenerateVaRequest {

    @SerializedName("pilihunitbayar")
    @Expose
    private List<PilihUnitBayarRequest> pilihUnitBayarRequestList;

    @SerializedName("pilihBANK")
    @Expose
    private String chooseBank;

    @SerializedName("totamount")
    @Expose
    private long totalAmount;

    public GenerateVaRequest(List<PilihUnitBayarRequest> pilihUnitBayarRequestList, String chooseBank, long totalAmount) {
        this.pilihUnitBayarRequestList = pilihUnitBayarRequestList;
        this.chooseBank = chooseBank;
        this.totalAmount = totalAmount;
    }
}
