package com.sip.grosirmobil.fragment.register;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.shuhart.stepview.StepView;
import com.sip.grosirmobil.R;
import com.sip.grosirmobil.activity.RegisterDataActivity;
import com.sip.grosirmobil.adapter.KabupatenAdapter;
import com.sip.grosirmobil.adapter.KecamatanAdapter;
import com.sip.grosirmobil.adapter.KelurahanAdapter;
import com.sip.grosirmobil.adapter.ProvinceAdapter;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.cloud.config.request.kabupaten.KabupatenRequest;
import com.sip.grosirmobil.cloud.config.request.kecamatan.KecamatanRequest;
import com.sip.grosirmobil.cloud.config.request.kelurahan.KelurahanRequest;
import com.sip.grosirmobil.cloud.config.response.kabupaten.KabupatenResponse;
import com.sip.grosirmobil.cloud.config.response.kecamatan.KecamatanResponse;
import com.sip.grosirmobil.cloud.config.response.kelurahan.KelurahanResponse;
import com.sip.grosirmobil.cloud.config.response.province.ProvinceResponse;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sip.grosirmobil.base.GrosirMobilApp.getApiGrosirMobil;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddressFragment extends Fragment {

    public static AddressFragment newInstance(int page, String title) {
        AddressFragment fragmentFirst = new AddressFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.step_view) StepView stepView;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_provinsi) TextInputEditText etProvinsi;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_kabupaten) TextInputEditText etKabupaten;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_kecamatan) TextInputEditText etKecamatan;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_kelurahan) TextInputEditText etKelurahan;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_kode_pos) TextInputEditText etKodePos;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.relative_dialog) RelativeLayout relativeDialog;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_choose) RecyclerView rvChoose;

    private GrosirMobilPreference grosirMobilPreference;
    private GrosirMobilFunction grosirMobilFunction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_address, container, false);
        ButterKnife.bind(this, view);

        grosirMobilPreference = new GrosirMobilPreference(getActivity());
        grosirMobilFunction = new GrosirMobilFunction(getActivity());

        stepView.go(2, true);
        return view;
    }

    private void showDialogChoose(){
        relativeDialog.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager layoutManagerChoose = new LinearLayoutManager(getActivity());
        rvChoose.setLayoutManager(layoutManagerChoose);
        rvChoose.setNestedScrollingEnabled(false);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.linear_content_dialog)
    void linearContentDialogClick(){
        relativeDialog.setVisibility(View.VISIBLE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.relative_dialog)
    void relativeDialogClick(){
        relativeDialog.setVisibility(View.GONE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.et_provinsi)
    void etProvinsiClick(){
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.base_tv_please_wait));
        progressDialog.show();
        final Call<ProvinceResponse> provinceApi = getApiGrosirMobil().provinceApi();
        provinceApi.enqueue(new Callback<ProvinceResponse>() {
            @Override
            public void onResponse(Call<ProvinceResponse> call, Response<ProvinceResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    try {
                        if(response.body().getMessage().equals("success")){
                            showDialogChoose();
                            ProvinceAdapter provinceAdapter = new ProvinceAdapter(response.body().getData(), dataProvinceResponse -> {
                               etProvinsi.setText(dataProvinceResponse.getProvinceName());
                               etProvinsi.setTag(dataProvinceResponse.getProvinceCode());
                               relativeDialogClick();
                            });
                            rvChoose.setAdapter(provinceAdapter);
                            provinceAdapter.notifyDataSetChanged();
                        }else {
                            grosirMobilFunction.showMessage(getActivity(), "GET Province", response.body().getMessage());
                        }
                    }catch (Exception e){
                        GrosirMobilLog.printStackTrace(e);
                    }
                }else {
                    try {
                        grosirMobilFunction.showMessage(getActivity(), getString(R.string.base_null_error_title), response.errorBody().string());
                    } catch (IOException e) {
                        GrosirMobilLog.printStackTrace(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<ProvinceResponse> call, Throwable t) {
                progressDialog.dismiss();
                grosirMobilFunction.showMessage(getActivity(), "GET Province", getString(R.string.base_null_server));
                GrosirMobilLog.printStackTrace(t);
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.et_kabupaten)
    void etKabupatenClick(){
        if(etProvinsi.getText().toString().equals("")){
            Toast.makeText(getActivity(), getString(R.string.toast_mohon_pilih_provinsi_terlebih_dahulu), Toast.LENGTH_SHORT).show();
        }else {
            ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setCancelable(false);
            progressDialog.setMessage(getString(R.string.base_tv_please_wait));
            progressDialog.show();
            KabupatenRequest kabupatenRequest = new KabupatenRequest(etProvinsi.getTag().toString());
            final Call<KabupatenResponse> kabupatenApi = getApiGrosirMobil().kabupatenApi(kabupatenRequest);
            kabupatenApi.enqueue(new Callback<KabupatenResponse>() {
                @Override
                public void onResponse(Call<KabupatenResponse> call, Response<KabupatenResponse> response) {
                    progressDialog.dismiss();
                    if (response.isSuccessful()) {
                        try {
                            if(response.body().getMessage().equals("success")){
                                showDialogChoose();
                                KabupatenAdapter kabupatenAdapter = new KabupatenAdapter(response.body().getData(), dataKabupatenResponse -> {
                                    etKabupaten.setText(dataKabupatenResponse.getCity());
                                    etKabupaten.setTag(dataKabupatenResponse.getCity());
                                    relativeDialogClick();
                                });
                                rvChoose.setAdapter(kabupatenAdapter);
                                kabupatenAdapter.notifyDataSetChanged();
                            }else {
                                grosirMobilFunction.showMessage(getActivity(), "GET Kabupaten", response.body().getMessage());
                            }
                        }catch (Exception e){
                            GrosirMobilLog.printStackTrace(e);
                        }
                    }else {
                        try {
                            grosirMobilFunction.showMessage(getActivity(), getString(R.string.base_null_error_title), response.errorBody().string());
                        } catch (IOException e) {
                            GrosirMobilLog.printStackTrace(e);
                        }
                    }
                }

                @Override
                public void onFailure(Call<KabupatenResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    grosirMobilFunction.showMessage(getActivity(), "GET Kabupaten", getString(R.string.base_null_server));
                    GrosirMobilLog.printStackTrace(t);
                }
            });
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.et_kecamatan)
    void etKecamatanClick(){
        if(etKabupaten.getText().toString().equals("")){
            Toast.makeText(getActivity(), getString(R.string.toast_mohon_pilih_kabupaten_terlebih_dahulu), Toast.LENGTH_SHORT).show();
        }else {
            ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setCancelable(false);
            progressDialog.setMessage(getString(R.string.base_tv_please_wait));
            progressDialog.show();
            KecamatanRequest kecamatanRequest = new KecamatanRequest(etKabupaten.getTag().toString());
            final Call<KecamatanResponse> kecamatanApi = getApiGrosirMobil().kecamatanApi(kecamatanRequest);
            kecamatanApi.enqueue(new Callback<KecamatanResponse>() {
                @Override
                public void onResponse(Call<KecamatanResponse> call, Response<KecamatanResponse> response) {
                    progressDialog.dismiss();
                    if (response.isSuccessful()) {
                        try {
                            if(response.body().getMessage().equals("success")){
                                showDialogChoose();
                                KecamatanAdapter kecamatanAdapter = new KecamatanAdapter(response.body().getData(), dataKecamatanResponse -> {
                                    etKecamatan.setText(dataKecamatanResponse.getSubDistrict());
                                    etKecamatan.setTag(dataKecamatanResponse.getSubDistrict());
                                    relativeDialogClick();
                                });
                                rvChoose.setAdapter(kecamatanAdapter);
                                kecamatanAdapter.notifyDataSetChanged();
                            }else {
                                grosirMobilFunction.showMessage(getActivity(), "GET Kecamatan", response.body().getMessage());
                            }
                        }catch (Exception e){
                            GrosirMobilLog.printStackTrace(e);
                        }
                    }else {
                        try {
                            grosirMobilFunction.showMessage(getActivity(), getString(R.string.base_null_error_title), response.errorBody().string());
                        } catch (IOException e) {
                            GrosirMobilLog.printStackTrace(e);
                        }
                    }
                }

                @Override
                public void onFailure(Call<KecamatanResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    grosirMobilFunction.showMessage(getActivity(), "GET Kecamatan", getString(R.string.base_null_server));
                    GrosirMobilLog.printStackTrace(t);
                }
            });
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.et_kelurahan)
    void etKelurahanClick(){
        if(etKecamatan.getText().toString().equals("")){
            Toast.makeText(getActivity(), getString(R.string.toast_mohon_pilih_kecamatan_terlebih_dahulu), Toast.LENGTH_SHORT).show();
        }else {
            ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setCancelable(false);
            progressDialog.setMessage(getString(R.string.base_tv_please_wait));
            progressDialog.show();
            KelurahanRequest kelurahanRequest = new KelurahanRequest(etKabupaten.getTag().toString(),etKecamatan.getTag().toString());
            final Call<KelurahanResponse> kelurahanApi = getApiGrosirMobil().kelurahanApi(kelurahanRequest);
            kelurahanApi.enqueue(new Callback<KelurahanResponse>() {
                @Override
                public void onResponse(Call<KelurahanResponse> call, Response<KelurahanResponse> response) {
                    progressDialog.dismiss();
                    if (response.isSuccessful()) {
                        try {
                            if(response.body().getMessage().equals("success")){
                                showDialogChoose();
                                KelurahanAdapter kelurahanAdapter = new KelurahanAdapter(response.body().getData(), dataKelurahanResponse -> {
                                    etKelurahan.setText(dataKelurahanResponse.getUrban());
                                    etKelurahan.setTag(dataKelurahanResponse.getUrban());
                                    etKodePos.setText(dataKelurahanResponse.getPostalCode());
                                    relativeDialogClick();
                                });
                                rvChoose.setAdapter(kelurahanAdapter);
                                kelurahanAdapter.notifyDataSetChanged();
                            }else {
                                grosirMobilFunction.showMessage(getActivity(), "GET Kelurahan", response.body().getMessage());
                            }
                        }catch (Exception e){
                            GrosirMobilLog.printStackTrace(e);
                        }
                    }else {
                        try {
                            grosirMobilFunction.showMessage(getActivity(), getString(R.string.base_null_error_title), response.errorBody().string());
                        } catch (IOException e) {
                            GrosirMobilLog.printStackTrace(e);
                        }
                    }
                }

                @Override
                public void onFailure(Call<KelurahanResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    grosirMobilFunction.showMessage(getActivity(), "GET Kelurahan", getString(R.string.base_null_server));
                    GrosirMobilLog.printStackTrace(t);
                }
            });
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_back)
    void ivBackClick(){
        ((RegisterDataActivity)getActivity()).setFragment();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_next_data_address)
    void btnNextDataAddressClick(){
        ((RegisterDataActivity)getActivity()).replaceFragment(new DocumentFragment());
    }
}