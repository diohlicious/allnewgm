package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderImageVehicleDetail;
import com.sip.grosirmobil.cloud.config.model.HardCodeDataModel;

import java.util.List;

public class ImageVehicleDetailAdapter extends RecyclerView.Adapter<ViewHolderImageVehicleDetail> {

    private List<HardCodeDataModel> hardCodeDataModelList;
    private Context contexts;


    public ImageVehicleDetailAdapter(Context context, List<HardCodeDataModel> hardCodeDataModels) {
        this.contexts = context;
        this.hardCodeDataModelList = hardCodeDataModels;
    }

    @NonNull
    @Override
    public ViewHolderImageVehicleDetail onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_image_vehicle_detail, viewGroup, false);
        return new ViewHolderImageVehicleDetail(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderImageVehicleDetail holder, int position) {
        HardCodeDataModel hardCodeDataBaruMasukModel = hardCodeDataModelList.get(position);

    }

    @Override
    public int getItemCount() {
        return hardCodeDataModelList.size();
    }

}
