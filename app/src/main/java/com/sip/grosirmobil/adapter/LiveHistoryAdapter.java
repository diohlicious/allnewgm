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
import com.sip.grosirmobil.activity.VehicleDetailActivity;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderItemVehicleHomeHistory;
import com.sip.grosirmobil.cloud.config.response.homehistory.DataHomeHistoryResponse;

import java.util.List;

import static com.sip.grosirmobil.base.contract.GrosirMobilContract.FROM_PAGE;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.ID_VEHICLE;
import static com.sip.grosirmobil.base.contract.GrosirMobilContract.KIK;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.convertDate;
import static com.sip.grosirmobil.base.function.GrosirMobilFunction.setCurrencyFormat;

public class LiveHistoryAdapter extends RecyclerView.Adapter<ViewHolderItemVehicleHomeHistory> {

    private final List<DataHomeHistoryResponse> dataHomeHistoryResponseList;
    private final Context contexts;


    public LiveHistoryAdapter(Context context, List<DataHomeHistoryResponse> dataHomeHistoryResponseList) {
        this.contexts = context;
        this.dataHomeHistoryResponseList = dataHomeHistoryResponseList;
    }

    @NonNull
    @Override
    public ViewHolderItemVehicleHomeHistory onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_vehicle_home_record, viewGroup, false);
        return new ViewHolderItemVehicleHomeHistory(itemView);
    }
    
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderItemVehicleHomeHistory holder, int position) {
        DataHomeHistoryResponse dataHomeHistoryResponse = dataHomeHistoryResponseList.get(position);
        holder.tvVehicleNameHistory.setText(dataHomeHistoryResponse.getVehicleName());
        holder.tvCityHistory.setText(dataHomeHistoryResponse.getWareHouse().replace("WAREHOUSE ",""));
        holder.tvOpenPriceHistory.setText("Rp "+setCurrencyFormat(dataHomeHistoryResponse.getHargaPembukaan()));
        holder.tvInitialNameHistory.setText(dataHomeHistoryResponse.getVehicleName().substring(0,1));
        
        holder.tvEventDate.setText("Event "+convertDate(dataHomeHistoryResponse.getEventDate(),"yyyy-MM-dd hh:mm:ss","dd MMM yyyy")+" - ");
        
        if(dataHomeHistoryResponse.getSoldPrice()==null){
            holder.tvSoldPriceHistory.setText("Rp 0");
        }else {
            holder.tvSoldPriceHistory.setText("Rp "+setCurrencyFormat(dataHomeHistoryResponse.getSoldPrice()));
        }
        holder.cardVehicleHistory.setOnClickListener(view -> {
            Intent intent = new Intent(contexts, VehicleDetailActivity.class);
            intent.putExtra(ID_VEHICLE, String.valueOf(dataHomeHistoryResponse.getOpenHouseId()));
            intent.putExtra(KIK, dataHomeHistoryResponse.getKik());
            intent.putExtra(FROM_PAGE, "HISTORY");
            contexts.startActivity(intent);
        });
    }
    @Override
    public int getItemCount() {
        return dataHomeHistoryResponseList.size();
    }
}
