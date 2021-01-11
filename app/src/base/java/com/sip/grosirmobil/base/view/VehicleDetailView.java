package com.sip.grosirmobil.base.view;

import com.sip.grosirmobil.cloud.config.response.GeneralResponse;
import com.sip.grosirmobil.cloud.config.response.vehicledetail.DataVehicleDetailResponse;

public interface VehicleDetailView {

    void showDialogLoading();

    void hideDialogLoading();

    void vehicleDetailSuccess(DataVehicleDetailResponse vehicleDetailResponse, String timeServer);

    void vehicleDetailNegoAndBuyNow(String type, GeneralResponse generalResponse);

}
