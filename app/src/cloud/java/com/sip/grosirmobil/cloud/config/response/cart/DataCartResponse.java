package com.sip.grosirmobil.cloud.config.response.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataCartResponse {

    @SerializedName("user_id_grosir")
    @Expose
    private int userIdGrosir; 
    
    @SerializedName("user_id_win")
    @Expose
    private int userIdWin;
    
    @SerializedName("ohid")
    @Expose
    private int ohid;
    
    @SerializedName("agreement_no")
    @Expose
    private String agreementNo;
    
    @SerializedName("start_date")
    @Expose
    private String start_Date;
    
    @SerializedName("end_date")
    @Expose
    private String endDate;
    
    @SerializedName("kik")
    @Expose
    private String kik;
    
    @SerializedName("vehicle_name")
    @Expose
    private String vehicleName;
    
    @SerializedName("tertinggi")
    @Expose
    private String tertinggi;
    
    @SerializedName("user_tertinggi")
    @Expose
    private String userTertinggi;
    
    @SerializedName("is_keranjang")
    @Expose
    private String isKeranjang;

    @SerializedName("is_winner")
    @Expose
    private int isWinner;
    
    @SerializedName("user_win")
    @Expose
    private int userWin;
    
    @SerializedName("bottom_price")
    @Expose
    private String bottomPrice;
    
    @SerializedName("open_price")
    @Expose
    private int openPrice;
    
    @SerializedName("grade")
    @Expose
    private String grade;
    
    @SerializedName("is_live")
    @Expose
    private int isLive;
    
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    
    @SerializedName("is_block")
    @Expose
    private int isBlock;
    
    @SerializedName("foto")
    @Expose
    private String foto;
    
    @SerializedName("stts")
    @Expose
    private String status;

    @SerializedName("Oto_json")
    @Expose
    private DataOtoJsonResponse dataOtoJsonResponse;

    public int getUserIdGrosir() {
        return userIdGrosir;
    }

    public int getUserIdWin() {
        return userIdWin;
    }

    public int getOhid() {
        return ohid;
    }

    public String getAgreementNo() {
        return agreementNo;
    }

    public String getStart_Date() {
        return start_Date;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getKik() {
        return kik;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public String getTertinggi() {
        return tertinggi;
    }

    public String getUserTertinggi() {
        return userTertinggi;
    }

    public String getIsKeranjang() {
        return isKeranjang;
    }

    public int getIsWinner() {
        return isWinner;
    }

    public int getUserWin() {
        return userWin;
    }

    public String getBottomPrice() {
        return bottomPrice;
    }

    public int getOpenPrice() {
        return openPrice;
    }

    public String getGrade() {
        return grade;
    }

    public int getIsLive() {
        return isLive;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getIsBlock() {
        return isBlock;
    }

    public String getFoto() {
        return foto;
    }

    public String getStatus() {
        return status;
    }

    public DataOtoJsonResponse getDataOtoJsonResponse() {
        return dataOtoJsonResponse;
    }

    public DataCartResponse(int userIdGrosir, int userIdWin, int ohid, String agreementNo, String start_Date, String endDate, String kik, String vehicleName, String tertinggi, String userTertinggi, String isKeranjang, int isWinner, int userWin, String bottomPrice, int openPrice, String grade, int isLive, String categoryName, int isBlock, String foto, String status) {
        this.userIdGrosir = userIdGrosir;
        this.userIdWin = userIdWin;
        this.ohid = ohid;
        this.agreementNo = agreementNo;
        this.start_Date = start_Date;
        this.endDate = endDate;
        this.kik = kik;
        this.vehicleName = vehicleName;
        this.tertinggi = tertinggi;
        this.userTertinggi = userTertinggi;
        this.isKeranjang = isKeranjang;
        this.isWinner = isWinner;
        this.userWin = userWin;
        this.bottomPrice = bottomPrice;
        this.openPrice = openPrice;
        this.grade = grade;
        this.isLive = isLive;
        this.categoryName = categoryName;
        this.isBlock = isBlock;
        this.foto = foto;
        this.status = status;
    }
}
