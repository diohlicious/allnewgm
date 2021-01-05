package com.sip.grosirmobil.cloud.config.response.vehicledetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageResponse {

    @SerializedName("file_name")
    @Expose
    private String fileName;
    
    @SerializedName("url_image")
    @Expose
    private String urlImage;

    @SerializedName("description")
    @Expose
    private String description;

    public String getFileName() {
        return fileName;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getDescription() {
        return description;
    }
}
