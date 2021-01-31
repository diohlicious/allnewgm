package com.sip.grosirmobil.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.PreviewBannerImageAdapter;
import com.sip.grosirmobil.adapter.PreviewImageBrokenAdapter;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;
import com.sip.grosirmobil.cloud.config.response.vehicledetail.ImageBrokenResponse;
import com.sip.grosirmobil.cloud.config.response.vehicledetail.ImageResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.FROM_PAGE;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.adjustFontScale;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setStatusBarOnBoarding;

public class PreviewImageActivity extends GrosirMobilActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_image) RecyclerView rvImage;

    private final List<ImageBrokenResponse> imageBrokenResponseList = new ArrayList<>();
    private final List<ImageResponse> imageResponseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarOnBoarding(this);
        setContentView(R.layout.activity_preview_image);
        adjustFontScale(this, getResources().getConfiguration());
        ButterKnife.bind(this);

        GrosirMobilPreference grosirMobilPreference = new GrosirMobilPreference(this);
        GrosirMobilFunction grosirMobilFunction = new GrosirMobilFunction(this);

        LinearLayoutManager layoutManagerImageCar = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvImage.setLayoutManager(layoutManagerImageCar);
        rvImage.setItemAnimator(new DefaultItemAnimator());
        rvImage.setNestedScrollingEnabled(false);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(rvImage);

        if(getIntent().getStringExtra(FROM_PAGE).equals("brokenImage")){
            imageBrokenResponseList.addAll(grosirMobilPreference.getDataVehicleDetail().getImageBrokenResponseList());
            PreviewImageBrokenAdapter previewImageBrokenAdapter = new PreviewImageBrokenAdapter(this, imageBrokenResponseList);
            rvImage.setAdapter(previewImageBrokenAdapter);
            previewImageBrokenAdapter.notifyDataSetChanged();
        }else if(getIntent().getStringExtra(FROM_PAGE).equals("bannerVehicleDetail")){
            imageResponseList.addAll(grosirMobilPreference.getDataVehicleDetail().getImageResponseList());
            PreviewBannerImageAdapter previewBannerImageAdapter = new PreviewBannerImageAdapter(this, imageResponseList);
            rvImage.setAdapter(previewBannerImageAdapter);
            previewBannerImageAdapter.notifyDataSetChanged();
        }
//            linearDescription.setVisibility(View.GONE);
//        }
//        else {
//            linearDescription.setVisibility(View.VISIBLE);
//            tvDescription.setText(getIntent().getStringExtra(DESCRIPTION));
//        }

//        if(getIntent().getStringExtra(URL_IMAGE)==null){
//
//        }else {
//            CircularProgressDrawable circularProgressDrawable = new  CircularProgressDrawable(this);
//            circularProgressDrawable.setStrokeWidth(5f);
//            circularProgressDrawable.setCenterRadius(30f);
//            circularProgressDrawable.start();
//            Glide.with(this)
//                    .load(getIntent().getStringExtra(URL_IMAGE))
//                    .apply(new RequestOptions()
//                            .placeholder(circularProgressDrawable)
////                        .error(R.drawable.ic_image_empty)
//                            .dontAnimate()
//                            .diskCacheStrategy(DiskCacheStrategy.NONE)
//                            .skipMemoryCache(false))
//                    .into(photoView);
//        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_back)
    void ivBackClick() {
        finish();
    }
}