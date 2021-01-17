package com.sip.grosirmobil.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.VehicleToBuyAdapter;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;
import com.sip.grosirmobil.cloud.config.request.invoice.InvoiceVaRequest;
import com.sip.grosirmobil.cloud.config.response.invoiceva.InvoiceVaResponse;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.BEARER;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.REF_NUMBER;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.REQUEST_MAIN;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.adjustFontScale;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.calculateDate;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.convertDate;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setCurrencyFormat;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setStatusBarOnBoarding;

public class PayDetailActivity extends GrosirMobilActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_hour_first) TextView tvHourFirst;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_hour_second) TextView tvHourSecond;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_minute_first) TextView tvMinuteFirst;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_minute_second) TextView tvMinuteSecond;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_second_first) TextView tvSecondFirst;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_second_second) TextView tvSecondSecond;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_pembayaran_dash) TextView tvPembayaranDash;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_pay_expired) TextView tvPayExpired;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_unit_to_buy) RecyclerView rvUnitToBuy;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_total_pembayaran) TextView tvTotalPembayaran;

    private GrosirMobilFunction grosirMobilFunction;
    private GrosirMobilPreference grosirMobilPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarOnBoarding(this);
        setContentView(R.layout.activity_pay_detail);
        adjustFontScale(this, getResources().getConfiguration());
        ButterKnife.bind(this);

        grosirMobilFunction = new GrosirMobilFunction(this);
        grosirMobilPreference = new GrosirMobilPreference(this);

        getInvoiceDetail(getIntent().getStringExtra(REF_NUMBER));

//        startTimer(1000000);

    }

    private void getInvoiceDetail(String orderNo){
        InvoiceVaRequest invoiceVaRequest = new InvoiceVaRequest(orderNo);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.base_tv_please_wait));
        progressDialog.show();
        final Call<InvoiceVaResponse> invoiceVaApi = getApiGrosirMobil().invoiceVaApi(BEARER+" "+grosirMobilPreference.getToken(), invoiceVaRequest);
        invoiceVaApi.enqueue(new Callback<InvoiceVaResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<InvoiceVaResponse> call, Response<InvoiceVaResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    try {
                        if (response.body().getMessage().equals("success")) {
                            String startDate = convertDate(response.body().getDataInvoiceResponse().getDataVaResponse().getStartDateVa(),"yyyy-MM-dd hh:mm:ss","dd-MM-yyyy HH:mm:ss");
                            String endDate = convertDate(response.body().getDataInvoiceResponse().getDataVaResponse().getEndDateVa(),"yyyy-MM-dd hh:mm:ss","dd-MM-yyyy HH:mm:ss");
                            startTimer(calculateDate(startDate,endDate));
                            LinearLayoutManager linearLayoutManagerBody = new LinearLayoutManager(PayDetailActivity.this);
                            rvUnitToBuy.setLayoutManager(linearLayoutManagerBody);
                            rvUnitToBuy.setItemAnimator(new DefaultItemAnimator());
                            rvUnitToBuy.setNestedScrollingEnabled(false);
                            VehicleToBuyAdapter vehicleToBuyAdapter = new VehicleToBuyAdapter(response.body().getDataInvoiceResponse().getDetailResponseList());
                            rvUnitToBuy.setAdapter(vehicleToBuyAdapter);
                            vehicleToBuyAdapter.notifyDataSetChanged();
                            tvPembayaranDash.setText(response.body().getDataInvoiceResponse().getDataVaResponse().getVaNumber());
                            tvPayExpired.setText("Sebelum "+convertDate(response.body().getDataInvoiceResponse().getDataVaResponse().getEndDateVa(),"yyyy-MM-dd hh:mm:ss","EEE, dd MMMM yyyy"));
                            tvTotalPembayaran.setText("Rp "+setCurrencyFormat(response.body().getDataInvoiceResponse().getDataVaResponse().getTotalAmount()));
                        }else {
                            grosirMobilFunction.showMessage(PayDetailActivity.this, "GET Invoice VA", response.body().getMessage());
                        }
                    }catch (Exception e){
                        GrosirMobilLog.printStackTrace(e);
                    }
                }else {
                    try {
                        grosirMobilFunction.showMessage(PayDetailActivity.this, getString(R.string.base_null_error_title), response.errorBody().string());
                    } catch (IOException e) {
                        GrosirMobilLog.printStackTrace(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<InvoiceVaResponse> call, Throwable t) {
                progressDialog.dismiss();
                grosirMobilFunction.showMessage(PayDetailActivity.this, "GET Invoice VA", getString(R.string.base_null_server));
                GrosirMobilLog.printStackTrace(t);
            }
        });


    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_back)
    void ivBackClick() {
        finish();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_check_status)
    void btnCheckStatusClick(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(REQUEST_MAIN, "win");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void startTimer(long noOfMinutes) {
        new CountDownTimer(noOfMinutes,  1000) {
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int hours = seconds / (60 * 60);
                int tempMint = (seconds - (hours * 60 * 60));
                int minutes = tempMint / 60;
                seconds = tempMint - (minutes * 60);

                tvHourFirst.setText(String.format("%02d", hours).substring(0,1));
                tvHourSecond.setText(String.format("%02d", hours).substring(1,2));
                tvMinuteFirst.setText(String.format("%02d", minutes).substring(0,1));
                tvMinuteSecond.setText(String.format("%02d", minutes).substring(1,2));
                tvSecondFirst.setText(String.format("%02d", seconds).substring(0,1));
                tvSecondSecond.setText(String.format("%02d", seconds).substring(1,2));
            }
            @SuppressLint("SetTextI18n")
            public void onFinish() {
                tvHourFirst.setText("0");
                tvHourSecond.setText("0");
                tvMinuteFirst.setText("0");
                tvMinuteSecond.setText("0");
                tvSecondFirst.setText("0");
                tvSecondSecond.setText("0");
            }
        }.start();
    }
}