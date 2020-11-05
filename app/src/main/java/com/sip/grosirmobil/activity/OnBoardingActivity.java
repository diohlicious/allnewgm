package com.sip.grosirmobil.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.OnBoardingAdapter;
import com.sip.grosirmobil.base.permission.MultiPermissionActivity;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;
import com.sip.grosirmobil.base.view.OnBoardingView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.REQUEST_CODE_MULTI_PERMISSION_ACTIVITY;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.REQUEST_MULTI_PERMISSION;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.adjustFontScale;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setStatusBarOnBoarding;

public class OnBoardingActivity extends GrosirMobilActivity implements OnBoardingView {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_skip) TextView tvSkip;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.iv_back) ImageView ivBack;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.view_pager_on_boarding) ViewPager viewPagerOnBoarding;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_next) Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarOnBoarding(this);
        setContentView(R.layout.activity_on_boarding);
        adjustFontScale(this, getResources().getConfiguration());
        ButterKnife.bind(this);

        OnBoardingAdapter onBoardingAdapter = new OnBoardingAdapter(getSupportFragmentManager());
        viewPagerOnBoarding.setAdapter(onBoardingAdapter);

        viewPagerOnBoarding.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {}
            @Override
            public void onPageSelected(int i) {
                if (i==0){
                    ivBack.setVisibility(View.GONE);
                    tvSkip.setVisibility(View.VISIBLE);
                    btnNext.setText(getString(R.string.btn_selanjutnya));
                }else if(i==1){
                    ivBack.setVisibility(View.VISIBLE);
                    tvSkip.setVisibility(View.VISIBLE);
                    btnNext.setText(getString(R.string.btn_selanjutnya));
                }else {
                    ivBack.setVisibility(View.VISIBLE);
                    tvSkip.setVisibility(View.GONE);
                    btnNext.setText(getString(R.string.btn_selesai));
                }
            }
            @Override
            public void onPageScrollStateChanged(int i) {}
        });

//        checkAndRequestPermissions();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_back)
    void ivBackClick(){
        if(viewPagerOnBoarding.getCurrentItem()==1){
            viewPagerOnBoarding.setCurrentItem(0);
        }else if(viewPagerOnBoarding.getCurrentItem()==2){
            viewPagerOnBoarding.setCurrentItem(1);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_next)
    void btnNextClick(){
        System.out.println("SDASD : " + viewPagerOnBoarding.getCurrentItem());
        if(viewPagerOnBoarding.getCurrentItem()==0){
            viewPagerOnBoarding.setCurrentItem(1);
        }else if(viewPagerOnBoarding.getCurrentItem()==1){
            viewPagerOnBoarding.setCurrentItem(2);
        }else if(viewPagerOnBoarding.getCurrentItem()==2){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_skip)
    void tvSkipClick(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void checkAndRequestPermissions() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                            android.Manifest.permission.CAMERA,
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_MULTI_PERMISSION);
        }
    }

    @Override
    public void onPermissionActivityResult(int resultCode) {
        if(RESULT_CANCELED == resultCode) {
            setResult(RESULT_CANCELED);
            finish();
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_CODE_MULTI_PERMISSION_ACTIVITY) {
            onPermissionActivityResult(resultCode);
        }
    }

    @Override
    public void onCameraPermissionResult(int[] grantResults) {
        boolean allGranted = grantResults.length == 6 &&
                grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == android.content.pm.PackageManager.PERMISSION_GRANTED &&
                grantResults[2] == android.content.pm.PackageManager.PERMISSION_GRANTED &&
                grantResults[3] == android.content.pm.PackageManager.PERMISSION_GRANTED &&
                grantResults[4] == android.content.pm.PackageManager.PERMISSION_GRANTED &&
                grantResults[5] == android.content.pm.PackageManager.PERMISSION_GRANTED;
        if (!allGranted) {
            Intent intent = new Intent(this, MultiPermissionActivity.class);
            startActivityForResult(intent, REQUEST_CODE_MULTI_PERMISSION_ACTIVITY);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_MULTI_PERMISSION) {
            onCameraPermissionResult(grantResults);
        }
    }
}