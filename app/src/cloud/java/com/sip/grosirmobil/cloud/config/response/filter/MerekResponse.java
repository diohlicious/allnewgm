package com.sip.grosirmobil.cloud.config.response.filter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;

import java.util.List;

public class MerekResponse extends GeneralResponse {

    @SerializedName("data")
    @Expose
    private List<DataMerekResponse> dataMerekResponseList;

    public List<DataMerekResponse> getDataMerekResponseList() {
        return dataMerekResponseList;
    }
}
