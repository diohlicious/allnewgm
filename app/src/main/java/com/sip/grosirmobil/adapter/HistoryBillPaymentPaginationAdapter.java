package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderHistoryBillPayment;
import com.sip.grosirmobil.cloud.config.response.history.RowsResponse;

import java.util.ArrayList;
import java.util.List;

public class HistoryBillPaymentPaginationAdapter extends RecyclerView.Adapter<ViewHolderHistoryBillPayment> {

    private List<RowsResponse> historyBillPaymentResponseList;
    private Context contexts;


    public HistoryBillPaymentPaginationAdapter(Context context) {
        historyBillPaymentResponseList = new ArrayList<>();
        contexts = context;
    }

    @NonNull
    @Override
    public ViewHolderHistoryBillPayment onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_example, viewGroup, false);
        return new ViewHolderHistoryBillPayment(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderHistoryBillPayment holder, int position) {
        RowsResponse historyBillPaymentResponse = historyBillPaymentResponseList.get(position);


    }

    @Override
    public int getItemCount() {
        return historyBillPaymentResponseList.size();
    }

    public void addItems(List<RowsResponse> rowsResponseList){
        historyBillPaymentResponseList.addAll(rowsResponseList);
        notifyDataSetChanged();
    }
}
