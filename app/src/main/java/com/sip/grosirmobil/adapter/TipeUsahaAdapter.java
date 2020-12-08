package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderSelected;
import com.sip.grosirmobil.cloud.config.response.tipeusaha.DataTipeUsahaResponse;

import java.util.List;

public class TipeUsahaAdapter extends RecyclerView.Adapter<ViewHolderSelected> {

    private final List<DataTipeUsahaResponse> dataTipeUsahaResponseList;

    public interface OnItemClickListener {
        void onItemClick(DataTipeUsahaResponse dataTipeUsahaResponse);
    }

    private final OnItemClickListener onItemClickListener;

    public TipeUsahaAdapter(List<DataTipeUsahaResponse> dataTipeUsahaResponses, OnItemClickListener onItemClickListener) {
        this.dataTipeUsahaResponseList = dataTipeUsahaResponses;
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
        DataTipeUsahaResponse dataTipeUsahaResponse = dataTipeUsahaResponseList.get(position);
        holder.bind(dataTipeUsahaResponse, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return dataTipeUsahaResponseList.size();
    }
}
