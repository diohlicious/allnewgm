package com.sip.grosirmobil.cloud.config.response.homehistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataHomeHistoryResponse {

    @SerializedName("ohid")
    @Expose
    private int openHouseId;

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

    @SerializedName("license_plate")
    @Expose
    private String licensePlate;

    @SerializedName("sold_price")
    @Expose
    private String soldPrice;

//    @SerializedName("open_price")
//    @Expose
//    private String openPrice;

//    @SerializedName("user_price")
//    @Expose
//    private String userPrice;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("foto")
    @Expose
    private String foto;

//    @SerializedName("mywinner")
//    @Expose
//    private String myWinner;

    @SerializedName("hargapembukaan")
    @Expose
    private String hargaPembukaan;

    public int getOpenHouseId() { return openHouseId; }

    public String getKik() { return kik; }

    public String getKikNumber() { return kikNumber; }

    public String getWareHouse() { return wareHouse; }

    public String getEventDate() { return eventDate; }

    public String getVehicleName() { return vehicleName; }

    public String getLicensePlate() { return licensePlate; }

    public String getSoldPrice() { return soldPrice; }


    public String getStatus() { return status; }

    public String getFoto() { return foto; }

    public String getHargaPembukaan() { return hargaPembukaan; }
}
