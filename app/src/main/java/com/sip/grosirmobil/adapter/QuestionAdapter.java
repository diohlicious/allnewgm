package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderSelected;
import com.sip.grosirmobil.cloud.config.response.question.DataQuestionResponse;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<ViewHolderSelected> {

    private final List<DataQuestionResponse> dataQuestionResponseList;

    public interface OnItemClickListener {
        void onItemClick(DataQuestionResponse dataQuestionResponse);
    }

    private final OnItemClickListener onItemClickListener;

    public QuestionAdapter(List<DataQuestionResponse> dataQuestionResponses, OnItemClickListener onItemClickListener) {
        this.dataQuestionResponseList = dataQuestionResponses;
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
        DataQuestionResponse dataQuestionResponse = dataQuestionResponseList.get(position);
        holder.bind(dataQuestionResponse, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return dataQuestionResponseList.size();
    }
}
