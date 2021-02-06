package com.sip.grosirmobil.fragment.navigationmenu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.activity.MainActivity;
import com.sip.grosirmobil.adapter.InfoMenuAdapter;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.base.util.GrosirMobilFragment;
import com.sip.grosirmobil.cloud.config.request.filter.MerekRequest;
import com.sip.grosirmobil.cloud.config.response.infomenu.InfoMenuResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sip.grosirmobil.base.GrosirMobilApp.getApiGrosirMobil;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.BEARER;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.REQUEST_MAIN;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setStatusBarFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends GrosirMobilFragment {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.swipe_refresh_notification) SwipeRefreshLayout swipeRefreshNotification;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_notification) RecyclerView rvNotification;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_notification_empty) LinearLayout linearNotificationEmpty;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.progress_bar) ProgressBar progressBar;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_date) TextView tvDate;

    private GrosirMobilPreference grosirMobilPreference;
    private GrosirMobilFunction grosirMobilFunction;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setStatusBarFragment(getActivity());
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        ButterKnife.bind(this, view);

        grosirMobilFunction = new GrosirMobilFunction(getActivity());
        grosirMobilPreference = new GrosirMobilPreference(getActivity());

        RecyclerView.LayoutManager layoutManagerLiveSoon = new LinearLayoutManager(getActivity());
        rvNotification.setLayoutManager(layoutManagerLiveSoon);
        rvNotification.setNestedScrollingEnabled(false);

        swipeRefreshNotification.setOnRefreshListener(() -> {
            swipeRefreshNotification.setRefreshing(false);
            getDataInfo();
        });

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
        Date date = new Date();
        System.out.println(formatter.format(date));

        tvDate.setText("Promo Today \n"+formatter.format(date));

        getDataInfo();
        return view;
    }

    private void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
    }



    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_view_all_unit)
    void btnViewAllUnitClick(){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra(REQUEST_MAIN, "");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        getActivity().finish();
    }

    private void getDataInfo(){
        showProgressBar();
        MerekRequest merekRequest = new MerekRequest();
        final Call<InfoMenuResponse> infoMenuApi = getApiGrosirMobil().infoMenuApi(BEARER+" "+grosirMobilPreference.getToken(), merekRequest);
        infoMenuApi.enqueue(new Callback<InfoMenuResponse>() {
            @Override
            public void onResponse(Call<InfoMenuResponse> call, Response<InfoMenuResponse> response) {
                hideProgressBar();
                if (response.isSuccessful()) {
                    try {
                        if(response.body().getMessage().equals("success")){
                            if(response.body().getDataInfoMenuResponseList().isEmpty()||response.body().getDataInfoMenuResponseList()==null){
                                rvNotification.setVisibility(View.GONE);
                                linearNotificationEmpty.setVisibility(View.VISIBLE);
                            }else {
                                rvNotification.setVisibility(View.VISIBLE);
                                linearNotificationEmpty.setVisibility(View.GONE);
                                InfoMenuAdapter infoMenuAdapter = new InfoMenuAdapter(response.body().getDataInfoMenuResponseList(), getActivity());
                                rvNotification.setAdapter(infoMenuAdapter);
                                infoMenuAdapter.notifyDataSetChanged();
                            }
                        }else {
                            grosirMobilFunction.showMessage(getActivity(), "GET Info Menu", response.body().getMessage());
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
            public void onFailure(Call<InfoMenuResponse> call, Throwable t) {
                hideProgressBar();
                grosirMobilFunction.showMessage(getActivity(), "GET Info Menu", getString(R.string.base_null_server));
                GrosirMobilLog.printStackTrace(t);
            }
        });
    }


}