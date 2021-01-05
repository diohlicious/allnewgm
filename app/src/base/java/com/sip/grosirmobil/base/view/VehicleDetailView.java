package com.sip.grosirmobil.base.view;

import com.sip.grosirmobil.cloud.config.response.GeneralResponse;
import com.sip.grosirmobil.cloud.config.response.vehicledetail.VehicleDetailResponse;

public interface VehicleDetailView {

    void showDialogLoading();

    void hideDialogLoading();

    void vehicleDetailSuccess(VehicleDetailResponse vehicleDetailResponse);

    void vehicleDetailNegoAndBuyNow(String type, GeneralResponse generalResponse);

}
