package com.sip.grosirmobil.cloud.config.response.province;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataProvinceResponse {

    @SerializedName("province_name")
    @Expose
    private String provinceName;

    @SerializedName("province_name_en")
    @Expose
    private String provinceNameEn;

    @SerializedName("province_code")
    @Expose
    private String provinceCode;

    public String getProvinceName() {
        return provinceName;
    }

    public String getProvinceNameEn() {
        return provinceNameEn;
    }

    public String getProvinceCode() {
        return provinceCode;
    }
}
