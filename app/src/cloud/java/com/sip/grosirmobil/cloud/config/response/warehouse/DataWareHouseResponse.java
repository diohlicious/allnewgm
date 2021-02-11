package com.sip.grosirmobil.cloud.config.response.warehouse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataWareHouseResponse {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("warehouse_code")
    @Expose
    private String warehouseCode;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("seq_no")
    @Expose
    private String seqNo;

    public String getId() {
        return id;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public String getName() {
        return name;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public DataWareHouseResponse(String id, String warehouseCode, String name, String seqNo) {
        this.id = id;
        this.warehouseCode = warehouseCode;
        this.name = name;
        this.seqNo = seqNo;
    }
}
