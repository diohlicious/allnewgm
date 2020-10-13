package com.office.template.base.success;

import android.os.Bundle;

import com.office.template.R;
import com.office.template.base.util.TemplateActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SuccessActivity extends TemplateActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        ButterKnife.bind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @OnClick(R.id.btn_done)
    void btnDoneClick() {
        setResult(RESULT_OK);
        finish();
    }
}
