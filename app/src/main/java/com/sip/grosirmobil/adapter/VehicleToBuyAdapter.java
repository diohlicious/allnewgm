package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderVehicleToBuy;
import com.sip.grosirmobil.cloud.config.response.invoiceva.DetailResponse;

import java.util.List;

public class VehicleToBuyAdapter extends RecyclerView.Adapter<ViewHolderVehicleToBuy> {

    private List<DetailResponse> detailResponseList;


    public VehicleToBuyAdapter(List<DetailResponse> detailResponses) {
        this.detailResponseList = detailResponses;
    }

    @NonNull
    @Override
    public ViewHolderVehicleToBuy onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_vehicle_to_buy, viewGroup, false);
        return new ViewHolderVehicleToBuy(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderVehicleToBuy holder, int position) {
        DetailResponse detailResponse = detailResponseList.get(position);
        holder.tvVehicleToBuy.setText("- "+detailResponse.getAssetDescription());
    }

    @Override
    public int getItemCount() {
        return detailResponseList.size();
    }

}
