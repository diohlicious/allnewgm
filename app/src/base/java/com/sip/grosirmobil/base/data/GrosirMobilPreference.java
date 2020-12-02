package com.sip.grosirmobil.base.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.sip.grosirmobil.BuildConfig;
import com.sip.grosirmobil.cloud.config.response.province.DataProvinceResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GrosirMobilPreference {

    private static final String PREFERENCE_NAME = BuildConfig.APP_NAME;
    private static final String TOKEN = "token";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String NO_KTP = "noKtp";
    private static final String FULL_NAME = "fullName";
    private static final String ADDRESS = "address";
    private static final String TYPE_USAHA = "typeUsaha";
    private static final String DEALER_NAME = "dealerName";
    private static final String PHONE_NUMBER_DEALER = "phoneNumberDealer";
    private static final String USIA_BISNIS = "usiaBisnis";
    private static final String PERPUTARAN_UNIT = "perputaranUnit";
    private static final String DEALER_ADDRESS = "dealerAddress";
    private static final String DATA_PROVINCE = "data_province";
    private static final String PROVINCE = "province";
    private static final String PROVINCE_CODE = "provinceCode";
    private static final String KABUPATEN = "kabupaten";
    private static final String KABUPATEN_CODE = "kabupatenCode";
    private static final String KECAMATAN = "kecamatan";
    private static final String KECAMATAN_CODE = "kecamatanCode";
    private static final String KELURAHAN = "kelurahan";
    private static final String KELURAHAN_CODE = "kelurahanCode";
    private static final String KODE_POS = "kodePos";
    private static final String URL_IMAGE_KTP = "urlImageKtp";
    private static final String URL_IMAGE_SELFIE_KTP = "urlImageSelfieKtp";

    private final SharedPreferences sharedpreferences;

    public GrosirMobilPreference(Context context) {
        sharedpreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public void saveToken(String token) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(TOKEN, token);
        editor.apply();
    }

    public String getToken() {
        return sharedpreferences.getString(TOKEN, null);
    }

    public void saveProvince(String province) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(PROVINCE, province);
        editor.apply();
    }

    public String getProvince() {
        return sharedpreferences.getString(PROVINCE, null);
    }

    public void saveProvinceCode(String provinceCode) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(PROVINCE_CODE, provinceCode);
        editor.apply();
    }

    public String getProvinceCode() {
        return sharedpreferences.getString(PROVINCE_CODE, null);
    }

    public void saveKabupaten(String kabupaten) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(KABUPATEN, kabupaten);
        editor.apply();
    }

    public String getKabupaten() {
        return sharedpreferences.getString(KABUPATEN, null);
    }

    public void saveKabupatenCode(String kabupatenCode) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(KABUPATEN_CODE, kabupatenCode);
        editor.apply();
    }

    public String getKabupatenCode() {
        return sharedpreferences.getString(KABUPATEN_CODE, null);
    }

    public void saveKecamatan(String kecamatan) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(KECAMATAN, kecamatan);
        editor.apply();
    }

    public String getKecamatan() {
        return sharedpreferences.getString(KECAMATAN, null);
    }

    public void saveKecamatanCode(String kecamatanCode) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(KECAMATAN_CODE, kecamatanCode);
        editor.apply();
    }

    public String getKecamatanCode() {
        return sharedpreferences.getString(KECAMATAN_CODE, null);
    }

    public void saveKelurahan(String kelurahan) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(KELURAHAN, kelurahan);
        editor.apply();
    }

    public String getKelurahan() {
        return sharedpreferences.getString(KELURAHAN, null);
    }

    public void saveKelurahanCode(String kelurahanCode) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(KELURAHAN_CODE, kelurahanCode);
        editor.apply();
    }

    public String getKelurahanCode() {
        return sharedpreferences.getString(KELURAHAN_CODE, null);
    }

    public void saveKodePos(String kodePos) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(KODE_POS, kodePos);
        editor.apply();
    }

    public String getKodePos() {
        return sharedpreferences.getString(KODE_POS, null);
    }

    public void savePhoneNumber(String phoneNumber) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(PHONE_NUMBER, phoneNumber);
        editor.apply();
    }

    public String getPhoneNumber() {
        return sharedpreferences.getString(PHONE_NUMBER, null);
    }

    public void savePassword(String password) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(PASSWORD, password);
        editor.apply();
    }

    public String getPassword() {
        return sharedpreferences.getString(PASSWORD, null);
    }


    public void saveDataProvinceList(List<DataProvinceResponse> dataProvinceResponseList){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        Gson gson = new Gson();
        String dataProvinceJson = gson.toJson(dataProvinceResponseList);
        editor.putString(DATA_PROVINCE, dataProvinceJson);
        editor.apply();
    }

    public ArrayList<DataProvinceResponse> getDataProvinceList(){
        List<DataProvinceResponse> dataProvinceResponseList;
        if (sharedpreferences.contains(DATA_PROVINCE)) {
            String dataProvinceJson = sharedpreferences.getString(DATA_PROVINCE, null);
            Gson gson = new Gson();
            DataProvinceResponse[] dataProvinceResponses = gson.fromJson(dataProvinceJson,
                    DataProvinceResponse[].class);

            dataProvinceResponseList = Arrays.asList(dataProvinceResponses);
            dataProvinceResponseList = new ArrayList<>(dataProvinceResponseList);
        } else
            return null;

        return (ArrayList<DataProvinceResponse>) dataProvinceResponseList;
    }



    public void clearSharePreference() {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove(TOKEN);
        editor.remove(PHONE_NUMBER);
        editor.remove(PASSWORD);
        editor.remove(EMAIL);
        editor.remove(NO_KTP);
        editor.remove(FULL_NAME);
        editor.remove(ADDRESS);
        editor.remove(TYPE_USAHA);
        editor.remove(DEALER_NAME);
        editor.remove(PHONE_NUMBER_DEALER);
        editor.remove(USIA_BISNIS);
        editor.remove(PERPUTARAN_UNIT);
        editor.remove(DEALER_ADDRESS);
        editor.remove(DATA_PROVINCE);
        editor.remove(PROVINCE);
        editor.remove(PROVINCE_CODE);
        editor.remove(KABUPATEN);
        editor.remove(KABUPATEN_CODE);
        editor.remove(KECAMATAN);
        editor.remove(KECAMATAN_CODE);
        editor.remove(KELURAHAN);
        editor.remove(KELURAHAN_CODE);
        editor.remove(KODE_POS);
        editor.remove(URL_IMAGE_KTP);
        editor.remove(URL_IMAGE_SELFIE_KTP);
        editor.apply();
    }
}
