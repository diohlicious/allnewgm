package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderVehicleDescription;
import com.sip.grosirmobil.cloud.config.model.HardCodeDataModel;

import java.util.List;

public class VehicleDescriptionAdapter extends RecyclerView.Adapter<ViewHolderVehicleDescription> {

    private List<HardCodeDataModel> hardCodeDataModelList;
    private Context contexts;


    public VehicleDescriptionAdapter(Context context, List<HardCodeDataModel> hardCodeDataModels) {
        this.contexts = context;
        this.hardCodeDataModelList = hardCodeDataModels;
    }

    @NonNull
    @Override
    public ViewHolderVehicleDescription onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_vehicle_description, viewGroup, false);
        return new ViewHolderVehicleDescription(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderVehicleDescription holder, int position) {
        HardCodeDataModel hardCodeDataModel = hardCodeDataModelList.get(position);
        holder.tvDescription.setText(hardCodeDataModel.getDataHardCode());
    }

    @Override
    public int getItemCount() {
        return hardCodeDataModelList.size();
    }

}
