package com.sip.grosirmobil.base.implement;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;

import com.naa.data.Nson;
import com.naa.utils.InternetX;
import com.sip.grosirmobil.R;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.base.presenter.LoginPresenter;
import com.sip.grosirmobil.base.view.LoginView;
import com.sip.grosirmobil.cloud.config.request.login.LoginRequest;
import com.sip.grosirmobil.cloud.config.response.login.LoginResponse;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

import static com.sip.grosirmobil.base.GrosirMobilApp.getApiGrosirMobil;


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
            LoginRequest loginRequest = new LoginRequest(email,password, false);
            progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(false);
            progressDialog.setMessage(context.getString(R.string.base_tv_please_wait));
            loginView.showDialogLoading(progressDialog);
            final Single<LoginResponse> loginAPi = getApiGrosirMobil().loginApi(loginRequest);

            loginAPi.subscribeOn(Schedulers.io())

                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<LoginResponse>() {
                        @Override
                        public void onSuccess(LoginResponse loginResponse) {
                            loginView.hideDialogLoading(progressDialog);
                            //loginView.loginSuccess(loginResponse);
                            try {
                                if(loginResponse.getDataLoginResponse().getToken() != null){
                                    loginView.loginSuccess(loginResponse);
                                }else {
                                    loginView.loginErrorResponse("User login salah");
                                }
                            }catch (Exception e){
                                GrosirMobilLog.printStackTrace(e);
                                loginView.loginErrorResponse("User login salah");
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            loginView.hideDialogLoading(progressDialog);

                            if (e instanceof HttpException) {
                                try {
                                    StringBuilder sb = new StringBuilder();
                                    HttpException httpException = (HttpException)e;
                                    int statusCode = httpException.code();
                                    InputStream inputStream = httpException.response().errorBody().byteStream();
                                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                                    String line = null;
                                    while ((line = br.readLine()) != null) {
                                        sb.append(line + "\n");
                                    }
                                    br.close();
                                    String string = sb.toString();
                                    Nson nson = Nson.readJson(string);
                                    loginView.loginErrorResponse(nson.get("error").asString());
                                    return;

                                }catch (Exception e1){

                                }



                                // handle different HTTP error codes here (4xx)
                            }

                            GrosirMobilLog.printStackTrace(e);
                            loginView.loginFailed();
                        }
                    });
        }
    }
}
