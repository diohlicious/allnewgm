package com.sip.grosirmobil.cloud.config.response.invoiceva;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataVaResponse {

    @SerializedName("idgm_Invoice")
    @Expose
    private String idGmInvoice;

    @SerializedName("ordernumber")
    @Expose
    private String orderNumber;

    @SerializedName("VA_number")
    @Expose
    private String VaNumber;

    @SerializedName("StartdateVA")
    @Expose
    private String startDateVa;

    @SerializedName("EndDateVA")
    @Expose
    private String endDateVa;

    @SerializedName("BANK")
    @Expose
    private String bank;

    @SerializedName("totamount")
    @Expose
    private String totalAmount;

    @SerializedName("CreatebyIDUser")
    @Expose
    private String createByIdUser;

    @SerializedName("invnum")
    @Expose
    private String invoiceNumber;

    @SerializedName("Nama_Lengkap")
    @Expose
    private String namaLengkap;

    public String getIdGmInvoice() {
        return idGmInvoice;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getVaNumber() {
        return VaNumber;
    }

    public String getStartDateVa() {
        return startDateVa;
    }

    public String getEndDateVa() {
        return endDateVa;
    }

    public String getBank() {
        return bank;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public String getCreateByIdUser() {
        return createByIdUser;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }
}
