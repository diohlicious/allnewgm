package com.sip.grosirmobil.base.util;

import android.annotation.SuppressLint;

import androidx.appcompat.app.AppCompatActivity;

import com.sip.grosirmobil.base.GrosirMobilApp;
import com.sip.grosirmobil.cloud.config.GrosirMobilApi;

/**
 * Created by Syahrul Hajji on 22/09/18.
 */

@SuppressLint("Registered")
public class GrosirMobilActivity extends AppCompatActivity {

    public GrosirMobilApi getApiTemplate() {
        return GrosirMobilApp.getApiTemplate();
    }
}
