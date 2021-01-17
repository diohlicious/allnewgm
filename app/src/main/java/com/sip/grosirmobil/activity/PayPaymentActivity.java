package com.sip.grosirmobil.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.base.data.DataWinNotPaymentModel;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;
import com.sip.grosirmobil.cloud.config.request.generateva.GenerateVaRequest;
import com.sip.grosirmobil.cloud.config.request.generateva.PilihUnitBayarRequest;
import com.sip.grosirmobil.cloud.config.response.generateva.GenerateVaResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.BEARER;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.FROM_PAGE;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.ID_VEHICLE;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.KIK;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.PRICE;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.REF_NUMBER;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.VEHICLE_SELECTED_TO_PAY;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.adjustFontScale;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setCurrencyFormat;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setStatusBarOnBoarding;

public class PayPaymentActivity extends GrosirMobilActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_total_price) TextView tvTotalPrice;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_harga_kendaraan) TextView tvHargaKendaraan;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_biaya_admin) TextView tvBiayaAdmin;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_total) TextView tvTotal;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_payment_method) RecyclerView rvPaymentMethod;

    private final List<PilihUnitBayarRequest> pilihUnitBayarRequestList = new ArrayList<>();

    private String openHouseId="";
    private String kik="";
    private long totalPrice = 0;
    private long hargaKendaraan = 0;
    private GrosirMobilPreference grosirMobilPreference;
    private GrosirMobilFunction grosirMobilFunction;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarOnBoarding(this);
        setContentView(R.layout.activity_pay_payment);
        adjustFontScale(this, getResources().getConfiguration());
        ButterKnife.bind(this);
        grosirMobilFunction = new GrosirMobilFunction(this);
        grosirMobilPreference = new GrosirMobilPreference(this);

        String fromPage = getIntent().getStringExtra(FROM_PAGE);
        try {
            long biayaAdmin = 0;
            if(fromPage.equals("Cart")){
                openHouseId = getIntent().getStringExtra(ID_VEHICLE);
                kik = getIntent().getStringExtra(KIK);
                long hargaKendaraan = Long.parseLong(getIntent().getStringExtra(PRICE));
                totalPrice = hargaKendaraan+biayaAdmin;
                tvTotalPrice.setText("Rp "+setCurrencyFormat(String.valueOf(totalPrice)));
                tvHargaKendaraan.setText("Rp "+setCurrencyFormat(getIntent().getStringExtra(PRICE)));
                tvBiayaAdmin.setText("Rp "+ setCurrencyFormat(String.valueOf(biayaAdmin)));
                tvTotal.setText("Rp "+setCurrencyFormat(String.valueOf(totalPrice)));
                PilihUnitBayarRequest pilihUnitBayarRequest = new PilihUnitBayarRequest(kik,hargaKendaraan);
                pilihUnitBayarRequestList.add(pilihUnitBayarRequest);
            }else if(fromPage.equals("Win")){
                Bundle bundle = getIntent().getExtras();
                ArrayList<DataWinNotPaymentModel> dataWinNotPaymentModels = bundle.getParcelableArrayList(VEHICLE_SELECTED_TO_PAY);
                System.out.println("SIZE DATA : "+ dataWinNotPaymentModels.size());
                for(int i=0; i<dataWinNotPaymentModels.size();i++){
                    hargaKendaraan = hargaKendaraan+Long.parseLong(dataWinNotPaymentModels.get(i).getUserTertinggi());
                    PilihUnitBayarRequest pilihUnitBayarRequest = new PilihUnitBayarRequest(
                            dataWinNotPaymentModels.get(i).getKik(),
                            Long.parseLong(dataWinNotPaymentModels.get(i).getUserTertinggi()));
                    pilihUnitBayarRequestList.add(pilihUnitBayarRequest);
                }

                totalPrice = hargaKendaraan+biayaAdmin;
                tvHargaKendaraan.setText("Rp "+setCurrencyFormat(String.valueOf(hargaKendaraan)));
                tvTotalPrice.setText("Rp "+setCurrencyFormat(String.valueOf(totalPrice)));
                tvBiayaAdmin.setText("Rp "+ setCurrencyFormat(String.valueOf(biayaAdmin)));
                tvTotal.setText("Rp "+setCurrencyFormat(String.valueOf(totalPrice)));
            }
        }catch (Exception e){
            GrosirMobilLog.printStackTrace(e);
        }
    }

    void generateVa(){
        GenerateVaRequest generateVaRequest = new GenerateVaRequest(pilihUnitBayarRequestList, "BCA",totalPrice);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.base_tv_please_wait));
        progressDialog.show();
        final Call<GenerateVaResponse> generateVaApi = getApiGrosirMobil().generateVaApi(BEARER+" "+grosirMobilPreference.getToken(), generateVaRequest);
        generateVaApi.enqueue(new Callback<GenerateVaResponse>() {
            @Override
            public void onResponse(Call<GenerateVaResponse> call, Response<GenerateVaResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    try {
                        if (response.body().getMessage().equals("success")) {
                            Intent intent = new Intent(PayPaymentActivity.this, PayDetailActivity.class);
                            intent.putExtra(REF_NUMBER, response.body().getOrderNo());
                            startActivity(intent);
                            finish();
                        }else {
                            grosirMobilFunction.showMessage(PayPaymentActivity.this, "GET Generate VA", response.body().getMessage());
                        }
                    }catch (Exception e){
                        GrosirMobilLog.printStackTrace(e);
                    }
                }else {
                    try {
                        grosirMobilFunction.showMessage(PayPaymentActivity.this, getString(R.string.base_null_error_title), response.errorBody().string());
                    } catch (IOException e) {
                        GrosirMobilLog.printStackTrace(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<GenerateVaResponse> call, Throwable t) {
                progressDialog.dismiss();
                grosirMobilFunction.showMessage(PayPaymentActivity.this, "GET Generate VA", getString(R.string.base_null_server));
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
    @OnClick(R.id.btn_pay)
    void btnPayClick(){
        Intent intent = new Intent(PayPaymentActivity.this, PayDetailActivity.class);
        intent.putExtra(REF_NUMBER, "cVJWSaz85Ds");
        startActivity(intent);
        finish();
//        generateVa();
    }
}