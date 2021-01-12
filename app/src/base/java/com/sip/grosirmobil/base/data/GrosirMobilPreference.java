package com.sip.grosirmobil.base.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.sip.grosirmobil.BuildConfig;
import com.sip.grosirmobil.cloud.config.model.SearchDataModel;
import com.sip.grosirmobil.cloud.config.response.checkactivetoken.DataCheckActiveTokenResponse;
import com.sip.grosirmobil.cloud.config.response.homecomingsoon.DataPageHomeComingSoonResponse;
import com.sip.grosirmobil.cloud.config.response.homelive.DataPageHomeLiveResponse;
import com.sip.grosirmobil.cloud.config.response.login.DataLoginResponse;
import com.sip.grosirmobil.cloud.config.response.province.DataProvinceResponse;
import com.sip.grosirmobil.cloud.config.response.tipeusaha.DataTipeUsahaResponse;
import com.sip.grosirmobil.cloud.config.response.vehicledetail.DataVehicleDetailResponse;
import com.sip.grosirmobil.cloud.config.response.warehouse.DataWareHouseResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GrosirMobilPreference {

    private static final String PREFERENCE_NAME = BuildConfig.APP_NAME;
    private static final String TOKEN = "token";
    private static final String TIME_SERVER = "timeServer";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String NO_KTP = "noKtp";
    private static final String FULL_NAME = "fullName";
    private static final String ADDRESS = "address";
    private static final String TYPE_USAHA = "typeUsaha";
    private static final String TYPE_USAHA_CODE = "typeUsahaCode";
    private static final String DATA_TYPE_USAHA = "dataTypeUsaha";
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
    private static final String USER_ID = "userId";
    private static final String URL_IMAGE_KTP = "urlImageKtp";
    private static final String URL_IMAGE_SELFIE_KTP = "urlImageSelfieKtp";

    private static final String DATA_LOGIN = "dataLogin";
    private static final String DATA_WARE_HOUSE = "dataWareHouse";
    private static final String DATA_CHECK_ACTIVE_TOKEN = "dataCheckActiveToken";
    private static final String DATA_SEARCH = "dataSearch";
    private static final String DATA_HOME_LIVE = "dataHomeLive";
    private static final String DATA_HOME_COMING_SOON = "dataHomeComingSoon";
    private static final String DATA_VEHICLE_DETAIL = "dataVehicleDetail";

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

    public void saveDealerAddress(String dealerAddress) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(DEALER_ADDRESS, dealerAddress);
        editor.apply();
    }

    public String getDealerAddress() {
        return sharedpreferences.getString(DEALER_ADDRESS, null);
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

    public void saveUserId(String userId) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(USER_ID, userId);
        editor.apply();
    }

    public String getUserId() {
        return sharedpreferences.getString(USER_ID, null);
    }

    public void savePhoneNumber(String phoneNumber) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(PHONE_NUMBER, phoneNumber);
        editor.apply();
    }

    public String getPhoneNumber() {
        return sharedpreferences.getString(PHONE_NUMBER, null);
    }

    public void saveFullName(String fullName) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(FULL_NAME, fullName);
        editor.apply();
    }

    public String getFullName() {
        return sharedpreferences.getString(FULL_NAME, null);
    }

    public void saveNoKtp(String noKtp) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(NO_KTP, noKtp);
        editor.apply();
    }

    public String getNoKtp() {
        return sharedpreferences.getString(NO_KTP, null);
    }

     public void saveTimeServer(String timeServer) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(TIME_SERVER, timeServer);
        editor.apply();
    }

    public String getTimeServer() {
        return sharedpreferences.getString(TIME_SERVER, null);
    }

    public void saveEmail(String email) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(EMAIL, email);
        editor.apply();
    }

    public String getEmail() {
        return sharedpreferences.getString(EMAIL, null);
    }

    public void savePassword(String password) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(PASSWORD, password);
        editor.apply();
    }

    public String getPassword() {
        return sharedpreferences.getString(PASSWORD, null);
    }

    public void saveTypeUsaha(String typeUsaha) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(TYPE_USAHA, typeUsaha);
        editor.apply();
    }

    public String getTypeUsaha() {
        return sharedpreferences.getString(TYPE_USAHA, null);
    }

    public void saveTypeUsahaCode(String typeUsahaCode) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(TYPE_USAHA_CODE, typeUsahaCode);
        editor.apply();
    }

    public String getTypeUsahaCode() {
        return sharedpreferences.getString(TYPE_USAHA_CODE, null);
    }

    public void saveDealerName(String dealerName) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(DEALER_NAME, dealerName);
        editor.apply();
    }

    public String getDealerName() {
        return sharedpreferences.getString(DEALER_NAME, null);
    }

    public void saveDealerPhoneNumber(String dealerPhoneNumber) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(PHONE_NUMBER_DEALER, dealerPhoneNumber);
        editor.apply();
    }

    public String getDealerPhoneNumber() {
        return sharedpreferences.getString(PHONE_NUMBER_DEALER, null);
    }

    public void saveUrlImageKtp(String urlImageKtp) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(URL_IMAGE_KTP, urlImageKtp);
        editor.apply();
    }

    public String getUrlImageKtp() {
        return sharedpreferences.getString(URL_IMAGE_KTP, null);
    }

    public void saveUrlImageSelfieKtp(String urlImageSelfieKtp) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(URL_IMAGE_SELFIE_KTP, urlImageSelfieKtp);
        editor.apply();
    }

    public String getUrlImageSelfieKtp() {
        return sharedpreferences.getString(URL_IMAGE_SELFIE_KTP, null);
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

    public void saveDataTypeUsahaList(List<DataTipeUsahaResponse> dataTipeUsahaResponseList){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        Gson gson = new Gson();
        String dataTipeUsahaJson = gson.toJson(dataTipeUsahaResponseList);
        editor.putString(DATA_TYPE_USAHA, dataTipeUsahaJson);
        editor.apply();
    }

    public ArrayList<DataTipeUsahaResponse> getDataTypeUsahaList(){
        List<DataTipeUsahaResponse> dataTipeUsahaResponseList;
        if (sharedpreferences.contains(DATA_TYPE_USAHA)) {
            String dataTypeUsahaJson = sharedpreferences.getString(DATA_TYPE_USAHA, null);
            Gson gson = new Gson();
            DataTipeUsahaResponse[] dataTipeUsahaResponses = gson.fromJson(dataTypeUsahaJson,
                    DataTipeUsahaResponse[].class);

            dataTipeUsahaResponseList = Arrays.asList(dataTipeUsahaResponses);
            dataTipeUsahaResponseList = new ArrayList<>(dataTipeUsahaResponseList);
        } else
            return null;

        return (ArrayList<DataTipeUsahaResponse>) dataTipeUsahaResponseList;
    }

    public void saveDataSearch(List<SearchDataModel> searchDataModelList){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        Gson gson = new Gson();
        String dataSearchModelJson = gson.toJson(searchDataModelList);
        editor.putString(DATA_SEARCH, dataSearchModelJson);
        editor.apply();
    }

    public ArrayList<SearchDataModel> getDataSearch(){
        List<SearchDataModel> searchDataModelList;
        if (sharedpreferences.contains(DATA_SEARCH)) {
            String dataSearchModelJson = sharedpreferences.getString(DATA_SEARCH, null);
            Gson gson = new Gson();
            SearchDataModel[] searchDataModels = gson.fromJson(dataSearchModelJson,
                    SearchDataModel[].class);

            searchDataModelList = Arrays.asList(searchDataModels);
            searchDataModelList = new ArrayList<>(searchDataModelList);
        } else
            return null;

        return (ArrayList<SearchDataModel>) searchDataModelList;
    }

    public void saveDataLogin(DataLoginResponse dataLoginResponse){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        Gson gSonDataProfile = new Gson();
        String jsonProfile = gSonDataProfile.toJson(dataLoginResponse);
        editor.putString(DATA_LOGIN, jsonProfile);
        editor.apply();
    }

    public DataLoginResponse getDataLogin(){
        String json = sharedpreferences.getString(DATA_LOGIN, "");
        Gson gson=new Gson();
        return gson.fromJson(json, DataLoginResponse.class);
    }

    public void saveDataHomeLive(DataPageHomeLiveResponse dataPageHomeLiveResponse){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        Gson gSonDataHomeLive = new Gson();
        String jsonDataHomeLive = gSonDataHomeLive.toJson(dataPageHomeLiveResponse);
        editor.putString(DATA_HOME_LIVE, jsonDataHomeLive);
        editor.apply();
    }

    public DataPageHomeLiveResponse getDataHomeLive(){
        String json = sharedpreferences.getString(DATA_HOME_LIVE, "");
        Gson gson=new Gson();
        return gson.fromJson(json, DataPageHomeLiveResponse.class);
    }

    public void saveDataHomeComingSoon(DataPageHomeComingSoonResponse dataPageHomeComingSoonResponse){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        Gson gSonDataHomeComingSoon = new Gson();
        String jsonDataHomeComingSoon = gSonDataHomeComingSoon.toJson(dataPageHomeComingSoonResponse);
        editor.putString(DATA_HOME_COMING_SOON, jsonDataHomeComingSoon);
        editor.apply();
    }

    public DataPageHomeComingSoonResponse getDataComingSoon(){
        String json = sharedpreferences.getString(DATA_HOME_COMING_SOON, "");
        Gson gson=new Gson();
        return gson.fromJson(json, DataPageHomeComingSoonResponse.class);
    }

    public void saveDataVehicleDetail(DataVehicleDetailResponse dataVehicleDetailResponse){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        Gson gSonDataVehicleDetail = new Gson();
        String jsonDataVehicleDetail = gSonDataVehicleDetail.toJson(dataVehicleDetailResponse);
        editor.putString(DATA_VEHICLE_DETAIL, jsonDataVehicleDetail);
        editor.apply();
    }

    public DataVehicleDetailResponse getDataVehicleDetail(){
        String json = sharedpreferences.getString(DATA_VEHICLE_DETAIL, "");
        Gson gson=new Gson();
        return gson.fromJson(json, DataVehicleDetailResponse.class);
    }

    public void saveDataCheckActiveToken(DataCheckActiveTokenResponse dataCheckActiveTokenResponse){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        Gson gSonCheckActiveToken = new Gson();
        String jsonCheckActiveToken = gSonCheckActiveToken.toJson(dataCheckActiveTokenResponse);
        editor.putString(DATA_CHECK_ACTIVE_TOKEN, jsonCheckActiveToken);
        editor.apply();
    }

    public DataCheckActiveTokenResponse getDataCheckActiveToken(){
        String json = sharedpreferences.getString(DATA_CHECK_ACTIVE_TOKEN, "");
        Gson gson=new Gson();
        return gson.fromJson(json, DataCheckActiveTokenResponse.class);
    }

    public void saveDataWareHouseList(List<DataWareHouseResponse> dataWareHouseResponseList){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        Gson gson = new Gson();
        String dataWareHouseJson = gson.toJson(dataWareHouseResponseList);
        editor.putString(DATA_WARE_HOUSE, dataWareHouseJson);
        editor.apply();
    }

    public ArrayList<DataWareHouseResponse> getDataWareHouseList(){
        List<DataWareHouseResponse> dataWareHouseResponseList;
        if (sharedpreferences.contains(DATA_WARE_HOUSE)) {
            String dataWareHouseJson = sharedpreferences.getString(DATA_WARE_HOUSE, null);
            Gson gson = new Gson();
            DataWareHouseResponse[] dataWareHouseResponses = gson.fromJson(dataWareHouseJson,
                    DataWareHouseResponse[].class);

            dataWareHouseResponseList = Arrays.asList(dataWareHouseResponses);
            dataWareHouseResponseList = new ArrayList<>(dataWareHouseResponseList);
        } else
            return null;

        return (ArrayList<DataWareHouseResponse>) dataWareHouseResponseList;
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
        editor.remove(TYPE_USAHA_CODE);
        editor.remove(DATA_TYPE_USAHA);
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
        editor.remove(DATA_LOGIN);
        editor.remove(DATA_WARE_HOUSE);
        editor.remove(DATA_CHECK_ACTIVE_TOKEN);
        editor.remove(DATA_HOME_LIVE);
        editor.remove(DATA_HOME_COMING_SOON);
        editor.remove(DATA_VEHICLE_DETAIL);
        editor.apply();
    }

    public void clearDataSearch() {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove(DATA_SEARCH);
        editor.apply();
    }
}
