package com.sip.grosirmobil.base.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataWinNotPaymentModel implements Parcelable {


    private int userIdGrosir;
    private int userIdWin;
    private int ohid;
    private String agreementNo;
    private String start_Date;
    private String endDate;
    private String kik;
    private String vehicleName;
    private String tertinggi;
    private String userTertinggi;
    private String isKeranjang;
    private int isWinner;
    private int userWin;
    private String bottomPrice;
    private int openPrice;
    private String grade;
    private int isLive;
    private String categoryName;
    private int isBlock;
    private String foto;
    private String status;

    public DataWinNotPaymentModel(int userIdGrosir, int userIdWin, int ohid, String agreementNo, String start_Date, String endDate, String kik, String vehicleName, String tertinggi, String userTertinggi, String isKeranjang, int isWinner, int userWin, String bottomPrice, int openPrice, String grade, int isLive, String categoryName, int isBlock, String foto, String status) {
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

    protected DataWinNotPaymentModel(Parcel in) {
        userIdGrosir = in.readInt();
        userIdWin = in.readInt();
        ohid = in.readInt();
        agreementNo = in.readString();
        start_Date = in.readString();
        endDate = in.readString();
        kik = in.readString();
        vehicleName = in.readString();
        tertinggi = in.readString();
        userTertinggi = in.readString();
        isKeranjang = in.readString();
        isWinner = in.readInt();
        userWin = in.readInt();
        bottomPrice = in.readString();
        openPrice = in.readInt();
        grade = in.readString();
        isLive = in.readInt();
        categoryName = in.readString();
        isBlock = in.readInt();
        foto = in.readString();
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userIdGrosir);
        dest.writeInt(userIdWin);
        dest.writeInt(ohid);
        dest.writeString(agreementNo);
        dest.writeString(start_Date);
        dest.writeString(endDate);
        dest.writeString(kik);
        dest.writeString(vehicleName);
        dest.writeString(tertinggi);
        dest.writeString(userTertinggi);
        dest.writeString(isKeranjang);
        dest.writeInt(isWinner);
        dest.writeInt(userWin);
        dest.writeString(bottomPrice);
        dest.writeInt(openPrice);
        dest.writeString(grade);
        dest.writeInt(isLive);
        dest.writeString(categoryName);
        dest.writeInt(isBlock);
        dest.writeString(foto);
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DataWinNotPaymentModel> CREATOR = new Creator<DataWinNotPaymentModel>() {
        @Override
        public DataWinNotPaymentModel createFromParcel(Parcel in) {
            return new DataWinNotPaymentModel(in);
        }

        @Override
        public DataWinNotPaymentModel[] newArray(int size) {
            return new DataWinNotPaymentModel[size];
        }
    };

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
}
