package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderSelected;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.cloud.config.response.filter.DataGradeResponse;

import java.util.List;

public class GradeAdapter extends RecyclerView.Adapter<ViewHolderSelected> {

    private final List<DataGradeResponse> dataGradeResponseList;

    public interface OnItemClickListener {
        void onItemClick(DataGradeResponse dataGradeResponse);
    }

    private final OnItemClickListener onItemClickListener;

    public GradeAdapter(List<DataGradeResponse> dataGradeResponses, OnItemClickListener onItemClickListener) {
        this.dataGradeResponseList = dataGradeResponses;
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
        try {
            DataGradeResponse dataMerekResponse = dataGradeResponseList.get(position);
            holder.bind(dataMerekResponse, onItemClickListener);
        }catch (Exception e){
            GrosirMobilLog.printStackTrace(e);
        }
    }

    @Override
    public int getItemCount() {
        return dataGradeResponseList.size();
    }
}
