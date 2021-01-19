 package com.sip.grosirmobil.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.LostBiddingAdapter;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;
import com.sip.grosirmobil.cloud.config.request.history.HistoryTransactionRequest;
import com.sip.grosirmobil.cloud.config.response.historytransaction.HistoryTransactionResponse;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.BEARER;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.adjustFontScale;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setStatusBarOnBoarding;

public class LostBiddingActivity extends GrosirMobilActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.progress) ProgressBar progress;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_bid_lost) RecyclerView rvBidLost;

    private GrosirMobilPreference grosirMobilPreference;
    private GrosirMobilFunction grosirMobilFunction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarOnBoarding(this);
        setContentView(R.layout.activity_lost_bidding);
        adjustFontScale(this, getResources().getConfiguration());
        ButterKnife.bind(this);

        grosirMobilFunction = new GrosirMobilFunction(this);
        grosirMobilPreference = new GrosirMobilPreference(this);

        getHistoryTransaction();

    }

    private void getHistoryTransaction(){
        progress.setVisibility(View.VISIBLE);
        HistoryTransactionRequest historyTransactionRequest = new HistoryTransactionRequest(1, 20, "0");
        final Call<HistoryTransactionResponse> historyTransactionApi = getApiGrosirMobil().historyTransactionApi(BEARER+" "+grosirMobilPreference.getToken(), historyTransactionRequest);
        historyTransactionApi.enqueue(new Callback<HistoryTransactionResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<HistoryTransactionResponse> call, Response<HistoryTransactionResponse> response) {
                progress.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    try {
                        if(response.body().getMessage().equals("success")){
                            RecyclerView.LayoutManager layoutManagerLive = new LinearLayoutManager(LostBiddingActivity.this);
                            rvBidLost.setLayoutManager(layoutManagerLive);
                            LostBiddingAdapter lostBiddingAdapter = new LostBiddingAdapter(LostBiddingActivity.this, response.body().getDataPageHistoryTransactionResponse().getDataHistoryTransactionResponseList());
                            rvBidLost.setAdapter(lostBiddingAdapter);
                            lostBiddingAdapter.notifyDataSetChanged();
                        }else {
                            grosirMobilFunction.showMessage(LostBiddingActivity.this, "History Transaction", response.body().getMessage());
                        }
                    }catch (Exception e){
                        GrosirMobilLog.printStackTrace(e);
                    }
                }
                else {
                    try {
                        grosirMobilFunction.showMessage(LostBiddingActivity.this, getString(R.string.base_null_error_title), response.errorBody().string());
                    } catch (IOException e) {
                        GrosirMobilLog.printStackTrace(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<HistoryTransactionResponse> call, Throwable t) {
                progress.setVisibility(View.GONE);
                grosirMobilFunction.showMessage(LostBiddingActivity.this, "History Transaction", getString(R.string.base_null_server));
                GrosirMobilLog.printStackTrace(t);
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_back)
    void ivBackClick(){
        finish();
    }
}