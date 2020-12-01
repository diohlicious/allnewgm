package com.sip.grosirmobil.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.implement.MainPresenterImp;
import com.sip.grosirmobil.base.permission.MultiPermissionActivity;
import com.sip.grosirmobil.base.presenter.MainPresenter;
import com.sip.grosirmobil.base.util.GrosirMobilFragment;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;
import com.sip.grosirmobil.base.view.MainView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.REQUEST_CODE_MULTI_PERMISSION_ACTIVITY;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.REQUEST_MAIN;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.REQUEST_MULTI_PERMISSION;

/**
 * Created by Syahrul Hajji on 22/09/18.
 */
public class MainActivity extends GrosirMobilActivity implements GrosirMobilFragment.FragmentInteractionListener, MainView {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;

    //----------------------------------------------------------------------
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_home) LinearLayout linearHome;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_home) TextView tvHome;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.iv_home) ImageView ivHome;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_cart) LinearLayout linearCart;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_cart) TextView tvCart;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.iv_cart) ImageView ivCart;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_notification) LinearLayout linearNotification;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_notification) TextView tvNotification;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.iv_notification) ImageView ivNotification;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_win) LinearLayout linearWin;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_win) TextView tvWin;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.iv_win) ImageView ivWin;

    private GrosirMobilPreference grosirMobilPreference;
    private GrosirMobilFunction grosirMobilFunction;
    private MainPresenter presenter;
    boolean doubleBackToExitPressedOnce = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        grosirMobilPreference = new GrosirMobilPreference(this);
        grosirMobilFunction = new GrosirMobilFunction(this);

        presenter = new MainPresenterImp(this, this);
        checkAndRequestPermissions();

        switch (getIntent().getStringExtra(REQUEST_MAIN)) {
            case "":
                presenter.replaceFragmentHome();
                break;
            case "cart":
                linearCartClick();
                break;
            case "win":
                linearWinClick();
                break;
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.linear_home)
    void linearHomeClick(){
        presenter.replaceFragmentHome();
        ivHome.setImageResource(R.drawable.ic_home_enable);
        tvHome.setTextColor(getResources().getColor(R.color.colorPrimaryTextActive));
        ivCart.setImageResource(R.drawable.ic_cart_disable);
        tvCart.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
        ivNotification.setImageResource(R.drawable.ic_notification_disable);
        tvNotification.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
        ivWin.setImageResource(R.drawable.ic_win_disable);
        tvWin.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
    }


    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.linear_cart)
    public void linearCartClick(){
        presenter.replaceFragmentCart();
        ivHome.setImageResource(R.drawable.ic_home_disable);
        tvHome.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
        ivCart.setImageResource(R.drawable.ic_cart_enable);
        tvCart.setTextColor(getResources().getColor(R.color.colorPrimaryTextActive));
        ivNotification.setImageResource(R.drawable.ic_notification_disable);
        tvNotification.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
        ivWin.setImageResource(R.drawable.ic_win_disable);
        tvWin.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.linear_notification)
    void linearNotificationClick(){
        presenter.replaceFragmentNotification();
        ivHome.setImageResource(R.drawable.ic_home_disable);
        tvHome.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
        ivCart.setImageResource(R.drawable.ic_cart_disable);
        tvCart.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
        ivNotification.setImageResource(R.drawable.ic_notification_enable);
        tvNotification.setTextColor(getResources().getColor(R.color.colorPrimaryTextActive));
        ivWin.setImageResource(R.drawable.ic_win_disable);
        tvWin.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.linear_win)
    void linearWinClick(){
        presenter.replaceFragmentWin();
        ivHome.setImageResource(R.drawable.ic_home_disable);
        tvHome.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
        ivCart.setImageResource(R.drawable.ic_cart_disable);
        tvCart.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
        ivNotification.setImageResource(R.drawable.ic_notification_disable);
        tvNotification.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
        ivWin.setImageResource(R.drawable.ic_win_enable);
        tvWin.setTextColor(getResources().getColor(R.color.colorPrimaryTextActive));
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getString(R.string.toast_please_click_back_again_to_exit), Toast.LENGTH_SHORT).show();
        presenter.replaceFragmentHome();
        ivHome.setImageResource(R.drawable.ic_home_enable);
        tvHome.setTextColor(getResources().getColor(R.color.colorPrimaryTextActive));
        ivCart.setImageResource(R.drawable.ic_cart_disable);
        tvCart.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
        ivNotification.setImageResource(R.drawable.ic_notification_disable);
        tvNotification.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
        ivWin.setImageResource(R.drawable.ic_win_disable);
        tvWin.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
        new Handler().postDelayed(() -> doubleBackToExitPressedOnce=false, 2000);

    }

    @Override
    public void onFragmentInteraction(boolean isTabSolid, boolean isTabVisible) {}


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQUEST_CODE_MULTI_PERMISSION_ACTIVITY) {
            onPermissionActivityResult(resultCode);
        }
    }

    @Override
    public void onCameraPermissionResult(int[] grantResults) {
        boolean allGranted = grantResults.length == 5 &&
                grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == android.content.pm.PackageManager.PERMISSION_GRANTED &&
                grantResults[2] == android.content.pm.PackageManager.PERMISSION_GRANTED &&
                grantResults[3] == android.content.pm.PackageManager.PERMISSION_GRANTED &&
                grantResults[4] == android.content.pm.PackageManager.PERMISSION_GRANTED;
        if (!allGranted) {
            Intent intent = new Intent(this, MultiPermissionActivity.class);
            startActivityForResult(intent, REQUEST_CODE_MULTI_PERMISSION_ACTIVITY);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_MULTI_PERMISSION) {
            onCameraPermissionResult(grantResults);
        }
    }

    @Override
    public void showDialogLoading(ProgressDialog progressDialog) {
        progressDialog.show();
    }

    @Override
    public void hideDialogLoading(ProgressDialog progressDialog) {
        progressDialog.dismiss();
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.page_fragment, fragment).commit();
    }

    @Override
    public void cannotReplaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.page_fragment, fragment).addToBackStack(null).commit();
    }

    @Override
    public void checkAndRequestPermissions() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                            android.Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_MULTI_PERMISSION);
        }
    }

    @Override
    public void onPermissionActivityResult(int resultCode) {
        if(RESULT_CANCELED == resultCode) {
            setResult(RESULT_CANCELED);
            finish();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if ((ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE)
                && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int[] scrcoords = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) Objects.requireNonNull(this.getSystemService(Context.INPUT_METHOD_SERVICE))).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}
