package com.office.template.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.office.template.R;
import com.office.template.base.data.TemplatePreference;
import com.office.template.base.function.TemplateFunction;
import com.office.template.base.implement.LoginPresenterImp;
import com.office.template.base.presenter.LoginPresenter;
import com.office.template.base.util.TemplateActivity;
import com.office.template.base.view.LoginView;
import com.office.template.cloud.config.response.login.LoginResponse;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressLint("Registered")
public class LoginActivity extends TemplateActivity implements LoginView {

    @BindView(R.id.et_email) EditText etEmail;
    @BindView(R.id.et_password) TextInputEditText etPassword;

    private TemplatePreference templatePreference;
    private TemplateFunction templateFunction;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        templatePreference = new TemplatePreference(getApplicationContext());
        templateFunction = new TemplateFunction(getApplicationContext());

        loginPresenter = new LoginPresenterImp(this, this);
    }

    @OnClick(R.id.btn_login)
    void btnLoginClick(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
//        loginPresenter.login(etEmail.getText().toString(), etPassword.getText().toString());
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
        templatePreference.saveToken(response.getToken());
        Intent intent = new Intent(this, MainActivity.class);
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
