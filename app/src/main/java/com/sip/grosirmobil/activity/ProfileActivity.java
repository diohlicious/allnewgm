package com.sip.grosirmobil.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.fxn.pix.Pix;
import com.sip.grosirmobil.R;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.BEARER;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.REQUEST_CAMERA;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.adjustFontScale;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setStatusBarOnBoarding;

public class ProfileActivity extends GrosirMobilActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.iv_back) ImageView ivBack;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.iv_profile) CircleImageView ivProfile;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_full_name) TextView tvFullName;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_full_name_data_diri) TextView tvFullNameDataDiri;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_phone_number) TextView tvPhoneNumber;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_email) TextView tvEmail;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_dealer_address) TextView tvDealerAddress;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_province) TextView tvProvince;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_kabupaten) TextView tvKabupaten;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_kecamatan) TextView tvKecamatan;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_kelurahan) TextView tvKelurahan;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_dealer_pos_code) TextView tvDealerPosCode;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_success_bidding) TextView tvSuccessBidding;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_loss_bidding) TextView tvLossBidding;

    private GrosirMobilPreference grosirMobilPreference;
    private GrosirMobilFunction grosirMobilFunction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarOnBoarding(this);
        setContentView(R.layout.actifity_profile);
        adjustFontScale(this, getResources().getConfiguration());
        ButterKnife.bind(this);

        grosirMobilPreference = new GrosirMobilPreference(this);
        grosirMobilFunction = new GrosirMobilFunction(this);

        try {
            
            tvFullName.setText(grosirMobilPreference.getDataLogin().getLoggedInUserResponse().getUserResponse().getNamaLengkap());
            tvFullNameDataDiri.setText(grosirMobilPreference.getDataLogin().getLoggedInUserResponse().getUserResponse().getNamaLengkap());
            tvPhoneNumber.setText(grosirMobilPreference.getDataLogin().getLoggedInUserResponse().getUserResponse().getNoHP());
            tvEmail.setText(grosirMobilPreference.getDataLogin().getLoggedInUserResponse().getUserResponse().getEmail());
            tvDealerAddress.setText(grosirMobilPreference.getDataLogin().getLoggedInUserResponse().getProfilResponse().getAlamatDealer());
            tvProvince.setText(grosirMobilPreference.getDataLogin().getLoggedInUserResponse().getProfilResponse().getPropinsi());
            tvKabupaten.setText(grosirMobilPreference.getDataLogin().getLoggedInUserResponse().getProfilResponse().getKabupaten());
            tvKecamatan.setText(grosirMobilPreference.getDataLogin().getLoggedInUserResponse().getProfilResponse().getKecamatan());
            tvKelurahan.setText(grosirMobilPreference.getDataLogin().getLoggedInUserResponse().getProfilResponse().getKelurahan());
            tvDealerPosCode.setText(grosirMobilPreference.getDataLogin().getLoggedInUserResponse().getProfilResponse().getPostalCode());

            CircularProgressDrawable circularProgressDrawable = new  CircularProgressDrawable(this);
            circularProgressDrawable.setStrokeWidth(5f);
            circularProgressDrawable.setCenterRadius(30f);
            circularProgressDrawable.start();
            Glide.with(this)
                    .load(grosirMobilPreference.getDataLogin().getLoggedInUserResponse().getUserResponse().getProfilePhotoUrl())
                    .apply(new RequestOptions()
                            .placeholder(circularProgressDrawable)
//                          .error(R.drawable.ic_image_empty)
                            .dontAnimate()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true))
                    .into(ivProfile);
        }catch (Exception e){
            GrosirMobilLog.printStackTrace(e);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.card_view_success_bidding)
    void cardViewSuccessBiddingClick() {
        Intent intent = new Intent(this, SuccessBiddingActivity.class);
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.card_view_loss_bidding)
    void cardViewLostBiddingClick() {
        Intent intent = new Intent(this, LostBiddingActivity.class);
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_edit_foto)
    void tvEditFotoClick(){
        Pix.start(this, REQUEST_CAMERA);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_back)
    void ivBackClick() {
        finish();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_change_password)
    void tvChangePasswordClick(){
        Intent intent = new Intent(this, ChangePasswordActivity.class);
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_logout)
    void tvLogoutClick(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.base_tv_please_wait));
        progressDialog.show();
        final Call<GeneralResponse> logoutApi = getApiGrosirMobil().logoutApi(BEARER+" "+grosirMobilPreference.getToken());
        logoutApi.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    try {
                        if(response.body().getMessage().contains("success")){
                            grosirMobilPreference.saveToken("");
                            grosirMobilPreference.clearSharePreference();
                            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }else {
                            grosirMobilFunction.showMessage(ProfileActivity.this, "GET Logout", response.body().getMessage());
                        }
                    }catch (Exception e){
                        GrosirMobilLog.printStackTrace(e);
                    }
                }else {
                    try {
                        grosirMobilFunction.showMessage(ProfileActivity.this, getString(R.string.base_null_error_title), response.errorBody().string());
                    } catch (IOException e) {
                        GrosirMobilLog.printStackTrace(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                progressDialog.dismiss();
                grosirMobilFunction.showMessage(ProfileActivity.this, "GET Logout", getString(R.string.base_null_server));
                GrosirMobilLog.printStackTrace(t);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CAMERA){
            try {
                ArrayList<String> returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
                System.out.println("Path : " + returnValue.get(0));
                String imageFilePath = returnValue.get(0);
                Glide.with(this).load(imageFilePath).into(ivProfile);
            }
            catch (Exception e){
                GrosirMobilLog.printStackTrace(e);
                Toast.makeText(this, getString(R.string.toast_cancel_add_image), Toast.LENGTH_SHORT).show();
            }
//            ivProfile.
        }
    }
}