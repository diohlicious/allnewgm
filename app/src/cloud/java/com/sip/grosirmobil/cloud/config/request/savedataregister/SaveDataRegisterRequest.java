package com.sip.grosirmobil.cloud.config.request.savedataregister;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveDataRegisterRequest {

    @SerializedName("noktp")
    @Expose
    private String noKtp;

    @SerializedName("namalengkap")
    @Expose
    private String namaLengkap;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("verif")
    @Expose
    private String verif;

    @SerializedName("tipeusaha")
    @Expose
    private String codeTypeUsaha;

    @SerializedName("namadealer")
    @Expose
    private String namaDealer;

    @SerializedName("notelpondealer")
    @Expose
    private String noTelponDealer;

    @SerializedName("usiabisnis")
    @Expose
    private String usiaBisnis;

    @SerializedName("perputaranunit")
    @Expose
    private String perputaranUnit;

    @SerializedName("kebutuhankendaraan")
    @Expose
    private String kebutuhanKendaraan;

    @SerializedName("ratapenjualan")
    @Expose
    private String rataPenjualan;

    @SerializedName("pembeliankebutuhankendaraan")
    @Expose
    private String pembelianKebutuhanKendaraan;

    @SerializedName("photoktp")
    @Expose
    private String photoKtp;

    @SerializedName("photoselfiektp")
    @Expose
    private String photoSelfieKtp;

    @SerializedName("alamat")
    @Expose
    private String alamat;

    @SerializedName("propinsi")
    @Expose
    private String propinsi;

    @SerializedName("kabupaten")
    @Expose
    private String kabupaten;

    @SerializedName("kecamatan")
    @Expose
    private String kecamatan;

    @SerializedName("kelurahan")
    @Expose
    private String kelurahan;

    @SerializedName("kodepos")
    @Expose
    private String kodePos;

    @SerializedName("konfirmasi_by")
    @Expose
    private String konfirmasiBy;

    public SaveDataRegisterRequest(String noKtp, String namaLengkap, String phone, String email, String password, String verif, String codeTypeUsaha, String namaDealer, String noTelponDealer, String usiaBisnis, String perputaranUnit, String kebutuhanKendaraan, String rataPenjualan, String pembelianKebutuhanKendaraan, String photoKtp, String photoSelfieKtp, String alamat, String propinsi, String kabupaten, String kecamatan, String kelurahan, String kodePos, String konfirmasiBy) {
        this.noKtp = noKtp;
        this.namaLengkap = namaLengkap;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.verif = verif;
        this.codeTypeUsaha = codeTypeUsaha;
        this.namaDealer = namaDealer;
        this.noTelponDealer = noTelponDealer;
        this.usiaBisnis = usiaBisnis;
        this.perputaranUnit = perputaranUnit;
        this.kebutuhanKendaraan = kebutuhanKendaraan;
        this.rataPenjualan = rataPenjualan;
        this.pembelianKebutuhanKendaraan = pembelianKebutuhanKendaraan;
        this.photoKtp = photoKtp;
        this.photoSelfieKtp = photoSelfieKtp;
        this.alamat = alamat;
        this.propinsi = propinsi;
        this.kabupaten = kabupaten;
        this.kecamatan = kecamatan;
        this.kelurahan = kelurahan;
        this.kodePos = kodePos;
        this.konfirmasiBy = konfirmasiBy;
    }
}
