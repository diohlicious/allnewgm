package com.sip.grosirmobil.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.PaymentMethodAdapter;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;
import com.sip.grosirmobil.cloud.config.model.HardCodeDataModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sip.grosirmobil.base.function.GrosirMobilFunction.adjustFontScale;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setStatusBarOnBoarding;

public class PayPaymentActivity extends GrosirMobilActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_total_price) TextView tvTotalPrice;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_payment_method) RecyclerView rvPaymentMethod;

    private List<HardCodeDataModel> hardCodeDataModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarOnBoarding(this);
        setContentView(R.layout.activity_pay_payment);
        adjustFontScale(this, getResources().getConfiguration());
        ButterKnife.bind(this);

        setDataPaymentMethod();

        LinearLayoutManager layoutManagerImageCar = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvPaymentMethod.setLayoutManager(layoutManagerImageCar);
        rvPaymentMethod.setItemAnimator(new DefaultItemAnimator());
        rvPaymentMethod.setNestedScrollingEnabled(false);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(rvPaymentMethod);

        PaymentMethodAdapter paymentMethodAdapter = new PaymentMethodAdapter(this, hardCodeDataModelList);
        rvPaymentMethod.setAdapter(paymentMethodAdapter);
        paymentMethodAdapter.notifyDataSetChanged();

    }

    private void setDataPaymentMethod(){
        HardCodeDataModel hardCodeDataModel = new HardCodeDataModel("");
        hardCodeDataModelList.add(hardCodeDataModel);
        hardCodeDataModel = new HardCodeDataModel("");
        hardCodeDataModelList.add(hardCodeDataModel);
        hardCodeDataModel = new HardCodeDataModel("");
        hardCodeDataModelList.add(hardCodeDataModel);
        hardCodeDataModel = new HardCodeDataModel("");
        hardCodeDataModelList.add(hardCodeDataModel);
        hardCodeDataModel = new HardCodeDataModel("");
        hardCodeDataModelList.add(hardCodeDataModel);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_back)
    void ivBackClick() {
        finish();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_pay)
    void btnPayClick(){
        Intent intent = new Intent(this, PayDetailActivity.class);
        startActivity(intent);
        finish();
    }
}