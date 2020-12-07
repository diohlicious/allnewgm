package com.sip.grosirmobil.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.sip.grosirmobil.R;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class RegisterActivity extends GrosirMobilActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_full_name) TextInputEditText etFullName;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_phone_number) TextInputEditText etPhoneNumber;
//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.et_password) TextInputEditText etPassword;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cb_term_and_condition) CheckBox cbTermAndCondition;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_register) Button btnRegister;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_login) LinearLayout linearLogin;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_login) TextView tvLogin;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.relative_dialog) RelativeLayout relativeLayoutDialog;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_tidak_setuju) Button btnTidakSetuju;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_setuju) Button btnSetuju;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.iv_close) ImageView ivClose;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.web_view_term) WebView webViewTerm;

    private GrosirMobilPreference grosirMobilPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        grosirMobilPreference = new GrosirMobilPreference(this);

        etFullName.setFilters(new InputFilter[] {new InputFilter.AllCaps()});

        webViewTerm.setNestedScrollingEnabled(false);
        webViewTerm.loadData(getString(R.string.tv_term_and_condition_html), "text/html; charset=utf-8", "utf-8");
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick({R.id.iv_close,R.id.btn_tidak_setuju})
    void ivCloseClick(){
        cbTermAndCondition.setChecked(false);
        relativeLayoutDialog.setVisibility(View.GONE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_setuju)
    void btnSetujuClick(){
        cbTermAndCondition.setChecked(true);
        relativeLayoutDialog.setVisibility(View.GONE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnCheckedChanged(R.id.cb_term_and_condition)
    void cbTermAndConditionChecked(){
        relativeLayoutDialog.setVisibility(View.VISIBLE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_register)
    void btnRegisterClick(){
        if(etFullName.getText().toString().isEmpty()){
            Toast.makeText(this, "Mohon Isi Nama Lengkap", Toast.LENGTH_SHORT).show();
        }else if(etPhoneNumber.getText().toString().isEmpty()){
            Toast.makeText(this, "Mohon Isi Nomor Telepon", Toast.LENGTH_SHORT).show();
        }else {
            if(cbTermAndCondition.isChecked()){
                grosirMobilPreference.saveFullName(etFullName.getText().toString());
                grosirMobilPreference.savePhoneNumber(etPhoneNumber.getText().toString());
                Intent intent = new Intent(this, RegisterDataActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(this, "Mohon Centang Term And Condition", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @OnClick({R.id.linear_login,R.id.tv_login})
    void linearLoginClick(){
        finish();
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