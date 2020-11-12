package com.sip.grosirmobil.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sip.grosirmobil.R;
import com.sip.grosirmobil.adapter.viewholder.ViewHolderPaymentMethod;
import com.sip.grosirmobil.cloud.config.model.HardCodeDataModel;

import java.util.List;

public class PaymentMethodAdapter extends RecyclerView.Adapter<ViewHolderPaymentMethod> {

    private List<HardCodeDataModel> hardCodeDataModelList;
    private Context contexts;


    public PaymentMethodAdapter(Context context, List<HardCodeDataModel> hardCodeDataModels) {
        this.contexts = context;
        this.hardCodeDataModelList = hardCodeDataModels;
    }

    @NonNull
    @Override
    public ViewHolderPaymentMethod onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_payment_method, viewGroup, false);
        return new ViewHolderPaymentMethod(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderPaymentMethod holder, int position) {
        HardCodeDataModel hardCodeDataModel = hardCodeDataModelList.get(position);

    }

    @Override
    public int getItemCount() {
        return hardCodeDataModelList.size();
    }

}
