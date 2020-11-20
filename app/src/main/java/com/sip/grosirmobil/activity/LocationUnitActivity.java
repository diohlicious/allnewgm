package com.sip.grosirmobil.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sip.grosirmobil.R;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;
import com.sip.grosirmobil.base.util.LocationTrack;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sip.grosirmobil.base.function.GrosirMobilFunction.adjustFontScale;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setStatusBarOnBoarding;

public class LocationUnitActivity extends GrosirMobilActivity implements OnMapReadyCallback {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_location) TextView tvLocation;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.map_view) MapView mapView;

    private String latitude = "", longitude = "";
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarOnBoarding(this);
        setContentView(R.layout.activity_location_unit);
        adjustFontScale(this, getResources().getConfiguration());
        ButterKnife.bind(this);

        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);

    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_back)
    void ivBackClick() {
        finish();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setMyLocationButtonEnabled(true);
//        map.setMinZoomPreference(6.0f);
//        map.setMaxZoomPreference(14.0f);

        LocationTrack locationTrack = new LocationTrack(LocationUnitActivity.this);

        if (locationTrack.canGetLocation()) {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            double longi = locationTrack.getLongitude();
            double lati = locationTrack.getLatitude();
            LatLng yourPosition = new LatLng(lati, longi);
            map.addMarker(new MarkerOptions().position(yourPosition)
                    .title("Posisi Kamu"));
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(yourPosition, 10);
            map.animateCamera(cameraUpdate);
            try {
                List<Address> addresses = geocoder.getFromLocation(yourPosition.latitude, yourPosition.longitude, 1);
                String cityName = addresses.get(0).getAddressLine(0);
                tvLocation.setText(cityName);

            }catch (IOException e){
                GrosirMobilLog.printStackTrace(e);
            }
        } else {
            locationTrack.showSettingsAlert();
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        map.setMyLocationEnabled(true);
        map.getUiSettings().setMapToolbarEnabled(true);
        map.getUiSettings().setZoomGesturesEnabled(true);

    }

    @OnClick(R.id.btn_direct_location)
    void btnDirectLocationClick(){

    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}