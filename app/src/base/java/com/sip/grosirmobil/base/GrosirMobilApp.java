package com.sip.grosirmobil.base;

import android.annotation.SuppressLint;
import android.app.Application;

import com.sip.grosirmobil.BuildConfig;
import com.sip.grosirmobil.base.contract.GrosirMobilContract;
import com.sip.grosirmobil.cloud.config.GrosirMobilApi;

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
public class GrosirMobilApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        Fabric.with(this, new Crashlytics());
    }

    public static GrosirMobilApi getApiGrosirMobil() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();

        return retrofit.create(GrosirMobilApi.class);
    }

    public static OkHttpClient getClient() {

        return new OkHttpClient.Builder()
                .connectTimeout(GrosirMobilContract.timesOut, TimeUnit.MINUTES)
                .readTimeout(GrosirMobilContract.timesOut,TimeUnit.MINUTES)
                .writeTimeout(GrosirMobilContract.timesOut,TimeUnit.MINUTES)
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
