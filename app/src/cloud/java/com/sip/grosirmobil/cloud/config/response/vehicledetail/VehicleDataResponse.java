package com.sip.grosirmobil.cloud.config.response.vehicledetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleDataResponse {

    @SerializedName("id_nomor")
    @Expose
    private String idNomor;

    @SerializedName("grade")
    @Expose
    private String grade;

    @SerializedName("nomor_polisi")
    @Expose
    private String nomorPolisi;

    @SerializedName("tahun")
    @Expose
    private int tahun;

    @SerializedName("transmisi")
    @Expose
    private String transmisi;

    @SerializedName("color")
    @Expose
    private String color;

    @SerializedName("km")
    @Expose
    private int km;

    @SerializedName("kepemilikan")
    @Expose
    private String kepemilikan;

    @SerializedName("lokasi")
    @Expose
    private String lokasi;

    @SerializedName("stnk")
    @Expose
    private String stnk;

    @SerializedName("masa_berlaku_pajak")
    @Expose
    private String masaBerlakuPajak;

    @SerializedName("masa_berlaku_stnk")
    @Expose
    private String masaBerlakuSTNK;

    public String getIdNomor() {
        return idNomor;
    }

    public String getGrade() {
        return grade;
    }

    public String getNomorPolisi() {
        return nomorPolisi;
    }

    public int getTahun() {
        return tahun;
    }

    public String getTransmisi() {
        return transmisi;
    }

    public String getColor() {
        return color;
    }

    public int getKm() {
        return km;
    }

    public String getKepemilikan() {
        return kepemilikan;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getStnk() {
        return stnk;
    }

    public String getMasaBerlakuPajak() {
        return masaBerlakuPajak;
    }

    public String getMasaBerlakuSTNK() {
        return masaBerlakuSTNK;
    }
}
