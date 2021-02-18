package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderVehicleDetailData;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.cloud.config.response.vehicledetail.VehicleDetailDataResponse;

import java.util.List;

public class VehicleDetailDataAdapter extends RecyclerView.Adapter<ViewHolderVehicleDetailData> {

    private final List<VehicleDetailDataResponse> vehicleDetailDataResponseList;
    private final Context contexts;


    public VehicleDetailDataAdapter(Context context, List<VehicleDetailDataResponse> vehicleDetailDataResponses) {
        this.contexts = context;
        this.vehicleDetailDataResponseList = vehicleDetailDataResponses;
    }

    @NonNull
    @Override
    public ViewHolderVehicleDetailData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_vehicle_detail_data, viewGroup, false);
        return new ViewHolderVehicleDetailData(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderVehicleDetailData holder, int position) {
        try {
            VehicleDetailDataResponse vehicleDetailDataResponse = vehicleDetailDataResponseList.get(position);
            holder.tvName.setText(vehicleDetailDataResponse.getItem());
            holder.tvDescription.setText(vehicleDetailDataResponse.getDescription());
            if(vehicleDetailDataResponse.getStatus().equals("1")){
                holder.ivStatus.setImageResource(R.drawable.ic_check_list_true);
            }else {
                holder.ivStatus.setImageResource(R.drawable.ic_check_list_false);
            }
        }catch (Exception e){
            GrosirMobilLog.printStackTrace(e);
        }
    }

    @Override
    public int getItemCount() {
        return vehicleDetailDataResponseList.size();
    }

}
