package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderSelected;
import com.sip.grosirmobil.cloud.config.response.province.DataProvinceResponse;

import java.util.List;

public class ProvinceAdapter extends RecyclerView.Adapter<ViewHolderSelected> {

    private final List<DataProvinceResponse> dataProvinceResponseList;

    public interface OnItemClickListener {
        void onItemClick(DataProvinceResponse dataProvinceResponse);
    }

    private final OnItemClickListener onItemClickListener;

    public ProvinceAdapter(List<DataProvinceResponse> dataProvinceResponses,  OnItemClickListener onItemClickListener) {
        this.dataProvinceResponseList = dataProvinceResponses;
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
        DataProvinceResponse dataProvinceResponse = dataProvinceResponseList.get(position);
        holder.bind(dataProvinceResponse, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return dataProvinceResponseList.size();
    }
}
