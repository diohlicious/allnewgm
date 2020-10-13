package com.office.template.base.error;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.office.template.R;
import com.office.template.base.util.TemplateActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.office.template.base.contract.KeyIntent.PARAM_IS_RETRY_ENABLE;
import static com.office.template.base.contract.KeyIntent.PARAM_MESSAGE;

public class NetworkErrorActivity extends TemplateActivity {

    @BindView(R.id.btn_retry) Button btnRetry;
    @BindView(R.id.tv_message) TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_error);
        ButterKnife.bind(this);

        initRetryButton();
    }

    private void initRetryButton() {
        boolean enabled = getIntent().getBooleanExtra(PARAM_IS_RETRY_ENABLE, false);
        btnRetry.setVisibility(enabled ? View.VISIBLE : View.GONE);
        if(getIntent().hasExtra(PARAM_MESSAGE)) {
            String message = getIntent().getStringExtra(PARAM_MESSAGE);
            tvMessage.setText(message);
        } else {
            tvMessage.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.btn_retry)
    void btnRetryClick(){
        setResult(RESULT_OK);
        finish();
    }

    @OnClick(R.id.btn_check_network)
    void btnCheckNetworkClick(){
        Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
        startActivity(intent);
    }

    @OnClick(R.id.iv_close)
    void btnClosekClick(){
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }

}
