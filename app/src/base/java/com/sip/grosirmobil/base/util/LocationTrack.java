package com.sip.grosirmobil.base.util;


import android.Manifest;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.base.log.GrosirMobilLog;

import java.util.List;
import java.util.Locale;

public class LocationTrack extends Service implements LocationListener {

    private final Context mContext;

    boolean checkGPS = false;

    boolean checkNetwork = false;

    boolean canGetLocation = false;

    Location loc;
    double latitude;
    double longitude;

    private String cityName;
    private String countryName;
    private String countryCode;
    private String adminArea;
    private String codePost;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;


    private static final long MIN_TIME_BW_UPDATES = 1000 * 60;
    protected LocationManager locationManager;

    public LocationTrack(Context mContext) {
        this.mContext = mContext;
        getLocation();
    }

    private Location getLocation() {

        try {
            locationManager = (LocationManager) mContext
                    .getSystemService(LOCATION_SERVICE);

            // get GPS status
            checkGPS = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // get network provider status
            checkNetwork = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!checkGPS && !checkNetwork) {
                Toast.makeText(mContext, R.string.toast_no_service_provider_is_available, Toast.LENGTH_SHORT).show();
            } else {
                this.canGetLocation = true;

                // if GPS Enabled get lat/long using GPS Services
                if (checkGPS) {

                    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION);
                    }
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    if (locationManager != null) {
                        loc = locationManager
                                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (loc != null) {
                            latitude = loc.getLatitude();
                            longitude = loc.getLongitude();
                        }
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return loc;
    }

    public double getLongitude() {
        if (loc != null) {
            longitude = loc.getLongitude();
        }
        return longitude;
    }

    public double getLatitude() {
        if (loc != null) {
            latitude = loc.getLatitude();
        }
        return latitude;
    }

    public boolean canGetLocation() {
        return this.canGetLocation;
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);


        alertDialog.setTitle(R.string.tittle_dialog);

        alertDialog.setMessage(R.string.message_dialog_turn_on_gps);


        alertDialog.setPositiveButton(R.string.btn_yes, (dialog, which) -> {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            mContext.startActivity(intent);
        });


        alertDialog.setNegativeButton(R.string.btn_no, (dialog, which) -> dialog.cancel());


        alertDialog.show();
    }


    public void stopListener() {
        if (locationManager != null) {
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.removeUpdates(LocationTrack.this);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    public String getCityName(){
        try {
            Geocoder gcd = new Geocoder(mContext, Locale.getDefault());
            List<Address> addresses;
            addresses = gcd.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
            if (addresses.size() > 0) {
                cityName = addresses.get(0).getLocality();
                System.out.println("CITY NAME : "+addresses.get(0).getLocality());
                System.out.println("STREET NAME : "+addresses.get(0).getFeatureName());
                System.out.println("COUNTRY CODE : "+addresses.get(0).getCountryCode());
                System.out.println("ADMIN AREA : "+addresses.get(0).getAdminArea());
                System.out.println("POSTAL CODE : "+addresses.get(0).getPostalCode());
            }
        }catch (Exception e){
            GrosirMobilLog.printStackTrace(e);
            cityName = "";
        }

        return cityName;
    }

    public String getCountryName(){
        try {
            Geocoder gcd = new Geocoder(mContext, Locale.getDefault());
            List<Address> addresses;
            addresses = gcd.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
            if (addresses.size() > 0) {
                System.out.println("COUNTRY NAME : "+addresses.get(0).getCountryName());
                countryName = addresses.get(0).getCountryName();
            }
        }catch (Exception e){
            GrosirMobilLog.printStackTrace(e);
            countryName = "";
        }
        return countryName;
    }

    public String getCountryCode(){
        try {
            Geocoder gcd = new Geocoder(mContext, Locale.getDefault());
            List<Address> addresses;
            addresses = gcd.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
            if (addresses.size() > 0) {
                System.out.println("COUNTRY CODE : "+addresses.get(0).getCountryCode());
                countryCode = addresses.get(0).getCountryCode();
            }
        }catch (Exception e){
            GrosirMobilLog.printStackTrace(e);
            countryCode = "";
        }
        return countryCode;
    }

    public String getAdminArea(){
        try {
            Geocoder gcd = new Geocoder(mContext, Locale.getDefault());
            List<Address> addresses;
            addresses = gcd.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
            if (addresses.size() > 0) {
                System.out.println("ADMIN AREA : "+addresses.get(0).getAdminArea());
                adminArea = addresses.get(0).getAdminArea();
            }
        }catch (Exception e){
            GrosirMobilLog.printStackTrace(e);
            adminArea = "";
        }
        return adminArea;
    }

    public String getCodePost(){
        try {
            Geocoder gcd = new Geocoder(mContext, Locale.getDefault());
            List<Address> addresses;
            addresses = gcd.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
            if (addresses.size() > 0) {
                System.out.println("CODE POST : "+addresses.get(0).getPostalCode());
                codePost = addresses.get(0).getPostalCode();
            }
        }catch (Exception e){
            GrosirMobilLog.printStackTrace(e);
            codePost = "";
        }
        return codePost;
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}


