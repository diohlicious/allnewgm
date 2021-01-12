package com.sip.grosirmobil.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;
import com.sip.grosirmobil.cloud.config.request.changepassword.ChangePasswordRequest;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sip.grosirmobil.base.function.GrosirMobilFunction.adjustFontScale;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setStatusBarOnBoarding;

public class ChangePasswordActivity extends GrosirMobilActivity {
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_old_password) EditText etOldPassword;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_new_password) EditText etNewPassword;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_confirm_password) EditText etConfirmPassword;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_change_password) Button btnChangePassword;
    ProgressDialog progressDialog;
    private GrosirMobilPreference grosirMobilPreference;
    private String password = "";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        grosirMobilPreference = new GrosirMobilPreference(this);
        setStatusBarOnBoarding(this);
        setContentView(R.layout.activity_change_password);
        adjustFontScale(this, getResources().getConfiguration());
        ButterKnife.bind(this);
    }
    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_back)
    void ivBackClick() {
        finish();
    }
    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_change_password)
    void btnChangePasswordClick() {
        String oldPassword = etOldPassword.getText().toString();
        String newPassword = etNewPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();
        if(oldPassword.isEmpty()){
            Toast.makeText(this, "Mohon Isi Password yang Lama", Toast.LENGTH_SHORT).show();
        }else if(newPassword.isEmpty()){
            Toast.makeText(this, "Mohon Isi Password baru", Toast.LENGTH_SHORT).show();
        }
        else if(confirmPassword.isEmpty()){
            Toast.makeText(this, "Mohon Isi Konfirmasi Password", Toast.LENGTH_SHORT).show();
        }
        else if(!newPassword.trim().equals(confirmPassword.trim())){
                Toast.makeText(this, "Password tidak matching", Toast.LENGTH_SHORT).show();
            }
        else if(!oldPassword.equals(grosirMobilPreference.getPassword())){
            Toast.makeText(this, "Password lama tidak sesuai", Toast.LENGTH_SHORT).show();
        }
            else{
                ProgressDialog progressDialog = new ProgressDialog(ChangePasswordActivity.this);
                progressDialog.setCancelable(true);
                progressDialog.setMessage(getString(R.string.base_tv_please_wait));
                progressDialog.show();

            ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(grosirMobilPreference.getDataCheckActiveToken().getLoggedInUserResponse().getUserResponse().getEmail(),newPassword+"");
            final Call<GeneralResponse> changePasswordApi = getApiGrosirMobil().changePassword(changePasswordRequest);
            changePasswordApi.enqueue(new Callback<GeneralResponse>() {
                @Override
                public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                    progressDialog.dismiss();
                    if(response.isSuccessful()) {
                        try {
                            if(response.body().getMessage().equals("success")){
                                Toast.makeText(ChangePasswordActivity.this, response.body().getDescription(), Toast.LENGTH_SHORT).show();
                                grosirMobilPreference.clearSharePreference();
                                Intent intent = new Intent(ChangePasswordActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(ChangePasswordActivity.this, response.body().getDescription(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (Exception e){
                            GrosirMobilLog.printStackTrace(e);
                        }
                    }
                }

                @Override
                public void onFailure(Call<GeneralResponse> call, Throwable t) {

                }
            });
            
                
            }
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