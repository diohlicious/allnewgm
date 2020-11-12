package com.sip.grosirmobil.fragment.navigationmenu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.LiveGarageAdapter;
import com.sip.grosirmobil.adapter.LostGarageAdapter;
import com.sip.grosirmobil.adapter.SuccessGarageAdapter;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.util.GrosirMobilFragment;
import com.sip.grosirmobil.cloud.config.model.HardCodeDataBaruMasukModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link androidx.fragment.app.Fragment} subclass.
 */

public class GarasiFragment extends GrosirMobilFragment {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.swipe_refresh_home) SwipeRefreshLayout swipeRefreshHome;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.nested_view) NestedScrollView nestedView;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_filter) TextView tvFilter;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_live_garage) RecyclerView rvLiveGarage;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_success_garage) RecyclerView rvSuccessGarage;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_lost_garage) RecyclerView rvLostGarage;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.relative_background_dialog_filter) RelativeLayout relativeBackgroundDialogFilter;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_dialog_filter) LinearLayout linearDialogFilter;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_penawaran_diterima) TextView tvPenawaranDiterima;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_penawaran_ditolak) TextView tvPenawaranDitolak;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_pembayaran_diterima) TextView tvPembayaranDiterima;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_kendaraan_siap_diambil) TextView tvKendaraanSiapDiambil;

    private GrosirMobilFunction grosirMobilFunction;
    private List<HardCodeDataBaruMasukModel> liveHardCodeDataBaruMasukModelList = new ArrayList<>();
    private List<HardCodeDataBaruMasukModel> successHardCodeDataBaruMasukModelList = new ArrayList<>();
    private List<HardCodeDataBaruMasukModel> lostHardCodeDataBaruMasukModelList = new ArrayList<>();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_garasi, container, false);
        ButterKnife.bind(this, view);

        grosirMobilFunction = new GrosirMobilFunction(getActivity());

        setDataLive();
        setDataSuccess();
        setDataLost();

        RecyclerView.LayoutManager layoutManagerLive = new LinearLayoutManager(getActivity());
        rvLiveGarage.setLayoutManager(layoutManagerLive);
        rvLiveGarage.setNestedScrollingEnabled(false);
        LiveGarageAdapter liveGarageAdapter = new LiveGarageAdapter(getActivity(), liveHardCodeDataBaruMasukModelList);
        rvLiveGarage.setAdapter(liveGarageAdapter);
        liveGarageAdapter.notifyDataSetChanged();

        RecyclerView.LayoutManager layoutManagerSuccess = new LinearLayoutManager(getActivity());
        rvSuccessGarage.setLayoutManager(layoutManagerSuccess);
        rvSuccessGarage.setNestedScrollingEnabled(false);
        SuccessGarageAdapter successGarageAdapter = new SuccessGarageAdapter(getActivity(), successHardCodeDataBaruMasukModelList);
        rvSuccessGarage.setAdapter(successGarageAdapter);
        successGarageAdapter.notifyDataSetChanged();

        RecyclerView.LayoutManager layoutManagerLost = new LinearLayoutManager(getActivity());
        rvLostGarage.setLayoutManager(layoutManagerLost);
        rvLostGarage.setNestedScrollingEnabled(false);
        LostGarageAdapter lostGarageAdapter = new LostGarageAdapter(getActivity(), lostHardCodeDataBaruMasukModelList);
        rvLostGarage.setAdapter(lostGarageAdapter);
        lostGarageAdapter.notifyDataSetChanged();

        swipeRefreshHome.setOnRefreshListener(() -> {
            swipeRefreshHome.setRefreshing(false);

        });

        return view;
    }

    private void setDataLive(){
        HardCodeDataBaruMasukModel 
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","04h 27m 03s");
        liveHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
    }
    private void setDataSuccess(){
        HardCodeDataBaruMasukModel 
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","04h 27m 03s");
        successHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel); 
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","04h 27m 03s");
        successHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
    }

    private void setDataLost(){
        HardCodeDataBaruMasukModel hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","04h 27m 03s");
        lostHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","04h 27m 03s");
        lostHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick({R.id.tv_filter, R.id.linear_dialog_filter})
    void tvFilterClick(){
        relativeBackgroundDialogFilter.setVisibility(View.VISIBLE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.relative_background_dialog_filter)
    void relativeBackgroundDialogFilterClick(){
        relativeBackgroundDialogFilter.setVisibility(View.GONE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_kendaraan_siap_diambil)
    void tvKendaraanSiapDiambilClick(){
        tvKendaraanSiapDiambil.setBackgroundResource(R.color.colorPrimaryTheme);
        tvKendaraanSiapDiambil.setTextColor(getResources().getColor(R.color.colorPrimaryWhite));
        tvPenawaranDiterima.setBackgroundResource(R.color.colorPrimaryWhite);
        tvPenawaranDiterima.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvPenawaranDitolak.setBackgroundResource(R.color.colorPrimaryWhite);
        tvPenawaranDitolak.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvPembayaranDiterima.setBackgroundResource(R.color.colorPrimaryWhite);
        tvPembayaranDiterima.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        relativeBackgroundDialogFilterClick();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_penawaran_diterima)
    void tvPenawaranDiterimaClick(){
        tvKendaraanSiapDiambil.setBackgroundResource(R.color.colorPrimaryWhite);
        tvKendaraanSiapDiambil.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvPenawaranDiterima.setBackgroundResource(R.color.colorPrimaryTheme);
        tvPenawaranDiterima.setTextColor(getResources().getColor(R.color.colorPrimaryWhite));
        tvPenawaranDitolak.setBackgroundResource(R.color.colorPrimaryWhite);
        tvPenawaranDitolak.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvPembayaranDiterima.setBackgroundResource(R.color.colorPrimaryWhite);
        tvPembayaranDiterima.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        relativeBackgroundDialogFilterClick();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_penawaran_ditolak)
    void tvPenawaranDitolakClick(){
        tvKendaraanSiapDiambil.setBackgroundResource(R.color.colorPrimaryWhite);
        tvKendaraanSiapDiambil.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvPenawaranDiterima.setBackgroundResource(R.color.colorPrimaryWhite);
        tvPenawaranDiterima.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvPenawaranDitolak.setBackgroundResource(R.color.colorPrimaryTheme);
        tvPenawaranDitolak.setTextColor(getResources().getColor(R.color.colorPrimaryWhite));
        tvPembayaranDiterima.setBackgroundResource(R.color.colorPrimaryWhite);
        tvPembayaranDiterima.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        relativeBackgroundDialogFilterClick();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_pembayaran_diterima)
    void tvPembayaranDiterimaClick(){
        tvKendaraanSiapDiambil.setBackgroundResource(R.color.colorPrimaryWhite);
        tvKendaraanSiapDiambil.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvPenawaranDiterima.setBackgroundResource(R.color.colorPrimaryWhite);
        tvPenawaranDiterima.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvPenawaranDitolak.setBackgroundResource(R.color.colorPrimaryWhite);
        tvPenawaranDitolak.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvPembayaranDiterima.setBackgroundResource(R.color.colorPrimaryTheme);
        tvPembayaranDiterima.setTextColor(getResources().getColor(R.color.colorPrimaryWhite));
        relativeBackgroundDialogFilterClick();
    }


}
