package com.sip.grosirmobil.fragment.navigationmenu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.sip.grosirmobil.R;
import com.sip.grosirmobil.activity.FilterActivity;
import com.sip.grosirmobil.activity.MainActivity;
import com.sip.grosirmobil.activity.ProfileActivity;
import com.sip.grosirmobil.activity.SearchActivity;
import com.sip.grosirmobil.adapter.BannerGalleryAdapter;
import com.sip.grosirmobil.adapter.LiveAdapter;
import com.sip.grosirmobil.adapter.LiveSoonAdapter;
import com.sip.grosirmobil.adapter.RecordAdapter;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.implement.HomePresenterImp;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.base.presenter.HomePresenter;
import com.sip.grosirmobil.base.util.AutoScrollViewPager;
import com.sip.grosirmobil.base.util.GrosirMobilFragment;
import com.sip.grosirmobil.base.view.HomeView;
import com.sip.grosirmobil.cloud.config.model.HardCodeDataBaruMasukModel;
import com.sip.grosirmobil.cloud.config.model.HardCodeDataModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import me.relex.circleindicator.CircleIndicator;

import static android.app.Activity.RESULT_OK;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.FILTER_REQUEST;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.FROM_PAGE;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.SEARCH_REQUEST;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setStatusBarFragment;

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
    @BindView(R.id.view_pager_home) AutoScrollViewPager viewPagerHome;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.circle_indicator) CircleIndicator circleIndicator;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_search_and_filter_show) LinearLayout linearSearchAndFilterShow;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_search_and_live) LinearLayout linearSearchAndLive;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_search) TextView tvSearch;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_result_title_content) LinearLayout linearResultTitleContent;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_result_title_content) TextView tvResultTitleContent;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_live) TextView tvLive;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_live_soon) TextView tvLiveSoon;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_record) TextView tvRecord;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_title_content) LinearLayout linearTitleContent;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_title_content) TextView tvTitleContent;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.iv_filter_title_content) ImageView ivFilterTitleContent;
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
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_all_unit) LinearLayout linearAllUnit;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_all_unit) TextView tvAllUnit;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_search_result_all_unit) TextView tvSearchResultAllUnit;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.card_search_result) CardView cardSearchResult;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_search_result) TextView tvSearchResult;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.iv_clear) ImageView ivClear;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.relative_background_dialog_sort) RelativeLayout relativeBackgroundDialogSort;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_waktu_penawaran_cepat_ke_lama) TextView tvWaktuPenawaranCepatKeLama;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_waktu_penawaran_lama_ke_cepat) TextView tvWaktuPenawaranLamaKeCepat;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_abjad_lokasi_warehouse_a_z) TextView tvAbjadLokasiWarehouseAZ;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_abjad_lokasi_warehouse_z_a) TextView tvAbjadLokasiWarehouseZA;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_bottom_price_terendah_ke_tertinggi) TextView tvBottomPriceTerendahKeTertinggi;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_bottom_price_tertinggi_ke_terendah) TextView tvBottomPriceTertinggiKeTerendah;


    private GrosirMobilFunction grosirMobilFunction;
    private GrosirMobilPreference grosirMobilPreference;
    private HomePresenter homePresenter;
    private List<HardCodeDataBaruMasukModel> liveHardCodeDataBaruMasukModelList = new ArrayList<>();
    private List<HardCodeDataBaruMasukModel> liveSoonHardCodeDataBaruMasukModelList = new ArrayList<>();
    private List<HardCodeDataBaruMasukModel> recordHardCodeDataBaruMasukModelList = new ArrayList<>();
    private List<HardCodeDataModel> hardCodeDataModelList = new ArrayList<>();
    private boolean search = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setStatusBarFragment(getActivity());
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        grosirMobilFunction = new GrosirMobilFunction(getActivity());
        grosirMobilPreference = new GrosirMobilPreference(getActivity());
        homePresenter = new HomePresenterImp(getActivity(), this);

        setDataBanner();
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

        viewPagerHome.startAutoScroll();
        viewPagerHome.setInterval(3000);
        viewPagerHome.setCycle(true);
        viewPagerHome.setStopScrollWhenTouch(true);
        BannerGalleryAdapter viewPagerAdapter = new BannerGalleryAdapter(getActivity(), hardCodeDataModelList);
        viewPagerHome.setAdapter(viewPagerAdapter);
        circleIndicator.setViewPager(viewPagerHome);

        try {
            CircularProgressDrawable circularProgressDrawable = new  CircularProgressDrawable(getActivity());
            circularProgressDrawable.setStrokeWidth(5f);
            circularProgressDrawable.setCenterRadius(30f);
            circularProgressDrawable.start();
            Glide.with(this)
                    .load(grosirMobilPreference.getDataLogin().getLoggedInUserResponse().getUserResponse().getProfilePhotoUrl())
                    .apply(new RequestOptions()
                            .placeholder(circularProgressDrawable)
//                        .error(R.drawable.ic_image_empty)
                            .dontAnimate()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true))
                    .into(ivProfile);
        }catch (Exception e){
            GrosirMobilLog.printStackTrace(e);
        }

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
        linearTitleContent.setVisibility(View.VISIBLE);
        relativeResultSearch.setVisibility(View.GONE);
        tvLiveClick();
    }

    private void setDataBanner(){
        HardCodeDataModel hardCodeDataModel = new HardCodeDataModel("Dapatkan promo akhir tahun dengan berbagai\nmacam unit yang ada!","Promo\nAkhir 2020");
        hardCodeDataModelList.add(hardCodeDataModel);
        hardCodeDataModel = new HardCodeDataModel("Dapatkan promo akhir tahun dengan berbagai\nmacam unit yang ada!","Promo\nAkhir 2020");
        hardCodeDataModelList.add(hardCodeDataModel);
        hardCodeDataModel = new HardCodeDataModel("Dapatkan promo akhir tahun dengan berbagai\nmacam unit yang ada!","Promo\nAkhir 2020");
        hardCodeDataModelList.add(hardCodeDataModel);
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

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_filter_title_content)
    void ivFilterTitleContentClick(){
        Intent intent = new Intent(getActivity(), FilterActivity.class);
        intent.putExtra(FROM_PAGE, "BEFORE_SEARCH");
        startActivityForResult(intent, FILTER_REQUEST);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick({R.id.iv_sort_title_content,R.id.iv_sort_search})
    void ivSortTitleContentClick(){
        ((MainActivity)getActivity()).linearMenuNavBar.setVisibility(View.GONE);
        relativeBackgroundDialogSort.setVisibility(View.VISIBLE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.relative_background_dialog_sort)
    void relativeBackgroundDialogSortClick(){
        ((MainActivity)getActivity()).linearMenuNavBar.setVisibility(View.VISIBLE);
        relativeBackgroundDialogSort.setVisibility(View.GONE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_waktu_penawaran_cepat_ke_lama)
    void tvWaktuPenawaranCepatKeLamaClick(){
        tvWaktuPenawaranCepatKeLama.setBackgroundResource(R.color.colorPrimaryTheme);
        tvWaktuPenawaranCepatKeLama.setTextColor(getResources().getColor(R.color.colorPrimaryWhite));
        tvBottomPriceTertinggiKeTerendah.setBackgroundResource(R.color.colorPrimaryWhite);
        tvBottomPriceTertinggiKeTerendah.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvBottomPriceTerendahKeTertinggi.setBackgroundResource(R.color.colorPrimaryWhite);
        tvBottomPriceTerendahKeTertinggi.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvAbjadLokasiWarehouseZA.setBackgroundResource(R.color.colorPrimaryWhite);
        tvAbjadLokasiWarehouseZA.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvAbjadLokasiWarehouseAZ.setBackgroundResource(R.color.colorPrimaryWhite);
        tvAbjadLokasiWarehouseAZ.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvWaktuPenawaranLamaKeCepat.setBackgroundResource(R.color.colorPrimaryWhite);
        tvWaktuPenawaranLamaKeCepat.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        relativeBackgroundDialogSortClick();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_waktu_penawaran_lama_ke_cepat)
    void tvWaktuPenawaranLamaKeCepatClick(){
        tvWaktuPenawaranLamaKeCepat.setBackgroundResource(R.color.colorPrimaryTheme);
        tvWaktuPenawaranLamaKeCepat.setTextColor(getResources().getColor(R.color.colorPrimaryWhite));
        tvBottomPriceTertinggiKeTerendah.setBackgroundResource(R.color.colorPrimaryWhite);
        tvBottomPriceTertinggiKeTerendah.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvBottomPriceTerendahKeTertinggi.setBackgroundResource(R.color.colorPrimaryWhite);
        tvBottomPriceTerendahKeTertinggi.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvAbjadLokasiWarehouseZA.setBackgroundResource(R.color.colorPrimaryWhite);
        tvAbjadLokasiWarehouseZA.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvAbjadLokasiWarehouseAZ.setBackgroundResource(R.color.colorPrimaryWhite);
        tvAbjadLokasiWarehouseAZ.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvWaktuPenawaranCepatKeLama.setBackgroundResource(R.color.colorPrimaryWhite);
        tvWaktuPenawaranCepatKeLama.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        relativeBackgroundDialogSortClick();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_abjad_lokasi_warehouse_a_z)
    void tvAbjadLokasiWarehouseAZClick(){
        tvAbjadLokasiWarehouseAZ.setBackgroundResource(R.color.colorPrimaryTheme);
        tvAbjadLokasiWarehouseAZ.setTextColor(getResources().getColor(R.color.colorPrimaryWhite));
        tvBottomPriceTertinggiKeTerendah.setBackgroundResource(R.color.colorPrimaryWhite);
        tvBottomPriceTertinggiKeTerendah.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvBottomPriceTerendahKeTertinggi.setBackgroundResource(R.color.colorPrimaryWhite);
        tvBottomPriceTerendahKeTertinggi.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvAbjadLokasiWarehouseZA.setBackgroundResource(R.color.colorPrimaryWhite);
        tvAbjadLokasiWarehouseZA.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvWaktuPenawaranLamaKeCepat.setBackgroundResource(R.color.colorPrimaryWhite);
        tvWaktuPenawaranLamaKeCepat.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvWaktuPenawaranCepatKeLama.setBackgroundResource(R.color.colorPrimaryWhite);
        tvWaktuPenawaranCepatKeLama.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        relativeBackgroundDialogSortClick();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_abjad_lokasi_warehouse_z_a)
    void tvAbjadLokasiWarehouseZAClick(){
        tvAbjadLokasiWarehouseZA.setBackgroundResource(R.color.colorPrimaryTheme);
        tvAbjadLokasiWarehouseZA.setTextColor(getResources().getColor(R.color.colorPrimaryWhite));
        tvBottomPriceTertinggiKeTerendah.setBackgroundResource(R.color.colorPrimaryWhite);
        tvBottomPriceTertinggiKeTerendah.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvBottomPriceTerendahKeTertinggi.setBackgroundResource(R.color.colorPrimaryWhite);
        tvBottomPriceTerendahKeTertinggi.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvAbjadLokasiWarehouseAZ.setBackgroundResource(R.color.colorPrimaryWhite);
        tvAbjadLokasiWarehouseAZ.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvWaktuPenawaranLamaKeCepat.setBackgroundResource(R.color.colorPrimaryWhite);
        tvWaktuPenawaranLamaKeCepat.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvWaktuPenawaranCepatKeLama.setBackgroundResource(R.color.colorPrimaryWhite);
        tvWaktuPenawaranCepatKeLama.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        relativeBackgroundDialogSortClick();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_bottom_price_terendah_ke_tertinggi)
    void tvBottomPriceTerendahKeTertinggiClick(){
        tvBottomPriceTerendahKeTertinggi.setBackgroundResource(R.color.colorPrimaryTheme);
        tvBottomPriceTerendahKeTertinggi.setTextColor(getResources().getColor(R.color.colorPrimaryWhite));
        tvBottomPriceTertinggiKeTerendah.setBackgroundResource(R.color.colorPrimaryWhite);
        tvBottomPriceTertinggiKeTerendah.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvAbjadLokasiWarehouseZA.setBackgroundResource(R.color.colorPrimaryWhite);
        tvAbjadLokasiWarehouseZA.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvAbjadLokasiWarehouseAZ.setBackgroundResource(R.color.colorPrimaryWhite);
        tvAbjadLokasiWarehouseAZ.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvWaktuPenawaranLamaKeCepat.setBackgroundResource(R.color.colorPrimaryWhite);
        tvWaktuPenawaranLamaKeCepat.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvWaktuPenawaranCepatKeLama.setBackgroundResource(R.color.colorPrimaryWhite);
        tvWaktuPenawaranCepatKeLama.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        relativeBackgroundDialogSortClick();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_bottom_price_tertinggi_ke_terendah)
    void tvBottomPriceTertinggiKeTerendahClick(){
        tvBottomPriceTertinggiKeTerendah.setBackgroundResource(R.color.colorPrimaryTheme);
        tvBottomPriceTertinggiKeTerendah.setTextColor(getResources().getColor(R.color.colorPrimaryWhite));
        tvBottomPriceTerendahKeTertinggi.setBackgroundResource(R.color.colorPrimaryWhite);
        tvBottomPriceTerendahKeTertinggi.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvAbjadLokasiWarehouseZA.setBackgroundResource(R.color.colorPrimaryWhite);
        tvAbjadLokasiWarehouseZA.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvAbjadLokasiWarehouseAZ.setBackgroundResource(R.color.colorPrimaryWhite);
        tvAbjadLokasiWarehouseAZ.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvWaktuPenawaranLamaKeCepat.setBackgroundResource(R.color.colorPrimaryWhite);
        tvWaktuPenawaranLamaKeCepat.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvWaktuPenawaranCepatKeLama.setBackgroundResource(R.color.colorPrimaryWhite);
        tvWaktuPenawaranCepatKeLama.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        relativeBackgroundDialogSortClick();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_clear)
    void ivClearClick(){
        setUiReset();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SEARCH_REQUEST){
            if(resultCode==RESULT_OK){
                String keySearch = data.getStringExtra("keySearch");
                tvSearchResult.setText(keySearch);
                search = true;
                relativeHome.setVisibility(View.GONE);
                linearSearchAndFilterShow.setVisibility(View.VISIBLE);
                linearSearchAndLive.setVisibility(View.GONE);
                linearTitleContent.setVisibility(View.GONE);
                relativeResultSearch.setVisibility(View.VISIBLE);
                cardSearchResult.setVisibility(View.VISIBLE);
                linearAllUnit.setVisibility(View.GONE);

            }
        }else if(requestCode == FILTER_REQUEST){
            if(resultCode==RESULT_OK){
                if(search){
                    cardSearchResult.setVisibility(View.VISIBLE);
                    linearAllUnit.setVisibility(View.GONE);
                }else {
                    cardSearchResult.setVisibility(View.GONE);
                    linearAllUnit.setVisibility(View.VISIBLE);
                }
                relativeHome.setVisibility(View.GONE);
                linearSearchAndFilterShow.setVisibility(View.VISIBLE);
                linearSearchAndLive.setVisibility(View.GONE);
                linearTitleContent.setVisibility(View.GONE);
                relativeResultSearch.setVisibility(View.VISIBLE);
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
            linearTitleContent.setVisibility(View.GONE);
            linearSearchAndLive.setVisibility(View.GONE);
        }else {
            linearTitleContent.setVisibility(View.VISIBLE);
            linearSearchAndLive.setVisibility(View.VISIBLE);
        }
        nestedView.setBackgroundResource(R.color.colorPrimaryWhite);
        rvLive.setVisibility(View.VISIBLE);
        rvLiveSoon.setVisibility(View.GONE);
        rvRecord.setVisibility(View.GONE);
        tvResultTitleContent.setText("Ada 32 kendaraan Live!");
        linearResultTitleContent.setBackgroundResource(R.drawable.design_card_live);
        tvResultTitleContent.setTextColor(getResources().getColor(R.color.colorPrimaryWhite));
        tvTitleContent.setText("Baru Masuk");
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
            linearTitleContent.setVisibility(View.GONE);
            linearSearchAndLive.setVisibility(View.GONE);
        }else {
            linearTitleContent.setVisibility(View.VISIBLE);
            linearSearchAndLive.setVisibility(View.VISIBLE);
        }
        nestedView.setBackgroundResource(R.color.colorPrimaryWhite);
        rvLive.setVisibility(View.GONE);
        rvLiveSoon.setVisibility(View.VISIBLE);
        rvRecord.setVisibility(View.GONE);
        tvResultTitleContent.setText("Ada 32 kendaraan yang Akan Tayang!");
        linearResultTitleContent.setBackgroundResource(R.drawable.design_card_soon);
        tvResultTitleContent.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvTitleContent.setText("Segera Tayang");
        tvLive.setBackgroundResource(R.drawable.design_line);
        tvLive.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvLiveSoon.setBackgroundResource(R.drawable.design_line_selected_soon);
        tvLiveSoon.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
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
        linearTitleContent.setVisibility(View.GONE);
        tvLive.setBackgroundResource(R.drawable.design_line);
        tvLive.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvLiveSoon.setBackgroundResource(R.drawable.design_line);
        tvLiveSoon.setTextColor(getResources().getColor(R.color.colorPrimaryFont));
        tvRecord.setBackgroundResource(R.drawable.design_line_selected);
        tvRecord.setTextColor(getResources().getColor(R.color.colorPrimaryWhite));
    }

    @OnClick(R.id.iv_filter_search)
    void relativeFilterClick(){
        Intent intent = new Intent(getActivity(), FilterActivity.class);
        intent.putExtra(FROM_PAGE, "AFTER_SEARCH");
        startActivityForResult(intent, FILTER_REQUEST);
    }


    @Override
    public void showDialogLoading() {

    }

    @Override
    public void hideDialogLoading() {

    }
//Edit Profile
    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_profile)
    void ivProfileClick(){
        Intent intent = new Intent (getActivity(), ProfileActivity.class);
        startActivityForResult(intent, FILTER_REQUEST);
    }
}
