package com.sip.grosirmobil.cloud.config.response.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfilResponse {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("userid")
    @Expose
    private String userId;

    @SerializedName("TipeUsaha")
    @Expose
    private String tipeUsaha;

    @SerializedName("NamaDealer")
    @Expose
    private String namaDealer;

    @SerializedName("NmTelpDealer")
    @Expose
    private String noTelephoneDealer;

    @SerializedName("MinatJenisMobil")
    @Expose
    private String minatJenisMobil;

    @SerializedName("MengetahuiMedia")
    @Expose
    private String mengetahuiMedia;

    @SerializedName("RataPenjualan")
    @Expose
    private String rataPenjualan;

    @SerializedName("KbthnKendaraan")
    @Expose
    private String kbThnKendaraan;

    @SerializedName("PembKbthnKendaraan")
    @Expose
    private String pembKbThnKendaraan;

    @SerializedName("PhotoKTP")
    @Expose
    private String photoKTP;

    @SerializedName("PhotoSKTP")
    @Expose
    private String photoSKTP;

    @SerializedName("Propinsi")
    @Expose
    private String propinsi;

    @SerializedName("Kabupaten")
    @Expose
    private String kabupaten;

    @SerializedName("Kecamatan")
    @Expose
    private String kecamatan;

    @SerializedName("Kelurahan")
    @Expose
    private String kelurahan;

    @SerializedName("AlamatDealer")
    @Expose
    private String alamatDealer;

    @SerializedName("postal_code")
    @Expose
    private String postalCode;

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getTipeUsaha() {
        return tipeUsaha;
    }

    public String getNamaDealer() {
        return namaDealer;
    }

    public String getNoTelephoneDealer() {
        return noTelephoneDealer;
    }

    public String getMinatJenisMobil() {
        return minatJenisMobil;
    }

    public String getMengetahuiMedia() {
        return mengetahuiMedia;
    }

    public String getRataPenjualan() {
        return rataPenjualan;
    }

    public String getKbThnKendaraan() {
        return kbThnKendaraan;
    }

    public String getPembKbThnKendaraan() {
        return pembKbThnKendaraan;
    }

    public String getPhotoKTP() {
        return photoKTP;
    }

    public String getPhotoSKTP() {
        return photoSKTP;
    }

    public String getPropinsi() {
        return propinsi;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public String getAlamatDealer() {
        return alamatDealer;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
