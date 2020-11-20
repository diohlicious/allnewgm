package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderItemVehicleLostGarage;
import com.sip.grosirmobil.cloud.config.model.HardCodeDataBaruMasukModel;

import java.util.List;

public class LostGarageAdapter extends RecyclerView.Adapter<ViewHolderItemVehicleLostGarage> {

    private List<HardCodeDataBaruMasukModel> hardCodeDataBaruMasukModelList;
    private Context contexts;


    public LostGarageAdapter(Context context, List<HardCodeDataBaruMasukModel> hardCodeDataBaruMasukModels) {
        this.contexts = context;
        this.hardCodeDataBaruMasukModelList = hardCodeDataBaruMasukModels;
    }

    @NonNull
    @Override
    public ViewHolderItemVehicleLostGarage onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_vehicle_lost_garage, viewGroup, false);
        return new ViewHolderItemVehicleLostGarage(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderItemVehicleLostGarage holder, int position) {
        HardCodeDataBaruMasukModel hardCodeDataBaruMasukModel = hardCodeDataBaruMasukModelList.get(position);
        holder.tvVehicleName.setText(hardCodeDataBaruMasukModel.getVehicleName());
        holder.tvPlatNumber.setText(hardCodeDataBaruMasukModel.getPlatNumber()+" - ");
        holder.tvCity.setText(hardCodeDataBaruMasukModel.getCity());
        holder.tvPrice.setText(hardCodeDataBaruMasukModel.getPrice());
        holder.tvPriceSold.setText("Rp 120.000.000");
    }

    @Override
    public int getItemCount() {
        return hardCodeDataBaruMasukModelList.size();
    }

}
