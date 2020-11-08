package com.sip.grosirmobil.fragment.navigationmenu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.activity.FilterActivity;
import com.sip.grosirmobil.activity.SearchActivity;
import com.sip.grosirmobil.adapter.LiveAdapter;
import com.sip.grosirmobil.adapter.LiveSoonAdapter;
import com.sip.grosirmobil.adapter.RecordAdapter;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.implement.HomePresenterImp;
import com.sip.grosirmobil.base.presenter.HomePresenter;
import com.sip.grosirmobil.base.util.GrosirMobilFragment;
import com.sip.grosirmobil.base.view.HomeView;
import com.sip.grosirmobil.cloud.config.model.HardCodeDataBaruMasukModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.FILTER_REQUEST;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.SEARCH_REQUEST;

/**
 * A simple {@link androidx.fragment.app.Fragment} subclass.
 */

public class HomeFragment extends GrosirMobilFragment implements HomeView {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.swipe_refresh_home) SwipeRefreshLayout swipeRefreshHome;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.nested_view) NestedScrollView nestedView;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.relative_home) RelativeLayout relativeHome;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.iv_profile) CircleImageView ivProfile;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_search_and_filter_show) LinearLayout linearSearchAndFilterShow;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_search_and_live) LinearLayout linearSearchAndLive;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_search) TextView tvSearch;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_kendaraan_tayang) TextView tvKendaraanTayang;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_see_all) TextView tvSeeAll;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_live) TextView tvLive;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_live_soon) TextView tvLiveSoon;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_record) TextView tvRecord;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_in_new) TextView tvInNew;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.relative_result_search) RelativeLayout relativeResultSearch;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_result_search) TextView tvResultSearch;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_live) RecyclerView rvLive;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_live_soon) RecyclerView rvLiveSoon;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_record) RecyclerView rvRecord;

    private GrosirMobilFunction grosirMobilFunction;
    private HomePresenter homePresenter;
    private List<HardCodeDataBaruMasukModel> liveHardCodeDataBaruMasukModelList = new ArrayList<>();
    private List<HardCodeDataBaruMasukModel> liveSoonHardCodeDataBaruMasukModelList = new ArrayList<>();
    private List<HardCodeDataBaruMasukModel> recordHardCodeDataBaruMasukModelList = new ArrayList<>();
    private boolean search = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        grosirMobilFunction = new GrosirMobilFunction(getActivity());
        homePresenter = new HomePresenterImp(getActivity(), this);

        setDataLive();
        setDataLiveSoon();
        setDataRecord();

        RecyclerView.LayoutManager layoutManagerLive = new LinearLayoutManager(getActivity());
        rvLive.setLayoutManager(layoutManagerLive);
        rvLive.setNestedScrollingEnabled(false);
        LiveAdapter liveAdapter = new LiveAdapter(getActivity(), liveHardCodeDataBaruMasukModelList);
        rvLive.setAdapter(liveAdapter);
        liveAdapter.notifyDataSetChanged();

        RecyclerView.LayoutManager layoutManagerLiveSoon = new LinearLayoutManager(getActivity());
        rvLiveSoon.setLayoutManager(layoutManagerLiveSoon);
        rvLiveSoon.setNestedScrollingEnabled(false);
        LiveSoonAdapter liveSoonAdapter = new LiveSoonAdapter(getActivity(), liveSoonHardCodeDataBaruMasukModelList);
        rvLiveSoon.setAdapter(liveSoonAdapter);
        liveSoonAdapter.notifyDataSetChanged();

        RecyclerView.LayoutManager layoutManagerRecord = new LinearLayoutManager(getActivity());
        rvRecord.setLayoutManager(layoutManagerRecord);
        rvRecord.setNestedScrollingEnabled(false);
        RecordAdapter recordAdapter = new RecordAdapter(getActivity(), recordHardCodeDataBaruMasukModelList);
        rvRecord.setAdapter(recordAdapter);
        recordAdapter.notifyDataSetChanged();

        setUiReset();
        swipeRefreshHome.setOnRefreshListener(() -> {
            swipeRefreshHome.setRefreshing(false);
            setUiReset();
        });
        return view;
    }

    private void setUiReset(){
        search = false;
        relativeHome.setVisibility(View.VISIBLE);
        linearSearchAndFilterShow.setVisibility(View.GONE);
        linearSearchAndLive.setVisibility(View.VISIBLE);
        tvInNew.setVisibility(View.VISIBLE);
        relativeResultSearch.setVisibility(View.GONE);
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

    private void setDataLiveSoon(){
        HardCodeDataBaruMasukModel hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","04h 27m 03s");
        liveSoonHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","04h 27m 03s");
        liveSoonHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","04h 27m 03s");
        liveSoonHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","04h 27m 03s");
        liveSoonHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","04h 27m 03s");
        liveSoonHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","04h 27m 03s");
        liveSoonHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","04h 27m 03s");
        liveSoonHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","04h 27m 03s");
        liveSoonHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","04h 27m 03s");
        liveSoonHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","04h 27m 03s");
        liveSoonHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
    }

    private void setDataRecord(){
        HardCodeDataBaruMasukModel hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","Penawaran Selesai");
        recordHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","Penawaran Selesai");
        recordHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","Penawaran Selesai");
        recordHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","Penawaran Selesai");
        recordHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","Penawaran Selesai");
        recordHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","Penawaran Selesai");
        recordHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","Penawaran Selesai");
        recordHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","Penawaran Selesai");
        recordHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","Penawaran Selesai");
        recordHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
        hardCodeDataBaruMasukModel = new HardCodeDataBaruMasukModel("Masserati DSE AT 2015","NI 21231324","Jakarta","Rp 111.000.000","Penawaran Selesai");
        recordHardCodeDataBaruMasukModelList.add(hardCodeDataBaruMasukModel);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_search)
    void tvSearchClick(){
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        startActivityForResult(intent, SEARCH_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SEARCH_REQUEST){
            if(resultCode==RESULT_OK){
                search = true;
                relativeHome.setVisibility(View.GONE);
                linearSearchAndFilterShow.setVisibility(View.VISIBLE);
                linearSearchAndLive.setVisibility(View.GONE);
                tvInNew.setVisibility(View.GONE);
                relativeResultSearch.setVisibility(View.VISIBLE);
            }
        }else if(requestCode == FILTER_REQUEST){
            if(resultCode==RESULT_OK){
                Toast.makeText(getActivity(), "Set Filter", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_back)
    void ivBackClick(){
        setUiReset();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_live)
    void tvLiveClick(){
        if(search){
            linearSearchAndLive.setVisibility(View.GONE);
        }else {
            linearSearchAndLive.setVisibility(View.VISIBLE);
        }
        nestedView.setBackgroundResource(R.color.colorPrimaryWhite);
        rvLive.setVisibility(View.VISIBLE);
        rvLiveSoon.setVisibility(View.GONE);
        rvRecord.setVisibility(View.GONE);
        tvLive.setBackgroundResource(R.drawable.design_line_selected_live);
        tvLive.setTextColor(getResources().getColor(R.color.colorPrimaryWhite));
        tvLiveSoon.setBackgroundResource(R.drawable.design_line);
        tvLiveSoon.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvRecord.setBackgroundResource(R.drawable.design_line);
        tvRecord.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_live_soon)
    void tvLiveSoonClick(){
        if(search){
            linearSearchAndLive.setVisibility(View.GONE);
        }else {
            linearSearchAndLive.setVisibility(View.VISIBLE);
        }
        nestedView.setBackgroundResource(R.color.colorPrimaryWhite);
        rvLive.setVisibility(View.GONE);
        rvLiveSoon.setVisibility(View.VISIBLE);
        rvRecord.setVisibility(View.GONE);
        tvLive.setBackgroundResource(R.drawable.design_line);
        tvLive.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvLiveSoon.setBackgroundResource(R.drawable.design_line_selected);
        tvLiveSoon.setTextColor(getResources().getColor(R.color.colorPrimaryWhite));
        tvRecord.setBackgroundResource(R.drawable.design_line);
        tvRecord.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_record)
    void tvRecordClick(){
        linearSearchAndLive.setVisibility(View.GONE);
        nestedView.setBackgroundResource(R.color.colorBackgroundHome);
        rvLive.setVisibility(View.GONE);
        rvLiveSoon.setVisibility(View.GONE);
        rvRecord.setVisibility(View.VISIBLE);
        tvLive.setBackgroundResource(R.drawable.design_line);
        tvLive.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvLiveSoon.setBackgroundResource(R.drawable.design_line);
        tvLiveSoon.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvRecord.setBackgroundResource(R.drawable.design_line_selected);
        tvRecord.setTextColor(getResources().getColor(R.color.colorPrimaryWhite));
    }

    @OnClick({R.id.relative_filter,R.id.tv_filter_ket, R.id.tv_filter})
    void relativeFilterClick(){
        Intent intent = new Intent(getActivity(), FilterActivity.class);
        startActivityForResult(intent, FILTER_REQUEST);
    }


    @Override
    public void showDialogLoading() {

    }

    @Override
    public void hideDialogLoading() {

    }

}
