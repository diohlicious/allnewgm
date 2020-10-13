package com.office.template.base.view;

import android.app.ProgressDialog;

import com.office.template.cloud.config.response.login.LoginResponse;

public interface LoginView {

    void showValidationError();

    void showDialogLoading(ProgressDialog progressDialog);

    void hideDialogLoading(ProgressDialog progressDialog);

    void loginSuccess(LoginResponse response);

    void loginErrorResponse(String errorResponse);

    void loginFailed();
}
