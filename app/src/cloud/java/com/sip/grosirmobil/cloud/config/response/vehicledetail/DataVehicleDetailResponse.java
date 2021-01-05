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

    @SerializedName("grade")
    @Expose
    private String grade;

    @SerializedName("is_favorite")
    @Expose
    private String isFavorite;

     @SerializedName("warehouse")
    @Expose
    private String warehouse;

    @SerializedName("vehicle_name")
    @Expose
    private String vehicleName;

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
    private List<VehicleDetailDataResponse> vehicleDataResponseList;
    
    @SerializedName("vehicle_body")
    @Expose
    private List<VehicleDetailDataResponse> vehicleBodyResponseList;

    @SerializedName("vehicle_interior")
    @Expose
    private List<VehicleDetailDataResponse> vehicleInteriorResponseList;

    @SerializedName("vehicle_mesin")
    @Expose
    private List<VehicleDetailDataResponse> vehicleMesinResponseList;

    @SerializedName("vehicle_other")
    @Expose
    private List<VehicleDetailDataResponse> vehicleOtherResponseList;

    @SerializedName("image_broken")
    @Expose
    private List<ImageBrokenResponse> imageBrokenResponseList;

    @SerializedName("is_winner")
    @Expose
    private String isWinner;

    @SerializedName("stts")
    @Expose
    private String status;

    public String getKik() {
        return kik;
    }

    public String getOpenHouseId() {
        return openHouseId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getIsLive() {
        return isLive;
    }

    public String getOpenPrice() {
        return openPrice;
    }

    public String getHargaAwal() {
        return hargaAwal;
    }

    public String getBottomPrice() {
        return bottomPrice;
    }

    public String getAgreementNo() {
        return agreementNo;
    }

    public List<ImageResponse> getImageResponseList() {
        return imageResponseList;
    }

    public String getVehicleSummary() {
        return vehicleSummary;
    }

    public List<VehicleDetailDataResponse> getVehicleDataResponseList() {
        return vehicleDataResponseList;
    }

    public List<VehicleDetailDataResponse> getVehicleBodyResponseList() {
        return vehicleBodyResponseList;
    }

    public List<VehicleDetailDataResponse> getVehicleInteriorResponseList() {
        return vehicleInteriorResponseList;
    }

    public List<VehicleDetailDataResponse> getVehicleMesinResponseList() {
        return vehicleMesinResponseList;
    }

    public List<VehicleDetailDataResponse> getVehicleOtherResponseList() {
        return vehicleOtherResponseList;
    }

    public List<ImageBrokenResponse> getImageBrokenResponseList() {
        return imageBrokenResponseList;
    }

    public String getIsWinner() {
        return isWinner;
    }

    public String getStatus() {
        return status;
    }

    public String getGrade() {
        return grade;
    }

    public String getIsFavorite() {
        return isFavorite;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public String getWarehouse() {
        return warehouse;
    }
}
