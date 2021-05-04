package com.sip.grosirmobil.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.sip.grosirmobil.R;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;
import com.sip.grosirmobil.cloud.config.request.changepassword.ChangePasswordRequest;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;


import java.io.IOException;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.EMAIL;

public class SetNewPasswordActivity extends GrosirMobilActivity {

    private String email = "";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_email) TextInputEditText etEmail;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_password) TextInputEditText etPassword;
    private GrosirMobilFunction grosirMobilFunction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);
        ButterKnife.bind(this);

        email = getIntent().getStringExtra(EMAIL);
        etEmail.setText(email);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_change_password)
    void btnChangePasswordClick(){
        if (etPassword.getText().toString().length()<8) {
            Toast.makeText(this, "Mohon Isi Password Minimal 8 Karakter", Toast.LENGTH_SHORT).show();
        } else {
            ProgressDialog progressDialog = new ProgressDialog(SetNewPasswordActivity.this);
            progressDialog.setCancelable(true);
            progressDialog.setMessage(getString(R.string.base_tv_please_wait));
            progressDialog.show();

            ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(etEmail.getText().toString(), etPassword.getText().toString() + "");
            final Call<GeneralResponse> changePasswordApi = getApiGrosirMobil().changePasswordApi(changePasswordRequest);
            changePasswordApi.enqueue(new Callback<GeneralResponse>() {
                @Override
                public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                    progressDialog.dismiss();
                    if (response.isSuccessful()) {
                        try {
                            if (response.body().getMessage().equals("success")) {
                                Toast.makeText(SetNewPasswordActivity.this, response.body().getDescription(), Toast.LENGTH_SHORT).show();
                                //grosirMobilPreference.clearSharePreference();
                                //Intent intent = new Intent(SetNewPasswordActivity.this, LoginActivity.class);
                                Intent intent = new Intent(SetNewPasswordActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                try {
                                    grosirMobilFunction.showMessage(SetNewPasswordActivity.this, getString(R.string.base_null_error_title), response.errorBody().string());
                                } catch (IOException e) {
                                    GrosirMobilLog.printStackTrace(e);
                                }
                            }
                        } catch (Exception e) {
                            GrosirMobilLog.printStackTrace(e);
                        }
                    }
                }

                @Override
                public void onFailure(Call<GeneralResponse> call, Throwable t) {
                    grosirMobilFunction.showMessage(SetNewPasswordActivity.this, "GET Change Password", getString(R.string.base_null_server));
                    GrosirMobilLog.printStackTrace(t);
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