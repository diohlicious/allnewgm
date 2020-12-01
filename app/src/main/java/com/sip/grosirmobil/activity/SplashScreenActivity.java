package com.sip.grosirmobil.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;

import butterknife.ButterKnife;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.REQUEST_MAIN;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.adjustFontScale;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setStatusBarSplashScreen;

/**
 * Created by Syahrul Hajji on 22/09/18.
 */
public class SplashScreenActivity extends GrosirMobilActivity {

    int progressBarValue = 0;
    Handler handler = new Handler();

    GrosirMobilPreference grosirMobilPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarSplashScreen(this);
        setContentView(R.layout.activity_splash_screen);
        adjustFontScale(this, getResources().getConfiguration());
        ButterKnife.bind(this);

        grosirMobilPreference = new GrosirMobilPreference(this);

        new Thread(() -> {
            while(progressBarValue < 100) {
                progressBarValue++;
                handler.post(() -> {
                    if(progressBarValue==100){
                        if(grosirMobilPreference.getToken()==null||grosirMobilPreference.getToken().equals("")){
                            Intent splashScreenActivity = new Intent(SplashScreenActivity.this, OnBoardingActivity.class);
                            startActivity(splashScreenActivity);
                            finish();
                        }else {
                            Intent mainActivity = new Intent(SplashScreenActivity.this, MainActivity.class);
                            mainActivity.putExtra(REQUEST_MAIN, "");
                            startActivity(mainActivity);
                            finish();
                        }
                    }
                });
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
