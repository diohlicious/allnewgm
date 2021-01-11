package com.sip.grosirmobil.base.function;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.sip.grosirmobil.R;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.base.util.GrosirMobilSpannable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static android.content.Context.WINDOW_SERVICE;

/**
 * Created by Syahrul Hajji on 22/09/18.
 */
public class GrosirMobilFunction {

    Context context;

    public GrosirMobilFunction(Context context) {
        super();
        this.context = context;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.toolbar_gradient);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarSplashScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.toolbar_status_splash_screen);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }

    public static String bitmapToBase64String(Bitmap bmp, int quality) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        byte[] bytes = baos.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    public Bitmap getBitmap(String path, ImageView imageView) {
        Bitmap bitmap=null;
        try {
            File f= new File(path);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            bitmap = BitmapFactory.decodeStream(new FileInputStream(f), null, options);
            imageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap ;
    }
//
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    public static void setStatusBarNotificationFragment(Activity activity) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = activity.getWindow();
////            Drawable background = activity.getResources().getDrawable(R.drawable.ic_bar_notification);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
////            window.setBackgroundDrawable(background);
//        }
//    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarFragment(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            Drawable drawable = activity.getResources().getDrawable(R.drawable.toolbar_gradient);
            window.setBackgroundDrawable(drawable);
        }
    }

    public static void adjustFontScale(Context context, Configuration configuration) {
        configuration.fontScale = (float) 1.0;
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        metrics.scaledDensity = configuration.fontScale * metrics.density;
        context.getResources().updateConfiguration(configuration, metrics);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarOnBoarding(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            Drawable drawable = activity.getResources().getDrawable(R.drawable.toolbar_gradient);
            window.setBackgroundDrawable(drawable);
        }
    }

    public static String convertDate(String inputDateString, String inputDateFormatString, String outputDateFormatString) {
        Date date;
        String outputDateString = null;
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputDateFormatString);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputDateFormatString);
        try {
            date = inputDateFormat.parse(inputDateString);
            outputDateString = outputDateFormat.format(date);
        } catch (ParseException e) {
            GrosirMobilLog.printStackTrace(e);
        }
        return outputDateString;
    }

    public static long getSecondTime(String inputDateString, String outputDateString){
        long secondsOutput = 0;
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat liveDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date inputDate = liveDateFormat.parse(inputDateString);
            Date outputDate = liveDateFormat.parse(outputDateString);


            long diff = outputDate.getTime() - inputDate.getTime();
            long seconds = diff / 1;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            long days = hours / 24;

            secondsOutput = seconds;
//            if (oldDate.before(currentDate)) {
//
//                Log.e("oldDate", "is previous date");
//                Log.e("Difference: ", " seconds: " + seconds + " minutes: " + minutes
//                        + " hours: " + hours + " days: " + days);
//
//            }

            // Log.e("toyBornTime", "" + toyBornTime);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return secondsOutput;
    }

    public static String convertDateServer(String inputDateString){
        Date date;
        String outPutTimeServer = null;
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = inputDateFormat.parse(inputDateString);
            outPutTimeServer = outputDateFormat.format(date);
        }catch (ParseException e){
            GrosirMobilLog.printStackTrace(e);
        }
        return outPutTimeServer;
    }

    public static long calculateDate(String start_date,
                                      String end_date) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        long timeToLong = 0;
        try {
            Date d1 = sdf.parse(start_date);
            Date d2 = sdf.parse(end_date);
            long difference_In_Time
                    = d2.getTime() - d1.getTime();

            long difference_In_Seconds
                    = TimeUnit.MILLISECONDS
                    .toSeconds(difference_In_Time)
                    % 60;

            long difference_In_Minutes
                    = TimeUnit
                    .MILLISECONDS
                    .toMinutes(difference_In_Time)
                    % 60;

            long difference_In_Hours
                    = TimeUnit
                    .MILLISECONDS
                    .toHours(difference_In_Time)
                    % 24;

            long difference_In_Days
                    = TimeUnit
                    .MILLISECONDS
                    .toDays(difference_In_Time)
                    % 365;

            long difference_In_Years
                    = TimeUnit
                    .MILLISECONDS
                    .toDays(difference_In_Time)
                    / 365l;
            
            timeToLong = difference_In_Time;

            System.out.print(
                    "Difference"
                            + " between two dates is: ");

            
            // Print result
            System.out.println(
                    difference_In_Years
                            + " years, "
                            + difference_In_Days
                            + " days, "
                            + difference_In_Hours
                            + " hours, "
                            + difference_In_Minutes
                            + " minutes, "
                            + difference_In_Seconds
                            + " seconds");
            
        }

        catch (ParseException e) {
            GrosirMobilLog.printStackTrace(e);
        }
        return timeToLong;
    }
//    public static void calculateDate(String start_date,
//                                      String end_date) {
//        @SuppressLint("SimpleDateFormat")
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//
//        try {
//            Date d1 = sdf.parse(start_date);
//            Date d2 = sdf.parse(end_date);
//            long difference_In_Time
//                    = d2.getTime() - d1.getTime();
//
//            long difference_In_Seconds
//                    = TimeUnit.MILLISECONDS
//                    .toSeconds(difference_In_Time)
//                    % 60;
//
//            long difference_In_Minutes
//                    = TimeUnit
//                    .MILLISECONDS
//                    .toMinutes(difference_In_Time)
//                    % 60;
//
//            long difference_In_Hours
//                    = TimeUnit
//                    .MILLISECONDS
//                    .toHours(difference_In_Time)
//                    % 24;
//
//            long difference_In_Days
//                    = TimeUnit
//                    .MILLISECONDS
//                    .toDays(difference_In_Time)
//                    % 365;
//
//            long difference_In_Years
//                    = TimeUnit
//                    .MILLISECONDS
//                    .toDays(difference_In_Time)
//                    / 365l;
//
//            System.out.print(
//                    "Difference"
//                            + " between two dates is: ");
//
//            // Print result
//            System.out.println(
//                    difference_In_Years
//                            + " years, "
//                            + difference_In_Days
//                            + " days, "
//                            + difference_In_Hours
//                            + " hours, "
//                            + difference_In_Minutes
//                            + " minutes, "
//                            + difference_In_Seconds
//                            + " seconds");
//        }
//        catch (ParseException e) {
//            GrosirMobilLog.printStackTrace(e);
//        }
//    }



    public void showMessage(String title, String message) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Close", (dialog, which) -> dialog.dismiss())
                .show();
    }

    public void showMessage(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(context.getString(R.string.btn_close), null);
        AlertDialog dialog = builder.create();

        dialog.show();
        Button btnYes = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        btnYes.setTextColor(context.getResources().getColor(R.color.colorAccent));
        btnYes.setOnClickListener(v -> {
            dialog.dismiss();

        });
    }

    public BitmapDescriptor bitmapDescriptorFromVector(Context context) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, R.drawable.ic_base_location);
        vectorDrawable.setBounds(0, 0, 60, 80);
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    public static void makeTextViewResizable(final TextView tv, final int maxLine, final String expandText, final boolean viewMore) {

        if (tv.getTag() == null) {
            tv.setTag(tv.getText());
        }
        ViewTreeObserver vto = tv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {

                ViewTreeObserver obs = tv.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (maxLine == 0) {
                    int lineEndIndex = tv.getLayout().getLineEnd(0);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + "" + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else if (maxLine > 0 && tv.getLineCount() >= maxLine) {
                    int lineEndIndex = tv.getLayout().getLineEnd(maxLine - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + "" + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else {
                    int lineEndIndex = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex) + "" + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, lineEndIndex, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                }
            }
        });

    }

    private static SpannableStringBuilder addClickablePartTextViewResizable(final Spanned strSpanned, final TextView tv,
                                                                            final int maxLine, final String spanableText, final boolean viewMore) {
        String str = strSpanned.toString();
        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);

        if (str.contains(spanableText)) {


            ssb.setSpan(new GrosirMobilSpannable(false) {
                @Override
                public void onClick(View widget) {
                    if (viewMore) {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, -1, spanableText, false);
                    } else {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, 1, spanableText, true);
                    }
                }
            }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length(), 0);

        }
        return ssb;
    }

//    public void refreshToken(Activity activity, Context context) {
//        GrosirMobilPreference grosirMobilPreference = new GrosirMobilPreference(context);
//        GrosirMobilFunction funcUtil = new GrosirMobilFunction(context);
//
//        final Call<LoginResponse> refreshTokenApi = getApiGrosirMobil().refreshTokenApi("Bearer " + grosirMobilPreference.getToken());
//        ProgressDialog progressDialog = new ProgressDialog(context);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage(context.getString(R.string.base_tv_please_wait));
//        progressDialog.show();
//        refreshTokenApi.enqueue(new Callback<LoginResponse>() {
//            @Override
//            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
//                progressDialog.dismiss();
//                if (response.isSuccessful()) {
//                    try {
//                        if (response.body() == null) {
//                            Intent intent = new Intent(context, LoginActivity.class);
//                            context.startActivity(intent);
//                            activity.finish();
//                        } else if (response.body().getErrorCode().equals("0000")) {
//                            grosirMobilPreference.saveToken(response.body().getToken());
//                            Intent intentMain = new Intent(context, MainActivity.class);
//                            intentMain.putExtra(REQUEST_MAIN, "");
//                            context.startActivity(intentMain);
//                            activity.finish();
//                        } else {
//                            Intent intent = new Intent(context, LoginActivity.class);
//                            context.startActivity(intent);
//                        }
//                    } catch (Exception e) {
//                        GrosirMobilLog.printStackTrace(e);
//                    }
//                } else {
//                    try {
//                        funcUtil.showMessage(context, context.getString(R.string.base_null_error_title), response.errorBody().string());
//                    } catch (IOException e) {
//                        GrosirMobilLog.printStackTrace(e);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
//                progressDialog.dismiss();
//                funcUtil.showMessage(context, context.getString(R.string.base_null_error_title), context.getString(R.string.base_null_server));
//                GrosirMobilLog.printStackTrace(t);
//            }
//        });
//    }

    public void showMessageResponse(Activity activity, Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(context.getString(R.string.btn_close), null);
        AlertDialog dialog = builder.create();

        dialog.show();
        Button btnYes = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        btnYes.setTextColor(context.getResources().getColor(R.color.colorAccent));
        btnYes.setOnClickListener(v -> {
            dialog.dismiss();
            activity.finish();
        });
    }

    public Bitmap decodeFile(File f) {
        try {
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            final int REQUIRED_SIZE = 200;
            int scale = 1;

            while (o.outWidth / scale / 2 >= REQUIRED_SIZE && o.outHeight / scale / 2 >= REQUIRED_SIZE)
                scale *= 2;

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;

            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);

        } catch (FileNotFoundException e) {
            GrosirMobilLog.printStackTrace(e);
        }
        return null;
    }

    private static DecimalFormatSymbols commaSeparator = null;

    public static String setCurrencyFormat(String str) {
        if (commaSeparator == null) {
            commaSeparator = new DecimalFormatSymbols();
            commaSeparator.setGroupingSeparator('.');
        }
        if (str.length() == 0) return "";
        str = str.replaceAll("[^0-9]", "");
        if (str.length() == 0) return "";
        long value = Long.parseLong(str);
        DecimalFormat comma = new DecimalFormat("#,###", commaSeparator);
        return comma.format(value);
    }

    public static String removeCurrencyFormat(String str) {
        return str.replace(".", "");
    }

    public static TextWatcher onTextChangedListener(EditText editText) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editText.removeTextChangedListener(this);

                try {
                    String originalString = s.toString();

                    Long longval;
                    if (originalString.contains(".")) {
                        originalString = originalString.replaceAll("[^0-9]", "");
                    }
                    longval = Long.parseLong(originalString);

                    Locale localeID = new Locale("in", "ID");
                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(localeID);
                    formatter.applyPattern("#,###,###,###");
                    String formattedString = formatter.format(longval);

                    //setting text after format to EditText
                    editText.setText(formattedString);
                    editText.setSelection(editText.getText().length());
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                editText.addTextChangedListener(this);
            }
        };
    }
}
