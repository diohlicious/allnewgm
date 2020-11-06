package com.sip.grosirmobil.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.sip.grosirmobil.R;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import static com.sip.grosirmobil.base.function.GrosirMobilFunction.adjustFontScale;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setStatusBarOnBoarding;

public class CodeOtpActivity extends GrosirMobilActivity {
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_new_otp_1) EditText etNewOtp1;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_new_otp_2) EditText etNewOtp2;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_new_otp_3) EditText etNewOtp3;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_new_otp_4) EditText etNewOtp4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarOnBoarding(this);
        setContentView(R.layout.activity_code_otp);
        adjustFontScale(this, getResources().getConfiguration());
        ButterKnife.bind(this);


    }

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
                    }
                }
            }
        }
    }

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
                    }
                }
            }
        }
    }

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
                    }
                }
            }
        }
    }

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
                    }
                }
            }
        }
    }

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
                    }
                }
            }
        }
    }

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
                    }
                }
            }
        }
    }

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
                    }
                }
            }
        }
    }

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
                    }
                }
            }
        }
    }

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
                    }
                }
            }
        }
    }

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
                    }
                }
            }
        }
    }

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
                    }
                }
            }
        }

    }

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
                    }
                }
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_verified_password)
    void btnVerifiedPassword(){
        if(etNewOtp1.getText().toString().isEmpty()){
            Toast.makeText(this, "Kode 1 is empty", Toast.LENGTH_SHORT).show();
        }else if(etNewOtp2.getText().toString().isEmpty()){
            Toast.makeText(this, "Kode 2 is empty", Toast.LENGTH_SHORT).show();
        }else if(etNewOtp3.getText().toString().isEmpty()){
            Toast.makeText(this, "Kode 3 is empty", Toast.LENGTH_SHORT).show();
        }else if(etNewOtp4.getText().toString().isEmpty()){
            Toast.makeText(this, "Kode 4 is empty", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent = new Intent(this, RegisterDataActivity.class);
            startActivity(intent);
        }
    }



}