package com.sip.grosirmobil.cloud.config.response.filter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataGradeResponse {

    @SerializedName("grade")
    @Expose
    private String grade;

    public String getGrade() {
        return grade;
    }
}
