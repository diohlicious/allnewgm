package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.activity.PayDetailActivity;
import com.sip.grosirmobil.activity.VehicleDetailActivity;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderItemHistoryBidding;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.cloud.config.request.history.CheckStatusHistoryRequest;
import com.sip.grosirmobil.cloud.config.response.historytransaction.CheckStatusHistoryTransactionResponse;
import com.sip.grosirmobil.cloud.config.response.historytransaction.DataHistoryTransactionResponse;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sip.grosirmobil.base.GrosirMobilApp.getApiGrosirMobil;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.BEARER;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.FROM_PAGE;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.ID_VEHICLE;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.KIK;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.REF_NUMBER;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.convertDate;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setCurrencyFormat;

public class SuccessBiddingAdapter extends RecyclerView.Adapter<ViewHolderItemHistoryBidding> {

    private final List<DataHistoryTransactionResponse> dataHistoryTransactionResponseList;
    private final Context contexts;
    private final GrosirMobilFunction grosirMobilFunction;
    private final GrosirMobilPreference grosirMobilPreference;


    public SuccessBiddingAdapter(Context context, List<DataHistoryTransactionResponse> dataHistoryTransactionResponses) {
        this.contexts = context;
        this.dataHistoryTransactionResponseList = dataHistoryTransactionResponses;
        this.grosirMobilFunction = new GrosirMobilFunction(context);
        this.grosirMobilPreference = new GrosirMobilPreference(context);
    }

    @NonNull
    @Override
    public ViewHolderItemHistoryBidding onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_history_bidding, viewGroup, false);
        return new ViewHolderItemHistoryBidding(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderItemHistoryBidding holder, int position) {
        DataHistoryTransactionResponse dataHistoryTransactionResponse = dataHistoryTransactionResponseList.get(position);
        holder.tvVehicleName.setText(dataHistoryTransactionResponse.getVehicleName());
        String eventDate = convertDate(dataHistoryTransactionResponse.getEventDate(),"yyyy-MM-dd HH:mm:ss","dd-MM-yyyy HH:mm:ss");
        holder.tvEventDate.setText(eventDate+" - ");
        holder.tvCity.setText(dataHistoryTransactionResponse.getWarehouse());
        holder.tvPriceWin.setText("Rp "+setCurrencyFormat(dataHistoryTransactionResponse.getSoldPrice()));
        holder.tvHargaTertinggi.setText("Rp "+setCurrencyFormat(dataHistoryTransactionResponse.getUserPrice()));
//        holder.tvStatus.setText(dataHistoryTransactionResponse.getStatus());
        holder.tvNoVa.setText(dataHistoryTransactionResponse.getVaNumber());
        if(dataHistoryTransactionResponse.getVaNumber()==null||dataHistoryTransactionResponse.getVaNumber().equals("")){
            holder.tvStatus.setText(dataHistoryTransactionResponse.getStatus());
        }else {
            checkStatus(holder.progressBar, holder.tvStatus, dataHistoryTransactionResponse.getVaNumber());
        }
        if(dataHistoryTransactionResponse.getStatus().equals("Menunggu Pembayaran")){
            holder.cardVehicle.setOnClickListener(view -> {
                Intent intent = new Intent(contexts, PayDetailActivity.class);
                intent.putExtra(REF_NUMBER, dataHistoryTransactionResponse.getOrderNumber());
                contexts.startActivity(intent);
            });
        }else{
            holder.cardVehicle.setOnClickListener(view -> {
                Intent intent = new Intent(contexts, VehicleDetailActivity.class);
                intent.putExtra(ID_VEHICLE, String.valueOf(dataHistoryTransactionResponse.getOhId()));
                intent.putExtra(KIK, dataHistoryTransactionResponse.getKik());
                intent.putExtra(FROM_PAGE, "HISTORY");
                contexts.startActivity(intent);
            });
        }

    }

    private void checkStatus(ProgressBar progressBar, TextView tvStatus, String vaNumber){
        progressBar.setVisibility(View.VISIBLE);
        CheckStatusHistoryRequest checkStatus = new CheckStatusHistoryRequest(vaNumber);
        final Call<CheckStatusHistoryTransactionResponse> checkStatusHistoryTransactionApi = getApiGrosirMobil().checkStatusHistoryTransactionApi(BEARER+" "+grosirMobilPreference.getToken(), checkStatus);
        checkStatusHistoryTransactionApi.enqueue(new Callback<CheckStatusHistoryTransactionResponse>() {
            @Override
            public void onResponse(Call<CheckStatusHistoryTransactionResponse> call, Response<CheckStatusHistoryTransactionResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    try {
                        if(response.body().getMessage().equals("success")){
                            tvStatus.setVisibility(View.VISIBLE);
                            tvStatus.setText(response.body().getDataCheckStatusHistoryTransactionResponse().getStatus());
                        }else {
                            grosirMobilFunction.showMessage(contexts, "Check Status", response.body().getMessage());
                        }
                    }catch (Exception e){
                        GrosirMobilLog.printStackTrace(e);
                    }
                }
                else {
                    try {
                        grosirMobilFunction.showMessage(contexts, contexts.getString(R.string.base_null_error_title), response.errorBody().string());
                    } catch (IOException e) {
                        GrosirMobilLog.printStackTrace(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<CheckStatusHistoryTransactionResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                grosirMobilFunction.showMessage(contexts, "Check Status", contexts.getString(R.string.base_null_server));
                GrosirMobilLog.printStackTrace(t);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataHistoryTransactionResponseList.size();
    }
}
