package com.sip.grosirmobil.cloud.config.request.favorite;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FavoriteRequest {

    @SerializedName("userid")
    @Expose
    private String userId;

    @SerializedName("kik")
    @Expose
    private String kik;

    @SerializedName("agreement_no")
    @Expose
    private String agreementNo;

    @SerializedName("gm_openhouse_id")
    @Expose
    private String openHouseId;

    @SerializedName("isfavorit")
    @Expose
    private int isFavorit;

    public FavoriteRequest(String userId, String kik, String agreementNo, String openHouseId, int isFavorit) {
        this.userId = userId;
        this.kik = kik;
        this.agreementNo = agreementNo;
        this.openHouseId = openHouseId;
        this.isFavorit = isFavorit;
    }
}
