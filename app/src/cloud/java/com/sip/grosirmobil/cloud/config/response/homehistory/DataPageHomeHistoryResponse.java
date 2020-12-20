package com.sip.grosirmobil.cloud.config.response.homehistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataPageHomeHistoryResponse {

    @SerializedName("total")
    @Expose
    private int total;

    @SerializedName("per_page")
    @Expose
    private int perPage;

    @SerializedName("current_page")
    @Expose
    private int currentPage;

    @SerializedName("max_page")
    @Expose
    private int maxPage;

    @SerializedName("data_history")
    @Expose
    
    private List<DataHomeHistoryResponse> dataHomeHistoryResponseList;

    public int getTotal() {
        return total;
    }

    public int getPerPage() {
        return perPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public List<DataHomeHistoryResponse> getDataHomeHistoryResponseList() { return dataHomeHistoryResponseList;
    }
}
