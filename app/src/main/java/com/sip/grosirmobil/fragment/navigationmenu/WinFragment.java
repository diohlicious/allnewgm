package com.sip.grosirmobil.fragment.navigationmenu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.activity.MainActivity;
import com.sip.grosirmobil.activity.PayPaymentActivity;
import com.sip.grosirmobil.adapter.ReadyTakeOutAdapter;
import com.sip.grosirmobil.adapter.WinAdapterAdapter;
import com.sip.grosirmobil.base.data.DataWinNotPaymentModel;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.base.util.GrosirMobilFragment;
import com.sip.grosirmobil.cloud.config.response.cart.CartResponse;
import com.sip.grosirmobil.cloud.config.response.cart.DataCartResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sip.grosirmobil.base.GrosirMobilApp.getApiGrosirMobil;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.BEARER;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.FROM_PAGE;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.REQUEST_MAIN;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.VEHICLE_SELECTED_TO_PAY;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setStatusBarFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class WinFragment extends GrosirMobilFragment {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.swipe_refresh_cart) SwipeRefreshLayout swipeRefreshCart;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_waiting_payment) LinearLayout linearWaitingPayment;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_waiting_payment) RecyclerView rvWaitingPayment;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_invoice_waiting_payment) RecyclerView rvInvoiceWaitingPayment;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_ready_take_vehicle) LinearLayout linearReadyTakeVehicle;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_ready_take_vehicle) RecyclerView rvReadyTakeVehicle;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_telusuri_kenderaan) Button btnFindVehicle;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_win_not_empty) LinearLayout linearWinNotEmpty;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.linear_win_empty) LinearLayout linearWinEmpty;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    private GrosirMobilFunction grosirMobilFunction;
    private GrosirMobilPreference grosirMobilPreference;

    private final List<DataCartResponse> dataCartWinNotYetPaymentList = new ArrayList<>();
    public final List<DataCartResponse> dataWaitingPaymentSelectedTempList = new ArrayList<>();
//    public final ArrayList<DataCartResponse> dataWaitingPaymentSelectedList = new ArrayList<>();
    private final List<DataCartResponse> dataCartWinFinishPaymentList = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setStatusBarFragment(getActivity());
        View view = inflater.inflate(R.layout.fragment_win, container, false);
        ButterKnife.bind(this, view);

        grosirMobilFunction = new GrosirMobilFunction(getActivity());
        grosirMobilPreference = new GrosirMobilPreference(getActivity());


        getDataWinApi();

        swipeRefreshCart.setOnRefreshListener(() -> {
            getDataWinApi();
            swipeRefreshCart.setRefreshing(false);
        });

        return view;
    }

    private void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
    }

    private void getDataWinApi(){
        showProgressBar();
        final Call<CartResponse> questionOneApi = getApiGrosirMobil().lisCartApi(BEARER+" "+grosirMobilPreference.getToken());
        questionOneApi.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                hideProgressBar();
                if (response.isSuccessful()) {
                    try {
                        if(response.body().getMessage().equals("success")){
                            if(response.body().getDataCartResponseList()==null||response.body().getDataCartResponseList().isEmpty()){
                                linearWinNotEmpty.setVisibility(View.GONE);
                                linearWinEmpty.setVisibility(View.VISIBLE);
                            }
                            else {
                                dataCartWinNotYetPaymentList.clear();
                                dataCartWinFinishPaymentList.clear();

                                linearWinNotEmpty.setVisibility(View.VISIBLE);
                                linearWinEmpty.setVisibility(View.GONE);
                                for(int i=0;i<response.body().getDataCartResponseList().size();i++){
                                    if(response.body().getDataCartResponseList().get(i).getIsWinner()==1 &&
                                       response.body().getDataCartResponseList().get(i).getUserWin()==1) {
                                        if(response.body().getDataCartResponseList().get(i).getCategoryName().equals("Menunggu Cetak Invoice")){
                                            dataCartWinNotYetPaymentList.add(response.body().getDataCartResponseList().get(i));
                                        }else {
                                            dataCartWinFinishPaymentList.add(response.body().getDataCartResponseList().get(i));
                                        }
                                    }
                                }
                                if(dataCartWinNotYetPaymentList.isEmpty()||dataCartWinNotYetPaymentList==null){
                                    linearWaitingPayment.setVisibility(View.GONE);
                                }else {
                                    dataWaitingPaymentSelectedTempList.clear();
                                    linearWaitingPayment.setVisibility(View.VISIBLE);
                                    LinearLayoutManager linearLayoutWinNotYetPayment = new LinearLayoutManager(getActivity());
                                    rvWaitingPayment.setLayoutManager(linearLayoutWinNotYetPayment);
                                    rvWaitingPayment.setNestedScrollingEnabled(false);
                                    WinAdapterAdapter winAdapterAdapter = new WinAdapterAdapter(getActivity(), dataWaitingPaymentSelectedTempList, dataCartWinNotYetPaymentList);
                                    rvWaitingPayment.setAdapter(winAdapterAdapter);
                                    winAdapterAdapter.notifyDataSetChanged();
                                }
                                if(dataCartWinFinishPaymentList.isEmpty()||dataCartWinFinishPaymentList==null){
                                    linearReadyTakeVehicle.setVisibility(View.GONE);
                                }else {
                                    linearReadyTakeVehicle.setVisibility(View.VISIBLE);
                                    LinearLayoutManager linearLayoutReadyTakeVehicle = new LinearLayoutManager(getActivity());
                                    rvReadyTakeVehicle.setLayoutManager(linearLayoutReadyTakeVehicle);
                                    rvReadyTakeVehicle.setNestedScrollingEnabled(false);
                                    ReadyTakeOutAdapter readyTakeOutAdapter = new ReadyTakeOutAdapter(getActivity(), dataCartWinFinishPaymentList);
                                    rvReadyTakeVehicle.setAdapter(readyTakeOutAdapter);
                                    readyTakeOutAdapter.notifyDataSetChanged();
                                }

                                if(dataCartWinNotYetPaymentList.isEmpty()||dataCartWinNotYetPaymentList==null &&
                                   dataCartWinFinishPaymentList.isEmpty()||dataCartWinFinishPaymentList==null){
                                    linearWinNotEmpty.setVisibility(View.GONE);
                                    linearWinEmpty.setVisibility(View.VISIBLE);
                                }else {
                                    linearWinNotEmpty.setVisibility(View.VISIBLE);
                                    linearWinEmpty.setVisibility(View.GONE);
                                }
                            }
                        }else {
                            grosirMobilFunction.showMessage(getActivity(), "GET Cart Data", response.body().getMessage());
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
            public void onFailure(Call<CartResponse> call, Throwable t) {
                hideProgressBar();
                grosirMobilFunction.showMessage(getActivity(), "GET Cart Data", getString(R.string.base_null_server));
                GrosirMobilLog.printStackTrace(t);
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_pay)
    void btnPayClick(){
        if(dataWaitingPaymentSelectedTempList.isEmpty()){
            Toast.makeText(getActivity(), "Mohon Pilih Kendaraan yang mau dibayar", Toast.LENGTH_SHORT).show();
        }else {
            ArrayList<DataWinNotPaymentModel> winNotPaymentModelArrayList = new ArrayList<DataWinNotPaymentModel>();
            for(int i=0;i<dataWaitingPaymentSelectedTempList.size();i++){
                DataWinNotPaymentModel dataWinNotPaymentModel = new DataWinNotPaymentModel(
                        dataWaitingPaymentSelectedTempList.get(i).getUserIdGrosir(),
                        dataWaitingPaymentSelectedTempList.get(i).getUserIdWin(),
                        dataWaitingPaymentSelectedTempList.get(i).getOhid(),
                        dataWaitingPaymentSelectedTempList.get(i).getAgreementNo(),
                        dataWaitingPaymentSelectedTempList.get(i).getStart_Date(),
                        dataWaitingPaymentSelectedTempList.get(i).getEndDate(),
                        dataWaitingPaymentSelectedTempList.get(i).getKik(),
                        dataWaitingPaymentSelectedTempList.get(i).getVehicleName(),
                        dataWaitingPaymentSelectedTempList.get(i).getTertinggi(),
                        dataWaitingPaymentSelectedTempList.get(i).getUserTertinggi(),
                        dataWaitingPaymentSelectedTempList.get(i).getIsKeranjang(),
                        dataWaitingPaymentSelectedTempList.get(i).getIsWinner(),
                        dataWaitingPaymentSelectedTempList.get(i).getUserWin(),
                        dataWaitingPaymentSelectedTempList.get(i).getAdminfee(),
                        dataWaitingPaymentSelectedTempList.get(i).getBottomPrice(),
                        dataWaitingPaymentSelectedTempList.get(i).getOpenPrice(),
                        dataWaitingPaymentSelectedTempList.get(i).getGrade(),
                        dataWaitingPaymentSelectedTempList.get(i).getIsLive(),
                        dataWaitingPaymentSelectedTempList.get(i).getCategoryName(),
                        dataWaitingPaymentSelectedTempList.get(i).getIsBlock(),
                        dataWaitingPaymentSelectedTempList.get(i).getFoto(),
                        dataWaitingPaymentSelectedTempList.get(i).getStatus());
                winNotPaymentModelArrayList.add(dataWinNotPaymentModel);
            }

            Intent intent = new Intent(getActivity(), PayPaymentActivity.class);
            intent.putExtra(FROM_PAGE, "Win");
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(VEHICLE_SELECTED_TO_PAY, winNotPaymentModelArrayList);
            intent.putExtras(bundle);
            startActivity(intent);

        }
    }

//    private Intent newInstance(List<DataCartResponse> dataCartResponses) {
//        Intent intent = new Intent(getActivity(), PayPaymentActivity.class);
//        intent.putExtra(FROM_PAGE, "Win");
//        intent.putParcelableArrayListExtra(VEHICLE_SELECTED_TO_PAY, dataCartResponses);
//        startActivity(intent);
//        return intent;
//    }

    public void sendIntent(String key, ArrayList<? extends Parcelable> value) {
        Intent intent = new Intent(getActivity(), PayPaymentActivity.class);
        intent.putExtra(FROM_PAGE, "Win");
        intent.putParcelableArrayListExtra(key, value);
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_telusuri_kenderaan)
    void btnFindVehicleClick(){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra(REQUEST_MAIN, "");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        getActivity().finish();
    }
}