package com.sip.grosirmobil.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.BrokenImageAdapter;
import com.sip.grosirmobil.adapter.ImageVehicleDetailAdapter;
import com.sip.grosirmobil.adapter.VehicleDetailDataAdapter;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.implement.VehicleDetailImp;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.base.presenter.VehicleDetailPresenter;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;
import com.sip.grosirmobil.base.view.VehicleDetailView;
import com.sip.grosirmobil.cloud.config.request.favorite.FavoriteRequest;
import com.sip.grosirmobil.cloud.config.request.negonbuynow.NegoAndBuyNowRequest;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;
import com.sip.grosirmobil.cloud.config.response.vehicledetail.DataVehicleDetailResponse;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.BEARER;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.FROM_PAGE;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.ID_VEHICLE;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.KIK;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.REQUEST_MAIN;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.adjustFontScale;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.calculateDate;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.convertDateServer;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setCurrencyFormat;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setStatusBarOnBoarding;

public class VehicleDetailActivity extends GrosirMobilActivity implements VehicleDetailView {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.iv_favorite) ImageView ivFavorite;
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
    @BindView(R.id.tv_timer) TextView tvTimer;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_description) TextView tvDescription;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_image_car) RecyclerView rvImageCar;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_car_data) TextView tvCarData;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_car_data) RecyclerView rvCarData;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_body) TextView tvBody;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_body) RecyclerView rvBody;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_interior) TextView tvInterior;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_interior) RecyclerView rvInterior;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_engine) TextView tvEngine;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_engine) RecyclerView rvEngine;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_other) TextView tvOther;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_other) RecyclerView rvOther;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_broken_image) TextView tvBrokenImage;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_broken_image) RecyclerView rvBrokenImage;

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
    private ProgressDialog progressDialog;
    private VehicleDetailPresenter vehicleDetailPresenter;

    private boolean carData = false;
    private boolean body = false;
    private boolean interior = false;
    private boolean engine = false;
    private boolean other = false;
    private boolean brokenImage = false;
    private boolean favorite = false;
    private long negoPrice, lastPrice, bidNego, buyNow;

    private Context context;
    private String openHouseId="";
    private String kik="";
    private String lastPriceFirst="";
    private String negoPriceFirst="";
    private DataVehicleDetailResponse dataVehicleDetailResponse = null;

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
        progressDialog = new ProgressDialog(this);

        vehicleDetailPresenter = new VehicleDetailImp(this, this);

        openHouseId = getIntent().getStringExtra(ID_VEHICLE);
        kik = getIntent().getStringExtra(KIK);

        vehicleDetailPresenter.vehicleDetailApi(kik,openHouseId);

        if(getIntent().getStringExtra(FROM_PAGE).equals("LIVE")){
            btnNego.setVisibility(View.VISIBLE);
            linearDescription.setVisibility(View.VISIBLE);
        }else if(getIntent().getStringExtra(FROM_PAGE).equals("HISTORY")){
            linearDescription.setVisibility(View.GONE);
            btnNego.setVisibility(View.INVISIBLE);
        }else if(getIntent().getStringExtra(FROM_PAGE).equals("COMING SOON")){
            linearDescription.setVisibility(View.GONE);
            btnNego.setVisibility(View.INVISIBLE);
        }

        loadData();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_favorite)
    void ivFavoriteClick(){
        if(favorite){
            favorite = false;
            ivFavorite.setImageResource(R.drawable.ic_favorite_empty);
            unFavorite(kik);
        }else {
            favorite = true;
            ivFavorite.setImageResource(R.drawable.ic_favorite);
            setFavorite(kik);
        }
    }

    @SuppressLint("SetTextI18n")
    private void loadData(){
        rb500Ribu.setChecked(true);
        rb1Jt.setChecked(false);
        rb2Jt.setChecked(false);
    }

    @SuppressLint("NonConstantResourceId")
    @OnCheckedChanged(R.id.rb_500_ribu)
    void rb500RibuClick(){
        if(rb500Ribu.isChecked()){
            bidNego = 500000;
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
            rb2Jt.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryBlue)));
            rb2Jt.setTextColor(getResources().getColor(R.color.colorPrimaryBlue));
            rb500Ribu.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorGray)));
            rb500Ribu.setTextColor(getResources().getColor(R.color.colorGray));
            rb1Jt.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorGray)));
            rb1Jt.setTextColor(getResources().getColor(R.color.colorGray));
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_car_data)
    void tvCarDataClick(){
        if(carData){
            carData = false;
            tvCarData.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(context,R.drawable.ic_chevron_down), null);
            rvCarData.setVisibility(View.GONE);
        }else {
            carData = true;
            tvCarData.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(context,R.drawable.ic_chevron_up), null);
            rvCarData.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_body)
    void tvBodyClick(){
        if(body){
            body = false;
            tvBody.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(context,R.drawable.ic_chevron_down), null);
            rvBody.setVisibility(View.GONE);
        }else {
            body = true;
            tvBody.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(context,R.drawable.ic_chevron_up), null);
            rvBody.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_interior)
    void tvInteriorClick(){
        if(interior){
            interior = false;
            tvInterior.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(context,R.drawable.ic_chevron_down), null);
            rvInterior.setVisibility(View.GONE);
        }else {
            interior = true;
            tvInterior.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(context,R.drawable.ic_chevron_up), null);
            rvInterior.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_engine)
    void tvEngineClick(){
        if(engine){
            engine = false;
            tvEngine.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(context,R.drawable.ic_chevron_down), null);
            rvEngine.setVisibility(View.GONE);
        }else {
            engine = true;
            tvEngine.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(context,R.drawable.ic_chevron_up), null);
            rvEngine.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_other)
    void tvOtherClick(){
        if(other){
            other = false;
            tvOther.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(context,R.drawable.ic_chevron_down), null);
            rvOther.setVisibility(View.GONE);
        }else {
            other = true;
            tvOther.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(context,R.drawable.ic_chevron_up), null);
            rvOther.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_broken_image)
    void tvBrokenImageClick(){
        if(brokenImage){
            brokenImage = false;
            tvBrokenImage.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(context,R.drawable.ic_chevron_down), null);
            rvBrokenImage.setVisibility(View.GONE);
        }else {
            brokenImage = true;
            tvBrokenImage.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(context,R.drawable.ic_chevron_up), null);
            rvBrokenImage.setVisibility(View.VISIBLE);
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

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @OnClick(R.id.btn_nego_dialog)
    void btnNegoDialogClick(){
        String vehicleName = dataVehicleDetailResponse.getVehicleName();
        tvMessageNego.setText(vehicleName+"\nseharga\nRp"+setCurrencyFormat(String.valueOf(negoPrice)));
        linearDialogNegoClick();
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @OnClick(R.id.btn_buy_now_dialog)
    void btnBuyNowDialogClick(){
        String vehicleName = dataVehicleDetailResponse.getVehicleName();
        tvMessageBuyNow.setText(vehicleName+"\nseharga\nRp"+setCurrencyFormat(dataVehicleDetailResponse.getOpenPrice()));
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
        NegoAndBuyNowRequest negoAndBuyNowRequest = new NegoAndBuyNowRequest(dataVehicleDetailResponse.getOpenHouseId(), dataVehicleDetailResponse.getKik(), dataVehicleDetailResponse.getAgreementNo(),String.valueOf(negoPrice));
        vehicleDetailPresenter.liveNegoApi(negoAndBuyNowRequest);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_confirm_buy_now_dialog)
    void btnConfirmBuyNowDialogClick(){
        NegoAndBuyNowRequest negoAndBuyNowRequest = new NegoAndBuyNowRequest(dataVehicleDetailResponse.getOpenHouseId(), dataVehicleDetailResponse.getKik(), dataVehicleDetailResponse.getAgreementNo(), dataVehicleDetailResponse.getOpenPrice());
        vehicleDetailPresenter.liveBuyNowApi(negoAndBuyNowRequest);
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @OnClick(R.id.linear_dialog_success_nego)
    void linearDialogSuccessNegoClick(){
        relativeBackgroundDialog.setVisibility(View.GONE);
        relativeBackgroundDialogConfirmNego.setVisibility(View.GONE);
        relativeBackgroundDialogSuccessNego.setVisibility(View.VISIBLE);
        String vehicleName = dataVehicleDetailResponse.getVehicleName();
        tvMessageSuccessNego.setText(vehicleName+"\nseharga\nRp"+setCurrencyFormat(String.valueOf(negoPrice)));
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @OnClick(R.id.linear_dialog_success_buy_now)
    void linearDialogSuccessBuyNowClick(){
        relativeBackgroundDialog.setVisibility(View.GONE);
        relativeBackgroundDialogConfirmBuyNow.setVisibility(View.GONE);
        relativeBackgroundDialogSuccessBuyNow.setVisibility(View.VISIBLE);
        String vehicleName = dataVehicleDetailResponse.getVehicleName();
        tvMessageSuccessBuyNow.setText(vehicleName+"\nseharga\nRp"+setCurrencyFormat(dataVehicleDetailResponse.getOpenPrice()));
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_cart)
    void btnCartClick(){
        relativeBackgroundDialog.setVisibility(View.GONE);
        relativeBackgroundDialogConfirmNego.setVisibility(View.GONE);
        relativeBackgroundDialogSuccessNego.setVisibility(View.GONE);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(REQUEST_MAIN, "cart");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_win)
    void btnWinClick(){
        relativeBackgroundDialog.setVisibility(View.GONE);
        relativeBackgroundDialogConfirmBuyNow.setVisibility(View.GONE);
        relativeBackgroundDialogSuccessBuyNow.setVisibility(View.GONE);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(REQUEST_MAIN, "win");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
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
        }else if(negoPrice<Long.parseLong(lastPriceFirst)){
            Toast.makeText(this, "Harga Error Kembali Ke Harga Awal", Toast.LENGTH_SHORT).show();
            tvInputPriceNego.setText("Rp "+setCurrencyFormat(String.valueOf(lastPriceFirst)));
        }
        else {
            negoPrice = negoPrice-bidNego;
            tvInputPriceNego.setText("Rp "+setCurrencyFormat(String.valueOf(negoPrice)));
        }
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @OnClick(R.id.iv_plus)
    void ivPlusClick(){
        if(negoPrice>buyNow){
            btnNegoDialog.setVisibility(View.GONE);
            tvInputPriceNego.setText("Rp "+setCurrencyFormat(String.valueOf(buyNow)));
        }else {
            btnNegoDialog.setVisibility(View.VISIBLE);
            negoPrice = negoPrice+bidNego;
            tvInputPriceNego.setText("Rp "+setCurrencyFormat(String.valueOf(negoPrice)));
        }
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @OnClick(R.id.iv_clear_price)
    void ivClearPriceClick(){
        lastPrice = Long.parseLong(lastPriceFirst);
        negoPrice = Long.parseLong(negoPriceFirst);
        tvInputPriceNego.setText("Rp "+setCurrencyFormat(lastPriceFirst));
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
                btnNego.setVisibility(View.INVISIBLE);
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
                btnNego.setVisibility(View.INVISIBLE);
            }
        }.start();
    }

    @Override
    public void showDialogLoading() {
        progressDialog.setCancelable(false);
        progressDialog.setMessage(context.getString(R.string.base_tv_please_wait));
        progressDialog.show();
    }

    @Override
    public void hideDialogLoading() {
        progressDialog.dismiss();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void vehicleDetailSuccess(DataVehicleDetailResponse dataVehicleDetailResponse, String timeServer) {
        this.dataVehicleDetailResponse = dataVehicleDetailResponse;
        startTimerDialog(calculateDate(convertDateServer(timeServer),dataVehicleDetailResponse.getEndDate()));
        startTimer(tvTimer, calculateDate(convertDateServer(timeServer),dataVehicleDetailResponse.getEndDate()));
        String vehicleName = dataVehicleDetailResponse.getVehicleName();
        System.out.println("SIZE NAME : "+ vehicleName.length());
        if(vehicleName.length()>17){
            tvTitleVehicle.setText(vehicleName+"...");
            tvMessageNego.setText(vehicleName+"...");
            tvMessageBuyNow.setText(vehicleName+"...");
        }else {
            tvTitleVehicle.setText(vehicleName);
            tvMessageNego.setText(vehicleName);
            tvMessageBuyNow.setText(vehicleName);
        }
        tvInitialName.setText(dataVehicleDetailResponse.getGrade());
        tvHargaAwal.setText("Rp "+setCurrencyFormat(dataVehicleDetailResponse.getHargaAwal()));
        tvHargaSekarang.setText("Rp "+setCurrencyFormat(dataVehicleDetailResponse.getBottomPrice()));

        favorite = dataVehicleDetailResponse.getIsFavorite() != null || dataVehicleDetailResponse.getIsFavorite().equals("1");

        if(favorite){
            ivFavorite.setImageResource(R.drawable.ic_favorite);
        }else {
            ivFavorite.setImageResource(R.drawable.ic_favorite_empty);
        }
        tvPlatNumber.setText(dataVehicleDetailResponse.getKik().substring(0, 10) + " - ");
        tvCity.setText(dataVehicleDetailResponse.getWarehouse().replace("WAREHOUSE ", ""));
        if(dataVehicleDetailResponse.getVehicleSummary()==null||dataVehicleDetailResponse.getVehicleSummary().equals("")){
            tvDescription.setText("-");
        }else {
            String description = dataVehicleDetailResponse.getVehicleSummary();
            String newDescription = description.replace(",","\n-");
            tvDescription.setText("-"+newDescription);
        }

        LinearLayoutManager layoutManagerImageCar = new LinearLayoutManager(VehicleDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rvImageCar.setLayoutManager(layoutManagerImageCar);
        rvImageCar.setItemAnimator(new DefaultItemAnimator());
        rvImageCar.setNestedScrollingEnabled(false);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(rvImageCar);
        System.out.println("DATA GAMBAR : "+ dataVehicleDetailResponse.getImageResponseList().size());
        if(dataVehicleDetailResponse.getImageResponseList()==null||dataVehicleDetailResponse.getImageResponseList().isEmpty()){
            rvImageCar.setVisibility(View.GONE);
        }else {
            rvImageCar.setVisibility(View.VISIBLE);
            ImageVehicleDetailAdapter imageVehicleDetailAdapter = new ImageVehicleDetailAdapter(VehicleDetailActivity.this, dataVehicleDetailResponse.getImageResponseList());
            rvImageCar.setAdapter(imageVehicleDetailAdapter);
            imageVehicleDetailAdapter.notifyDataSetChanged();
        }

        if(dataVehicleDetailResponse.getVehicleDataResponseList()==null||dataVehicleDetailResponse.getVehicleDataResponseList().isEmpty()){
            rvCarData.setVisibility(View.GONE);
            tvCarData.setVisibility(View.GONE);
        }else {
            LinearLayoutManager linearLayoutManagerBody = new LinearLayoutManager(VehicleDetailActivity.this);
            rvCarData.setLayoutManager(linearLayoutManagerBody);
            rvCarData.setItemAnimator(new DefaultItemAnimator());
            rvCarData.setNestedScrollingEnabled(false);
            VehicleDetailDataAdapter vehicleDetailDataAdapter = new VehicleDetailDataAdapter(VehicleDetailActivity.this, dataVehicleDetailResponse.getVehicleDataResponseList());
            rvCarData.setAdapter(vehicleDetailDataAdapter);
            vehicleDetailDataAdapter.notifyDataSetChanged();
        }

        if(dataVehicleDetailResponse.getVehicleBodyResponseList()==null||dataVehicleDetailResponse.getVehicleBodyResponseList().isEmpty()){
            rvBody.setVisibility(View.GONE);
            tvBody.setVisibility(View.GONE);
        }else {
            LinearLayoutManager linearLayoutManagerBody = new LinearLayoutManager(VehicleDetailActivity.this);
            rvBody.setLayoutManager(linearLayoutManagerBody);
            rvBody.setItemAnimator(new DefaultItemAnimator());
            rvBody.setNestedScrollingEnabled(false);
            VehicleDetailDataAdapter vehicleDetailDataAdapter = new VehicleDetailDataAdapter(VehicleDetailActivity.this, dataVehicleDetailResponse.getVehicleBodyResponseList());
            rvBody.setAdapter(vehicleDetailDataAdapter);
            vehicleDetailDataAdapter.notifyDataSetChanged();
        }

        if(dataVehicleDetailResponse.getVehicleInteriorResponseList()==null||dataVehicleDetailResponse.getVehicleInteriorResponseList().isEmpty()){
            rvInterior.setVisibility(View.GONE);
            tvInterior.setVisibility(View.GONE);
        }else {
            LinearLayoutManager linearLayoutManagerInterior = new LinearLayoutManager(VehicleDetailActivity.this);
            rvInterior.setLayoutManager(linearLayoutManagerInterior);
            rvInterior.setItemAnimator(new DefaultItemAnimator());
            rvInterior.setNestedScrollingEnabled(false);
            VehicleDetailDataAdapter vehicleDetailDataAdapter = new VehicleDetailDataAdapter(VehicleDetailActivity.this, dataVehicleDetailResponse.getVehicleInteriorResponseList());
            rvInterior.setAdapter(vehicleDetailDataAdapter);
            vehicleDetailDataAdapter.notifyDataSetChanged();
        }

        if(dataVehicleDetailResponse.getVehicleMesinResponseList()==null||dataVehicleDetailResponse.getVehicleMesinResponseList().isEmpty()){
            rvEngine.setVisibility(View.GONE);
            tvEngine.setVisibility(View.GONE);
        }else {
            LinearLayoutManager linearLayoutManagerEngine = new LinearLayoutManager(VehicleDetailActivity.this);
            rvEngine.setLayoutManager(linearLayoutManagerEngine);
            rvEngine.setItemAnimator(new DefaultItemAnimator());
            rvEngine.setNestedScrollingEnabled(false);
            VehicleDetailDataAdapter vehicleDetailDataAdapter = new VehicleDetailDataAdapter(VehicleDetailActivity.this, dataVehicleDetailResponse.getVehicleMesinResponseList());
            rvEngine.setAdapter(vehicleDetailDataAdapter);
            vehicleDetailDataAdapter.notifyDataSetChanged();
        }

        if(dataVehicleDetailResponse.getVehicleOtherResponseList()==null||dataVehicleDetailResponse.getVehicleOtherResponseList().isEmpty()){
            rvOther.setVisibility(View.GONE);
            tvOther.setVisibility(View.GONE);
        }else {
            LinearLayoutManager linearLayoutManagerOther = new LinearLayoutManager(VehicleDetailActivity.this);
            rvOther.setLayoutManager(linearLayoutManagerOther);
            rvOther.setItemAnimator(new DefaultItemAnimator());
            rvOther.setNestedScrollingEnabled(false);
            VehicleDetailDataAdapter vehicleDetailDataAdapter = new VehicleDetailDataAdapter(VehicleDetailActivity.this, dataVehicleDetailResponse.getVehicleOtherResponseList());
            rvOther.setAdapter(vehicleDetailDataAdapter);
            vehicleDetailDataAdapter.notifyDataSetChanged();
        }

        if(dataVehicleDetailResponse.getImageBrokenResponseList()==null||dataVehicleDetailResponse.getImageBrokenResponseList().isEmpty()){
            rvBrokenImage.setVisibility(View.GONE);
            tvBrokenImage.setVisibility(View.GONE);
        }else {
            GridLayoutManager gridLayoutManagerBrokenImage = new GridLayoutManager(VehicleDetailActivity.this, 2);
            rvBrokenImage.setLayoutManager(gridLayoutManagerBrokenImage);
            rvBrokenImage.setItemAnimator(new DefaultItemAnimator());
            rvBrokenImage.setNestedScrollingEnabled(false);
            BrokenImageAdapter brokenImageAdapter = new BrokenImageAdapter(VehicleDetailActivity.this, dataVehicleDetailResponse.getImageBrokenResponseList());
            rvBrokenImage.setAdapter(brokenImageAdapter);
            brokenImageAdapter.notifyDataSetChanged();
        }
        btnBuyNowDialog.setText("Buy Now Rp "+setCurrencyFormat(dataVehicleDetailResponse.getOpenPrice()));
        lastPriceFirst  = dataVehicleDetailResponse.getBottomPrice();
        negoPriceFirst  = dataVehicleDetailResponse.getBottomPrice();
        buyNow          = Long.parseLong(dataVehicleDetailResponse.getOpenPrice());
        lastPrice       = Long.parseLong(dataVehicleDetailResponse.getBottomPrice());
        negoPrice       = Long.parseLong(dataVehicleDetailResponse.getBottomPrice());

        tvInputPriceNego.setText("Rp "+setCurrencyFormat(dataVehicleDetailResponse.getBottomPrice()));

        LinearLayoutManager layoutManagerBid = new LinearLayoutManager(this);
        rvBid.setLayoutManager(layoutManagerBid);
        rvBid.setNestedScrollingEnabled(false);

    }

    @Override
    public void vehicleDetailNegoAndBuyNow(String type, GeneralResponse generalResponse) {
        if(type.equals("Nego")){
            linearDialogSuccessNegoClick();
        }else {
            linearDialogSuccessBuyNowClick();
        }
    }

    public void setFavorite(String kik){
        FavoriteRequest favoriteRequest = new FavoriteRequest(kik);
        final Call<GeneralResponse> timeServerApi = getApiGrosirMobil().setFavoriteApi(BEARER+" "+grosirMobilPreference.getToken(),favoriteRequest);
        timeServerApi.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                if (response.isSuccessful()) {
                    try {
                        if(response.body().getMessage().equals("success")){
                            ivFavorite.setImageResource(R.drawable.ic_favorite);
                        }else {
                            grosirMobilFunction.showMessage(VehicleDetailActivity.this, "POST Favorite", response.body().getMessage());
                        }
                    }catch (Exception e){
                        GrosirMobilLog.printStackTrace(e);
                    }
                }else {
                    try {
                        grosirMobilFunction.showMessage(VehicleDetailActivity.this, getString(R.string.base_null_error_title), response.errorBody().string());
                    } catch (IOException e) {
                        GrosirMobilLog.printStackTrace(e);
                    }
                }
            }
            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                grosirMobilFunction.showMessage(VehicleDetailActivity.this, "POST Favorite", getString(R.string.base_null_server));
                GrosirMobilLog.printStackTrace(t);
            }
        });
    }

    public void unFavorite(String kik){
        FavoriteRequest favoriteRequest = new FavoriteRequest(kik);
        final Call<GeneralResponse> timeServerApi = getApiGrosirMobil().unFavoriteApi(BEARER+" "+grosirMobilPreference.getToken(),favoriteRequest);
        timeServerApi.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                if (response.isSuccessful()) {
                    try {
                        if(response.body().getMessage().equals("success")){
                            ivFavorite.setImageResource(R.drawable.ic_favorite_empty);
                        }else {
                            grosirMobilFunction.showMessage(VehicleDetailActivity.this, "POST Favorite", response.body().getMessage());
                        }
                    }catch (Exception e){
                        GrosirMobilLog.printStackTrace(e);
                    }
                }else {
                    try {
                        grosirMobilFunction.showMessage(VehicleDetailActivity.this, getString(R.string.base_null_error_title), response.errorBody().string());
                    } catch (IOException e) {
                        GrosirMobilLog.printStackTrace(e);
                    }
                }
            }
            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                grosirMobilFunction.showMessage(VehicleDetailActivity.this, "POST Favorite", getString(R.string.base_null_server));
                GrosirMobilLog.printStackTrace(t);
            }
        });
    }
}