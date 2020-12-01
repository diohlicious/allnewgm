package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderSelected;
import com.sip.grosirmobil.cloud.config.response.kecamatan.DataKecamatanResponse;

import java.util.List;

public class KecamatanAdapter extends RecyclerView.Adapter<ViewHolderSelected> {

    private final List<DataKecamatanResponse> dataKecamatanResponseList;

    public interface OnItemClickListener {
        void onItemClick(DataKecamatanResponse dataKecamatanResponse);
    }

    private final OnItemClickListener onItemClickListener;

    public KecamatanAdapter(List<DataKecamatanResponse> dataKecamatanResponses, OnItemClickListener onItemClickListener) {
        this.dataKecamatanResponseList = dataKecamatanResponses;
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
        DataKecamatanResponse dataKecamatanResponse = dataKecamatanResponseList.get(position);
        holder.bind(dataKecamatanResponse, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return dataKecamatanResponseList.size();
    }
}
