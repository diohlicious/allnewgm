package com.sip.grosirmobil.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.bendik.simplerangeview.SimpleRangeView;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.FROM_PAGE;

public class FilterActivity extends GrosirMobilActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_merek) LinearLayout linearMerek;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_start_range) TextView tvStartRange;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_end_range) TextView tvEndRange;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.range_seek_bar) SimpleRangeView rangeSeekBar;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);

        if(getIntent().getStringExtra(FROM_PAGE).equals("BEFORE_SEARCH")){
            linearMerek.setVisibility(View.VISIBLE);
        }else {
            linearMerek.setVisibility(View.GONE);
        }

//        rangeSeekBar.setOnTrackRangeListener(new SimpleRangeView.OnTrackRangeListener() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onStartRangeChanged(@NotNull SimpleRangeView rangeView, int start) {
//                if(start==0){
//                    tvStartRange.setText("Rp "+ start+"");
//                }else {
//                    tvStartRange.setText("Rp "+ start+"00.000.000");
//                }
//            }
//
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onEndRangeChanged(@NotNull SimpleRangeView rangeView, int end) {
//                tvEndRange.setText("Rp "+ end+"00.000.000");
//            }
//        });
//
//        rangeSeekBar.setOnChangeRangeListener((rangeView, start, end) -> {
//            if(start==0){
//                tvStartRange.setText("Rp "+ start+"");
//            }else {
//                tvStartRange.setText("Rp "+ start+"00.000.000");
//            }
//            tvEndRange.setText("Rp "+ end+"00.000.000");
//        });
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_back)
    void ivBackClick(){
        finish();
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