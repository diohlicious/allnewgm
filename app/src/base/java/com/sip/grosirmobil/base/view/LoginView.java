package com.sip.grosirmobil.base.view;

import android.app.ProgressDialog;

import com.sip.grosirmobil.cloud.config.response.login.LoginResponse;

public interface LoginView {

    void showValidationError();

    void showDialogLoading(ProgressDialog progressDialog);

    void hideDialogLoading(ProgressDialog progressDialog);

    void loginSuccess(LoginResponse response);

    void loginErrorResponse(String errorResponse);

    void loginFailed();
}
