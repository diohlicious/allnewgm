package com.sip.grosirmobil.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.WareHouseAdapter;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;
import com.sip.grosirmobil.cloud.config.response.warehouse.WareHouseResponse;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.bendik.simplerangeview.SimpleRangeView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.FROM_PAGE;

public class FilterActivity extends GrosirMobilActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_content) LinearLayout linearContent;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_merek) LinearLayout linearMerek;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_merek) TextView tvMerek;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_location) LinearLayout linearLocation;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_location) TextView tvLocation;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_start_range) TextView tvStartRange;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_end_range) TextView tvEndRange;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.range_seek_bar) SimpleRangeView rangeSeekBar;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_year) LinearLayout linearYear;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_year) TextView tvYear;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_grade) LinearLayout linearGrade;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_grade) TextView tvGrade;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.relative_dialog) RelativeLayout relativeDialog;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_choose) RecyclerView rvChoose;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.progress_circular) ProgressBar progressBar;
    private GrosirMobilPreference grosirMobilPreference;
    private GrosirMobilFunction grosirMobilFunction;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);
        grosirMobilPreference = new GrosirMobilPreference(this);
        grosirMobilFunction = new GrosirMobilFunction(this);

        if(getIntent().getStringExtra(FROM_PAGE).equals("BEFORE_SEARCH")){
            linearMerek.setVisibility(View.VISIBLE);
        }else {
            linearMerek.setVisibility(View.GONE);
        }

        rangeSeekBar.setOnTrackRangeListener(new SimpleRangeView.OnTrackRangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onStartRangeChanged(@NotNull SimpleRangeView rangeView, int start) {
                if(start==0){
                    tvStartRange.setText("Rp "+ start+"");
                }else {
                    tvStartRange.setText("Rp "+ start+"00.000.000");
                }
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onEndRangeChanged(@NotNull SimpleRangeView rangeView, int end) {
                tvEndRange.setText("Rp "+ end+"00.000.000");
            }
        });

        rangeSeekBar.setOnChangeRangeListener((rangeView, start, end) -> {
            if(start==0){
                tvStartRange.setText("Rp "+ start+"");
            }else {
                tvStartRange.setText("Rp "+ start+"00.000.000");
            }
            tvEndRange.setText("Rp "+ end+"00.000.000");
        });
    }

    private void showDialogChoose(){
        relativeDialog.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager layoutManagerChoose = new LinearLayoutManager(this);
        rvChoose.setLayoutManager(layoutManagerChoose);
        rvChoose.setNestedScrollingEnabled(false);
    }

    private void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.linear_content_dialog)
    void linearContentDialogClick(){
        relativeDialog.setVisibility(View.VISIBLE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.relative_dialog)
    void relativeDialogClick(){
        relativeDialog.setVisibility(View.GONE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.linear_merek)
    void linearMerekClick(){
        showDialogChoose();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.linear_location)
    void linearLocationClick(){
        if(grosirMobilPreference.getDataWareHouseList()==null||grosirMobilPreference.getDataWareHouseList().isEmpty()){
            showProgressBar();
            showDialogChoose();
            final Call<WareHouseResponse> wareHouseApi = getApiGrosirMobil().wareHouseApi("");
            wareHouseApi.enqueue(new Callback<WareHouseResponse>() {
                @Override
                public void onResponse(Call<WareHouseResponse> call, Response<WareHouseResponse> response) {
                    hideProgressBar();
                    if (response.isSuccessful()) {
                        try {
                            if(response.body().getMessage().equals("success")){
                                if(!response.body().getData().isEmpty()){
                                    grosirMobilPreference.saveDataWareHouseList(response.body().getData());
                                }
                                WareHouseAdapter wareHouseAdapter = new WareHouseAdapter(response.body().getData(), dataWareHouseResponse -> {
                                    tvLocation.setText(dataWareHouseResponse.getName());
                                    tvLocation.setTag(dataWareHouseResponse.getWarehouseCode());
                                    relativeDialogClick();
                                });
                                rvChoose.setAdapter(wareHouseAdapter);
                                wareHouseAdapter.notifyDataSetChanged();
                            }else {
                                grosirMobilFunction.showMessage(FilterActivity.this, "GET WareHouse", response.body().getMessage());
                            }
                        }catch (Exception e){
                            GrosirMobilLog.printStackTrace(e);
                        }
                    }else {
                        try {
                            grosirMobilFunction.showMessage(FilterActivity.this, getString(R.string.base_null_error_title), response.errorBody().string());
                        } catch (IOException e) {
                            GrosirMobilLog.printStackTrace(e);
                        }
                    }
                }
                @Override
                public void onFailure(Call<WareHouseResponse> call, Throwable t) {
                    hideProgressBar();
                    grosirMobilFunction.showMessage(FilterActivity.this, "GET WareHouse", getString(R.string.base_null_server));
                    GrosirMobilLog.printStackTrace(t);
                }
            });
        }
        else {
            showDialogChoose();
            hideProgressBar();
            WareHouseAdapter wareHouseAdapter = new WareHouseAdapter(grosirMobilPreference.getDataWareHouseList(), dataWareHouseResponse -> {
                tvLocation.setText(dataWareHouseResponse.getName());
                tvLocation.setTag(dataWareHouseResponse.getWarehouseCode());
                relativeDialogClick();
            });
            rvChoose.setAdapter(wareHouseAdapter);
            wareHouseAdapter.notifyDataSetChanged();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.linear_year)
    void linearYearClick(){
        showDialogChoose();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.linear_grade)
    void linearGradeClick(){
        showDialogChoose();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_back)
    void ivBackClick(){
        finish();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_delete)
    void tvDeleteClick(){
        tvMerek.setText("");
        tvLocation.setText("");
        tvStartRange.setText("");
        tvEndRange.setText("");
        tvYear.setText("");
        tvGrade.setText("");
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_filter)
    void btnFilterClick() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if ((ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE)
                && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int[] scrcoords = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) Objects.requireNonNull(this.getSystemService(Context.INPUT_METHOD_SERVICE))).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}