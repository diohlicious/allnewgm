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
import com.sip.grosirmobil.activity.ProfileActivity;
import com.sip.grosirmobil.activity.SearchActivity;
import com.sip.grosirmobil.adapter.LiveGarageAdapter;
import com.sip.grosirmobil.adapter.LostGarageAdapter;
import com.sip.grosirmobil.adapter.SuccessGarageAdapter;
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

public class GarasiFragment extends GrosirMobilFragment implements HomeView {
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.swipe_refresh_home) SwipeRefreshLayout swipeRefreshHome;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.nested_view) NestedScrollView nestedView;
    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.relative_background_dialog) RelativeLayout relativeHome;
//    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_filter) TextView tvFilter;
    
//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.iv_profile) CircleImageView ivProfile;
//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.linear_description) LinearLayout linearDescription;
//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.linear_search_and_live) LinearLayout linearSearchAndLive;
//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.tv_search) TextView tvSearch;
//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.tv_kendaraan_tayang) TextView tvKendaraanTayang;
//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.tv_see_all) TextView tvSeeAll;
//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.tv_live) TextView tvLive;
//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.tv_live_soon) TextView tvLiveSoon;
//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.tv_record) TextView tvRecord;
//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.tv_in_new) TextView tvInNew;
//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.relative_result_search) RelativeLayout relativeResultSearch;
//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.tv_result_search) TextView tvResultSearch;
//   

    
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_live_garage)
    RecyclerView rvLiveGarage;  
    
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_success_garage)
    RecyclerView rvSuccessGarage;
    
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_lost_garage)
    RecyclerView rvLostGarage;
    
    
    
    
    private GrosirMobilFunction grosirMobilFunction;
    private HomePresenter homePresenter;
    private List<HardCodeDataBaruMasukModel> liveHardCodeDataBaruMasukModelList = new ArrayList<>();
    private List<HardCodeDataBaruMasukModel> successHardCodeDataBaruMasukModelList = new ArrayList<>();
    private List<HardCodeDataBaruMasukModel> lostHardCodeDataBaruMasukModelList = new ArrayList<>();
    private boolean search = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_garasi, container, false);
        ButterKnife.bind(this, view);

        grosirMobilFunction = new GrosirMobilFunction(getActivity());
        homePresenter = new HomePresenterImp(getActivity(), this);

        setDataLive();
        setDataSuccess();
        setDataLost();

        RecyclerView.LayoutManager layoutManagerLive = new LinearLayoutManager(getActivity());
        rvLiveGarage.setLayoutManager(layoutManagerLive);
        rvLiveGarage.setNestedScrollingEnabled(false);
        LiveGarageAdapter liveGarageAdapter = new LiveGarageAdapter(getActivity(), liveHardCodeDataBaruMasukModelList);
        rvLiveGarage.setAdapter(liveGarageAdapter);
        liveGarageAdapter.notifyDataSetChanged();

        RecyclerView.LayoutManager layoutManagerScccess = new LinearLayoutManager(getActivity());
        rvSuccessGarage.setLayoutManager(layoutManagerScccess);
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

//    @SuppressLint("NonConstantResourceId")
//    @OnClick({R.id.iv_filter,R.id.tv_filter})
//    void setTvFilterOnClick(){
//        relativeHome.setVisibility(View.VISIBLE);
//    }
    
    
    @Override
    public void showDialogLoading() {

    }

    @Override
    public void hideDialogLoading() {

    }
    //Edit Profile
    
}
