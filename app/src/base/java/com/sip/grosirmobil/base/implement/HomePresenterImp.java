package com.sip.grosirmobil.base.implement;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.BannerGalleryAdapter;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.base.presenter.HomePresenter;
import com.sip.grosirmobil.base.util.AutoScrollViewPager;
import com.sip.grosirmobil.base.view.HomeView;
import com.sip.grosirmobil.cloud.config.model.BannerDataModel;
import com.sip.grosirmobil.cloud.config.request.home.HomeHistoryRequest;
import com.sip.grosirmobil.cloud.config.request.home.HomeLiveRequest;
import com.sip.grosirmobil.cloud.config.response.checkactivetoken.CheckActiveTokenResponse;
import com.sip.grosirmobil.cloud.config.response.homehistory.HomeHistoryResponse;
import com.sip.grosirmobil.cloud.config.response.homelive.HomeLiveResponse;
import com.sip.grosirmobil.cloud.config.response.timeserver.TimeServerResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sip.grosirmobil.base.GrosirMobilApp.getApiGrosirMobil;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.BEARER;

public class HomePresenterImp implements HomePresenter {

    private final HomeView homeView;
    private final Context context;
    private final boolean search;
    private final LinearLayout linearTitleContent;
    private final LinearLayout linearSearchAndLive;
    private final LinearLayout linearEmptyData;
    private final RecyclerView rvLive;
    private final NestedScrollView nestedView;
    private final RecyclerView rvLiveSoon;
    private final RecyclerView rvRecord;
    private final LinearLayout linearResultTitleContent;
    private final TextView tvResultTitleContent;
    private final TextView tvTitleContent;
    private final TextView tvLive;
    private final TextView tvLiveSoon;
    private final TextView tvRecord;
    private final TextView tvKetEmptyDataHome;
    private GrosirMobilPreference grosirMobilPreference;
    private GrosirMobilFunction grosirMobilFunction;
    private final int page;
    private final int max;
    private final String lokasi;
    private final int tahunStart;
    private final int tahunEnd;
    private final long hargaStart;
    private final long hargaEnd;
    private final String merek;
    private final String grade;

    public HomePresenterImp(HomeView homeView, Context context, boolean search, LinearLayout linearTitleContent, LinearLayout linearSearchAndLive, LinearLayout linearEmptyData, RecyclerView rvLive, NestedScrollView nestedView, RecyclerView rvLiveSoon, RecyclerView rvRecord, LinearLayout linearResultTitleContent, TextView tvResultTitleContent, TextView tvTitleContent, TextView tvLive, TextView tvLiveSoon, TextView tvRecord, TextView tvKetEmptyDataHome, int page, int max, String lokasi, int tahunStart, int tahunEnd, long hargaStart, long hargaEnd, String merek, String grade) {
        this.homeView = homeView;
        this.context = context;
        this.search = search;
        this.linearTitleContent = linearTitleContent;
        this.linearSearchAndLive = linearSearchAndLive;
        this.linearEmptyData = linearEmptyData;
        this.rvLive = rvLive;
        this.nestedView = nestedView;
        this.rvLiveSoon = rvLiveSoon;
        this.rvRecord = rvRecord;
        this.linearResultTitleContent = linearResultTitleContent;
        this.tvResultTitleContent = tvResultTitleContent;
        this.tvTitleContent = tvTitleContent;
        this.tvLive = tvLive;
        this.tvLiveSoon = tvLiveSoon;
        this.tvRecord = tvRecord;
        this.tvKetEmptyDataHome = tvKetEmptyDataHome;
        this.page = page;
        this.max = max;
        this.lokasi = lokasi;
        this.tahunStart = tahunStart;
        this.tahunEnd = tahunEnd;
        this.hargaStart = hargaStart;
        this.hargaEnd = hargaEnd;
        this.merek = merek;
        this.grade = grade;
        grosirMobilFunction     = new GrosirMobilFunction(context);
        grosirMobilPreference   = new GrosirMobilPreference(context);
    }


    @Override
    public void getHomeLiveApi(int page, int max, String lokasi, int tahunStart, int tahunEnd, long hargaStart, long hargaEnd, String merek) {
        linearEmptyData.setVisibility(View.GONE);
        homeView.showDialogLoading();
        rvLive.setVisibility(View.GONE);
        HomeLiveRequest homeLiveRequest = new HomeLiveRequest(page,max,lokasi,tahunStart,tahunEnd, hargaStart,hargaEnd,merek);
        final Call<HomeLiveResponse> timeServerApi = getApiGrosirMobil().homeLiveApi(BEARER+" "+grosirMobilPreference.getToken(),homeLiveRequest);
        timeServerApi.enqueue(new Callback<HomeLiveResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<HomeLiveResponse> call, Response<HomeLiveResponse> response) {
                homeView.hideDialogLoading();
                rvLive.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    try {
                        if(response.body().getMessage().equals("success")){
                            homeView.homeLiveSuccess(response.body());
                        }else {
                            grosirMobilFunction.showMessage(context, "GET Home Live", response.body().getMessage());
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
            public void onFailure(Call<HomeLiveResponse> call, Throwable t) {
                homeView.hideDialogLoading();
                rvLive.setVisibility(View.VISIBLE);
                grosirMobilFunction.showMessage(context, "GET Home Live", context.getString(R.string.base_null_server));
                GrosirMobilLog.printStackTrace(t);
            }
        });
    }

    @Override
    public void getHomeComingSoonApi() {

    }

    @Override
    public void setDataBannerHome(AutoScrollViewPager viewPagerHome, CircleIndicator circleIndicator) {
        List<BannerDataModel> bannerDataModelList = new ArrayList<>();

        BannerDataModel bannerDataModel = new BannerDataModel("Dapatkan promo akhir tahun dengan berbagai\nmacam unit yang ada!","Promo\nAkhir 2020");
        bannerDataModelList.add(bannerDataModel);
        bannerDataModel = new BannerDataModel("Dapatkan promo akhir tahun dengan berbagai\nmacam unit yang ada!","Promo\nAkhir 2020");
        bannerDataModelList.add(bannerDataModel);
        bannerDataModel = new BannerDataModel("Dapatkan promo akhir tahun dengan berbagai\nmacam unit yang ada!","Promo\nAkhir 2020");
        bannerDataModelList.add(bannerDataModel);

        viewPagerHome.startAutoScroll();
        viewPagerHome.setInterval(3000);
        viewPagerHome.setCycle(true);
        viewPagerHome.setStopScrollWhenTouch(true);
        BannerGalleryAdapter viewPagerAdapter = new BannerGalleryAdapter(context, bannerDataModelList);
        viewPagerHome.setAdapter(viewPagerAdapter);
        circleIndicator.setViewPager(viewPagerHome);
    }

    @Override
    public void getHomeHistoryApi(int page, int max, String isMenang) {
        linearEmptyData.setVisibility(View.GONE);
        homeView.showDialogLoading();
        rvRecord.setVisibility(View.GONE);
        HomeHistoryRequest homeHistoryRequest = new HomeHistoryRequest(page,max,isMenang);
        final Call<HomeHistoryResponse> timeServerApi = getApiGrosirMobil().homeHistoryApi(BEARER+" "+grosirMobilPreference.getToken(),homeHistoryRequest);
        timeServerApi.enqueue(new Callback<HomeHistoryResponse>() {
            @Override
            public void onResponse(Call<HomeHistoryResponse> call, Response<HomeHistoryResponse> response) {
                homeView.hideDialogLoading();
                rvRecord.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    try {
                        if(response.body().getMessage().equals("success")){
                            homeView.homeHistorySuccess(response.body());
                        }else {
                            grosirMobilFunction.showMessage(context, "GET Home History", response.body().getMessage());
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
            public void onFailure(Call<HomeHistoryResponse> call, Throwable t) {
                homeView.hideDialogLoading();
                rvRecord.setVisibility(View.VISIBLE);
                grosirMobilFunction.showMessage(context, "GET Home History", context.getString(R.string.base_null_server));
                GrosirMobilLog.printStackTrace(t);
            }

        });
    }

    @Override
    public void getTimeServerApi() {
        homeView.showDialogLoading();
        final Call<TimeServerResponse> timeServerApi = getApiGrosirMobil().timeServerApi();
        timeServerApi.enqueue(new Callback<TimeServerResponse>() {
            @Override
            public void onResponse(Call<TimeServerResponse> call, Response<TimeServerResponse> response) {
                homeView.hideDialogLoading();
                if (response.isSuccessful()) {
                    try {
                        if(response.body().getMessage().equals("success")){
                            grosirMobilPreference.saveTimeServer(response.body().getData().getTimeServer());
                            getCheckActiveTokenApi();
//                            tvLiveClick();
                            getHomeLiveApi(page,max,lokasi,tahunStart,tahunEnd, hargaStart,hargaEnd,merek);
                        }else {
                            grosirMobilFunction.showMessage(context, "GET Time Server", response.body().getMessage());
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
            public void onFailure(Call<TimeServerResponse> call, Throwable t) {
                homeView.hideDialogLoading();
                grosirMobilFunction.showMessage(context, "GET Time Server", context.getString(R.string.base_null_server));
                GrosirMobilLog.printStackTrace(t);
            }
        });
    }

    @Override
    public void getCheckActiveTokenApi() {
        homeView.showDialogLoading();
        final Call<CheckActiveTokenResponse> checkActiveTokenApi = getApiGrosirMobil().checkActiveTokenApi(BEARER+" "+grosirMobilPreference.getToken());
        checkActiveTokenApi.enqueue(new Callback<CheckActiveTokenResponse>() {
            @Override
            public void onResponse(Call<CheckActiveTokenResponse> call, Response<CheckActiveTokenResponse> response) {
                homeView.hideDialogLoading();
                if (response.isSuccessful()) {
                    try {
                        if(response.body().getMessage().equals("success")){
                            grosirMobilPreference.saveDataCheckActiveToken(response.body().getData());
                        }else {
                            grosirMobilFunction.showMessage(context, "GET Check Active Token", response.body().getMessage());
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
            public void onFailure(Call<CheckActiveTokenResponse> call, Throwable t) {
                homeView.hideDialogLoading();
                grosirMobilFunction.showMessage(context, "GET Check Active Token", context.getString(R.string.base_null_server));
                GrosirMobilLog.printStackTrace(t);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void tvLiveClick() {
//        if(search){
//            linearTitleContent.setVisibility(View.GONE);
//            linearSearchAndLive.setVisibility(View.GONE);
//        }else {
//            linearTitleContent.setVisibility(View.VISIBLE);
//            linearSearchAndLive.setVisibility(View.VISIBLE);
//        }
//        nestedView.setBackgroundResource(R.color.colorPrimaryWhite);
//        rvLive.setVisibility(View.GONE);
//        rvLiveSoon.setVisibility(View.GONE);
//        rvRecord.setVisibility(View.GONE);
//        linearResultTitleContent.setBackgroundResource(R.drawable.design_card_live);
//        tvResultTitleContent.setTextColor(context.getResources().getColor(R.color.colorPrimaryWhite));
//        tvTitleContent.setText("Baru Masuk");
//        tvLive.setBackgroundResource(R.drawable.design_line_selected_live);
//        tvLive.setTextColor(context.getResources().getColor(R.color.colorPrimaryWhite));
//        tvLiveSoon.setBackgroundResource(R.drawable.design_line);
//        tvLiveSoon.setTextColor(context.getResources().getColor(R.color.colorPrimaryFont));
//        tvRecord.setBackgroundResource(R.drawable.design_line);
//        tvRecord.setTextColor(context.getResources().getColor(R.color.colorPrimaryFont));
    }

    @Override
    public void tvRecordClick() {
//        linearSearchAndLive.setVisibility(View.GONE);
//        nestedView.setBackgroundResource(R.color.colorBackgroundHome);
//        rvLive.setVisibility(View.GONE);
//        rvLiveSoon.setVisibility(View.GONE);
//        rvRecord.setVisibility(View.GONE);
//        linearTitleContent.setVisibility(View.GONE);
//        tvLive.setBackgroundResource(R.drawable.design_line);
//        tvLive.setTextColor(context.getResources().getColor(R.color.colorPrimaryFont));
//        tvLiveSoon.setBackgroundResource(R.drawable.design_line);
//        tvLiveSoon.setTextColor(context.getResources().getColor(R.color.colorPrimaryFont));
//        tvRecord.setBackgroundResource(R.drawable.design_line_selected);
//        tvRecord.setTextColor(context.getResources().getColor(R.color.colorPrimaryWhite));
    }

    @Override
    public void tvLiveSoonClick() {
//        if(search){
//            linearTitleContent.setVisibility(View.GONE);
//            linearSearchAndLive.setVisibility(View.GONE);
//        }else {
//            linearTitleContent.setVisibility(View.VISIBLE);
//            linearSearchAndLive.setVisibility(View.VISIBLE);
//        }
//        nestedView.setBackgroundResource(R.color.colorPrimaryWhite);
//        rvLive.setVisibility(View.GONE);
//        rvLiveSoon.setVisibility(View.GONE);
//        rvLiveSoon.setVisibility(View.GONE);
//        rvRecord.setVisibility(View.GONE);
//        tvResultTitleContent.setText("Ada " + "0" + " kendaraan yang Akan Tayang!");
////        tvResultTitleContent.setText("Ada 32 kendaraan yang Akan Tayang!");
//        linearResultTitleContent.setBackgroundResource(R.drawable.design_card_soon);
//        tvResultTitleContent.setTextColor(context.getResources().getColor(R.color.colorPrimaryFont));
//        tvTitleContent.setText("Segera Tayang");
//        tvLive.setBackgroundResource(R.drawable.design_line);
//        tvLive.setTextColor(context.getResources().getColor(R.color.colorPrimaryFont));
//        tvLiveSoon.setBackgroundResource(R.drawable.design_line_selected_soon);
//        tvLiveSoon.setTextColor(context.getResources().getColor(R.color.colorPrimaryFont));
//        tvRecord.setBackgroundResource(R.drawable.design_line);
//        tvRecord.setTextColor(context.getResources().getColor(R.color.colorPrimaryFont));
//
//        //LIVE SOON EMPTY
//        tvKetEmptyDataHome.setText(context.getString(R.string.tv_empty_data_coming_soon));
//        linearEmptyData.setVisibility(View.VISIBLE);
    }
}
