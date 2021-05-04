package com.sip.grosirmobil.fragment.navigationmenu;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.naa.data.Utility;
import com.naa.data.UtilityAndroid;
import com.sip.grosirmobil.R;
import com.sip.grosirmobil.activity.MainActivity;
import com.sip.grosirmobil.activity.VehicleDetailActivity;
import com.sip.grosirmobil.adapter.LiveGarageAdapter;
import com.sip.grosirmobil.adapter.LostGarageAdapter;
import com.sip.grosirmobil.adapter.SuccessGarageAdapter;
import com.sip.grosirmobil.base.contract.GrosirMobilContract;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.base.util.GrosirMobilFragment;
import com.sip.grosirmobil.cloud.config.request.negonbuynow.NegoAndBuyNowRequest;
import com.sip.grosirmobil.cloud.config.response.cart.CartResponse;
import com.sip.grosirmobil.cloud.config.response.cart.DataCartResponse;
import com.sip.grosirmobil.cloud.config.response.nego.GeneralNegoAndBuyNowResponse;
import com.sip.grosirmobil.cloud.config.response.timeserver.TimeServerResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sip.grosirmobil.base.GrosirMobilApp.getApiGrosirMobil;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.BEARER;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.REQUEST_MAIN;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.convertDateServer;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setCurrencyFormat;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setStatusBarFragment;

/**
 * A simple {@link androidx.fragment.app.Fragment} subclass.
 */

public class CartFragment extends GrosirMobilFragment {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.swipe_refresh_garage) SwipeRefreshLayout swipeRefreshGarage;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.nested_view) NestedScrollView nestedView;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_filter) TextView tvFilter;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_live_garage) RecyclerView rvLiveGarage;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_success_garage) RecyclerView rvSuccessGarage;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_lost_garage) RecyclerView rvLostGarage;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.relative_background_dialog_filter) RelativeLayout relativeBackgroundDialogFilter;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_dialog_filter) LinearLayout linearDialogFilter;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_cart_empty) LinearLayout linearCartEmpty;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_cart_not_empty) LinearLayout linearCartNotEmpty;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_all) TextView tvAll;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_penawaran_sedang_berlangsung) TextView tvPenawaranSedangBerlangsung;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_penawaran_diterima) TextView tvPenawaranDiterima;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_penawaran_ditolak) TextView tvPenawaranDitolak;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_live_garage) LinearLayout linearLiveGarage;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_success_garage) LinearLayout linearSuccessGarage;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_lost_garage) LinearLayout linearLostGarage;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_telusuri_kenderaan) Button btnFindVehicle;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.progress_bar) ProgressBar progressBar;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.relative_background_dialog_confirm_buy_now) RelativeLayout relativeBackgroundDialogConfirmBuyNow;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.iv_close_dialog_confirm_buy_now) ImageView ivCloseDialogConfirmBuyNow;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_message_buy_now) TextView tvMessageBuyNow;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_confirm_buy_now_dialog) Button btnConfirmBuyNowDialog;

    Handler handler = new Handler();
    Runnable runnable;
    int delay = 1000;
    private boolean checkAuto = false;
    private boolean first = true;
    private LiveGarageAdapter liveGarageAdapter;
    private GrosirMobilFunction grosirMobilFunction;
    private GrosirMobilPreference grosirMobilPreference;
    private NegoAndBuyNowRequest negoAndBuyNowRequest;

    private final List<DataCartResponse> dataCartLiveResponseListTemp = new ArrayList<>();
    private final List<DataCartResponse> dataCartLiveResponseList = new ArrayList<>();
    private final List<DataCartResponse> dataCartSuccessResponseList = new ArrayList<>();
    private final List<DataCartResponse> dataCartLostResponseList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setStatusBarFragment(getActivity());
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        ButterKnife.bind(this, view);

        grosirMobilFunction = new GrosirMobilFunction(getActivity());
        grosirMobilPreference = new GrosirMobilPreference(getActivity());

        UtilityAndroid.setSetting("keranjangfilter", "");
        getTimeServerApi("1");

        LinearLayoutManager layoutManagerLive = new LinearLayoutManager(getActivity());
        rvLiveGarage.setLayoutManager(layoutManagerLive);
        rvLiveGarage.setNestedScrollingEnabled(false);

        swipeRefreshGarage.setOnRefreshListener(() -> {
            getTimeServerApi("1");
            swipeRefreshGarage.setRefreshing(false);
            linearLiveGarage.setVisibility(View.VISIBLE);
            linearSuccessGarage.setVisibility(View.VISIBLE);
            linearLostGarage.setVisibility(View.VISIBLE);
        });
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        handler.postDelayed(runnable = () -> {
            handler.postDelayed(runnable, delay);
            if(checkAuto){
                getTimeServerApi("0");
            }
        }, delay);
        super.onResume();
    }

    private void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
    }

    public void getTimeServerApi(String loadingShow) {
        //Log.i("HIT", Utility.Now());
        final Call<TimeServerResponse> timeServerApi = getApiGrosirMobil().timeServerApi();
        timeServerApi.enqueue(new Callback<TimeServerResponse>() {
            @Override
            public void onResponse(Call<TimeServerResponse> call, Response<TimeServerResponse> response) {
                if (response.isSuccessful()) {
                    try {
                        if(response.body().getMessage().equals("success")){
                            grosirMobilPreference.saveTimeServer(response.body().getData().getTimeServer());
                            getDataCartApi(response.body().getData().getTimeServer(), loadingShow);
                        }else {
                            //grosirMobilFunction.showMessage(getActivity(), "GET Time Server", response.body().getMessage());
                        }
                    }catch (Exception e){
                        GrosirMobilLog.printStackTrace(e);
                    }
                }else {
                    try {
                        grosirMobilFunction.showMessage(getContext(), getString(R.string.base_null_error_title), response.errorBody().string());
                    } catch (IOException e) {
                        GrosirMobilLog.printStackTrace(e);
                    }
                }
            }
            @Override
            public void onFailure(Call<TimeServerResponse> call, Throwable t) {
                //grosirMobilFunction.showMessage(getActivity(), "GET Time Server", getString(R.string.base_null_server));
                GrosirMobilLog.printStackTrace(t);
            }
        });
    }

    private void getDataCartApi(String timeServer, String loadingShow){
        if(loadingShow.equals("1")){
            showProgressBar();
        }
        final Call<CartResponse> questionOneApi = getApiGrosirMobil().lisCartApi(BEARER+" "+grosirMobilPreference.getToken());
        questionOneApi.enqueue(new Callback<CartResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                hideProgressBar();
                if (response.isSuccessful()) {
                    try {
                        if(response.body().getMessage().equals("success")){
                            if(response.body().getDataCartResponseList()==null||response.body().getDataCartResponseList().isEmpty()){
                                linearCartNotEmpty.setVisibility(View.GONE);
                                linearCartEmpty.setVisibility(View.VISIBLE);
                            }else {
                                linearCartNotEmpty.setVisibility(View.VISIBLE);
                                linearCartEmpty.setVisibility(View.GONE);
                                dataCartLiveResponseList.clear();
                                dataCartLiveResponseListTemp.clear();
                                dataCartSuccessResponseList.clear();
                                dataCartLostResponseList.clear();

                                tvPenawaranSedangBerlangsung.setBackgroundResource(R.color.colorPrimaryWhite);
                                tvPenawaranSedangBerlangsung.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
                                tvPenawaranDiterima.setBackgroundResource(R.color.colorPrimaryWhite);
                                tvPenawaranDiterima.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
                                tvPenawaranDitolak.setBackgroundResource(R.color.colorPrimaryWhite);
                                tvPenawaranDitolak.setTextColor(getResources().getColor(R.color.colorPrimaryFont));

                                for(int i=0;i<response.body().getDataCartResponseList().size();i++){
                                    if(response.body().getDataCartResponseList().get(i).getIsLive()==1){
                                        DataCartResponse dataCartResponse;
                                        String negoBid = null;
//                                        dataCartLiveResponseListTemp.add(response.body().getDataCartResponseList().get(i));
                                        if(first){
                                            dataCartLiveResponseListTemp.add(response.body().getDataCartResponseList().get(i));
                                            grosirMobilPreference.saveDataCartLive(dataCartLiveResponseListTemp);
                                        }
//                                        else {
//                                            //                                            if(dataCartLiveResponseListTemp.get(i).getNego()==null||dataCartLiveResponseListTemp.get(i).getNego().equals("")){
////                                                dataCartLiveResponseListTemp.get(i).setNego(response.body().getDataCartResponseList().get(i).getTertinggi());
////                                            }
//                                        }
                                        negoBid = response.body().getDataCartResponseList().get(i).getTertinggi();
//                                        System.out.println("DATA : "+ dataCartLiveResponseListTemp.get(i).getNego());
                                        dataCartResponse = new DataCartResponse(response.body().getDataCartResponseList().get(i).getUserIdGrosir(),
                                                response.body().getDataCartResponseList().get(i).getUserIdWin(), response.body().getDataCartResponseList().get(i).getOhid(),
                                                response.body().getDataCartResponseList().get(i).getAgreementNo(), response.body().getDataCartResponseList().get(i).getStart_Date(),
                                                response.body().getDataCartResponseList().get(i).getEndDate(), response.body().getDataCartResponseList().get(i).getKik(),
                                                response.body().getDataCartResponseList().get(i).getVehicleName(), negoBid,
                                                response.body().getDataCartResponseList().get(i).getTertinggi(), response.body().getDataCartResponseList().get(i).getUserTertinggi(),
                                                response.body().getDataCartResponseList().get(i).getIsKeranjang(), response.body().getDataCartResponseList().get(i).getIsWinner(),
                                                response.body().getDataCartResponseList().get(i).getUserWin(), response.body().getDataCartResponseList().get(i).getBottomPrice(),
                                                response.body().getDataCartResponseList().get(i).getAdminfee(),response.body().getDataCartResponseList().get(i).getTotalbayar(),
                                                response.body().getDataCartResponseList().get(i).getOpenPrice(), response.body().getDataCartResponseList().get(i).getPriceNow(),
                                                response.body().getDataCartResponseList().get(i).getGrade(),
                                                response.body().getDataCartResponseList().get(i).getIsLive(), response.body().getDataCartResponseList().get(i).getCategoryName(),
                                                response.body().getDataCartResponseList().get(i).getIsBlock(), response.body().getDataCartResponseList().get(i).getFoto(),
                                                response.body().getDataCartResponseList().get(i).getStatus(),
                                                response.body().getDataCartResponseList().get(i).getDataOtoJsonResponse());
                                        dataCartLiveResponseList.add(dataCartResponse);
                                    }
                                    if(response.body().getDataCartResponseList().get(i).getIsWinner()==1 &&
                                       response.body().getDataCartResponseList().get(i).getUserWin()==1) {
                                        dataCartSuccessResponseList.add(response.body().getDataCartResponseList().get(i));
                                    }
                                    if(response.body().getDataCartResponseList().get(i).getIsWinner()==0 &&
                                       response.body().getDataCartResponseList().get(i).getUserWin()==1 ){
                                        dataCartLostResponseList.add(response.body().getDataCartResponseList().get(i));
                                    }
                                }//end for
                                if (UtilityAndroid.getSetting("keranjangfilter").equalsIgnoreCase("berlangsung")){
                                    //dataCartLiveResponseList.clear();
                                    dataCartSuccessResponseList.clear();
                                    dataCartLostResponseList.clear();
                                }else if (UtilityAndroid.getSetting("keranjangfilter").equalsIgnoreCase("diterima")){
                                    dataCartLiveResponseList.clear();
                                    //dataCartSuccessResponseList.clear();
                                    dataCartLostResponseList.clear();
                                }else if (UtilityAndroid.getSetting("keranjangfilter").equalsIgnoreCase("ditolak")){
                                    dataCartLiveResponseList.clear();
                                    dataCartSuccessResponseList.clear();
                                    //dataCartLostResponseList.clear();
                                }


                                if(dataCartLiveResponseList.isEmpty()||dataCartLiveResponseList==null){
                                    linearLiveGarage.setVisibility(View.GONE);
                                }else {
                                    linearLiveGarage.setVisibility(View.VISIBLE);

                                    liveGarageAdapter = new LiveGarageAdapter(first,  getActivity(), convertDateServer(timeServer), dataCartLiveResponseList, dataCartResponse -> {
                                        tvMessageBuyNow.setText(dataCartResponse.getVehicleName()+"\nseharga\nRp"+setCurrencyFormat(String.valueOf(dataCartResponse.getOpenPrice())));
                                        relativeBackgroundDialogConfirmBuyNow.setVisibility(View.VISIBLE);
                                        negoAndBuyNowRequest = new NegoAndBuyNowRequest(String.valueOf(dataCartResponse.getOhid()), dataCartResponse.getKik(), dataCartResponse.getAgreementNo().trim(), String.valueOf(dataCartResponse.getOpenPrice()));
//                                        liveBuyNowApi(negoAndBuyNowRequest);
                                    }, dataCartResponse -> {
                                        Intent intent = new Intent(getActivity(), VehicleDetailActivity.class);
                                        intent.putExtra(GrosirMobilContract.ID_VEHICLE, String.valueOf(dataCartResponse.getOhid()));
                                        intent.putExtra(GrosirMobilContract.KIK, dataCartResponse.getKik());
                                        intent.putExtra(GrosirMobilContract.FROM_PAGE, "LIVE");
                                        getActivity().startActivity(intent);
                                    });

                                    rvLiveGarage.setAdapter(liveGarageAdapter);
                                    liveGarageAdapter.notifyDataSetChanged();
                                    first = false;
                                }
                                if(dataCartSuccessResponseList.isEmpty()||dataCartSuccessResponseList==null){
                                    linearSuccessGarage.setVisibility(View.GONE);
                                }else {
                                    linearSuccessGarage.setVisibility(View.VISIBLE);
                                    LinearLayoutManager layoutManagerSuccess = new LinearLayoutManager(getActivity());
                                    rvSuccessGarage.setLayoutManager(layoutManagerSuccess);
                                    rvSuccessGarage.setNestedScrollingEnabled(false);
                                    SuccessGarageAdapter successGarageAdapter = new SuccessGarageAdapter(getActivity(), dataCartSuccessResponseList);
                                    rvSuccessGarage.setAdapter(successGarageAdapter);
                                    successGarageAdapter.notifyDataSetChanged();
                                }
                                if(dataCartLostResponseList.isEmpty()||dataCartLostResponseList==null){
                                    linearLostGarage.setVisibility(View.GONE);
                                }else {
                                    linearLostGarage.setVisibility(View.VISIBLE);
                                    LinearLayoutManager layoutManagerLost = new LinearLayoutManager(getActivity());
                                    rvLostGarage.setLayoutManager(layoutManagerLost);
                                    rvLostGarage.setNestedScrollingEnabled(false);
                                    LostGarageAdapter lostGarageAdapter = new LostGarageAdapter(getActivity(), dataCartLostResponseList);
                                    rvLostGarage.setAdapter(lostGarageAdapter);
                                    lostGarageAdapter.notifyDataSetChanged();
                                }
                                System.out.println("Data Live : " + dataCartLiveResponseList.size());
                                System.out.println("Data Success : " + dataCartSuccessResponseList.size());
                                System.out.println("Data Lost : " + dataCartLostResponseList.size());





                                if(dataCartSuccessResponseList.isEmpty()&&
                                   dataCartLiveResponseList.isEmpty()&&
                                   dataCartLostResponseList.isEmpty()){
                                    linearCartEmpty.setVisibility(View.VISIBLE);
                                    linearCartNotEmpty.setVisibility(View.GONE);
                                    checkAuto=false;
                                }else {
                                    checkAuto=true;
                                    linearCartEmpty.setVisibility(View.GONE);
                                    linearCartNotEmpty.setVisibility(View.VISIBLE);
                                }
                            }
                        }else {
                            grosirMobilFunction.showMessage(getActivity(), "GET Cart Data", response.body().getMessage());
                        }
                    }catch (Exception e){
                        GrosirMobilLog.printStackTrace(e);
                    }
                }else {
                    try {
                        grosirMobilFunction.showMessage(getActivity(), getString(R.string.base_null_error_title), response.errorBody().string());
                    } catch (IOException e) {
                        GrosirMobilLog.printStackTrace(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                hideProgressBar();
                grosirMobilFunction.showMessage(getActivity(), "GET Cart Data", getString(R.string.base_null_server));
                GrosirMobilLog.printStackTrace(t);
            }
        });
    }


    @SuppressLint("NonConstantResourceId")
    @OnClick({R.id.tv_filter, R.id.linear_dialog_filter})
    void tvFilterClick(){
        relativeBackgroundDialogFilter.setVisibility(View.VISIBLE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.relative_background_dialog_filter)
    void relativeBackgroundDialogFilterClick(){
        relativeBackgroundDialogFilter.setVisibility(View.GONE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_close_dialog_confirm_buy_now)
    void ivCloseDialogConfirmBuyNowClick(){
        relativeBackgroundDialogConfirmBuyNow.setVisibility(View.GONE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_confirm_buy_now_dialog)
    void btnConfirmBuyNowDialogClick(){
        liveBuyNowApi(negoAndBuyNowRequest);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_all)
    void tvAllClick(){
        tvAll.setBackgroundResource(R.color.colorPrimaryTheme);
        tvAll.setTextColor(getResources().getColor(R.color.colorPrimaryWhite));
        tvPenawaranSedangBerlangsung.setBackgroundResource(R.color.colorPrimaryWhite);
        tvPenawaranSedangBerlangsung.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvPenawaranDiterima.setBackgroundResource(R.color.colorPrimaryWhite);
        tvPenawaranDiterima.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvPenawaranDitolak.setBackgroundResource(R.color.colorPrimaryWhite);
        tvPenawaranDitolak.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        UtilityAndroid.setSetting("keranjangfilter", "");
        relativeBackgroundDialogFilterClick();
        linearLiveGarage.setVisibility(View.VISIBLE);
        linearSuccessGarage.setVisibility(View.VISIBLE);
        linearLostGarage.setVisibility(View.VISIBLE);
        getTimeServerApi("1");
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_penawaran_sedang_berlangsung)
    void tvPenawaranSedangBerlangsungClick(){
        tvAll.setBackgroundResource(R.color.colorPrimaryWhite);
        tvAll.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvPenawaranSedangBerlangsung.setBackgroundResource(R.color.colorPrimaryTheme);
        tvPenawaranSedangBerlangsung.setTextColor(getResources().getColor(R.color.colorPrimaryWhite));
        tvPenawaranDiterima.setBackgroundResource(R.color.colorPrimaryWhite);
        tvPenawaranDiterima.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvPenawaranDitolak.setBackgroundResource(R.color.colorPrimaryWhite);
        tvPenawaranDitolak.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        UtilityAndroid.setSetting("keranjangfilter", "berlangsung");
        relativeBackgroundDialogFilterClick();
        linearLiveGarage.setVisibility(View.VISIBLE);
        linearSuccessGarage.setVisibility(View.GONE);
        linearLostGarage.setVisibility(View.GONE);
        getTimeServerApi("1");
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_penawaran_diterima)
    void tvPenawaranDiterimaClick(){
        tvAll.setBackgroundResource(R.color.colorPrimaryWhite);
        tvAll.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvPenawaranDiterima.setBackgroundResource(R.color.colorPrimaryTheme);
        tvPenawaranDiterima.setTextColor(getResources().getColor(R.color.colorPrimaryWhite));
        tvPenawaranSedangBerlangsung.setBackgroundResource(R.color.colorPrimaryWhite);
        tvPenawaranSedangBerlangsung.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvPenawaranDitolak.setBackgroundResource(R.color.colorPrimaryWhite);
        tvPenawaranDitolak.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        UtilityAndroid.setSetting("keranjangfilter", "diterima");
        relativeBackgroundDialogFilterClick();
        linearLiveGarage.setVisibility(View.GONE);
        linearSuccessGarage.setVisibility(View.VISIBLE);
        linearLostGarage.setVisibility(View.GONE);
        getTimeServerApi("1");
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_penawaran_ditolak)
    void tvPenawaranDitolakClick(){
        tvAll.setBackgroundResource(R.color.colorPrimaryWhite);
        tvAll.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvPenawaranDiterima.setBackgroundResource(R.color.colorPrimaryWhite);
        tvPenawaranDiterima.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvPenawaranDitolak.setBackgroundResource(R.color.colorPrimaryTheme);
        tvPenawaranDitolak.setTextColor(getResources().getColor(R.color.colorPrimaryWhite));
        tvPenawaranSedangBerlangsung.setBackgroundResource(R.color.colorPrimaryWhite);
        tvPenawaranSedangBerlangsung.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        UtilityAndroid.setSetting("keranjangfilter", "ditolak");
        relativeBackgroundDialogFilterClick();
        linearLiveGarage.setVisibility(View.GONE);
        linearSuccessGarage.setVisibility(View.GONE);
        linearLostGarage.setVisibility(View.VISIBLE);
        getTimeServerApi("1");
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_telusuri_kenderaan)
    void btnFindVehicleClick(){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra(REQUEST_MAIN, "");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        getActivity().finish();
    }

    public void liveBuyNowApi(NegoAndBuyNowRequest negoAndBuyNowRequest) {
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.base_tv_please_wait));
        progressDialog.show();
        final Call<GeneralNegoAndBuyNowResponse> vehicleDetailApi = getApiGrosirMobil().liveBuyNowApi(BEARER+" "+grosirMobilPreference.getToken(),negoAndBuyNowRequest);
        vehicleDetailApi.enqueue(new Callback<GeneralNegoAndBuyNowResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<GeneralNegoAndBuyNowResponse> call, Response<GeneralNegoAndBuyNowResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    try {
                        if(response.body().getMessage().equals("success")){
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            intent.putExtra(REQUEST_MAIN, "win");
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
//                            finish();
                        }else {
                            grosirMobilFunction.showMessage(getActivity(), "POST Live Buy Now", response.body().getMessage());
                        }
                    }catch (Exception e){
                        GrosirMobilLog.printStackTrace(e);
                    }
                }else {
                    try {
                        grosirMobilFunction.showMessage(getActivity(), getString(R.string.base_null_error_title), response.errorBody().string());
                    } catch (IOException e) {
                        GrosirMobilLog.printStackTrace(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<GeneralNegoAndBuyNowResponse> call, Throwable t) {
                progressDialog.dismiss();
                grosirMobilFunction.showMessage(getActivity(), "POST Live Buy Now", getString(R.string.base_null_server));
                GrosirMobilLog.printStackTrace(t);
            }
        });
    }
}
