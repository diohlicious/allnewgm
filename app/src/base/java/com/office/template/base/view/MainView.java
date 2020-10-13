package com.office.template.base.view;

import android.app.ProgressDialog;

import androidx.fragment.app.Fragment;

public interface MainView {

    void showDialogLoading(ProgressDialog progressDialog);

    void hideDialogLoading(ProgressDialog progressDialog);

    void replaceFragment(Fragment fragment);

    void cannotReplaceFragment(Fragment fragment);

    void checkAndRequestPermissions();

    void onPermissionActivityResult(int resultCode);

    void onCameraPermissionResult(int[] grantResults);


}
