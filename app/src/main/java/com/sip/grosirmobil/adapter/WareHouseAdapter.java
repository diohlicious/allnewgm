package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderSelected;
import com.sip.grosirmobil.cloud.config.response.warehouse.DataWareHouseResponse;

import java.util.List;

public class WareHouseAdapter extends RecyclerView.Adapter<ViewHolderSelected> {
    private final List<DataWareHouseResponse> dataWareHouseResponseList;

    public interface OnItemClickListener {
        void onItemClick(DataWareHouseResponse dataWareHouseResponse);
    }

    private final OnItemClickListener onItemClickListener;

    public WareHouseAdapter(List<DataWareHouseResponse> dataWareHouseResponses, OnItemClickListener onItemClickListener) {
        this.dataWareHouseResponseList = dataWareHouseResponses;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolderSelected onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_selected, viewGroup, false);
        return new ViewHolderSelected(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderSelected holder, int position) {
        DataWareHouseResponse dataWareHouseResponse = dataWareHouseResponseList.get(position);
        holder.bind(dataWareHouseResponse,onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return dataWareHouseResponseList.size();
    }
}
