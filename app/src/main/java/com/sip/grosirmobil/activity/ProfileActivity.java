package com.sip.grosirmobil.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.LostBiddingAdapter;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sip.grosirmobil.base.function.GrosirMobilFunction.adjustFontScale;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setStatusBarOnBoarding;

public class ProfileActivity extends GrosirMobilActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.iv_back)
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarOnBoarding(this);
        setContentView(R.layout.actifity_profile);
        adjustFontScale(this, getResources().getConfiguration());
        ButterKnife.bind(this);


    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_success_bidding)
    void ivSuccessBiddingClick() {
        Intent intent = new Intent(this, SuccessBiddingActivity.class);
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_lost_bidding)
    void ivLostBiddingClick() {
        Intent intent = new Intent(this, LostBiddingActivity.class);
        startActivity(intent);

    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_back)
    void ivBackClick() {
        onBackPressed();
    }


}