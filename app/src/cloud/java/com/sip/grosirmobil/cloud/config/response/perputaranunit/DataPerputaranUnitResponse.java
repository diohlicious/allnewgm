package com.sip.grosirmobil.cloud.config.response.perputaranunit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataPerputaranUnitResponse {

    @SerializedName("VALUE")
    @Expose
    private String value;

    @SerializedName("TEXT")
    @Expose
    private String text;

    public String getValue() { return value;
    }

    public String getText() { return text;
    }
}
