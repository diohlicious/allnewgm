package com.office.template.base.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.office.template.BuildConfig;

public class TemplatePreference {

    private static final String PREFERENCE_NAME = BuildConfig.APP_NAME;
    private static final String TOKEN = "token";
    private SharedPreferences sharedpreferences;

    public TemplatePreference(Context context) {
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

//    public void saveCategoryList(List<DataCategoryResponse> dataCategoryResponseList){
//        SharedPreferences.Editor editor = sharedpreferences.edit();
//        Gson gson = new Gson();
//        String dataCategoryJson = gson.toJson(dataCategoryResponseList);
//        editor.putString(CATEGORY, dataCategoryJson);
//        editor.apply();
//    }

//    public ArrayList<DataCategoryResponse> getCategoryList(){
//        List<DataCategoryResponse> dataCategoryResponseList;
//        if (sharedpreferences.contains(CATEGORY)) {
//            String jsonFavorites = sharedpreferences.getString(CATEGORY, null);
//            Gson gson = new Gson();
//            DataCategoryResponse[] dataCategoryResponses = gson.fromJson(jsonFavorites,
//                    DataCategoryResponse[].class);
//
//            dataCategoryResponseList = Arrays.asList(dataCategoryResponses);
//            dataCategoryResponseList = new ArrayList<>(dataCategoryResponseList);
//        } else
//            return null;
//
//        return (ArrayList<DataCategoryResponse>) dataCategoryResponseList;
//    }
//



    public void clearSharePreference() {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove(TOKEN);
        editor.apply();
    }
}
