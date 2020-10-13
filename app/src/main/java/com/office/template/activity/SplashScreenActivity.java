package com.office.template.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.office.template.R;
import com.office.template.base.data.TemplatePreference;
import com.office.template.base.util.TemplateActivity;

import butterknife.ButterKnife;

import static com.office.template.base.function.TemplateFunction.setStatusBar;

/**
 * Created by Syahrul Hajji on 22/09/18.
 */
public class SplashScreenActivity extends TemplateActivity {

    int progressBarValue = 0;
    Handler handler = new Handler();

    TemplatePreference templatePreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar(this);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        templatePreference = new TemplatePreference(this);

        new Thread(() -> {
            while(progressBarValue < 100) {
                progressBarValue++;
                handler.post(() -> {
                    if(progressBarValue==100){
                        Intent mainActivity = new Intent(SplashScreenActivity.this, LoginActivity.class);
                        startActivity(mainActivity);
                        finish();
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
