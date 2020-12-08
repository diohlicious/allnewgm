package com.sip.grosirmobil.cloud.config.response.tipeusaha;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataTipeUsahaResponse {

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("name")
    @Expose
    private String name;

    public String getCode() { return code;
    }

    public String getName() { return name;
    }
}
