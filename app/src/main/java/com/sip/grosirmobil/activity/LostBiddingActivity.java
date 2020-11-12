 package com.sip.grosirmobil.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.LostBiddingAdapter;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;
import com.sip.grosirmobil.cloud.config.model.HardCodeDataBaruMasukModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sip.grosirmobil.base.function.GrosirMobilFunction.adjustFontScale;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setStatusBarOnBoarding;

public class LostBiddingActivity extends GrosirMobilActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_bid_lost) RecyclerView rvBidLost;

    private List<HardCodeDataBaruMasukModel> liveHardCodeDataBaruMasukModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarOnBoarding(this);
        setContentView(R.layout.activity_lost_bidding);
        adjustFontScale(this, getResources().getConfiguration());
        ButterKnife.bind(this);

        setDataLive();

        RecyclerView.LayoutManager layoutManagerLive = new LinearLayoutManager(this);
        rvBidLost.setLayoutManager(layoutManagerLive);
        LostBiddingAdapter lostBiddingAdapter = new LostBiddingAdapter(this, liveHardCodeDataBaruMasukModelList);
        rvBidLost.setAdapter(lostBiddingAdapter);
        lostBiddingAdapter.notifyDataSetChanged();
    }

    private void setDataLive(){
        HardCodeDataBaruMasukModel hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","04h 27m 03s");
        liveHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","04h 27m 03s");
        liveHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","04h 27m 03s");
        liveHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","04h 27m 03s");
        liveHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","04h 27m 03s");
        liveHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","04h 27m 03s");
        liveHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","04h 27m 03s");
        liveHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","04h 27m 03s");
        liveHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","04h 27m 03s");
        liveHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","04h 27m 03s");
        liveHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_back)
    void ivBackClick(){
        finish();
    }
}