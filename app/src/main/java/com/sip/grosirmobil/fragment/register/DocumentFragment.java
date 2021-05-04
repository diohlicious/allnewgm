package com.sip.grosirmobil.fragment.register;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.fxn.pix.Pix;
import com.naa.data.Nson;
import com.naa.data.UtilityAndroid;
import com.shuhart.stepview.StepView;
import com.sip.grosirmobil.R;
import com.sip.grosirmobil.activity.EditImageActivity;
import com.sip.grosirmobil.activity.RegisterDataActivity;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.fragment.OnBackListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.PATH_IMAGE;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.REQUEST_EDIT_IMAGE;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.REQUEST_KTP;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.REQUEST_SELFIE_KTP;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.bitmapToBase64String;

/**
 * A simple {@link Fragment} subclass.
 */
public class DocumentFragment extends Fragment implements OnBackListener {

    public static DocumentFragment newInstance(int page, String title) {
        DocumentFragment fragmentFirst = new DocumentFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.step_view)
    StepView stepView;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.card_view_upload_photo_ktp)
    CardView cardViewUploadPhotoKtp;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.iv_photo_ktp)
    ImageView ivPhotoKtp;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.card_view_upload_selfi_ktp)
    CardView cardViewUploadSelfieKtp;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.iv_photo_selfie)
    ImageView ivPhotoSelfie;

    private GrosirMobilPreference grosirMobilPreference;
    private GrosirMobilFunction grosirMobilFunction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_document, container, false);
        ButterKnife.bind(this, view);

        grosirMobilPreference = new GrosirMobilPreference(getActivity());
        grosirMobilFunction = new GrosirMobilFunction(getActivity());

        stepView.go(2, true);

        //Nson nson = Nson.readJson(UtilityAndroid.getSetting("daftar") ) ;
        //Glide.with(this).load(nson.get("pathImageKtp").asString()).into(ivPhotoKtp);
        //Glide.with(this).load(nson.get("pathImageSelfie").asString()).into(ivPhotoSelfie);
        byte[] imageByteArraySelfie = Base64.decode(grosirMobilPreference.getUrlImageSelfieKtp() == null ? "" : grosirMobilPreference.getUrlImageSelfieKtp(), Base64.DEFAULT);
        Glide.with(this)
                .load(imageByteArraySelfie)
                .into(ivPhotoSelfie);
        byte[] imageByteArrayKtp = Base64.decode(grosirMobilPreference.getUrlImageKtp()== null ? "":grosirMobilPreference.getUrlImageKtp(), Base64.DEFAULT);
        Glide.with(this)
                .load(imageByteArrayKtp)
                .into(ivPhotoKtp);

        return view;
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_back)
    void ivBackClick() {
        onBack();
        ((RegisterDataActivity) getActivity()).setFragment();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_next_document)
    void btnEndRegisterDataClick() {
        if (grosirMobilPreference.getUrlImageKtp() == null || grosirMobilPreference.getUrlImageKtp().equals("")) {
            Toast.makeText(getActivity(), "Mohon Ambil Foto KTP", Toast.LENGTH_SHORT).show();
        } else if (grosirMobilPreference.getUrlImageSelfieKtp() == null || grosirMobilPreference.getUrlImageSelfieKtp().equals("")) {
            Toast.makeText(getActivity(), "Mohon Ambil Foto Selfie KTP", Toast.LENGTH_SHORT).show();
        } else {

            ((RegisterDataActivity) getActivity()).replaceFragment(new AddressFragment());
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick({R.id.card_view_upload_photo_ktp, R.id.iv_photo_ktp})
    void cardViewUploadPhotoKtpClick() {
        Pix.start(this, REQUEST_KTP);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick({R.id.card_view_upload_selfi_ktp, R.id.iv_photo_selfie})
    void cardViewUploadSelfieKtpClick() {
        Pix.start(this, REQUEST_SELFIE_KTP);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Nson nson = Nson.readJson(UtilityAndroid.getSetting("daftar"));
        if (requestCode == REQUEST_KTP) {
            try {
                ArrayList<String> returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
                System.out.println("Path : " + returnValue.get(0));
                String imageFilePath = returnValue.get(0);
                Intent intentDisplay = new Intent(getActivity(), EditImageActivity.class);
                intentDisplay.putExtra(PATH_IMAGE, imageFilePath);
                startActivityForResult(intentDisplay, REQUEST_EDIT_IMAGE);
            } catch (Exception e) {
                GrosirMobilLog.printStackTrace(e);
                Toast.makeText(getActivity(), getString(R.string.toast_cancel_add_image), Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_SELFIE_KTP) {
            try {
                ArrayList<String> returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
                System.out.println("Path : " + returnValue.get(0));
                String imageFilePath = returnValue.get(0);
                Glide.with(this).load(imageFilePath).into(ivPhotoSelfie);
                String imageSelfieBase64String = bitmapToBase64String(grosirMobilFunction.getBitmap(returnValue.get(0), ivPhotoSelfie), 70);
                grosirMobilPreference.saveUrlImageSelfieKtp(imageSelfieBase64String);
                //nson.set("datadiri-nik", imageFilePath);
            } catch (Exception e) {
                GrosirMobilLog.printStackTrace(e);
                Toast.makeText(getActivity(), getString(R.string.toast_cancel_add_image), Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_EDIT_IMAGE) {
            try {
                String pathImage = data.getStringExtra(PATH_IMAGE);
                Glide.with(this).load(pathImage).into(ivPhotoKtp);
                String imageKtpBase64String = bitmapToBase64String(grosirMobilFunction.getBitmap(pathImage, ivPhotoKtp), 70);
                grosirMobilPreference.saveUrlImageKtp(imageKtpBase64String);
                //nson.set("datadiri-nik", pathImage);
            } catch (Exception e) {
                GrosirMobilLog.printStackTrace(e);
            }
        }
        UtilityAndroid.setSetting("daftar", nson.toJson());

    }

    @Override
    public void onBack() {


    }
}