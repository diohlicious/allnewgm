package com.sip.grosirmobil.base.permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.REQUEST_MULTI_PERMISSION;

/**
 * Created by Syahrul Hajji on 22/09/18.
 */
public class MultiPermissionActivity extends GrosirMobilActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_permission);
        ButterKnife.bind(this);
        initStatusBar();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    private void initStatusBar() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    }

    @OnClick(R.id.btn_enable)
    void btnEnableClick(){
        onEnableClick();
    }

    @OnClick(R.id.btn_cancel)
    void btnCancelClick(){
        onCancelClick();
    }

    private void onEnableClick() {
        boolean permissionRequired = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED;
        if (permissionRequired) {
            boolean rationaleRequired = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)
                    || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION);
            if (rationaleRequired) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION},
                        REQUEST_MULTI_PERMISSION);
            } else {
                openSettingsActivity();
            }
        } else {
            finishWithResultOk();
        }
    }

    private void openSettingsActivity() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }

    private void finishWithResultOk() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_MULTI_PERMISSION) {
            if (grantResults.length == 5
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED
                    && grantResults[2] == PackageManager.PERMISSION_GRANTED
                    && grantResults[3] == PackageManager.PERMISSION_GRANTED
                    && grantResults[4] == PackageManager.PERMISSION_GRANTED) {
                finishWithResultOk();
            }
        }
    }

    private void onCancelClick() {
        setResult(RESULT_CANCELED);
        finish();
    }
}
