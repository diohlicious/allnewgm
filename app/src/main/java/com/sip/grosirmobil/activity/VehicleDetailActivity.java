package com.sip.grosirmobil.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.BrokenImageAdapter;
import com.sip.grosirmobil.adapter.ImageVehicleDetailAdapter;
import com.sip.grosirmobil.adapter.VehicleDescriptionAdapter;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;
import com.sip.grosirmobil.cloud.config.model.HardCodeDataModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.FROM_PAGE;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.adjustFontScale;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setCurrencyFormat;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setStatusBarOnBoarding;

public class VehicleDetailActivity extends GrosirMobilActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_title_vehicle) TextView tvTitleVehicle;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_plat_number) TextView tvPlatNumber;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_city) TextView tvCity;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_harga_awal) TextView tvHargaAwal;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_harga_sekarang) TextView tvHargaSekarang;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_initial_name) TextView tvInitialName;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.circle_image_view_item) CircleImageView circleImageViewItem;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_description) LinearLayout linearDescription;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.iv_timer) ImageView ivTimer;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_description) TextView tvDescription;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_image_car) RecyclerView rvImageCar;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_description) RecyclerView rvDescription;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_car_data) TextView tvCarData;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_car_data) LinearLayout linearCarData;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_id_number) TextView tvIdNumber;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_score) TextView tvScore;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_plat_number_car_data) TextView tvPlatNumberCarData;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_year) TextView tvYear;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_transmition) TextView tvTransmition;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_color) TextView tvColor;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_km) TextView tvKM;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_kepemilikan) TextView tvKepemilikan;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_location) TextView tvLocation;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_stnk) TextView tvSTNK;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_masa_berlaku_pajak) TextView tvMasaBerlakuPajak;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_masa_berlaku_stnk) TextView tvMasaBerlakuSTNK;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_body) TextView tvBody;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_body) LinearLayout linearBody;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_power_window) TextView tvPowerWindow;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_electric_mirror) TextView tvElectricMirror;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_cat_body) TextView tvCatBody;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_panel_pintu) TextView tvPanelPintu;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_pilar_pintu) TextView tvPilarPintu;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_handle_pintu_body) TextView tvHandlePintuBody;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_lisplang) TextView tvLisplang;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_fender_belakang) TextView tvFenderBelakang;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_bemper_belakang) TextView tvBemperBelakang;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_pintu_bagasi) TextView tvPintuBagasi;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_fender_depan) TextView tvFenderDepan;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_bember_depan) TextView tvBemperDepan;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_grill) TextView tvGrill;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_kap_mesin) TextView tvKapMesin;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_panel_atap) TextView tvPanelAtap;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_kaca_depan) TextView tvKacaDepan;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_kaca_jendela) TextView tvKacaJendela;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_spion) TextView tvSpion;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_kaca_belakang) TextView tvKacaBelakang;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_lampu_depan) TextView tvLampuDepan;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_lampu_belakang) TextView tvLampuBelakang;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_ban) TextView tvBan;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_velg) TextView tvVelg;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_disc_brake) TextView tvDiscBrake;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_brake_pad) TextView tvBrakePad;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_master_rem) TextView tvMasterRem;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_shockbreaker) TextView tvShockbreaker;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_link_stabilizier) TextView tvLinkStabilizier;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_rack_setir) TextView tvRackSetir;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_knalpot) TextView tvKnalpot;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_upper_lower_arm) TextView tvUpperLowerArm;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_faktur_asli) TextView tvFakturAsli;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_nik_asli) TextView tvNikAsli;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_bebas_tabrakan_besar) TextView tvBebasTabrakanBesar;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_interior) TextView tvInterior;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_interior) LinearLayout linearInterior;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_airbag) TextView tvAirbag;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_central_lock) TextView tvCentralLock;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_sistem_audio) TextView tvSistemAudio;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_panel_indikator) TextView tvPanelIndikator;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_setir) TextView tvSetir;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_panel_dashboard) TextView tvPanelDashboard;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_rem_tangan) TextView tvRemTangan;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_pembuka_kap_mesin) TextView tvPembukaKapMesin;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_switch_lampu) TextView tvSwitchLampu;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_switch_wiper) TextView tvSwitchWiper;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_klakson) TextView tvKlakson;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_jok) TextView tvJok;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_console_box) TextView tvConsoleBox;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_door_trim) TextView tvDoorTrim;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_kaca_film) TextView tvKacaFilm;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_handle_pintu_interior) TextView tvHandlePintuInterior;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_plafon) TextView tvPlafon;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_karpet_dasar) TextView tvKarpetDasar;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_buku_service) TextView tvBukuService;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_buku_manual) TextView tvBukuManual;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_dongkrak_dan_kunci) TextView tvDongkrakDanKunci;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_tidak_ada_indikasi_banjir) TextView tvTidakAdaIndikasiBanjir;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_engine) TextView tvEngine;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_engine) LinearLayout linearEngine;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_asap_knalpot) TextView tvAsapKnalpot;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_sistem_ac_engine) TextView tvSistemAcEngine;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_rem_abs_engine) TextView tvRemAbsEngine;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_power_steering) TextView tvPowerSteering;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_oli_mesin) TextView tvOliMesin;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_oli_transmisi_at) TextView tvOliTransmisiAT;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_oli_rem) TextView tvOliRem;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_oli_power_steering) TextView tvOliPowerSteering;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_air_radiator) TextView tvAirRadiator;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_mesin_bebas_rembes) TextView tvMesinBebasRembes;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_pengisian_aki) TextView tvPengisianAki;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_dinamo_ampere) TextView tvDinamoAmpere;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_pompa_power_steering) TextView tvPompaPowerSteering;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_kompresor_ac) TextView tvKompresorAC;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_water_pump) TextView tvWaterPump;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_belt) TextView tvBelt;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_fan) TextView tvFan;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_radiator) TextView tvRadiator;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_suara_mesin_normal) TextView tvSuaraMesinNormal;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_getaran_mesin_normal) TextView tvGetaranMesinNormal;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_starter_mesin_normal) TextView tvStarterMesinNormal;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_rpm_stabil) TextView tvRpmStabil;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_setir_tidak_baret_bunyi) TextView tvSetirTidakBaretBunyi;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_ketebalan_kampas_kopling) TextView tvKetebalanKampasKopling;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_other) TextView tvOther;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_other) LinearLayout linearOther;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_gesekan_no_rangka) TextView tvGesekanNoRangka;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_sistem_ac_other) TextView tvSistemAcOther;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_rem_abs_other) TextView tvRemAbsOther;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_broken_image) TextView tvBrokenImage;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_broken_image) LinearLayout linearBrokenImage;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_broker_image) RecyclerView rvBrokenImage;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_nego) Button btnNego;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.relative_background_dialog) RelativeLayout relativeBackgroundDialog;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_hour_first) TextView tvHourFirst;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_hour_second) TextView tvHourSecond;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_minute_first) TextView tvMinuteFirst;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_minute_second) TextView tvMinuteSecond;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_second_first) TextView tvSecondFirst;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_second_second) TextView tvSecondSecond;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rb_500_ribu) RadioButton rb500Ribu;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rb_1_jt) RadioButton rb1Jt;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rb_2_jt) RadioButton rb2Jt;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.iv_min) ImageView ivMin;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_input_price_nego) TextView tvInputPriceNego;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.iv_clear_price) ImageView ivClearPrice;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.iv_plus) ImageView ivPlus;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_nego_dialog) Button btnNegoDialog;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_buy_now_dialog) Button btnBuyNowDialog;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_bid) RecyclerView rvBid;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.relative_background_dialog_confirm_nego) RelativeLayout relativeBackgroundDialogConfirmNego;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.iv_close_dialog_confirm_nego) ImageView ivCloseDialogConfirmNego;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_message_nego) TextView tvMessageNego;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_nego_confirm_dialog) Button btnNegoConfirmDialog;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.relative_background_dialog_success_nego) RelativeLayout relativeBackgroundDialogSuccessNego;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_message_success_nego) TextView tvMessageSuccessNego;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.relative_background_dialog_confirm_buy_now) RelativeLayout relativeBackgroundDialogConfirmBuyNow;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.iv_close_dialog_confirm_buy_now) ImageView ivCloseDialogConfirmBuyNow;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_message_buy_now) TextView tvMessageBuyNow;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_confirm_buy_now_dialog) Button btnConfirmBuyNowDialog;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.relative_background_dialog_success_buy_now) RelativeLayout relativeBackgroundDialogSuccessBuyNow;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_message_success_buy_now) TextView tvMessageSuccessBuyNow;

    private GrosirMobilPreference grosirMobilPreference;
    private GrosirMobilFunction grosirMobilFunction;

    private boolean carData = false;
    private boolean body = false;
    private boolean interior = false;
    private boolean engine = false;
    private boolean other = false;
    private boolean brokenImage = false;
    private long negoPrice, lastPrice, bidNego;

    private List<HardCodeDataModel> hardCodeDataImageVehicleDetailModelList = new ArrayList<>();
    private List<HardCodeDataModel> hardCodeDataDescriptionModelList = new ArrayList<>();
    private List<HardCodeDataModel> hardCodeDataBrokenImageModelList = new ArrayList<>();
    private Context context;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarOnBoarding(this);
        setContentView(R.layout.activity_vehicle_detail);
        adjustFontScale(this, getResources().getConfiguration());
        ButterKnife.bind(this);
        context = this.getApplicationContext();
        grosirMobilPreference = new GrosirMobilPreference(this);
        grosirMobilFunction = new GrosirMobilFunction(this);

        if(getIntent().getStringExtra(FROM_PAGE).equals("LIVE")){
            btnNego.setVisibility(View.VISIBLE);
        }else {
            btnNego.setVisibility(View.GONE);
        }
        setDataHardCodeImageVehicleDetail();
        setDataHardCodeDescription();
        setDataHardCodeBrokenImage();

        LinearLayoutManager layoutManagerImageCar = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvImageCar.setLayoutManager(layoutManagerImageCar);
        rvImageCar.setItemAnimator(new DefaultItemAnimator());
        rvImageCar.setNestedScrollingEnabled(false);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(rvImageCar);
        ImageVehicleDetailAdapter imageVehicleDetailAdapter = new ImageVehicleDetailAdapter(this, hardCodeDataImageVehicleDetailModelList);
        rvImageCar.setAdapter(imageVehicleDetailAdapter);
        imageVehicleDetailAdapter.notifyDataSetChanged();

        RecyclerView.LayoutManager layoutManagerDescription = new LinearLayoutManager(this);
        rvDescription.setLayoutManager(layoutManagerDescription);
        VehicleDescriptionAdapter vehicleDescriptionAdapter = new VehicleDescriptionAdapter(this, hardCodeDataDescriptionModelList);
        rvDescription.setAdapter(vehicleDescriptionAdapter);
        vehicleDescriptionAdapter.notifyDataSetChanged();

        GridLayoutManager gridLayoutManagerBrokenImage = new GridLayoutManager(this, 2);
        rvBrokenImage.setLayoutManager(gridLayoutManagerBrokenImage);
        rvBrokenImage.setItemAnimator(new DefaultItemAnimator());
        rvBrokenImage.setNestedScrollingEnabled(false);
        BrokenImageAdapter brokenImageAdapter = new BrokenImageAdapter(this, hardCodeDataBrokenImageModelList);
        rvBrokenImage.setAdapter(brokenImageAdapter);
        brokenImageAdapter.notifyDataSetChanged();

        RecyclerView.LayoutManager layoutManagerBid = new LinearLayoutManager(this);
        rvBid.setLayoutManager(layoutManagerBid);

//        getIntent().getStringExtra(ID_VEHICLE);

        loadData();
    }

    @SuppressLint("SetTextI18n")
    private void loadData(){
        rb1Jt.setChecked(false);
        rb2Jt.setChecked(false);

        startTimerDialog(1000000000);
        startTimer(tvDescription, 1000000000);
        lastPrice = Long.parseLong("120000000");
        negoPrice = Long.parseLong("120000000");

        tvInputPriceNego.setText("Rp "+setCurrencyFormat("120000000"));
    }

    @SuppressLint("NonConstantResourceId")
    @OnCheckedChanged(R.id.rb_500_ribu)
    void rb500RibuClick(){
        if(rb500Ribu.isChecked()){
            bidNego = 500000;
//            ColorStateList myColorStateList = new ColorStateList(
//                    new int[][]{new int[]{getResources().getColor(R.color.colorPrimaryBlue)}},
//                    new int[]{getResources().getColor(R.color.colorPrimaryBlue)}
//            );
            rb500Ribu.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryBlue)));
            rb500Ribu.setTextColor(getResources().getColor(R.color.colorPrimaryBlue));
            rb1Jt.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorGray)));
            rb1Jt.setTextColor(getResources().getColor(R.color.colorGray));
            rb2Jt.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorGray)));
            rb2Jt.setTextColor(getResources().getColor(R.color.colorGray));
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnCheckedChanged(R.id.rb_1_jt)
    void rb1JtClick(){
        if(rb1Jt.isChecked()){
            bidNego = 1000000;
//            ColorStateList myColorStateList = new ColorStateList(
//                    new int[][]{new int[]{getResources().getColor(R.color.colorPrimaryBlue)}},
//                    new int[]{getResources().getColor(R.color.colorPrimaryBlue)}
//            );
            rb1Jt.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryBlue)));
            rb1Jt.setTextColor(getResources().getColor(R.color.colorPrimaryBlue));
            rb500Ribu.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorGray)));
            rb500Ribu.setTextColor(getResources().getColor(R.color.colorGray));
            rb2Jt.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorGray)));
            rb2Jt.setTextColor(getResources().getColor(R.color.colorGray));

        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnCheckedChanged(R.id.rb_2_jt)
    void rb2JtClick(){
        if(rb2Jt.isChecked()){
            bidNego = 2000000;
//            ColorStateList myColorStateList = new ColorStateList(
//                    new int[][]{new int[]{getResources().getColor(R.color.colorPrimaryBlue)}},
//                    new int[]{getResources().getColor(R.color.colorPrimaryBlue)}
//            );
            rb2Jt.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryBlue)));
            rb2Jt.setTextColor(getResources().getColor(R.color.colorPrimaryBlue));
            rb500Ribu.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorGray)));
            rb500Ribu.setTextColor(getResources().getColor(R.color.colorGray));
            rb1Jt.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorGray)));
            rb1Jt.setTextColor(getResources().getColor(R.color.colorGray));
        }
    }

    private void setDataHardCodeImageVehicleDetail(){
        HardCodeDataModel hardCodeDataModel = new HardCodeDataModel("");
        hardCodeDataImageVehicleDetailModelList.add(hardCodeDataModel);
        hardCodeDataModel = new HardCodeDataModel("");
        hardCodeDataImageVehicleDetailModelList.add(hardCodeDataModel);
        hardCodeDataModel = new HardCodeDataModel("");
        hardCodeDataImageVehicleDetailModelList.add(hardCodeDataModel);
        hardCodeDataModel = new HardCodeDataModel("");
        hardCodeDataImageVehicleDetailModelList.add(hardCodeDataModel);
        hardCodeDataModel = new HardCodeDataModel("");
        hardCodeDataImageVehicleDetailModelList.add(hardCodeDataModel);
    }

    private void setDataHardCodeDescription(){
        HardCodeDataModel hardCodeDataModel = new HardCodeDataModel("- Bamper Depan Kiri Baret");
        hardCodeDataDescriptionModelList.add(hardCodeDataModel);
        hardCodeDataModel = new HardCodeDataModel("- Bodykit Belakang Baret");
        hardCodeDataDescriptionModelList.add(hardCodeDataModel);
        hardCodeDataModel = new HardCodeDataModel("- Kaca Bolong");
        hardCodeDataDescriptionModelList.add(hardCodeDataModel);
    }

    private void setDataHardCodeBrokenImage(){
        HardCodeDataModel hardCodeDataModel = new HardCodeDataModel("Kap Penyok","1/8");
        hardCodeDataBrokenImageModelList.add(hardCodeDataModel);
        hardCodeDataModel = new HardCodeDataModel("Bumper Baret","2/8");
        hardCodeDataBrokenImageModelList.add(hardCodeDataModel);
        hardCodeDataModel = new HardCodeDataModel("Pintu Penyok","3/8");
        hardCodeDataBrokenImageModelList.add(hardCodeDataModel);
        hardCodeDataModel = new HardCodeDataModel("Kap Penyok","4/8");
        hardCodeDataBrokenImageModelList.add(hardCodeDataModel);
        hardCodeDataModel = new HardCodeDataModel("Kap Penyok","5/8");
        hardCodeDataBrokenImageModelList.add(hardCodeDataModel);
        hardCodeDataModel = new HardCodeDataModel("Kap Penyok","6/8");
        hardCodeDataBrokenImageModelList.add(hardCodeDataModel);
        hardCodeDataModel = new HardCodeDataModel("Kap Penyok","7/8");
        hardCodeDataBrokenImageModelList.add(hardCodeDataModel);
        hardCodeDataModel = new HardCodeDataModel("Kap Penyok","8/8");
        hardCodeDataBrokenImageModelList.add(hardCodeDataModel);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_car_data)
    void tvCarDataClick(){
        if(carData){
            carData = false;
            linearCarData.setVisibility(View.GONE);
        }else {
            carData = true;
            linearCarData.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_body)
    void tvBodyClick(){
        if(body){
            body = false;
            linearBody.setVisibility(View.GONE);
        }else {
            body = true;
            linearBody.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_interior)
    void tvInteriorClick(){
        if(interior){
            interior = false;
            linearInterior.setVisibility(View.GONE);
        }else {
            interior = true;
            linearInterior.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_engine)
    void tvEngineClick(){
        if(engine){
            engine = false;
            linearEngine.setVisibility(View.GONE);
        }else {
            engine = true;
            linearEngine.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_other)
    void tvOtherClick(){
        if(other){
            other = false;
            linearOther.setVisibility(View.GONE);
        }else {
            other = true;
            linearOther.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_broken_image)
    void tvBrokenImageClick(){
        if(brokenImage){
            brokenImage = false;
            linearBrokenImage.setVisibility(View.GONE);
        }else {
            brokenImage = true;
            linearBrokenImage.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.relative_background_dialog)
    void relativeBackgroundDialogClick(){
        relativeBackgroundDialog.setVisibility(View.GONE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.relative_background_dialog_confirm_nego)
    void relativeBackgroundDialogConfirmNegoClick(){
        relativeBackgroundDialogConfirmNego.setVisibility(View.GONE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.relative_background_dialog_success_nego)
    void relativeBackgroundDialogSuccessNegoClick(){
        relativeBackgroundDialogSuccessNego.setVisibility(View.GONE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.relative_background_dialog_confirm_buy_now)
    void relativeBackgroundDialogConfirmBuyNowClick(){
        relativeBackgroundDialogConfirmBuyNow.setVisibility(View.GONE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.relative_background_dialog_success_buy_now)
    void relativeBackgroundDialogSuccessBuyNowClick(){
        relativeBackgroundDialogSuccessBuyNow.setVisibility(View.GONE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_nego_dialog)
    void btnNegoDialogClick(){
        linearDialogNegoClick();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_buy_now_dialog)
    void btnBuyNowDialogClick(){
        linearDialogBuyNowClick();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.linear_dialog_confirm_nego)
    void linearDialogNegoClick(){
        relativeBackgroundDialogConfirmNego.setVisibility(View.VISIBLE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.linear_dialog_confirm_buy_now)
    void linearDialogBuyNowClick(){
        relativeBackgroundDialogConfirmBuyNow.setVisibility(View.VISIBLE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_nego_confirm_dialog)
    void btnNegoConfirmDialogClick(){
        linearDialogSuccessNegoClick();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_confirm_buy_now_dialog)
    void btnConfirmBuyNowDialogClick(){
        linearDialogSuccessBuyNowClick();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.linear_dialog_success_nego)
    void linearDialogSuccessNegoClick(){
        relativeBackgroundDialog.setVisibility(View.GONE);
        relativeBackgroundDialogConfirmNego.setVisibility(View.GONE);
        relativeBackgroundDialogSuccessNego.setVisibility(View.VISIBLE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.linear_dialog_success_buy_now)
    void linearDialogSuccessBuyNowClick(){
        relativeBackgroundDialog.setVisibility(View.GONE);
        relativeBackgroundDialogConfirmBuyNow.setVisibility(View.GONE);
        relativeBackgroundDialogSuccessBuyNow.setVisibility(View.VISIBLE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_garasi)
    void btnGarasiClick(){
        relativeBackgroundDialog.setVisibility(View.GONE);
        relativeBackgroundDialogConfirmNego.setVisibility(View.GONE);
        relativeBackgroundDialogSuccessNego.setVisibility(View.GONE);
        finish();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_pembayaran)
    void btnPembayaranClick(){
        relativeBackgroundDialog.setVisibility(View.GONE);
        relativeBackgroundDialogConfirmBuyNow.setVisibility(View.GONE);
        relativeBackgroundDialogSuccessBuyNow.setVisibility(View.GONE);
        finish();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_close_dialog_confirm_nego)
    void ivCloseDialogConfirmNegoClick(){
        relativeBackgroundDialogConfirmNego.setVisibility(View.GONE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_close_dialog_confirm_buy_now)
    void ivCloseDialogConfirmBuyNowClick(){
        relativeBackgroundDialogConfirmBuyNow.setVisibility(View.GONE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick({R.id.btn_nego,R.id.linear_dialog_nego})
    void btnNegoClick(){
        relativeBackgroundDialog.setVisibility(View.VISIBLE);
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @OnClick(R.id.iv_min)
    void ivMinClick(){
        if(negoPrice==lastPrice){
            Toast.makeText(this, "Minimum Tawar Harus Lebih Besar dari Penawaran Terakhir", Toast.LENGTH_SHORT).show();
        }else {
            negoPrice = negoPrice-bidNego;
            tvInputPriceNego.setText("Rp "+setCurrencyFormat(String.valueOf(negoPrice)));
        }
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @OnClick(R.id.iv_plus)
    void ivPlusClick(){
        negoPrice = negoPrice+bidNego;
        tvInputPriceNego.setText("Rp "+setCurrencyFormat(String.valueOf(negoPrice)));
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @OnClick(R.id.iv_clear_price)
    void ivClearPriceClick(){
        lastPrice = Long.parseLong("120000000");
        negoPrice = Long.parseLong("120000000");
        tvInputPriceNego.setText("Rp "+setCurrencyFormat("120000000"));
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_back)
    void ivBackClick(){
        finish();
    }

    public void startTimerDialog(long noOfMinutes) {
        new CountDownTimer(noOfMinutes,  1000) {
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int hours = seconds / (60 * 60);
                int tempMint = (seconds - (hours * 60 * 60));
                int minutes = tempMint / 60;
                seconds = tempMint - (minutes * 60);

                tvHourFirst.setText(String.format("%02d", hours).substring(0,1));
                tvHourSecond.setText(String.format("%02d", hours).substring(1,2));
                tvMinuteFirst.setText(String.format("%02d", minutes).substring(0,1));
                tvMinuteSecond.setText(String.format("%02d", minutes).substring(1,2));
                tvSecondFirst.setText(String.format("%02d", seconds).substring(0,1));
                tvSecondSecond.setText(String.format("%02d", seconds).substring(1,2));

            }
            @SuppressLint("SetTextI18n")
            public void onFinish() {
                tvHourFirst.setText("0");
                tvHourSecond.setText("0");
                tvMinuteFirst.setText("0");
                tvMinuteSecond.setText("0");
                tvSecondFirst.setText("0");
                tvSecondSecond.setText("0");
            }
        }.start();
    }

    public void startTimer(TextView tvTimer, long noOfMinutes) {
        new CountDownTimer(noOfMinutes,  1000) {
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int hours = seconds / (60 * 60);
                int tempMint = (seconds - (hours * 60 * 60));
                int minutes = tempMint / 60;
                seconds = tempMint - (minutes * 60);
                tvTimer.setText(String.format("%02d", hours) + "h " +
                        String.format("%02d", minutes) + "m " +
                        String.format("%02d", seconds) + "s");
            }
            @SuppressLint("SetTextI18n")
            public void onFinish() {
                tvTimer.setText("00h 00m 00s");
            }
        }.start();
    }

}