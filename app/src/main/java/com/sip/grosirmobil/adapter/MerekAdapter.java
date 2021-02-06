package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderSelected;
import com.sip.grosirmobil.cloud.config.response.filter.DataMerekResponse;

import java.util.List;

public class MerekAdapter extends RecyclerView.Adapter<ViewHolderSelected> {

    private final List<DataMerekResponse> dataMerekResponseList;

    public interface OnItemClickListener {
        void onItemClick(DataMerekResponse dataMerekResponse);
    }

    private final OnItemClickListener onItemClickListener;

    public MerekAdapter(List<DataMerekResponse> dataMerekResponses, OnItemClickListener onItemClickListener) {
        this.dataMerekResponseList = dataMerekResponses;
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
        DataMerekResponse dataMerekResponse = dataMerekResponseList.get(position);
        holder.bind(dataMerekResponse, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return dataMerekResponseList.size();
    }
}
