package com.sip.grosirmobil.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.cloud.config.request.resendotp.ResendOtpRequest;
import com.sip.grosirmobil.cloud.config.request.validationotp.ValidationOtpRequest;
import com.sip.grosirmobil.cloud.config.response.GeneralResponse;
import com.sip.grosirmobil.fragment.register.RegisterSuccessFragment;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sip.grosirmobil.base.GrosirMobilApp.getApiGrosirMobil;

public class CodeOtpFragment extends Fragment {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_new_otp_1) EditText etNewOtp1;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_new_otp_2) EditText etNewOtp2;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_new_otp_3) EditText etNewOtp3;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_new_otp_4) EditText etNewOtp4;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_new_otp_5) EditText etNewOtp5;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_new_otp_6) EditText etNewOtp6;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_resend_otp) TextView tvResendOtp;

    private GrosirMobilFunction grosirMobilFunction;
    private GrosirMobilPreference grosirMobilPreference;

    public static CodeOtpFragment newInstance(int page, String title) {
        CodeOtpFragment fragmentFirst = new CodeOtpFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_code_otp, container, false);
        ButterKnife.bind(this, view);

        grosirMobilFunction = new GrosirMobilFunction(getActivity());
        grosirMobilPreference = new GrosirMobilPreference(getActivity());

        return view;
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_resend_otp)
    void tvResendOtpClick(){
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.base_tv_please_wait));
        progressDialog.show();
        ResendOtpRequest resendOtpRequest = new ResendOtpRequest(grosirMobilPreference.getUserId(), "Register", grosirMobilPreference.getPhoneNumber(), grosirMobilPreference.getEmail(), grosirMobilPreference.getFullName());
        final Call<GeneralResponse> resendOtpApi = getApiGrosirMobil().resendOtpApi(resendOtpRequest);
        resendOtpApi.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    try {
                        if(response.body().getMessage().equals("success")){
                            etNewOtp1.setText("");
                            etNewOtp2.setText("");
                            etNewOtp3.setText("");
                            etNewOtp4.setText("");
                            etNewOtp5.setText("");
                            etNewOtp6.setText("");
                        }else {
                            grosirMobilFunction.showMessage(getActivity(), "GET Resend OTP", response.body().getDescription());
                        }
                    }catch (Exception e){
                        GrosirMobilLog.printStackTrace(e);
                    }
                }else {
                    try {
                        grosirMobilFunction.showMessage(getActivity(), getString(R.string.base_null_error_title), response.errorBody().string());
                    } catch (IOException e) {
                        GrosirMobilLog.printStackTrace(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                grosirMobilFunction.showMessage(getActivity(), "GET Resend OTP", getString(R.string.base_null_server));
                GrosirMobilLog.printStackTrace(t);
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_0)
    void btn0Click(){
        if(etNewOtp1.getText().toString().equals("")){
            etNewOtp1.setText("0");
        }else {
            if(etNewOtp2.getText().toString().equals("")){
                etNewOtp2.setText("0");
            }else {
                if(etNewOtp3.getText().toString().equals("")){
                    etNewOtp3.setText("0");
                }else {
                    if(etNewOtp4.getText().toString().equals("")){
                        etNewOtp4.setText("0");
                    }else {
                        if(etNewOtp5.getText().toString().equals("")){
                            etNewOtp5.setText("0");
                        }else {
                            if(etNewOtp6.getText().toString().equals("")){
                                etNewOtp6.setText("0");
                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_1)
    void btn1Click(){
        if(etNewOtp1.getText().toString().equals("")){
            etNewOtp1.setText("1");
        }else {
            if(etNewOtp2.getText().toString().equals("")){
                etNewOtp2.setText("1");
            }else {
                if(etNewOtp3.getText().toString().equals("")){
                    etNewOtp3.setText("1");
                }else {
                    if(etNewOtp4.getText().toString().equals("")){
                        etNewOtp4.setText("1");
                    }else {
                        if(etNewOtp5.getText().toString().equals("")){
                            etNewOtp5.setText("1");
                        }else {
                            if(etNewOtp6.getText().toString().equals("")){
                                etNewOtp6.setText("1");
                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_2)
    void btn2Click(){
        if(etNewOtp1.getText().toString().equals("")){
            etNewOtp1.setText("2");
        }else {
            if(etNewOtp2.getText().toString().equals("")){
                etNewOtp2.setText("2");
            }else {
                if(etNewOtp3.getText().toString().equals("")){
                    etNewOtp3.setText("2");
                }else {
                    if(etNewOtp4.getText().toString().equals("")){
                        etNewOtp4.setText("2");
                    }else {
                        if(etNewOtp5.getText().toString().equals("")){
                            etNewOtp5.setText("2");
                        }else {
                            if(etNewOtp6.getText().toString().equals("")){
                                etNewOtp6.setText("2");
                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_3)
    void btn3Click(){
        if(etNewOtp1.getText().toString().equals("")){
            etNewOtp1.setText("3");
        }else {
            if(etNewOtp2.getText().toString().equals("")){
                etNewOtp2.setText("3");
            }else {
                if(etNewOtp3.getText().toString().equals("")){
                    etNewOtp3.setText("3");
                }else {
                    if(etNewOtp4.getText().toString().equals("")){
                        etNewOtp4.setText("3");
                    }else {
                        if(etNewOtp5.getText().toString().equals("")){
                            etNewOtp5.setText("3");
                        }else {
                            if(etNewOtp6.getText().toString().equals("")){
                                etNewOtp6.setText("3");
                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_4)
    void btn4Click(){
        if(etNewOtp1.getText().toString().equals("")){
            etNewOtp1.setText("4");
        }else {
            if(etNewOtp2.getText().toString().equals("")){
                etNewOtp2.setText("4");
            }else {
                if(etNewOtp3.getText().toString().equals("")){
                    etNewOtp3.setText("4");
                }else {
                    if(etNewOtp4.getText().toString().equals("")){
                        etNewOtp4.setText("4");
                    }else {
                        if(etNewOtp5.getText().toString().equals("")){
                            etNewOtp5.setText("4");
                        }else {
                            if(etNewOtp6.getText().toString().equals("")){
                                etNewOtp6.setText("4");
                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_5)
    void btn5Click(){
        if(etNewOtp1.getText().toString().equals("")){
            etNewOtp1.setText("5");
        }else {
            if(etNewOtp2.getText().toString().equals("")){
                etNewOtp2.setText("5");
            }else {
                if(etNewOtp3.getText().toString().equals("")){
                    etNewOtp3.setText("5");
                }else {
                    if(etNewOtp4.getText().toString().equals("")){
                        etNewOtp4.setText("5");
                    }else {
                        if(etNewOtp5.getText().toString().equals("")){
                            etNewOtp5.setText("5");
                        }else {
                            if(etNewOtp6.getText().toString().equals("")){
                                etNewOtp6.setText("5");
                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_6)
    void btn6Click(){
        if(etNewOtp1.getText().toString().equals("")){
            etNewOtp1.setText("6");
        }else {
            if(etNewOtp2.getText().toString().equals("")){
                etNewOtp2.setText("6");
            }else {
                if(etNewOtp3.getText().toString().equals("")){
                    etNewOtp3.setText("6");
                }else {
                    if(etNewOtp4.getText().toString().equals("")){
                        etNewOtp4.setText("6");
                    }else {
                        if(etNewOtp5.getText().toString().equals("")){
                            etNewOtp5.setText("6");
                        }else {
                            if(etNewOtp6.getText().toString().equals("")){
                                etNewOtp6.setText("6");
                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_7)
    void btn7Click(){
        if(etNewOtp1.getText().toString().equals("")){
            etNewOtp1.setText("7");
        }else {
            if(etNewOtp2.getText().toString().equals("")){
                etNewOtp2.setText("7");
            }else {
                if(etNewOtp3.getText().toString().equals("")){
                    etNewOtp3.setText("7");
                }else {
                    if(etNewOtp4.getText().toString().equals("")){
                        etNewOtp4.setText("7");
                    }else {
                        if(etNewOtp5.getText().toString().equals("")){
                            etNewOtp5.setText("7");
                        }else {
                            if(etNewOtp6.getText().toString().equals("")){
                                etNewOtp6.setText("7");
                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_8)
    void btn8Click(){
        if(etNewOtp1.getText().toString().equals("")){
            etNewOtp1.setText("8");
        }else {
            if(etNewOtp2.getText().toString().equals("")){
                etNewOtp2.setText("8");
            }else {
                if(etNewOtp3.getText().toString().equals("")){
                    etNewOtp3.setText("8");
                }else {
                    if(etNewOtp4.getText().toString().equals("")){
                        etNewOtp4.setText("8");
                    }else {
                        if(etNewOtp5.getText().toString().equals("")){
                            etNewOtp5.setText("8");
                        }else {
                            if(etNewOtp6.getText().toString().equals("")){
                                etNewOtp6.setText("8");
                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_9)
    void btn9Click(){
        if(etNewOtp1.getText().toString().equals("")){
            etNewOtp1.setText("9");
        }else {
            if(etNewOtp2.getText().toString().equals("")){
                etNewOtp2.setText("9");
            }else {
                if(etNewOtp3.getText().toString().equals("")){
                    etNewOtp3.setText("9");
                }else {
                    if(etNewOtp4.getText().toString().equals("")){
                        etNewOtp4.setText("9");
                    }else {
                        if(etNewOtp5.getText().toString().equals("")){
                            etNewOtp5.setText("9");
                        }else {
                            if(etNewOtp6.getText().toString().equals("")){
                                etNewOtp6.setText("9");
                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_back_press)
    void btnBackPressClick(){
        if(!etNewOtp4.getText().toString().equals("")){
            etNewOtp4.setText("");
        }else {
            if(!etNewOtp3.getText().toString().equals("")){
                etNewOtp3.setText("");
            }else {
                if(!etNewOtp2.getText().toString().equals("")){
                    etNewOtp2.setText("");
                }else {
                    if(!etNewOtp1.getText().toString().equals("")){
                        etNewOtp1.setText("");
                    }else {
                        if(!etNewOtp5.getText().toString().equals("")){
                            etNewOtp5.setText("");
                        }else {
                            if(!etNewOtp6.getText().toString().equals("")){
                                etNewOtp6.setText("");
                            }
                        }
                    }
                }
            }
        }

    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_dot)
    void btnDotClick(){
        if(etNewOtp1.getText().toString().equals("")){
            etNewOtp1.setText(".");
        }else {
            if(etNewOtp2.getText().toString().equals("")){
                etNewOtp2.setText(".");
            }else {
                if(etNewOtp3.getText().toString().equals("")){
                    etNewOtp3.setText(".");
                }else {
                    if(etNewOtp4.getText().toString().equals("")){
                        etNewOtp4.setText(".");
                    }else {
                        if(etNewOtp5.getText().toString().equals("")){
                            etNewOtp5.setText(".");
                        }else {
                            if(etNewOtp6.getText().toString().equals("")){
                                etNewOtp6.setText(".");
                            }
                        }
                    }
                }
            }
        }
    }



    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_verified_password)
    void btnVerifiedPassword(){
        String otp;
        if(etNewOtp1.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Kode 1 is empty", Toast.LENGTH_SHORT).show();
        }else if(etNewOtp2.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Kode 2 is empty", Toast.LENGTH_SHORT).show();
        }else if(etNewOtp3.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Kode 3 is empty", Toast.LENGTH_SHORT).show();
        }else if(etNewOtp4.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Kode 4 is empty", Toast.LENGTH_SHORT).show();
        }else if(etNewOtp5.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Kode 5 is empty", Toast.LENGTH_SHORT).show();
        }else if(etNewOtp6.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Kode 6 is empty", Toast.LENGTH_SHORT).show();
        }
        else{
            otp = etNewOtp1.getText().toString()+etNewOtp2.getText().toString()+etNewOtp3.getText().toString()+etNewOtp4.getText().toString()+etNewOtp5.getText().toString()+etNewOtp6.getText().toString();
            ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setCancelable(false);
            progressDialog.setMessage(getString(R.string.base_tv_please_wait));
            progressDialog.show();
            ValidationOtpRequest validationOtpRequest = new ValidationOtpRequest(Integer.parseInt(otp), Integer.parseInt(grosirMobilPreference.getUserId()));
            final Call<GeneralResponse> validationOtpApi = getApiGrosirMobil().validationOtpApi(validationOtpRequest);
            validationOtpApi.enqueue(new Callback<GeneralResponse>() {
                @Override
                public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                    progressDialog.dismiss();
                    if (response.isSuccessful()) {
                        try {
                            if(response.body().getMessage().equals("success")){
                                ((RegisterDataActivity)getActivity()).replaceFragment(new RegisterSuccessFragment());
                            }else {
                                grosirMobilFunction.showMessage(getActivity(), "GET Validation OTP", response.body().getDescription());
                            }
                        }catch (Exception e){
                            GrosirMobilLog.printStackTrace(e);
                        }
                    }else {
                        try {
                            grosirMobilFunction.showMessage(getActivity(), getString(R.string.base_null_error_title), response.errorBody().string());
                        } catch (IOException e) {
                            GrosirMobilLog.printStackTrace(e);
                        }
                    }
                }

                @Override
                public void onFailure(Call<GeneralResponse> call, Throwable t) {
                    grosirMobilFunction.showMessage(getActivity(), "GET Validation OTP", getString(R.string.base_null_server));
                    GrosirMobilLog.printStackTrace(t);
                }
            });
        }
    }



}