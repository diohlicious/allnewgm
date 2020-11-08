package com.sip.grosirmobil.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;
import com.sip.grosirmobil.R;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.EMAIL;

public class ChangePasswordActivity extends GrosirMobilActivity {

    private String email = "";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_email) TextInputEditText etEmail;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_password) TextInputEditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);

        email = getIntent().getStringExtra(EMAIL);
    }
    @OnClick(R.id.btn_change_password)
    void btnChangePasswordClick(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}