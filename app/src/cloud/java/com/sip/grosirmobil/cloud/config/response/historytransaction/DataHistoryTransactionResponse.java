package com.sip.grosirmobil.cloud.config.response.historytransaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataHistoryTransactionResponse {

    @SerializedName("ohid")
    @Expose
    private int ohId;

    @SerializedName("kik")
    @Expose
    private String kik;

    @SerializedName("kik_number")
    @Expose
    private String kikNumber;

    @SerializedName("warehouse")
    @Expose
    private String warehouse;

    @SerializedName("event_date")
    @Expose
    private String eventDate;

    @SerializedName("vehicle_name")
    @Expose
    private String vehicleName;

    @SerializedName("sold_price")
    @Expose
    private String soldPrice;

    @SerializedName("user_price")
    @Expose
    private String userPrice;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("va_number")
    @Expose
    private String vaNumber;

    @SerializedName("foto")
    @Expose
    private String foto;

    @SerializedName("mywinner")
    @Expose
    private String myWinner;

    @SerializedName("hargapembukaan")
    @Expose
    private String hargaPembukaan;

    @SerializedName("ordernumber")
    @Expose
    private String orderNumber;

    @SerializedName("grade")
    @Expose
    private String grade;

    public int getOhId() {
        return ohId;
    }

    public String getKik() {
        return kik;
    }

    public String getKikNumber() {
        return kikNumber;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public String getSoldPrice() {
        return soldPrice;
    }

    public String getUserPrice() {
        return userPrice;
    }

    public String getStatus() {
        return status;
    }

    public String getVaNumber() {
        return vaNumber;
    }

    public String getFoto() {
        return foto;
    }

    public String getMyWinner() {
        return myWinner;
    }

    public String getHargaPembukaan() {
        return hargaPembukaan;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getGrade() {
        return grade;
    }
}
