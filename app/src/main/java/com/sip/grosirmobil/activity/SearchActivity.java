package com.sip.grosirmobil.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.SearchDataAdapter;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;
import com.sip.grosirmobil.cloud.config.model.SearchDataModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends GrosirMobilActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_search_result) EditText etSearchResult;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.iv_clear) ImageView ivClear;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_clear_search) TextView tvClearSearch;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_history_search) RecyclerView rvHistorySearch;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_not_found) LinearLayout linearNotFound;

    private GrosirMobilPreference grosirMobilPreference;
    private List<SearchDataModel> searchDataModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        grosirMobilPreference = new GrosirMobilPreference(this);

        RecyclerView.LayoutManager layoutManagerLive = new LinearLayoutManager(this);
        rvHistorySearch.setLayoutManager(layoutManagerLive);
        rvHistorySearch.setNestedScrollingEnabled(false);

        if(grosirMobilPreference.getDataSearch()==null||grosirMobilPreference.getDataSearch().isEmpty()){
            rvHistorySearch.setVisibility(View.GONE);
            tvClearSearch.setVisibility(View.GONE);
        }else {
            tvClearSearch.setVisibility(View.VISIBLE);
            searchDataModelList.addAll(grosirMobilPreference.getDataSearch());
            rvHistorySearch.setVisibility(View.VISIBLE);
        }
        SearchDataAdapter searchDataAdapter = new SearchDataAdapter(this,this, grosirMobilPreference.getDataSearch());
        rvHistorySearch.setAdapter(searchDataAdapter);
        searchDataAdapter.notifyDataSetChanged();

        etSearchResult.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || event.getAction() == KeyEvent.ACTION_DOWN
                    && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                if(!etSearchResult.getText().toString().equals("")){
                    SearchDataModel searchDataModel = new SearchDataModel(etSearchResult.getText().toString());
                    searchDataModelList.add(searchDataModel);
                    grosirMobilPreference.saveDataSearch(searchDataModelList);
                }
                Intent resultIntent = new Intent();
                resultIntent.putExtra("keySearch", etSearchResult.getText().toString());
                setResult(RESULT_OK, resultIntent);
                finish();
                return true;
            }
            return false;
        });

        etSearchResult.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().isEmpty()){
                    ivClear.setVisibility(View.GONE);
                }else {
                    ivClear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.tv_clear_search)
    void tvClearSearchClick(){
        rvHistorySearch.setVisibility(View.GONE);
        tvClearSearch.setVisibility(View.GONE);
        grosirMobilPreference.clearDataSearch();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_clear)
    void ivClearClick(){
        etSearchResult.setText("");
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_back)
    void ivBackClick(){
        finish();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_back_to_gallery)
    void btnBackToGalleryClick(){
        finish();
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