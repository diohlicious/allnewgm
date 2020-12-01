package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderSelected;
import com.sip.grosirmobil.cloud.config.response.kabupaten.DataKabupatenResponse;

import java.util.List;

public class KabupatenAdapter extends RecyclerView.Adapter<ViewHolderSelected> {

    private final List<DataKabupatenResponse> dataKabupatenResponseList;

    public interface OnItemClickListener {
        void onItemClick(DataKabupatenResponse dataKabupatenResponse);
    }

    private final OnItemClickListener onItemClickListener;

    public KabupatenAdapter(List<DataKabupatenResponse> dataProvinceResponses, OnItemClickListener onItemClickListener) {
        this.dataKabupatenResponseList = dataProvinceResponses;
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
        DataKabupatenResponse dataKabupatenResponse = dataKabupatenResponseList.get(position);
        holder.bind(dataKabupatenResponse, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return dataKabupatenResponseList.size();
    }
}
