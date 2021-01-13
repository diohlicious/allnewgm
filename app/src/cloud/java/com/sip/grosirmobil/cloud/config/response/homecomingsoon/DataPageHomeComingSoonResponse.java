package com.sip.grosirmobil.cloud.config.response.homecomingsoon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataPageHomeComingSoonResponse {

    @SerializedName("data_list_event")
    @Expose
    private List<DataHomeComingSoonResponse> dataHomeComingSoonResponseList;

    public List<DataHomeComingSoonResponse> getDataHomeComingSoonResponseList() {
        return dataHomeComingSoonResponseList;
    }
}
