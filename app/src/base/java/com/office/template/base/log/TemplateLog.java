package com.office.template.base.log;

import android.util.Log;

import com.office.template.BuildConfig;

/**
 * Created by Syahrul Hajji on 22/09/18.
 */
public class TemplateLog {

    public static void e(String tag, String message) {
        if(BuildConfig.DEBUG) {
            Log.e(tag, message);
        } else {
//            Crashlytics.log(tag + ": " + message);
        }
    }

    public static void i(String tag, String message) {
        if(BuildConfig.DEBUG) {
            Log.i(tag, message);
        } else {
//            Crashlytics.log(tag + ": " + message);
        }
    }

    public static void d(String tag, String message) {
        if(BuildConfig.DEBUG) {
            Log.d(tag, message);
        } else {
//            Crashlytics.log(tag + ": " + message);
        }
    }

    public static void w(String tag, String message) {
        if(BuildConfig.DEBUG) {
            Log.w(tag, message);
        } else {
//            Crashlytics.log(tag + ": " + message);
        }
    }

    public static void v(String tag, String message) {
        if(BuildConfig.DEBUG) {
            Log.v(tag, message);
        } else {
//            Crashlytics.log(tag + ": " + message);
        }
    }

    public static void printStackTrace(Exception e) {
        if(BuildConfig.DEBUG) {
            e.printStackTrace();
        }
    }

    public static void printStackTrace(Throwable e) {
        if (BuildConfig.DEBUG) {
            e.printStackTrace();
        } else {
//            Crashlytics.logException(e);
        }
    }
}
