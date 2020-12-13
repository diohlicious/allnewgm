package com.sip.grosirmobil.cloud.config.response.vehicledetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageBrokenResponse {

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

    public String getFileName() {
        return fileName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getBlobUri() {
        return blobUri;
    }

    public String getBlobUriThumb() {
        return blobUriThumb;
    }

    public String getDescription() {
        return description;
    }
}
