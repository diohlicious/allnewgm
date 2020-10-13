package com.office.template.base.implement;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;

import com.office.template.R;
import com.office.template.base.log.TemplateLog;
import com.office.template.base.presenter.LoginPresenter;
import com.office.template.base.view.LoginView;
import com.office.template.cloud.config.request.login.LoginRequest;
import com.office.template.cloud.config.response.login.LoginResponse;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.office.template.base.TemplateApp.getApiTemplate;

public class LoginPresenterImp implements LoginPresenter {

    private LoginView loginView;
    private ProgressDialog progressDialog;
    private Context context;

    public LoginPresenterImp(Context context, LoginView loginView) {
        this.context = context;
        this.loginView = loginView;
    }

    @SuppressLint("CheckResult")
    @Override
    public void login(String email, String password) {
        if(email.isEmpty()&&password.isEmpty()){
            loginView.showValidationError();
        }else {
            LoginRequest loginRequest = new LoginRequest(email,password);
            progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(false);
            progressDialog.setMessage(context.getString(R.string.base_tv_please_wait));
            loginView.showDialogLoading(progressDialog);
            final Single<LoginResponse> loginAPi = getApiTemplate().loginApi(loginRequest);
            loginAPi.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<LoginResponse>() {
                        @Override
                        public void onSuccess(LoginResponse loginResponse) {
                            loginView.hideDialogLoading(progressDialog);
                            try {
                                if(loginResponse.getErrorCode().equals("0000")){
                                    loginView.loginSuccess(loginResponse);
                                }else {
                                    loginView.loginErrorResponse(loginResponse.getErrorDesc());
                                }
                            }catch (Exception e){
                                TemplateLog.printStackTrace(e);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            loginView.hideDialogLoading(progressDialog);
                            TemplateLog.printStackTrace(e);
                            loginView.loginFailed();
                        }
                    });
        }
    }
}
