package com.sip.grosirmobil.cloud.config.response.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataOtoJsonResponse {
    
    @SerializedName("asset_description")
    @Expose
    private String assetDescription;
    
    @SerializedName("KM")
    @Expose
    private String KM;
    
    @SerializedName("Lokasi")
    @Expose
    private String Lokasi;
    
    @SerializedName("gradeoto")
    @Expose
    private String gradeOto;
    
    
    
  

}
