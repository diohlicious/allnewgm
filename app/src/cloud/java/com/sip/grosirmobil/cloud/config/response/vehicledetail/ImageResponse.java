package com.sip.grosirmobil.cloud.config.response.vehicledetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageResponse {
    @SerializedName("FileName")
    @Expose
    private String fileName;
    
    @SerializedName("MimeType")
    @Expose
    private String mimeType;

    @SerializedName("BlobUri")
    @Expose
    private String blobUri;

    @SerializedName("BlobUrithumb")
    @Expose
    private String blobUriThumb;

    @SerializedName("description")
    @Expose
    private String description;
}
