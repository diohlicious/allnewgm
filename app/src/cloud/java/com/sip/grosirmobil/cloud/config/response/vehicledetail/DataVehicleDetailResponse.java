package com.sip.grosirmobil.cloud.config.response.vehicledetail;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class DataVehicleDetailResponse {
    @SerializedName("kik")
    @Expose
    private String kik;

    @SerializedName("openhouseid")
    @Expose
    private String openHouseId;

    @SerializedName("start_date")
    @Expose
    private String startDate;

    @SerializedName("end_date")
    @Expose
    private String endDate;

    @SerializedName("islive")
    @Expose
    private String isLive;

    @SerializedName("open_price")
    @Expose
    private String openPrice;

    @SerializedName("harga_awal")
    @Expose
    private String hargaAwal;

    @SerializedName("bottom_price")
    @Expose
    private String bottomPrice;

    @SerializedName("agreementno")
    @Expose
    private String agreementNo;
    
    @SerializedName("image")
    @Expose
    private List<ImageResponse> imageResponseList;

    @SerializedName("vehicle_summary")
    @Expose
    private String vehicleSummary;

    @SerializedName("vehicle_data")
    @Expose
    private String vehicleData;
    
    @SerializedName("vehicle_body")
    @Expose
    private List<VehicleBodyResponse> vehicleBodyResponseList;

    @SerializedName("vehicle_interior")
    @Expose
    private List<VehicleInteriorResponse> vehicleInteriorResponseList;

    @SerializedName("vehicle_mesin")
    @Expose
    private List<VehicleMesinResponse> vehicleMesinResponseList;

    @SerializedName("vehicle_other")
    @Expose
    private List<VehicleOtherResponse> vehicleOtherResponseList;

    @SerializedName("image_broken")
    @Expose
    private List<ImageBrokenResponse> imageBrokenResponseList;

    @SerializedName("is_winner")
    @Expose
    private String isWinner;

    @SerializedName("stts")
    @Expose
    private String stts;
    
}
