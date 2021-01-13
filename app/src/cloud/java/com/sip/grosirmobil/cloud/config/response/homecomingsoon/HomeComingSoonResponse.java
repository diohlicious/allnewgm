package com.sip.grosirmobil.cloud.config.response.homecomingsoon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeComingSoonResponse {

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

    @SerializedName("message")
    @Expose
    private String message;

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

    public String getMessage() {
        return message;
    }

    @SerializedName("data")
    @Expose
    private DataPageHomeComingSoonResponse dataPageHomeComingSoonResponse;

    public DataPageHomeComingSoonResponse getDataPageHomeComingSoonResponse() {
        return dataPageHomeComingSoonResponse;
    }

}
