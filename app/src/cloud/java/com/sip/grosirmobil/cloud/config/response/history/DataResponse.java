package com.sip.grosirmobil.cloud.config.response.history;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataResponse {

    @SerializedName("page")
    @Expose
    private String page;

    @SerializedName("perpage")
    @Expose
    private int perPage;

    @SerializedName("total_data")
    @Expose
    private int totalData;

    @SerializedName("total_page")
    @Expose
    private String totalPage;

    @SerializedName("rows")
    @Expose
    private List<RowsResponse> rows;

    public String getTotalPage() {
        return totalPage;
    }

    public String getPage() {
        return page;
    }

    public int getPerPage() {
        return perPage;
    }

    public int getTotalData() {
        return totalData;
    }

    public List<RowsResponse> getRows() {
        return rows;
    }
}
