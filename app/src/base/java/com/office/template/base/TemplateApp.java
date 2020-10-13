package com.office.template.base;

import android.annotation.SuppressLint;
import android.app.Application;

import com.office.template.BuildConfig;
import com.office.template.base.contract.TemplateContract;
import com.office.template.cloud.config.TemplateApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Syahrul Hajji on 22/09/18.
 */

@SuppressLint("Registered")
public class TemplateApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        Fabric.with(this, new Crashlytics());
    }

    public static TemplateApi getApiTemplate() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();

        return retrofit.create(TemplateApi.class);
    }

    public static OkHttpClient getClient() {

        return new OkHttpClient.Builder()
                .connectTimeout(TemplateContract.timesOut, TimeUnit.MINUTES)
                .readTimeout(TemplateContract.timesOut,TimeUnit.MINUTES)
                .writeTimeout(TemplateContract.timesOut,TimeUnit.MINUTES)
                .addInterceptor(getLoggingInterceptor())
                .build();
    }

    public static HttpLoggingInterceptor.Level getInterceptorLevel() {
        if (BuildConfig.DEBUG) return HttpLoggingInterceptor.Level.BODY;
        else return HttpLoggingInterceptor.Level.NONE;
    }

    public static HttpLoggingInterceptor getLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(getInterceptorLevel());
        return httpLoggingInterceptor;
    }
}
