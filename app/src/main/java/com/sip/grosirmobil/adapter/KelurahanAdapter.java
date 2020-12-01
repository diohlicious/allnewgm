package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderSelected;
import com.sip.grosirmobil.cloud.config.response.kelurahan.DataKelurahanResponse;

import java.util.List;

public class KelurahanAdapter extends RecyclerView.Adapter<ViewHolderSelected> {

    private final List<DataKelurahanResponse> dataKelurahanResponseList;

    public interface OnItemClickListener {
        void onItemClick(DataKelurahanResponse dataKelurahanResponse);
    }

    private final OnItemClickListener onItemClickListener;

    public KelurahanAdapter(List<DataKelurahanResponse> dataKelurahanResponses, OnItemClickListener onItemClickListener) {
        this.dataKelurahanResponseList = dataKelurahanResponses;
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
        DataKelurahanResponse dataKelurahanResponse = dataKelurahanResponseList.get(position);
        holder.bind(dataKelurahanResponse, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return dataKelurahanResponseList.size();
    }
}
