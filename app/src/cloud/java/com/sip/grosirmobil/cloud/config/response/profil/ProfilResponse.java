package com.sip.grosirmobil.cloud.config.response.profil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;

import java.util.List;

public class ProfilResponse extends GeneralResponse {

    @SerializedName("data")
    @Expose
    private List<DataProfilResponse> data;

    public List<DataProfilResponse> getData() {
        return data;
    }
}
