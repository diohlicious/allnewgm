package com.sip.grosirmobil.activity;

import android.os.Bundle;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;

import butterknife.ButterKnife;

public class SearchAndFilterResultActivity extends GrosirMobilActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_and_filter_result);
        ButterKnife.bind(this);
    }
}