package com.sip.grosirmobil.cloud.config.response.question;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;

import java.util.List;

public class QuestionResponse extends GeneralResponse {

    @SerializedName("data")
    @Expose
    private List<DataQuestionResponse> data;

    public List<DataQuestionResponse> getData() {
        return data;
    }
}
