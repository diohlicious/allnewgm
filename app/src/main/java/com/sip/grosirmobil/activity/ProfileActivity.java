package com.sip.grosirmobil.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.fxn.pix.Pix;
import com.sip.grosirmobil.R;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.REQUEST_CAMERA;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.adjustFontScale;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setStatusBarOnBoarding;

public class ProfileActivity extends GrosirMobilActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.iv_back) ImageView ivBack;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.iv_profile) CircleImageView ivProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarOnBoarding(this);
        setContentView(R.layout.actifity_profile);
        adjustFontScale(this, getResources().getConfiguration());
        ButterKnife.bind(this);

    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_success_bidding)
    void ivSuccessBiddingClick() {
        Intent intent = new Intent(this, SuccessBiddingActivity.class);
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_lost_bidding)
    void ivLostBiddingClick() {
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
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
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