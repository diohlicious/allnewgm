package com.sip.grosirmobil.base.error;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sip.grosirmobil.base.contract.KeyIntent.PARAM_BUTTON_CANCEL;
import static com.sip.grosirmobil.base.contract.KeyIntent.PARAM_CODE;
import static com.sip.grosirmobil.base.contract.KeyIntent.PARAM_MESSAGE;
import static com.sip.grosirmobil.base.contract.KeyIntent.PARAM_TITLE;

public class ErrorActivity extends GrosirMobilActivity {

    @BindView(R.id.btn_cancel) Button btnCancel;
    @BindView(R.id.tv_code) TextView tvCode;
    @BindView(R.id.tv_message) TextView tvMessage;
    @BindView(R.id.tv_title) TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        ButterKnife.bind(this);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        showErrorCode();
        showTitle();
        showMessage();
        showSecondaryButton();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @OnClick(R.id.iv_close)
    void btnCloseClick() {
        setResult(RESULT_CANCELED);
        finish();
    }
    @OnClick(R.id.btn_cancel)
    void btnCanceClick() {
        setResult(RESULT_OK);
        finish();
    }

    private void showSecondaryButton() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.containsKey(PARAM_BUTTON_CANCEL)) {
            String text = bundle.getString(PARAM_BUTTON_CANCEL);
            btnCancel.setText(text);
            btnCancel.setVisibility(View.VISIBLE);
        } else {
            btnCancel.setVisibility(View.GONE);
        }
    }

    private void showTitle() {
        String title = getIntent().getStringExtra(PARAM_TITLE);
        tvTitle.setText(title);
    }

    private void showErrorCode() {
        tvCode.setVisibility(View.VISIBLE);
        String code = getIntent().getStringExtra(PARAM_CODE);
        tvCode.setText(code);
    }

    private void showMessage() {
        String message = getIntent().getStringExtra(PARAM_MESSAGE);
        tvMessage.setText(message);
        tvMessage.setVisibility(View.VISIBLE);
    }
}
