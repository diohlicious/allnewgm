package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderBrokenImage;
import com.sip.grosirmobil.cloud.config.model.HardCodeDataModel;

import java.util.List;

public class BrokenImageAdapter extends RecyclerView.Adapter<ViewHolderBrokenImage> {

    private List<HardCodeDataModel> hardCodeDataModelList;
    private Context contexts;


    public BrokenImageAdapter(Context context, List<HardCodeDataModel> hardCodeDataModels) {
        this.contexts = context;
        this.hardCodeDataModelList = hardCodeDataModels;
    }

    @NonNull
    @Override
    public ViewHolderBrokenImage onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_broken_image, viewGroup, false);
        return new ViewHolderBrokenImage(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderBrokenImage holder, int position) {
        HardCodeDataModel hardCodeDataModel = hardCodeDataModelList.get(position);
        holder.tvDescription.setText(hardCodeDataModel.getDescription());
        holder.tvImageNumber.setText(hardCodeDataModel.getImageNumber());
    }

    @Override
    public int getItemCount() {
        return hardCodeDataModelList.size();
    }

}
