package com.sip.grosirmobil.base.implement;

import android.annotation.SuppressLint;
import android.content.Context;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.base.presenter.VehicleDetailPresenter;
import com.sip.grosirmobil.base.view.VehicleDetailView;
import com.sip.grosirmobil.cloud.config.request.negonbuynow.NegoAndBuyNowRequest;
import com.sip.grosirmobil.cloud.config.request.vehicledetail.VehicleDetailRequest;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;
import com.sip.grosirmobil.cloud.config.response.vehicledetail.VehicleDetailResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sip.grosirmobil.base.GrosirMobilApp.getApiGrosirMobil;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.BEARER;

public class VehicleDetailImp implements VehicleDetailPresenter {

    private VehicleDetailView vehicleDetailView;
    private Context context;
    private GrosirMobilFunction grosirMobilFunction;
    private GrosirMobilPreference grosirMobilPreference;

    public VehicleDetailImp(VehicleDetailView vehicleDetailView, Context context) {
        this.vehicleDetailView = vehicleDetailView;
        this.context = context;
        grosirMobilFunction = new GrosirMobilFunction(context);
        grosirMobilPreference = new GrosirMobilPreference(context);
    }

    @Override
    public void vehicleDetailApi(String kik, String openHouseId) {
        vehicleDetailView.showDialogLoading();
        VehicleDetailRequest vehicleDetailRequest = new VehicleDetailRequest(openHouseId,kik);
        final Call<VehicleDetailResponse> vehicleDetailApi = getApiGrosirMobil().liveVehicleDetailApi(BEARER+" "+grosirMobilPreference.getToken(),vehicleDetailRequest);
        vehicleDetailApi.enqueue(new Callback<VehicleDetailResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<VehicleDetailResponse> call, Response<VehicleDetailResponse> response) {
                vehicleDetailView.hideDialogLoading();
                if (response.isSuccessful()) {
                    try {
                        if(response.body().getMessage().equals("success")){
                            vehicleDetailView.vehicleDetailSuccess(response.body());
                        }else {
                            grosirMobilFunction.showMessage(context, "GET Vehicle Detail", response.body().getMessage());
                        }
                    }catch (Exception e){
                        GrosirMobilLog.printStackTrace(e);
                    }
                }else {
                    try {
                        grosirMobilFunction.showMessage(context, context.getString(R.string.base_null_error_title), response.errorBody().string());
                    } catch (IOException e) {
                        GrosirMobilLog.printStackTrace(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<VehicleDetailResponse> call, Throwable t) {
                vehicleDetailView.hideDialogLoading();
                grosirMobilFunction.showMessage(context, "GET Vehicle Detail", context.getString(R.string.base_null_server));
                GrosirMobilLog.printStackTrace(t);
            }
        });
    }

    @Override
    public void liveNegoApi(NegoAndBuyNowRequest negoAndBuyNowRequest) {
        vehicleDetailView.showDialogLoading();
        final Call<GeneralResponse> vehicleDetailApi = getApiGrosirMobil().liveNegoApi(BEARER+" "+grosirMobilPreference.getToken(),negoAndBuyNowRequest);
        vehicleDetailApi.enqueue(new Callback<GeneralResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                vehicleDetailView.hideDialogLoading();
                if (response.isSuccessful()) {
                    try {
                        if(response.body().getMessage().equals("success")){
                            vehicleDetailView.vehicleDetailNegoAndBuyNow("Nego",response.body());
                        }else {
                            grosirMobilFunction.showMessage(context, "POST Live Nego", response.body().getMessage());
                        }
                    }catch (Exception e){
                        GrosirMobilLog.printStackTrace(e);
                    }
                }else {
                    try {
                        grosirMobilFunction.showMessage(context, context.getString(R.string.base_null_error_title), response.errorBody().string());
                    } catch (IOException e) {
                        GrosirMobilLog.printStackTrace(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                vehicleDetailView.hideDialogLoading();
                grosirMobilFunction.showMessage(context, "POST Live Nego", context.getString(R.string.base_null_server));
                GrosirMobilLog.printStackTrace(t);
            }
        });
    }

    @Override
    public void liveBuyNowApi(NegoAndBuyNowRequest negoAndBuyNowRequest) {
        vehicleDetailView.showDialogLoading();
        final Call<GeneralResponse> vehicleDetailApi = getApiGrosirMobil().liveBuyNowApi(BEARER+" "+grosirMobilPreference.getToken(),negoAndBuyNowRequest);
        vehicleDetailApi.enqueue(new Callback<GeneralResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                vehicleDetailView.hideDialogLoading();
                if (response.isSuccessful()) {
                    try {
                        if(response.body().getMessage().equals("success")){
                            vehicleDetailView.vehicleDetailNegoAndBuyNow("Buy Now",response.body());
                        }else {
                            grosirMobilFunction.showMessage(context, "POST Live Buy Now", response.body().getMessage());
                        }
                    }catch (Exception e){
                        GrosirMobilLog.printStackTrace(e);
                    }
                }else {
                    try {
                        grosirMobilFunction.showMessage(context, context.getString(R.string.base_null_error_title), response.errorBody().string());
                    } catch (IOException e) {
                        GrosirMobilLog.printStackTrace(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                vehicleDetailView.hideDialogLoading();
                grosirMobilFunction.showMessage(context, "POST Live Buy Now", context.getString(R.string.base_null_server));
                GrosirMobilLog.printStackTrace(t);
            }
        });
    }
}
