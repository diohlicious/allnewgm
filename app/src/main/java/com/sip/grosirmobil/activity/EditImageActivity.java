package com.sip.grosirmobil.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;
import com.sip.grosirmobil.R;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.base.util.GrosirMobilActivity;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.PATH_IMAGE;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.REQUEST_EDIT_IMAGE;

public class EditImageActivity extends GrosirMobilActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.crop_image_view) CropImageView cropImageView;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.photo_view) PhotoView photoView;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.iv_crop) ImageView ivCrop;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.iv_submit) ImageView ivSubmit;

    private String pathImage = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_image);
        ButterKnife.bind(this);

        pathImage = getIntent().getStringExtra(PATH_IMAGE);

        Glide.with(this)
                .load(pathImage)
                .apply(new RequestOptions()
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(false))
                .into(photoView);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_back)
    void ivBackClick(){
        finish();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_crop)
    void ivCropClick(){
        ivSubmit.setVisibility(View.GONE);
        ivCrop.setVisibility(View.GONE);
        cropImageView.setVisibility(View.VISIBLE);
        photoView.setVisibility(View.GONE);
        CropImage.activity(Uri.fromFile(new File(pathImage)))
                .start(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                ivSubmit.setVisibility(View.VISIBLE);
                ivCrop.setVisibility(View.VISIBLE);
                photoView.setVisibility(View.VISIBLE);
                cropImageView.setVisibility(View.GONE);
                System.out.println("PATH NO CROP : "+ pathImage);
                pathImage = resultUri.getPath();
                System.out.println("PATH CROP : "+ resultUri.getPath());
                Glide.with(this).load(pathImage).into(photoView);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                GrosirMobilLog.printStackTrace(error);
                ivSubmit.setVisibility(View.VISIBLE);
                ivCrop.setVisibility(View.VISIBLE);
                photoView.setVisibility(View.VISIBLE);
                cropImageView.setVisibility(View.GONE);
                Glide.with(this).load(pathImage).into(photoView);
            }else {
                ivSubmit.setVisibility(View.VISIBLE);
                ivCrop.setVisibility(View.VISIBLE);
                photoView.setVisibility(View.VISIBLE);
                cropImageView.setVisibility(View.GONE);
                Glide.with(this).load(pathImage).into(photoView);
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_submit)
    void ivSubmitClick(){
        Intent resultIntent = new Intent();  // or // Intent i = getIntent()
        resultIntent.putExtra(PATH_IMAGE,pathImage);
        setResult(REQUEST_EDIT_IMAGE, resultIntent);
        finish();
    }
}