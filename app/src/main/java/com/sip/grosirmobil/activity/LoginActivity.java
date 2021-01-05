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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.sip.grosirmobil.BuildConfig;
import com.sip.grosirmobil.R;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.implement.LoginPresenterImp;
import com.sip.grosirmobil.base.presenter.LoginPresenter;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;
import com.sip.grosirmobil.base.view.LoginView;
import com.sip.grosirmobil.cloud.config.response.login.LoginResponse;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.EMAIL;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.REQUEST_MAIN;

@SuppressLint("Registered")
public class LoginActivity extends GrosirMobilActivity implements LoginView {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_email) TextInputEditText etEmail;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_password) TextInputEditText etPassword;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_register) LinearLayout linearRegister;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_register) TextView tvRegister;

    private GrosirMobilPreference grosirMobilPreference;
    private GrosirMobilFunction templateFunction;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        grosirMobilPreference = new GrosirMobilPreference(getApplicationContext());
        templateFunction = new GrosirMobilFunction(getApplicationContext());

        if(BuildConfig.ENVIRONTMENT.equals("STAGING")){
            etEmail.setText("syahrulhajji@gmail.com");
            etPassword.setText("123456");
        }

        loginPresenter = new LoginPresenterImp(this, this);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_login)
    void btnLoginClick(){
        if(etEmail.getText().toString().isEmpty()){
            Toast.makeText(this, "Mohon Isi Email", Toast.LENGTH_SHORT).show();
        }else if(etPassword.getText().toString().isEmpty()){
            Toast.makeText(this, "Mohon Isi Password", Toast.LENGTH_SHORT).show();
        }else {
            loginPresenter.login(etEmail.getText().toString(), etPassword.getText().toString());
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick({R.id.tv_register,R.id.linear_register})
    void tvRegisterClick(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_forgot_password)
    void tvForgotPasswordClick(){
        if(etEmail.getText().toString().isEmpty()||etEmail.getText().toString().equals("")){
            Toast.makeText(this, "Mohon Isi Email", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(this, ForgotPasswordActivity.class);
            intent.putExtra(EMAIL, etEmail.getText().toString());
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void showValidationError() {
        templateFunction.showMessage(getString(R.string.app_name), getString(R.string.tv_empty_email_password));
    }

    @Override
    public void loginSuccess(LoginResponse response) {
        grosirMobilPreference.saveToken(response.getDataLoginResponse().getToken());
        grosirMobilPreference.saveDataLogin(response.getDataLoginResponse());
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(REQUEST_MAIN, "");
        startActivity(intent);
        finish();
    }

    @Override
    public void loginErrorResponse(String errorResponse) {
        templateFunction.showMessage(this, getString(R.string.app_name), errorResponse);
    }

    @Override
    public void showDialogLoading(ProgressDialog progressDialog) {
        progressDialog.show();
    }

    @Override
    public void hideDialogLoading(ProgressDialog progressDialog) {
        progressDialog.dismiss();
    }

    @Override
    public void loginFailed() {
        templateFunction.showMessage(LoginActivity.this, getString(R.string.base_null_error_title),getString(R.string.base_null_server));
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
