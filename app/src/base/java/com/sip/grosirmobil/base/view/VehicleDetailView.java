package com.sip.grosirmobil.base.view;

import com.sip.grosirmobil.cloud.config.response.nego.GeneralNegoAndBuyNowResponse;
import com.sip.grosirmobil.cloud.config.response.vehicledetail.DataVehicleDetailResponse;

public interface VehicleDetailView {

    void showDialogLoading();

    void hideDialogLoading();

    void vehicleDetailSuccess(boolean flag, DataVehicleDetailResponse vehicleDetailResponse, String timeServer);

    void vehicleDetailNegoAndBuyNow(String type, GeneralNegoAndBuyNowResponse generalResponse);

}
