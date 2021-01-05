package com.sip.grosirmobil.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;
import com.sip.grosirmobil.R;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.DESCRIPTION;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.URL_IMAGE;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.adjustFontScale;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setStatusBarOnBoarding;

public class PreviewImageActivity extends GrosirMobilActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.photo_view) PhotoView photoView;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_description) LinearLayout linearDescription;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_description) TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarOnBoarding(this);
        setContentView(R.layout.activity_preview_image);
        adjustFontScale(this, getResources().getConfiguration());
        ButterKnife.bind(this);

        if(getIntent().getStringExtra(DESCRIPTION).equals("")){
            linearDescription.setVisibility(View.GONE);
        }
        else {
            linearDescription.setVisibility(View.VISIBLE);
            tvDescription.setText(getIntent().getStringExtra(DESCRIPTION));
        }

        if(getIntent().getStringExtra(URL_IMAGE)==null){

        }else {
            CircularProgressDrawable circularProgressDrawable = new  CircularProgressDrawable(this);
            circularProgressDrawable.setStrokeWidth(5f);
            circularProgressDrawable.setCenterRadius(30f);
            circularProgressDrawable.start();
            Glide.with(this)
                    .load(getIntent().getStringExtra(URL_IMAGE))
                    .apply(new RequestOptions()
                            .placeholder(circularProgressDrawable)
//                        .error(R.drawable.ic_image_empty)
                            .dontAnimate()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(false))
                    .into(photoView);
        }

    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_back)
    void ivBackClick() {
        finish();
    }
}