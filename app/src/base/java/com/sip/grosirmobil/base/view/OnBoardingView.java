package com.sip.grosirmobil.base.view;

public interface OnBoardingView {

    void checkAndRequestPermissions();

    void onPermissionActivityResult(int resultCode);

    void onCameraPermissionResult(int[] grantResults);
}
