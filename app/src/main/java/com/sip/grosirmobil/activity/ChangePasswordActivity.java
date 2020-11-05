package com.sip.grosirmobil.activity;

import android.os.Bundle;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;

import butterknife.ButterKnife;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.EMAIL;

public class ChangePasswordActivity extends GrosirMobilActivity {

    private String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);

        email = getIntent().getStringExtra(EMAIL);
    }
}