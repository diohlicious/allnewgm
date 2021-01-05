package com.sip.grosirmobil.cloud.config.response.homecomingsoon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataHomeComingSoonResponse {

    @SerializedName("ohid")
    @Expose
    private int openHouseId;

    @SerializedName("order_id")
    @Expose
    private String orderId;

    @SerializedName("kik")
    @Expose
    private String kik;

    @SerializedName("kik_number")
    @Expose
    private String kikNumber;

    @SerializedName("warehouse")
    @Expose
    private String wareHouse;

    @SerializedName("event_date")
    @Expose
    private String eventDate;

    @SerializedName("vehicle_name")
    @Expose
    private String vehicleName;

    @SerializedName("sold_price")
    @Expose
    private String soldPrice;

    @SerializedName("open_price")
    @Expose
    private String openPrice;

    @SerializedName("grade")
    @Expose
    private String grade;

    @SerializedName("is_create_open_house")
    @Expose
    private String isCreateOpenHouse;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("is_favorite")
    @Expose
    private String isFavorite;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("start_date")
    @Expose
    private String startDate;

    @SerializedName("end_date")
    @Expose
    private String endDate;



    public int getOpenHouseId() {
        return openHouseId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getKik() {
        return kik;
    }

    public String getKikNumber() {
        return kikNumber;
    }

    public String getWareHouse() {
        return wareHouse;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public String getIsFavorite() {
        return isFavorite;
    }

    public String getSoldPrice() {
        return soldPrice;
    }

    public String getOpenPrice() {
        return openPrice;
    }

    public String getGrade() {
        return grade;
    }

    public String getIsCreateOpenHouse() {
        return isCreateOpenHouse;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getImage() {return image;


    }
}