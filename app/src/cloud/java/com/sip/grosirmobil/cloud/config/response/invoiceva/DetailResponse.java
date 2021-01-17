package com.sip.grosirmobil.cloud.config.response.invoiceva;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailResponse {

    @SerializedName("ohid")
    @Expose
    private int ohId;

    @SerializedName("kik")
    @Expose
    private String kik;

    @SerializedName("asset_description")
    @Expose
    private String assetDescription;

    @SerializedName("PlatNo")
    @Expose
    private String platNo;

    @SerializedName("Lokasi")
    @Expose
    private String lokasi;

    @SerializedName("hargaunit")
    @Expose
    private String hargaUnit;

    @SerializedName("foto")
    @Expose
    private String foto;

    public int getOhId() {
        return ohId;
    }

    public String getKik() {
        return kik;
    }

    public String getAssetDescription() {
        return assetDescription;
    }

    public String getPlatNo() {
        return platNo;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getHargaUnit() {
        return hargaUnit;
    }

    public String getFoto() {
        return foto;
    }
}
