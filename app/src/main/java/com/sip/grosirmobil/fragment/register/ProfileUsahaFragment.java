package com.sip.grosirmobil.fragment.register;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.shuhart.stepview.StepView;
import com.sip.grosirmobil.R;
import com.sip.grosirmobil.activity.RegisterDataActivity;
import com.sip.grosirmobil.adapter.TipeUsahaAdapter;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.cloud.config.response.tipeusaha.TipeUsahaResponse;

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
public class ProfileUsahaFragment extends Fragment {

    public static ProfileUsahaFragment newInstance(int page, String title) {
        ProfileUsahaFragment fragmentFirst = new ProfileUsahaFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.step_view) StepView stepView;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_type_usaha) TextInputEditText etTypeUsaha;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_dealer_name) TextInputEditText etDealerName;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_dealer_phone) TextInputEditText etDealerPhone;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.relative_dialog) RelativeLayout relativeDialog;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_choose) RecyclerView rvChoose;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.progress_circular) ProgressBar progressBar;

    private GrosirMobilPreference grosirMobilPreference;
    private GrosirMobilFunction grosirMobilFunction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_usaha, container, false);
        ButterKnife.bind(this, view);

        grosirMobilPreference = new GrosirMobilPreference(getActivity());
        grosirMobilFunction = new GrosirMobilFunction(getActivity());

        stepView.go(1, true);
        return view;
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_back)
    void ivBackClick(){
        ((RegisterDataActivity)getActivity()).setFragment();
    }

    private void showDialogChoose(){
        relativeDialog.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager layoutManagerChoose = new LinearLayoutManager(getActivity());
        rvChoose.setLayoutManager(layoutManagerChoose);
        rvChoose.setNestedScrollingEnabled(false);
        rvChoose.setAdapter(null);
    }

    private void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
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
    @OnClick(R.id.et_type_usaha)
    void etTypeUsahaClick(){
        if(grosirMobilPreference.getDataTypeUsahaList()==null||grosirMobilPreference.getDataTypeUsahaList().isEmpty()){
            showProgressBar();
            showDialogChoose();
            final Call<TipeUsahaResponse> tipeUsahaApi = getApiGrosirMobil().tipeUsahaApi();
            tipeUsahaApi.enqueue(new Callback<TipeUsahaResponse>() {
                @Override
                public void onResponse(Call<TipeUsahaResponse> call, Response<TipeUsahaResponse> response) {
                    hideProgressBar();
                    if (response.isSuccessful()) {
                        try {
                            if(response.body().getMessage().equals("success")){
                                if(!response.body().getData().isEmpty()){
                                    grosirMobilPreference.saveDataTypeUsahaList(response.body().getData());
                                }
                                TipeUsahaAdapter tipeUsahaAdapter = new TipeUsahaAdapter(response.body().getData(), dataTipeUsahaResponse -> {
                                    etTypeUsaha.setText(dataTipeUsahaResponse.getName());
                                    etTypeUsaha.setTag(dataTipeUsahaResponse.getCode());
                                    relativeDialogClick();
                                });
                                rvChoose.setAdapter(tipeUsahaAdapter);
                                tipeUsahaAdapter.notifyDataSetChanged();
                            }else {
                                grosirMobilFunction.showMessage(getActivity(), "GET Tipe Usaha", response.body().getMessage());
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
                public void onFailure(Call<TipeUsahaResponse> call, Throwable t) {
                    hideProgressBar();
                    grosirMobilFunction.showMessage(getActivity(), "GET Tipe Usaha", getString(R.string.base_null_server));
                    GrosirMobilLog.printStackTrace(t);
                }
            });
        }
        else {
            showDialogChoose();
            hideProgressBar();
            TipeUsahaAdapter tipeUsahaAdapter = new TipeUsahaAdapter(grosirMobilPreference.getDataTypeUsahaList(), dataTipeUsahaResponse -> {
                etTypeUsaha.setText(dataTipeUsahaResponse.getName());
                etTypeUsaha.setTag(dataTipeUsahaResponse.getCode());
                relativeDialogClick();
            });
            rvChoose.setAdapter(tipeUsahaAdapter);
            tipeUsahaAdapter.notifyDataSetChanged();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_next_profile_usaha)
    void btnNextProfileUsahaClick(){
        if(etTypeUsaha.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Mohon Pilih Tipe Usaha", Toast.LENGTH_SHORT).show();
        }else if(etDealerName.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Mohon Isi Nama Dealer", Toast.LENGTH_SHORT).show();
        }else if(etDealerPhone.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Mohon Isi No Telp Dealer", Toast.LENGTH_SHORT).show();
        } else {
            grosirMobilPreference.saveTypeUsaha(etTypeUsaha.getText().toString());
            grosirMobilPreference.saveDealerName(etDealerName.getText().toString());
            grosirMobilPreference.saveDealerPhoneNumber(etDealerPhone.getText().toString());
            ((RegisterDataActivity)getActivity()).replaceFragment(new DocumentFragment());
        }
    }
}