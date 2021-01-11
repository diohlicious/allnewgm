package com.sip.grosirmobil.base.presenter;

import com.sip.grosirmobil.cloud.config.request.negonbuynow.NegoAndBuyNowRequest;
import com.sip.grosirmobil.cloud.config.response.vehicledetail.DataVehicleDetailResponse;

public interface VehicleDetailPresenter {

    void vehicleDetailApi(String kik, String openHouseId);

    void liveNegoApi(NegoAndBuyNowRequest negoAndBuyNowRequest);

    void liveBuyNowApi(NegoAndBuyNowRequest negoAndBuyNowRequest);

    void getTimeServerApi(DataVehicleDetailResponse dataVehicleDetailResponse);
}
