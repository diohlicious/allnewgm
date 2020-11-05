package com.sip.grosirmobil.cloud.config.response.history;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RowsResponse {

    @SerializedName("order_id")
    @Expose
    private String orderId;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("transaction_amount")
    @Expose
    private String transactionAmount;

    @SerializedName("product_parent")
    @Expose
    private String productParent;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("unique_number")
    @Expose
    private String uniqueNumber;

    @SerializedName("imageSrc")
    @Expose
    private String imageSrc;

    public String getOrderId() {
        return orderId;
    }

    public String getDate() {
        return date;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public String getProductParent() {
        return productParent;
    }

    public String getStatus() {
        return status;
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public String getImageSrc() {
        return imageSrc;
    }
}
