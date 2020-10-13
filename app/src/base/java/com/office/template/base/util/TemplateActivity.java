package com.office.template.base.util;

import android.annotation.SuppressLint;

import androidx.appcompat.app.AppCompatActivity;

import com.office.template.base.TemplateApp;
import com.office.template.cloud.config.TemplateApi;

/**
 * Created by Syahrul Hajji on 22/09/18.
 */

@SuppressLint("Registered")
public class TemplateActivity extends AppCompatActivity {

    public TemplateApi getApiTemplate() {
        return TemplateApp.getApiTemplate();
    }
}
