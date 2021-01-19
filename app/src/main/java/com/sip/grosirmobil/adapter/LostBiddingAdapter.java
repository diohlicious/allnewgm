package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.activity.PayDetailActivity;
import com.sip.grosirmobil.activity.VehicleDetailActivity;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderItemHistoryBidding;
import com.sip.grosirmobil.cloud.config.response.historytransaction.DataHistoryTransactionResponse;

import java.util.List;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.FROM_PAGE;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.ID_VEHICLE;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.KIK;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.REF_NUMBER;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.convertDate;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setCurrencyFormat;

public class LostBiddingAdapter  extends RecyclerView.Adapter<ViewHolderItemHistoryBidding> {

    private final List<DataHistoryTransactionResponse> dataHistoryTransactionResponseList;
    private final Context contexts;


    public LostBiddingAdapter(Context context, List<DataHistoryTransactionResponse> dataHistoryTransactionResponses) {
        this.contexts = context;
        this.dataHistoryTransactionResponseList = dataHistoryTransactionResponses;
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
        holder.tvStatus.setText(dataHistoryTransactionResponse.getStatus());
        if(dataHistoryTransactionResponse.getVaNumber()==null||dataHistoryTransactionResponse.getVaNumber().equals("")){
            holder.tvNoVa.setText("-");
        }else {
            holder.tvNoVa.setText(dataHistoryTransactionResponse.getVaNumber());
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

    @Override
    public int getItemCount() {
        return dataHistoryTransactionResponseList.size();
    }
}
