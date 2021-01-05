package com.sip.grosirmobil.cloud.config.response.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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
    private String stts;

    @SerializedName("Oto_json")
    @Expose
    private List<DataOtoJsonResponse> dataOtoJsonResponseList;



}
